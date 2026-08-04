import {test, expect} from "@playwright/test";
import { epic, feature, story } from "allure-js-commons";
import { LoginPage } from "../pages/LoginPage";
import { MyInforPage } from "../pages/MyInforPage";

test.describe("My Info tests", () => {
    test.beforeEach(async ({page}) => {
        await epic("OrangeHRM My Info")
        await feature("My Info feature")

        // login trước khi vào my info
        const loginPage = new LoginPage(page)
        await loginPage.login("Admin", "admin123")
        await loginPage.isLoginSuccessful()
    })

    test("Verify My Info page is displayed", async ({page}) => {
        await story("Verify My Info page is displayed")

        const myInfoPage = new MyInforPage(page)
        await myInfoPage.uploadAvatar("/data/images/dog.png")

        const isUploadSuccessful = await myInfoPage.isUploadAvatarSuccessful("dog.png")
        expect(isUploadSuccessful).toBe(true)
    })
})