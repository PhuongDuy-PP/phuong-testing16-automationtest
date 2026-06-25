public class LoginPage {
//    define cac locator, element (input, button, h1,...)
    private String usernameLocator = "//div[@id='username']";
    private String passwordLocator = "//div[@id='password']";
    private String loginButtonLocator = "//button[@id='login-button']";

//    step 1: enterUsername
    public void enterUsername(String username) {
        System.out.println("Enter username: " + username);
    }

//    step 2: enterPassword
    public void enterPassword(String password) {
        System.out.println("Enter password: " + password);
    }

//    step 3: click login button
    public void clickLoginButton() {
        System.out.println("Click login button");
    }

//    gom 3 step nay ve 1 function
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

//    function kiem tra expected - actual
    public boolean verifyLoginSuccess(String username, String password) {
        if(username.equals("admin") && password.equals("123456")) {
            return true;
        }
        return false;
    }
}
