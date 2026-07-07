package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CsvReader;

import java.io.IOException;
import java.util.List;

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

//    tao data provider
    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() throws IOException {
        String filePath = "src/test/resources/loginData.csv";

        List<String[]> data = CsvReader.readCsv(filePath);

        return CsvReader.toDataProviderArray(data);
    }

    @Test(description = "Test login data provider", dataProvider = "loginData")
    public void testDataLogin(String username, String password, String expectedResult) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(username, password);

        if(expectedResult.equalsIgnoreCase("success")) {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("dashboard"));
        } else {
            Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"));
        }
    }
}
