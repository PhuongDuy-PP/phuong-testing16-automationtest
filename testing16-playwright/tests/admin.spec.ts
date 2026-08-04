import test, { expect } from "@playwright/test";
import { LoginPage } from "../pages/LoginPage";
import { AdminPage } from "../pages/AdminPage";

test.describe("Admin page tests", () => {
    // khai báo object adminPage
    let adminPage: AdminPage
    test.beforeEach(async ({page}) => {
        const loginPage = new LoginPage(page)

        await loginPage.login("Admin", "admin123")
        await loginPage.isLoginSuccessful()
    })

    test("Filter admin user", async ({page}) => {
        adminPage = new AdminPage(page)
        await adminPage.goToAdminPage()
        await adminPage.searchUser("Admin", "Admin")

        const rowCount = await adminPage.getTableRowCount()
        expect(rowCount).toBeGreaterThan(0)
    })
})