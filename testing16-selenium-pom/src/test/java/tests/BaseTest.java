package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.DriverFactory;

import java.lang.reflect.Method;
import java.time.Duration;


public class BaseTest {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    protected WebDriverWait getWait() {
        return waitThreadLocal.get();
    }

//    setup moi truong
    @Parameters({"browser", "device"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, @Optional("") String device, Method method) {
//        TODO: tạo folder chứa screenshot, video record


        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");


//        WebDriver driver = new ChromeDriver(options);
        WebDriver driver = DriverFactory.createDriver(browser, device);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driverThreadLocal.set(driver);
        waitThreadLocal.set(wait);

//        TODO: start recording
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
//        CHỈ LƯU RECORD VỚI TEST CASE LỖI
//        VỚI TEST CASE PASS => CLEAR RECORD ĐÓ

        WebDriver driver = getDriver();

//        TODO: kiểm tra test case pass hay fail để xem xét lưu record

        if(driver != null){
            driver.quit();
        }
        driverThreadLocal.remove();
        waitThreadLocal.remove();
    }
}
