# Tài liệu chi tiết: Đọc CSV, đọc Config Properties, Flow lưu Screenshot/Video

Tài liệu này mô tả 3 cơ chế đang được sử dụng trong project `testing16-selenium-pom`:

1. Đọc dữ liệu test từ file CSV (data-driven testing).
2. Đọc cấu hình từ file `config.properties`.
3. Flow chụp screenshot và quay video làm bằng chứng (evidence) khi chạy test.

---

## 0. Flow tổng quát (Big Picture)

Trước khi đi vào chi tiết từng phần, đây là bức tranh tổng thể về cách mọi thứ phối hợp với nhau khi một test case chạy:

```
┌─────────────────────────────────────────────────────────────────────┐
│                        TestNG chạy 1 Test Method                     │
└─────────────────────────────────────────────────────────────────────┘
                                   │
                    ① @BeforeMethod (BaseTest.setUp)
                                   │
        ┌──────────────────────────┼──────────────────────────┐
        ▼                          ▼                          ▼
  Tạo folder evidence      Đọc config.properties        Khởi tạo WebDriver
  target/test-output/      (ConfigReader) để lấy         (DriverFactory) theo
  <Class>_<method>_        browser/headless/url/...      browser + headless
  <timestamp>_<browser>_
  <device>
        │                                                       │
        ▼                                                       ▼
  ScreenshotUtil.setTestFolder()                     VideoRecorderUtil.start()
  (gán folder cho ThreadLocal,                        (bắt đầu quay video vào
   reset step count = 0)                               folder .../video, bỏ qua
                                                        nếu môi trường headless)
                                   │
                                   ▼
                    ② Test method thực thi (LoginTest...)
        - Nếu test dùng @DataProvider: đọc dữ liệu từ CSV (CsvReader)
          để chạy nhiều bộ dữ liệu (nhiều "lần lặp" của cùng 1 test)
        - Trong lúc chạy, các Page Object (VD: LoginPage) gọi
          ScreenshotUtil.takeScreenshot() sau mỗi action quan trọng
          → sinh ra step_01_xxx.png, step_02_xxx.png...
                                   │
                    ③ @AfterMethod (BaseTest.tearDown)
                                   │
        ┌──────────────────────────┼──────────────────────────┐
        ▼                          ▼                          ▼
  Nếu test FAIL:              driver.quit()             VideoRecorderUtil.stop()
  chụp thêm 1 screenshot                                (dừng và lưu file video)
  "FAILED_<tên test>.png"
                                   │
                                   ▼
                    ScreenshotUtil.clear() (dọn ThreadLocal)
```

**Ý tưởng cốt lõi:**
- **CSV** dùng để cấp *dữ liệu đầu vào* cho test (data-driven), tách biệt dữ liệu ra khỏi code.
- **config.properties** dùng để cấp *cấu hình môi trường* (URL, tài khoản, timeout, headless...), tách biệt cấu hình ra khỏi code.
- **Screenshot/Video** dùng để *ghi lại bằng chứng* quá trình chạy test, phục vụ debug khi test fail.
- Cả 3 cơ chế đều nằm trong package `utils` (`src/main/java/utils/`), được các test class trong `src/test/java/tests/` gọi tới.

---

## 1. Đọc file CSV (Data-Driven Testing)

### 1.1. File liên quan

| Thành phần | Đường dẫn |
|---|---|
| Class đọc CSV | [src/main/java/utils/CsvReader.java](../src/main/java/utils/CsvReader.java) |
| File dữ liệu mẫu | [src/test/resources/loginData.csv](../src/test/resources/loginData.csv) |
| Nơi sử dụng | [src/test/java/tests/LoginTest.java](../src/test/java/tests/LoginTest.java) |

### 1.2. Nội dung file CSV

```csv
username,password,expectedResult
Admin,admin123,success
Admin,admin,fail
Admin123,admin123,fail
Admin123,admin,fail
```

Dòng đầu tiên là **header** (tên cột) — không phải dữ liệu test, sẽ bị bỏ qua khi đọc. Mỗi dòng còn lại tương ứng với **1 lần chạy test** với 3 tham số: `username`, `password`, `expectedResult`.

