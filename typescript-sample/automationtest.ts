// MÔ PHỎNG PLAYWRIGHT POM
// class LoginPage
// class LoginTest

class LoginPage {
    // thuộc tính: locator: usernameInput, passwordInput, loginButton
    // object locator: selector của các element trên trang web
    private usernameInput: string = '#username';
    private passwordInput: string = '#password'
    private loginButton: string = '#login-button';

    // phương thức: goto, enterUsername, enterPassword, clickLoginButton, login
    goto = (): void => {
        // playwright: await page.goto('https://example.com/login');
        console.log('Navigating to login page...');
    }

    enterUsername = (username: string): void => {
        // page: object của playwright, dùng để thao tác với trang web
        // playwright: await page.fill(this.usernameInput, username);
        console.log(`Đã nhập username: ${username} vào ô ${this.usernameInput}`);
    }

    enterPassword = (password: string): void => {
        console.log(`Đã nhập password: ${password} vào ô ${this.passwordInput}`);
    }

    clickLoginBtn = (): void => {
        // playwright: await page.click(this.loginButton);
        console.log(`Đã click vào nút login: ${this.loginButton}`);
    }

    login = (username: string, password: string): void => {
        this.goto();
        this.enterUsername(username);
        this.enterPassword(password);
        this.clickLoginBtn();
    }
}

class LoginTest {
    private loginPage: LoginPage;

    constructor() {
        this.loginPage = new LoginPage();
    }

    testValidLogin = (): void => {
        this.loginPage.login('validUser', 'validPassword');

        // playwright: await expect(page).toHaveURL('https://example.com/dashboard');
        console.log('Test valid login passed');
    }
}

// gọi LoginTest
const loginTest = new LoginTest()
loginTest.testValidLogin()