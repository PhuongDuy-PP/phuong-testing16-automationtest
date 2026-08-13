// để chạy tuần tự các test case => mode serial
// chạy test từ TC1 -> TC n
// nếu TC nào fail thì sẽ dừng luôn, không chạy các test case còn lại

import { test, expect } from "@playwright/test"
import { AirbnbAuthPage, RegisterInfor } from "../pages/AirbnbAuthPage"
import { epic, feature, story } from "allure-js-commons"

// test.describe.configure({ mode: "serial" })

test.describe("Register then login tests", () => {
    // tạo data để đăng ký user mới
    const newUser: RegisterInfor = {
        name: "Phuong testing 16",
        email: `qa.user${Date.now()}@gmail.com`,
        password: "123456",
        phone: "0123456789",
        birthday: "06/08/2026",
        gender: "Nam"
    }

    test.beforeEach(async () => {
        await epic("Airbnb Register and Login")
        await feature("Register and Login feature")
    })

    test("TC1: Register new user", async ({page}) => {
        await story("Register new user")

        const airbnbPage = new AirbnbAuthPage(page)
        await airbnbPage.goto()
        await airbnbPage.register(newUser)
        const isRegisterSuccess = await airbnbPage.isSuccessfull()

        expect(isRegisterSuccess).toBeTruthy()
    })

    test("TC2: Login with new user", async ({page}) => {
        await story("Login with new user")

        const airbnbPage = new AirbnbAuthPage(page)
        await airbnbPage.goto()
        await airbnbPage.login(newUser.email, newUser.password)
        const isLoginSuccess = await airbnbPage.isSuccessfull()

        expect(isLoginSuccess).toBeTruthy()
    })
})