import test, {expect} from '@playwright/test'
import { LoginPage } from '../pages/LoginPage'
import { epic, feature, story } from 'allure-js-commons'

test.describe("Login tests", () => {
    test.beforeEach(async () => {
        await epic("OrangeHRM Login")
        await feature("Login feature")
    })

    test("Login successful", async ({page}) => {
        await story("Login success")

        const loginPage = new LoginPage(page)
        await loginPage.login("Admin", "admin123")
        await loginPage.isLoginSuccessful()
    })

    test("Login failed", async ({page}) => {
        await story("Login failed")

        const loginPage = new LoginPage(page)
        await loginPage.login("Admin", "admin1234")
        const isLoginSuccessful = await loginPage.isLoginSuccessful()
        expect(isLoginSuccessful).toBeFalsy()
    })

    // TODO: viết test case đọc file excel để login với nhiều bộ dữ liệu
})