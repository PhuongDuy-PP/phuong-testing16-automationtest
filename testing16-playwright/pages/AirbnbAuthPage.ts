import { Locator, Page } from "@playwright/test";

export type RegisterInfor = {
    name: string
    email: string
    password: string
    phone: string
    birthday: string
    gender: "Nam" | "Nữ"
}

export class AirbnbAuthPage {
    readonly page: Page

    // header: avatar, register menu, login menu
    readonly avatarBtn: Locator
    readonly registerMenu: Locator
    readonly loginMenu: Locator

    // modal (popup) register
    readonly nameInput: Locator
    readonly emailInput: Locator
    readonly passwordInput: Locator
    readonly phoneInput: Locator
    readonly birthdayInput: Locator
    readonly genderSelect: Locator
    readonly registerBtn: Locator

    // modal (popup) login
    readonly loginEmailInput: Locator
    readonly loginPasswordInput: Locator
    readonly loginBtn: Locator

    constructor(page: Page) {
        this.page = page

        // header
        // <button
        //     class=" text-sm bg-main  rounded-full md:me-0 focus:ring-4 focus:ring-gray-300 font-bold duration-300 hover:scale-105 hover:bg-white hover:text-white"
        // >
        //     <img class="h-10" src="https://cdn-icons-png.flaticon.com/512/6596/6596121.png">
        // </button>
        this.avatarBtn = page.locator("button.bg-main.rounded-full")
        this.registerMenu = page.getByRole("button", { name: "Đăng ký", exact: true })
        this.loginMenu = page.getByRole("button", { name: "Đăng nhập", exact: true })

        // modal register
        this.nameInput = page.locator("#name")
        // cách khác
        // getByPlaceHolder, getByLabel,...
        this.emailInput = page.locator("#email")
        this.passwordInput = page.locator("#password")
        this.phoneInput = page.locator("#phone")
        this.birthdayInput = page.locator("#birthday")
        this.genderSelect = page.locator("#gender")
        this.registerBtn = page.getByRole("button", { name: "Đăng ký" })
        // this.registerBtn = page.locator("button[type='submit']")
        // this.registerBtn = page.locator("//button[@type='submit']")

        // modal login
        this.loginEmailInput = page.locator("#email")
        this.loginPasswordInput = page.locator("#password")
        this.loginBtn = page.getByRole("button", { name: "Đăng nhập" })
    }

    async goto():Promise<void> {
        await this.page.goto("https://demo5.cybersoft.edu.vn/", {
            waitUntil: "domcontentloaded",
            timeout: 30000
        })
    }

    async register(newUserInfor: RegisterInfor): Promise<void> {
        await this.avatarBtn.click()
        await this.registerMenu.click()

        await this.nameInput.fill(newUserInfor.name)
        await this.emailInput.fill(newUserInfor.email)
        await this.passwordInput.fill(newUserInfor.password)
        await this.phoneInput.fill(newUserInfor.phone)

        await this.birthdayInput.click()
        await this.birthdayInput.fill(newUserInfor.birthday)

        // await this.genderSelect.click()
        await this.registerBtn.click()
    }

    async login(email: string, password: string): Promise<void> {
        await this.avatarBtn.click()
        await this.loginMenu.click()

        await this.loginEmailInput.fill(email)
        await this.loginPasswordInput.fill(password)

        await this.loginBtn.click()
    }

    async isSuccessfull(): Promise<boolean> {
        try {
            await this.page.locator(".anticon-check-circle").waitFor({ state: "visible", timeout: 10000 })
            return true
        } catch (error) {
            return false
        }
    }
}