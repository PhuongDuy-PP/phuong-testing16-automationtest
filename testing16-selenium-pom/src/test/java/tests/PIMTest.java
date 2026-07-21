package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PIMPage;
import utils.ConfigReader;

import java.util.List;

@Epic("OrangeHRM web")
@Feature("PIM module")
public class PIMTest extends BaseTest{
    private PIMPage pimPage;

    @BeforeMethod
    public void loginAndOpenPIMPage() throws InterruptedException{
        LoginPage loginPage = new LoginPage(getDriver(), getWait());
        String username = ConfigReader.get("admin.username");
        String password = ConfigReader.get("admin.password");
        loginPage.login(username, password);

        pimPage = new PIMPage(getDriver(), getWait());
        pimPage.open();
    }

    @Story("Sort by id asc")
    @Severity(SeverityLevel.NORMAL)
    @Description("Sort by id asc")
    @Test(description = "Test sort by id asc")
    public void testSortByIdAsc() {
        pimPage.sortByIdAsc();

        List<String> ids = pimPage.getEmployeeIds();

        for(int i = 0; i < ids.size() - 1; i++) {
            String current = ids.get(i);
            String next = ids.get(i + 1);
            Assert.assertTrue(
                    current.compareTo(next) <= 0,
                    String.format("ID %s is not less than or equal to ID %s", current, next)
            );
        }

    }
}