### 1.3. Cơ chế đọc (CsvReader.java)

Class `CsvReader` có 2 method static:

**a) `readCsv(String filePath)`** — đọc file CSV thành `List<String[]>`:

```java
public static List<String[]> readCsv(String filePath) throws IOException {
    List<String[]> data = new ArrayList<>();

    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    try (reader) {
        String line;
        boolean isHeader = true;

        while ((line = reader.readLine()) != null) {
            // bỏ qua dòng header
            if (isHeader) {
                isHeader = false;
                continue;
            }
            // bỏ qua dòng trống
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] values = line.split(",");
            data.add(values);
        }
    }

    return data;
}
```

Cách hoạt động:
- Dùng `BufferedReader` để đọc file theo từng dòng (hiệu quả hơn đọc từng ký tự).
- Dòng đầu tiên (`isHeader = true`) luôn bị bỏ qua vì đó là tên cột, không phải dữ liệu.
- Các dòng trống bị bỏ qua để tránh lỗi khi parse.
- Mỗi dòng còn lại được `split(",")` thành mảng `String[]` (VD: `["Admin", "admin123", "success"]`) rồi thêm vào danh sách kết quả.

**b) `toDataProviderArray(List<String[]> rows)`** — chuyển đổi sang định dạng mà TestNG `@DataProvider` yêu cầu (`Object[][]`):

```java
public static Object[][] toDataProviderArray(List<String[]> rows) {
    Object[][] data = new Object[rows.size()][];
    for (int i = 0; i < rows.size(); i++) {
        data[i] = rows.get(i);
    }
    return data;
}
```

- TestNG yêu cầu `@DataProvider` trả về `Object[][]`, trong đó **mỗi hàng** = 1 lần chạy test, **mỗi cột** = 1 tham số truyền vào method test.
- Method này chỉ đơn giản "bọc" `List<String[]>` thành `Object[][]` để tương thích với TestNG.

### 1.4. Nơi sử dụng (LoginTest.java)

```java
@DataProvider(name = "loginData")
public Object[][] loginDataProvider() throws IOException {
    String filePath = "src/test/resources/loginData.csv";
    List<String[]> data = CsvReader.readCsv(filePath);
    return CsvReader.toDataProviderArray(data);
}

@Test(description = "Test login data provider", dataProvider = "loginData")
public void testDataLogin(String username, String password, String expectedResult) throws InterruptedException {
    LoginPage loginPage = new LoginPage(getDriver(), getWait());
    loginPage.login(username, password);

    if (expectedResult.equalsIgnoreCase("success")) {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("dashboard"));
    } else {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("auth/login"));
    }
}
```

Với file CSV mẫu ở trên, TestNG sẽ tự động chạy `testDataLogin()` **4 lần**, mỗi lần với 1 bộ `(username, password, expectedResult)` tương ứng với 4 dòng dữ liệu.

> **Lưu ý:** Hiện project chưa có class DataProvider dùng chung — mỗi test method cần dữ liệu CSV sẽ tự khai báo `@DataProvider` riêng và gọi `CsvReader.readCsv(...)`. Nếu cần thêm test dùng CSV khác, chỉ cần lặp lại pattern này với đường dẫn file mới.

---

## 2. Đọc file Config Properties

### 2.1. File liên quan

| Thành phần | Đường dẫn |
|---|---|
| Class đọc config | [src/main/java/utils/ConfigReader.java](../src/main/java/utils/ConfigReader.java) |
| File cấu hình | [src/test/resources/config.properties](../src/test/resources/config.properties) |

### 2.2. Nội dung config.properties

```properties
login.base.url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login

admin.username=Admin
admin.password=admin1234

explicit.wait.seconds=30

headless=false
```

| Key | Ý nghĩa | Nơi sử dụng |
|---|---|---|
| `login.base.url` | URL trang login | Khai báo sẵn, hiện chưa thấy nơi nào gọi trực tiếp trong code (đáng để kiểm tra lại `LoginPage`/`BasePage` xem có hard-code URL không) |
| `admin.username` / `admin.password` | Tài khoản đăng nhập test | `LoginTest.testLoginSuccess()` |
| `explicit.wait.seconds` | Thời gian chờ mặc định | Khai báo sẵn nhưng `BaseTest` đang hard-code `Duration.ofSeconds(30)` thay vì đọc từ key này |
| `headless` | Bật/tắt chế độ headless cho Chrome | `DriverFactory.createDriver()` |

