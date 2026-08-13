import { Locator, Page } from "@playwright/test";
import { highlight, unhighlight } from "./utils/highlight";

export class LoginPage {
    // thuộc tính để tương tác với browser
    readonly page: Page
    readonly usernameInput: Locator
    readonly passwordInput: Locator
    readonly loginBtn: Locator

    // <input
    //     name="username"
    //     type="text"
    //     placeholder="Username"
    //     class="oxd-input oxd-input--focus"
    // >

    constructor(page: Page) {
        this.page = page
        this.usernameInput = page.locator("input[name='username']")
        // C2: xpath
        // this.usernameInput = page.locator("//input[@name='username']")
        // C3: getByRole
        // this.usernameInput = page.getByRole('textbox', { name: 'Username' })
        // C4: getByPlaceholder
        // this.usernameInput = page.getByPlaceholder('Username')
        // C5: getyText, getByLabel,...
        this.passwordInput = page.locator("//input[@name='password']")
        this.loginBtn = page.getByRole('button', { name: 'Login' })
    }

    // define các step thì dùng BẤT ĐỒNG BỘ (async - await)
    async login(username: string, password: string): Promise<void> {
        // B1: truy cập vào trang login
        await this.page.goto("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login", {
            // domcontentloaded: trang load xong DOM, nhưng chưa load xong các resource (css, js, image,...)
            waitUntil: "domcontentloaded",
            timeout: 30000
        })

        // B2: nhập username
        highlight(this.usernameInput)
        await this.usernameInput.fill(username)
        await this.page.waitForTimeout(1000)
        unhighlight(this.usernameInput)
        
        // B3: nhập password
        highlight(this.passwordInput)
        await this.passwordInput.fill(password)
        await this.page.waitForTimeout(1000)
        unhighlight(this.passwordInput)

        // B4: click login button
        highlight(this.loginBtn)
        await this.loginBtn.click()
        await this.page.waitForTimeout(1000)
        unhighlight(this.loginBtn)
    }

    async isLoginSuccessful(): Promise<boolean> {
        try {
            // endpoint không chứa từ "login" => login thành công
            // bên trong waitForURL() có thể truyền vào 1 string, regex, hoặc function
            // refer regular expression
            // .* => match tất cả các ký tự
            await this.page.waitForURL(/.*dashboard/, {
                waitUntil: "domcontentloaded",
                timeout: 30000
            })
            return true
        } catch (error) {
            return false
        }
    }
}