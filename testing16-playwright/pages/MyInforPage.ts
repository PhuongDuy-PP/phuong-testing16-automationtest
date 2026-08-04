import { Locator, Page } from "@playwright/test";

export class MyInforPage {
    readonly page: Page

    // locator
    readonly avatarWrapper: Locator
    readonly uploadBtn: Locator
    readonly fileInput: Locator

    constructor(page: Page) {
        this.page = page

        this.avatarWrapper = page.locator("//div[@class='orangehrm-edit-employee-image-wrapper']")
        this.uploadBtn = page.locator("//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")
        this.fileInput = page.locator("//input[@type='file']")
    }

    async uploadAvatar(filePath: string): Promise<void> {
        // truy cập vào trang MyInfor
        // https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7
        await this.page.goto("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7", {
            waitUntil: "domcontentloaded",
            timeout: 30000
        })

        // click vào avatar wrapper để hiển thị button upload
        await this.avatarWrapper.waitFor({ state: "visible", timeout: 30000 })
        await this.avatarWrapper.click()

        // click vào button upload
        await this.uploadBtn.waitFor({ state: "visible", timeout: 30000 })
        await this.uploadBtn.click()

        // upload file
        // convert filePath to absolute path
        const absolutePath = `${process.cwd()}/tests${filePath}`
        console.log(`absolutePath = ${absolutePath}`)
        
        await this.fileInput.setInputFiles(absolutePath)
        await this.page.waitForTimeout(10000)
    }

    // hàm kiểm tra upload avatar thành công hay không
    // input[type='file'] luôn luôn trả về value dạng "<path>/fakepath/<filename>"
    // chỉ cần kiểm value có chứa tên file hay không
    async isUploadAvatarSuccessful(fileName: string): Promise<boolean> {
        const value = await this.fileInput.inputValue()
        return value.includes(fileName)
    }
}