### 2.3. Cơ chế đọc (ConfigReader.java)

```java
public class ConfigReader {

    private static final Properties PROPERTIES = loadProperties();

    public static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream =
                ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("File not found");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error reading properties file", e);
        }

        return properties;
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key not found: " + key);
        }
        return value.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
```

Cách hoạt động:
- `PROPERTIES` là field `static final`, được khởi tạo **1 lần duy nhất** khi class `ConfigReader` được load lần đầu (do gọi `loadProperties()` ngay tại khai báo field). Toàn bộ ứng dụng dùng chung 1 instance `Properties` này (singleton kiểu "eager loading").
- `loadProperties()` đọc file `config.properties` **từ classpath** (không phải đường dẫn hệ thống file) thông qua `getClassLoader().getResourceAsStream(...)`. Vì Maven build sẽ copy `src/test/resources/` vào `target/test-classes/`, nên `config.properties` luôn nằm sẵn trên classpath khi chạy test.
- Nếu không tìm thấy file → ném `RuntimeException("File not found")` ngay lập tức (fail-fast).
- `get(key)`: lấy giá trị string, `trim()` để loại khoảng trắng thừa; nếu key không tồn tại → ném lỗi ngay thay vì trả về `null` (giúp phát hiện lỗi cấu hình sớm).
- `getInt(key)` / `getBoolean(key)`: parse giá trị string thành `int`/`boolean` tương ứng.

### 2.4. Ví dụ sử dụng thực tế

**a) Lấy tài khoản đăng nhập** (`LoginTest.java`):
```java
String username = ConfigReader.get("admin.username");
String password = ConfigReader.get("admin.password");
```

**b) Bật/tắt headless khi tạo driver** (`DriverFactory.java`):
```java
boolean isHeadless = ConfigReader.getBoolean("headless");
...
if (isHeadless) {
    chromeOptions.addArguments(
            "--headless=new",
            "--disable-gpu",
            "--no-sandbox",
            "--window-size=1920,1080",
            "--disable-dev-shm-usage"
    );
}
```

→ Muốn chạy Chrome ở chế độ headless (thường dùng khi chạy trên CI/CD), chỉ cần đổi `headless=false` thành `headless=true` trong `config.properties`, không cần sửa code.

---

## 3. Flow lưu Screenshot và Video (Evidence khi chạy Test)

### 3.1. File liên quan

| Thành phần | Đường dẫn |
|---|---|
| Class chụp screenshot | [src/main/java/utils/ScreenshotUtil.java](../src/main/java/utils/ScreenshotUtil.java) |
| Class quay video | [src/main/java/utils/VideoRecorderUtil.java](../src/main/java/utils/VideoRecorderUtil.java) |
| Nơi wiring (hook TestNG) | [src/test/java/tests/BaseTest.java](../src/test/java/tests/BaseTest.java) |
| Thư viện quay video | `com.github.stephenc.monte:monte-screen-recorder` (khai báo trong `pom.xml`) |

Project **không** dùng một class `ITestListener` riêng — mà tận dụng thẳng 2 annotation có sẵn của TestNG là `@BeforeMethod` và `@AfterMethod` trong `BaseTest` để làm nơi "hook" (điểm móc nối) cho việc tạo folder, bắt đầu/dừng quay video, và chụp ảnh khi fail.

### 3.2. Bước 1 — Chuẩn bị trước khi test chạy (`@BeforeMethod` trong BaseTest)

