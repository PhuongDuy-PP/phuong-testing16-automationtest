package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PIMPage extends BasePage{
    private static final By ICON_SORT = By.xpath("(//div[@role='columnheader'])[2]//i[@class='oxd-icon bi-arrow-down-up oxd-icon-button__icon oxd-table-header-sort-icon']");

    private static final By ICON_SORT_ASC = By.xpath("(//div[@role='columnheader'])[2]//li[.//span[text()='Ascending']]");

    private static final By EMPLOYEE_ROWS = By.xpath("//div[@class='oxd-table-card']");

    public PIMPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

//    buoc 1; step open page PIM
    public void open() {
        Allure.step("Open PIM page", () -> {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");

            wait.until(ExpectedConditions.visibilityOfElementLocated(EMPLOYEE_ROWS));
        });
    }

//    buoc 2: click icon sort -> click icon sort asc
    public void sortByIdAsc() {
        Allure.step("Sort by id ascending", () -> {
//            doi den khi icon sort co the click duoc
            WebElement iconSort = wait.until(ExpectedConditions.elementToBeClickable(ICON_SORT));
            highlight(iconSort);
            iconSort.click();
            unhighlight(iconSort);

            Thread.sleep(2000);

//            doi den khi icon sort asc hien thi va co the click duoc
            WebElement iconSortAsc = wait.until(ExpectedConditions.elementToBeClickable(ICON_SORT_ASC));
            highlight(iconSortAsc);
            iconSortAsc.click();
            unhighlight(iconSortAsc);
            Thread.sleep(2000);
        });
    }

//    function de lay list cac ID cua trang 1
    public List<String> getEmployeeIds() {
        return Allure.step("Get ids", () -> {
           List<WebElement> rows = driver.findElements(EMPLOYEE_ROWS);
//           cach 1: truyen thong -> for
           List<String> ids = new ArrayList<>();
           for(int i = 0; i < rows.size(); i++) {
//               lay row thu i
               WebElement row = rows.get(i);
//               1 row se co nhieu column (cell)
               List<WebElement> cells = row.findElements(By.xpath(".//div[@role='cell']"));
               String id = cells.get(1).getText();
               ids.add(id);
           }
           return ids;

//           cach 2: stream()
        });
    }
}
