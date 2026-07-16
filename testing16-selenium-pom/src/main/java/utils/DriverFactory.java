package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    public static WebDriver createDriver(String browser, String deviceName) {
//        trường hợp không truyền tên browser hay device thì setup giá trị mặc định
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

//        handle thêm cho việc chạy test case trên CI
        boolean isHeadless = ConfigReader.getBoolean("headless")

//        dùng switch-case
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");

                return new FirefoxDriver(firefoxOptions);
            case "edge":
                WebDriverManager.edgedriver().setup();

                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");

                return new EdgeDriver(edgeOptions);
            default:
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();

                if (isHeadless) {
                    chromeOptions.addArguments(
                            "--headless=new",
                            "--disable-gpu",
                            "--no-sandbox",
                            "--window-size=1920,1080",
                            "--disable-dev-shm-usage"
                    );
                }

                if (deviceName != null && !deviceName.isEmpty()) {
//                    chrome yêu cầu có 1 Map chứa key deviceName để bắt đầu giả lập
//                    device trên browser
                    Map<String, String> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", deviceName.trim());

                    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                } else if(!isHeadless) {
                    chromeOptions.addArguments("--start-maximized");
                }
                return new ChromeDriver(chromeOptions);
        }
    }
}