```java
@Parameters({"browser", "device"})
@BeforeMethod(alwaysRun = true)
public void setUp(@Optional("chrome") String browser, @Optional("") String device, Method method) throws IOException {
    String className = method.getDeclaringClass().getSimpleName();
    String methodName = method.getName();
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

    String folderName = String.format("%s_%s_%s_%s_%s", className, methodName, timestamp, browser, device);
    String testFolderPath = "target/test-output/" + folderName;
    Files.createDirectories(Paths.get(testFolderPath));

    ScreenshotUtil.setTestFolder(testFolderPath);

    WebDriver driver = DriverFactory.createDriver(browser, device);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    driverThreadLocal.set(driver);
    waitThreadLocal.set(wait);

    VideoRecorderUtil.start(testFolderPath);
}
```

Trình tự thực thi:
1. Lấy tên class + tên method của test hiện tại (thông qua reflection `Method method`).
2. Ghép thành tên folder theo format:
   ```
   <ClassName>_<methodName>_<yyyyMMdd_HHmmss>_<browser>_<device>
   ```
   Ví dụ: `LoginTest_testLoginSuccess_20260721_143012_chrome_`
3. Tạo folder tại `target/test-output/<folderName>`.
4. Gọi `ScreenshotUtil.setTestFolder(testFolderPath)` để gán folder này cho luồng (thread) hiện tại đang chạy test, đồng thời reset bộ đếm step về 0.
5. Khởi tạo `WebDriver` thông qua `DriverFactory` (có áp dụng `headless` nếu config bật).
6. Gọi `VideoRecorderUtil.start(testFolderPath)` để **bắt đầu quay video** ngay sau khi driver được tạo.

> **Vì sao dùng `ThreadLocal`?** Khi chạy test song song (parallel = "methods" hoặc "classes" trong TestNG), mỗi thread cần folder/step-count/driver riêng để tránh việc thread này ghi đè dữ liệu của thread khác.

### 3.3. Bước 2 — Chụp screenshot trong lúc test chạy (`ScreenshotUtil`)

```java
public class ScreenshotUtil {
    private static final ThreadLocal<String> testFolder = new ThreadLocal<>();
    private static final ThreadLocal<Integer> stepCount = new ThreadLocal<>();

    public static void setTestFolder(String folderPath) {
        testFolder.set(folderPath);
        stepCount.set(0);
    }

    public static void clear() {
        testFolder.remove();
        stepCount.remove();
    }

    public static void takeScreenshot(WebDriver driver, String stepName) {
        try {
            String testFolderName = testFolder.get();
            String screenshotFolderName = testFolderName + File.separator + "screenshots";
            Files.createDirectories(Paths.get(screenshotFolderName));

            Integer stepNumber = stepCount.get();
            stepNumber = (stepNumber == null) ? 1 : stepNumber + 1;
            stepCount.set(stepNumber);

            String fileName = String.format("step_%02d_%s.png", stepNumber, stepName);

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotFolderName, fileName);
            Files.copy(srcFile.toPath(), destFile.toPath());
        } catch (IOException e) {
            System.out.println("Error taking screenshot: " + e.getMessage());
        }
    }
}
```

Cách hoạt động:
- Folder ảnh nằm trong: `<testFolderPath>/screenshots/`
- Mỗi lần gọi `takeScreenshot(driver, stepName)`, số thứ tự step tự động **tăng dần** (`stepNumber + 1`) và được lưu lại trong `ThreadLocal`.
- Tên file theo định dạng: `step_<số thứ tự 2 chữ số>_<tên bước>.png`, ví dụ: `step_01_enterUsername.png`, `step_02_enterPassword.png`.
- `driver.getScreenshotAs(OutputType.FILE)`: Selenium chụp màn hình hiện tại, lưu tạm ra 1 file tạm (RAM/temp), sau đó `Files.copy(...)` copy file tạm đó vào đúng folder `screenshots/` với tên đã đặt.

**Các nơi đang gọi `takeScreenshot()` trong code:**
- `LoginPage` (Page Object): chụp ảnh sau mỗi hành động UI quan trọng — `login_page`, `enter_username`, `enter_password`, `click_login_btn`.
- `LoginTest.testLoginSuccess()`: chụp thêm ảnh `login_success` sau khi assert thành công.
- `BaseTest.tearDown()`: chụp ảnh `FAILED_<tên test>` **chỉ khi test fail** (xem mục 3.5).

