package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage{
    private static final By USER_DROPDOWN = By.xpath("//li[@class='oxd-userdropdown']");
    private static final By LOGOUT_LINK = By.linkText("Logout");
//    By.xpath("//a[text()='Logout']")

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void logout() {
//        WebElement userDropdown = driver.findElement(USER_DROPDOWN);
//        userDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(USER_DROPDOWN)).click();

//        WebElement logoutLink = driver.findElement(LOGOUT_LINK);
//        logoutLink.click();
//        an toan hon: doi co dieu kien
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK)).click();

//        sau khi click logout thi chuyen sang trang login
        wait.until(ExpectedConditions.urlContains("auth/login"));
    }
}
