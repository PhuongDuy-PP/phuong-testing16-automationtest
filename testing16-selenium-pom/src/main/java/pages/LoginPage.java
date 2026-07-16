package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.ScreenshotUtil;

public class LoginPage extends BasePage {
//    <input
//        data-v-1f99f73c=""
//        class="oxd-input oxd-input--active"
//        name="username"
//        placeholder="Username"
//        autofocus="">
//    1. tìm tên thẻ: input, button, a, p, h1, h2,...
//    2. tìm theo class name
//    3. tìm theo id, placeholder, name,...
//    4. tìm theo quan hệ cha-con
//    ưu tiên cao nhất: tìm theo id -> name -> placeholder -> class name ->
    private static final By USERNAME_INPUT = By.xpath("//input[@name='username']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@name='password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

//    define các step
    public void open() {
        Allure.step("Open login page", () -> {
            String url = ConfigReader.get("login.base.url");
            driver.get(url);
            wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
            ScreenshotUtil.takeScreenshot(driver, "login_page");
        });
    }

    public void enterUsername(String username) throws InterruptedException {
        Allure.step("Enter username: " + username, () -> {
            WebElement usernameInput = driver.findElement(USERNAME_INPUT);
            highlight(usernameInput);

            usernameInput.sendKeys(username);
            ScreenshotUtil.takeScreenshot(driver, "enter_username");
            Thread.sleep(2000);
            unhighlight(usernameInput);
        });
    }

    public void enterPassword(String password)throws InterruptedException {
        Allure.step("Enter password: ", () -> {
            WebElement passwordInput = driver.findElement(PASSWORD_INPUT);
            highlight(passwordInput);

            passwordInput.sendKeys(password);
            ScreenshotUtil.takeScreenshot(driver, "enter_password");
            Thread.sleep(2000);
            unhighlight(passwordInput);
        });
    }

    public void clickLoginBtn() throws InterruptedException {
        Allure.step("Click login button", () -> {
            WebElement loginBtn = driver.findElement(LOGIN_BUTTON);
            highlight(loginBtn);

            ScreenshotUtil.takeScreenshot(driver, "click_login_btn");
            Thread.sleep(1000);

            loginBtn.click();

            Thread.sleep(1000);
            unhighlight(loginBtn);
        });
    }

//    func gom 3 step này với nhau
    public void login(String username, String password) throws  InterruptedException {
        open();
        enterUsername(username);
        enterPassword(password);
        clickLoginBtn();
    }
}