→ Kết quả: mỗi test case sẽ có 1 chuỗi ảnh step-by-step ghi lại toàn bộ hành trình thao tác, giúp debug trực quan khi xem lại log test.

### 3.4. Bước 3 — Quay video song song (`VideoRecorderUtil`)

Sử dụng thư viện **Monte Screen Recorder** (`com.github.stephenc.monte:monte-screen-recorder`).

```java
public class VideoRecorderUtil {
    private static ScreenRecorder recorder;

    public static void start(String testFolderPath) {
        if (GraphicsEnvironment.isHeadless()) {
            return; // bỏ qua quay video nếu JVM đang chạy ở chế độ headless (VD: trên CI)
        }

        try {
            File videoFolder = new File(testFolderPath, "video");
            Files.createDirectories(Paths.get(videoFolder.getPath()));

            GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();

            Format fileFormat = new Format(
                    MediaTypeKey, FormatKeys.MediaType.FILE,
                    MimeTypeKey, "video/avi"
            );

            Format screenFormat = new Format(
                    MediaTypeKey, FormatKeys.MediaType.VIDEO,
                    EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                    CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                    DepthKey, 24,
                    FrameRateKey, Rational.valueOf(15),
                    QualityKey, 1.0f,
                    KeyFrameIntervalKey, 15 * 60
            );

            recorder = new ScreenRecorder(gc, gc.getBounds(), fileFormat, screenFormat, null, null, videoFolder);
            recorder.start();
        } catch (IOException | AWTException e) {
            System.out.println("Error starting video recording: " + e.getMessage());
        }
    }

    public static void stop(boolean testFailed) {
        if (recorder == null) return;
        try {
            recorder.stop();
        } catch (IOException e) {
            System.out.println("Error stopping video recording: " + e.getMessage());
        } finally {
            recorder = null;
        }
    }
}
```

Cách hoạt động:
- **Điều kiện tiên quyết:** nếu JVM chạy ở chế độ headless (`GraphicsEnvironment.isHeadless() == true`, thường xảy ra trên CI/server không có màn hình) → `start()` sẽ `return` ngay, **không quay video** (vì thư viện quay màn hình cần có màn hình thật để chụp).
- Nếu không headless: tạo folder `<testFolderPath>/video/`, cấu hình định dạng video là **AVI** với codec **TechSmith Screen Capture**, 15 khung hình/giây (fps), chất lượng tối đa (`1.0f`), quay toàn bộ vùng màn hình hiện tại (`gc.getBounds()`).
- `recorder.start()` bắt đầu quay ngay khi được gọi (trong `BaseTest.setUp()`, ngay sau khi driver được khởi tạo).
- `stop(boolean testFailed)`: dừng recorder và lưu file video vào folder đã cấu hình. **Lưu ý:** tham số `testFailed` hiện **không được dùng bên trong method** — video luôn được dừng/lưu như nhau bất kể test pass hay fail (khác với cơ chế screenshot chỉ chụp thêm khi fail).

> **Lưu ý quan trọng — 2 khái niệm "headless" khác nhau:**
> - `headless` trong `config.properties` → điều khiển việc thêm flag `--headless=new` cho **Chrome** (`DriverFactory`).
> - `GraphicsEnvironment.isHeadless()` → kiểm tra chính **JVM** (Java) có đang chạy ở môi trường không có màn hình vật lý hay không (thường set qua `-Djava.awt.headless=true`, hoặc tự động true khi chạy trên server/CI không có GUI).
>
> Hai cái này **độc lập với nhau**: chạy Chrome headless không tự động khiến `GraphicsEnvironment.isHeadless()` trả về `true`. Nghĩa là nếu chạy trên máy local có màn hình nhưng bật `headless=true` cho Chrome, video **vẫn** được quay bình thường (vì JVM không headless) — chỉ là video sẽ không thấy cửa sổ Chrome (do Chrome chạy ẩn). Ngược lại, khi chạy thật trên CI (server không màn hình), `GraphicsEnvironment.isHeadless()` mới là `true` và video sẽ bị bỏ qua.

### 3.5. Bước 4 — Dọn dẹp sau khi test chạy xong (`@AfterMethod` trong BaseTest)

