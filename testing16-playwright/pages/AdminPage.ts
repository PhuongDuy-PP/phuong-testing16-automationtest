import { Locator, Page } from "@playwright/test";

export class AdminPage {
    readonly page: Page

    readonly usernameInput: Locator
    readonly userRoleSelect: Locator
    readonly adminRoleOption: Locator
    readonly searchBtn: Locator
    readonly tableRows: Locator
    readonly loadingSpinner: Locator

    constructor(page: Page){
        this.page = page

    //     private static final By USER_INPUT = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    // private static final By USER_ROLE_SELECT = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[1]");
    // private static final By ADMIN_ROLE_OPTION = By.xpath("//div[@role='option']//span[text()='Admin']");
    // private static final By SEARCH_BUTTON = By.xpath("//button[@type='submit']");
    // private static final By dataRows = By.xpath("//div[@class='oxd-table-card']");
    // private static final By RECORD_COUNT_TEXT = By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//span");
        this.usernameInput = page.locator("(//input[@class='oxd-input oxd-input--active'])[2]")
        this.userRoleSelect = page.locator("(//div[@class='oxd-select-text oxd-select-text--active'])[1]")
        this.adminRoleOption = page.locator("//div[@role='option']//span[text()='Admin']")
        this.searchBtn = page.locator("//button[@type='submit']")
        this.tableRows = page.locator("//div[@class='oxd-table-card']")
        this.loadingSpinner = page.locator("//div[@class='oxd-table-loader']")
    }

    async waitForLoadingSpinnerToDisappear(): Promise<void> {
        const isVisible = await this.loadingSpinner.isVisible()
        if (isVisible) {
            await this.loadingSpinner.waitFor({ state: "hidden", timeout: 30000 })
        }
    }

    async goToAdminPage(): Promise<void> {
        await this.page.goto("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers", {
            waitUntil: "domcontentloaded",
            timeout: 60000
        })
    }

    async searchUser(username: string, userRole: string): Promise<void> {
        await this.usernameInput.fill(username)

        await this.page.waitForTimeout(2000)

        await this.userRoleSelect.click()
        await this.page.waitForTimeout(1000)
        // chọn option Admin
        if (userRole === "Admin") {
            await this.page.waitForTimeout(1000)
            await this.adminRoleOption.click()
        }

        await this.searchBtn.click()

        await this.waitForLoadingSpinnerToDisappear()
    }

    async getTableRowCount(): Promise<number> {
        return await this.tableRows.count()
    }
}

// cách chạy allure report:
// B1: tạo file html allure dựa trên file allure-results
// npx allure generate --clean allure-results
// B2: mở file html allure
// npx allure open allure-report