package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(description = "Test login success")
    public void testLoginSuccess() throws InterruptedException {
//        khoi tao doi tuong LoginPage
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.login("Admin", "admin123");

//        kiem tra expected - actual result
        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("dashboard"));
    }

    @Test(description = "Test login fail")
    public void testLoginFail() throws  InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("Admin", "admin1234");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("auth/login"));
        Assert.assertFalse(currentUrl.contains("dashboard"));
    }
}
