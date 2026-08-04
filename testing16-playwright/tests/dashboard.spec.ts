import {expect, test} from '@playwright/test'
import { epic, feature, story } from 'allure-js-commons'
import { LoginPage } from '../pages/LoginPage'
import { DashboardPage } from '../pages/DashboardPage'

test.describe("Dashboard tests", () => {
    test.beforeEach(async ({page}) => {
        await epic("OrangeHRM Dashboard")
        await feature("Dashboard feature")

        // login trước khi vào dashboard
        const loginPage = new LoginPage(page)
        await loginPage.login("Admin", "admin123")
        await loginPage.isLoginSuccessful()
    })

    test("Logout successful", async ({page}) => {
        await story("Logout success")

        const dashboardPage = new DashboardPage(page)
        await dashboardPage.logout()
        const isLogoutSuccess = await dashboardPage.isLogoutSuccessful()
        expect(isLogoutSuccess).toBe(true)
    })
})