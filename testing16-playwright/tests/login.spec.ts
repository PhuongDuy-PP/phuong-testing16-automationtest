import test, {expect} from '@playwright/test'
import { LoginPage } from '../pages/LoginPage'
import { epic, feature, story } from 'allure-js-commons'
import { CsvRow, readCsv } from './utils/readCsv'

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

test.describe("Login tests with data from CSV", () => {
    test.beforeEach(async () => {
        await epic("OrangeHRM Login")
        await feature("Login feature")
    })

    // đọc dữ liệu từ file csv
    const loginData: CsvRow[] = readCsv("/data/loginData.csv")

    // tạo test case cho từng bộ dữ liệu trong file csv
    // dùng for để duyệt qua từng data => test case
    for (const data of loginData) {
        // LUƯ Ý: TRÁNH TRÙNG TÊN TEST CASE
        // => ĐẶT TITLE KHÁC NHAU => DÙNG DỮ LIỆU TRONG FILE CSV
        const title = `Login with username: ${data.username}, expected: ${data.expected}`
        test(title, async ({page}) => {
            await story(`Login with username: ${data.username}, expected: ${data.expected}`)

            const loginPage = new LoginPage(page)
            await loginPage.login(data.username, data.password)

            const isLoginSuccessful = await loginPage.isLoginSuccessful()
            const expected = data.expected
            if(expected === "success") {
                expect(isLoginSuccessful).toBeTruthy()
            } else {
                expect(isLoginSuccessful).toBeFalsy()
            }
        })
    }
})