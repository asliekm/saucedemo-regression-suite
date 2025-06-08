package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * Created by Asli Ekmekci
 */
public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--incognito");

            try {
                Path userDataDir = Files.createTempDirectory("chrome-profile-");
                options.addArguments("--user-data-dir=" + userDataDir.toAbsolutePath().toString());
            } catch (IOException e) {
                throw new RuntimeException("Failed to create temp Chrome profile directory", e);
            }

            options.setExperimentalOption("excludeSwitches", List.of("enable-automation"));
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("prefs", Map.of(
                    "credentials_enable_service", false,
                    "profile.password_manager_enabled", false,
                    "profile.default_content_setting_values.notifications", 2
            ));

            driver = new ChromeDriver(options);

            long timeout = Long.parseLong(PropManager.get("implicitWait"));
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(timeout));
            driver.manage().window().maximize();
        }
        return driver;
    }


    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