```java
@AfterMethod(alwaysRun = true)
public void tearDown(ITestResult result) {
    WebDriver driver = getDriver();

    if (driver != null && result.getStatus() == ITestResult.FAILURE) {
        ScreenshotUtil.takeScreenshot(driver, "FAILED_" + result.getName());
    }

    if (driver != null) {
        driver.quit();
    }
    VideoRecorderUtil.stop(true);

    ScreenshotUtil.clear();
    driverThreadLocal.remove();
    waitThreadLocal.remove();
}
```

Trình tự thực thi (chạy sau **mỗi** test method, kể cả pass/fail nhờ `alwaysRun = true`):
1. Kiểm tra `result.getStatus() == ITestResult.FAILURE` — **chỉ khi test fail** mới chụp thêm 1 ảnh cuối cùng đặt tên `FAILED_<tên test>.png` để ghi lại trạng thái màn hình lúc lỗi xảy ra.
2. Đóng trình duyệt (`driver.quit()`).
3. Dừng quay video (`VideoRecorderUtil.stop(true)`) — file video được lưu lại vào folder `video/` bất kể test pass hay fail.
4. Xoá dữ liệu `ThreadLocal` (`ScreenshotUtil.clear()`, `driverThreadLocal.remove()`, `waitThreadLocal.remove()`) để tránh rò rỉ bộ nhớ (memory leak) hoặc dữ liệu tồn dư ảnh hưởng đến lần chạy test tiếp theo trên cùng 1 thread.

### 3.6. Cấu trúc thư mục kết quả sau khi chạy test

```
target/test-output/
└── LoginTest_testLoginSuccess_20260721_143012_chrome_/
    ├── screenshots/
    │   ├── step_01_login_page.png
    │   ├── step_02_enter_username.png
    │   ├── step_03_enter_password.png
    │   ├── step_04_click_login_btn.png
    │   └── step_05_login_success.png
    └── video/
        └── <video được Monte Screen Recorder đặt tên tự động>.avi
```

Nếu test fail, sẽ có thêm 1 ảnh: `step_06_FAILED_testLoginSuccess.png` (số thứ tự tiếp theo trong chuỗi step).

### 3.7. Tổng kết bảng điều khiển (Config liên quan)

| Hành vi | Được điều khiển bởi | Có thể tắt qua config không? |
|---|---|---|
| Chụp screenshot theo từng step | Gọi thủ công `ScreenshotUtil.takeScreenshot()` trong Page Object/Test | Không — hiện code luôn chụp, không có flag `screenshot.enabled` trong `config.properties` |
| Chụp screenshot khi fail | `BaseTest.tearDown()` kiểm tra `ITestResult.FAILURE` | Không — logic cố định trong code |
| Quay video | `VideoRecorderUtil.start()` | Gián tiếp qua `GraphicsEnvironment.isHeadless()` của JVM (không phải property `headless` trong file config) — muốn tắt video trên CI thì set `-Djava.awt.headless=true` khi chạy JVM |
| Chrome headless | `headless` trong `config.properties` | Có — đổi `true`/`false` |

---

## Phụ lục — Tóm tắt vị trí các file chính

```
src/
├── main/java/
│   ├── utils/
│   │   ├── CsvReader.java          # đọc CSV
│   │   ├── ConfigReader.java       # đọc config.properties
│   │   ├── ScreenshotUtil.java     # chụp screenshot
│   │   ├── VideoRecorderUtil.java  # quay video
│   │   └── DriverFactory.java      # tạo WebDriver (dùng ConfigReader để lấy headless)
│   └── pages/
│       └── LoginPage.java          # Page Object, gọi ScreenshotUtil sau mỗi action
└── test/
    ├── java/tests/
    │   ├── BaseTest.java           # hook @BeforeMethod/@AfterMethod: tạo folder, start/stop video, screenshot khi fail
    │   └── LoginTest.java          # test dùng CSV DataProvider, ConfigReader, ScreenshotUtil
    └── resources/
        ├── config.properties       # cấu hình môi trường
        └── loginData.csv           # dữ liệu test login
```
