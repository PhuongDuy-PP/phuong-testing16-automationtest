import { Locator, Page } from "@playwright/test";

export class DashboardPage {
    readonly page: Page
    readonly avatarSelect: Locator
    readonly logoutBtn: Locator

    constructor(page: Page) {
        this.page = page
        this.avatarSelect = page.locator("//span[@class='oxd-userdropdown-tab']")
        this.logoutBtn = page.locator("//a[text()='Logout']")
    }

    async logout(): Promise<void> {
        await this.avatarSelect.click()
        await this.logoutBtn.click()
    }

    async isLogoutSuccessful(): Promise<boolean> {
        try {
            // đợi chuyển sang page login => pass
            // /auth/login
            await this.page.waitForURL(/.*login/)
            return true
        } catch (error) {
            return false
        }
    }
}