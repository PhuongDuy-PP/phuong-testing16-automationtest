//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
//        tạo các test case cho page login

//        Test
//        BaseTest - class cha
//        - setup môi trường giả lập chạy test case
//        - clear môi trường về ban đầu
//        LoginTest - class con
//        define cac test case

//        Page object model
//       LoginPage
//        thuoc tinh:
//        usernameLocator
//        passwordLocator
//        loginButtonLocator
//        Phuong thuc:
//        step 1: enterUsername
//        step 2: enterPassword
//        step3: clickLoginButton
//        ham kiem tra expected - actual

//        class de setup cac browser test
//        class cha: BrowserDriver
//        class con: ChromeDriver, SafariDriver
        LoginTest test1 = new LoginTest();
        test1.runTest();
    }
}