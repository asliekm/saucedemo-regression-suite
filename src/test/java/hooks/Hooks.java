package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import utilities.Driver;
import utilities.PropManager;

import java.util.List;

/**
 * Created by Asli Ekmekci
 */
@Slf4j
public class Hooks {
    private static final String env = System.getProperty("env", "test");

    @Before
    public void setUp() {
        log.info("Environment: [{}]", env);
        PropManager.load(env);
        Driver.getDriver().get(PropManager.get("baseUrl"));
        log.info(" Navigated to: {}", PropManager.get("baseUrl"));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error(" Scenario FAILED: {}", scenario.getName());

            // Cucumber HTML Report
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");

            // Allure Attachment
            saveScreenshotToAllure();

            try {
                List<LogEntry> logs = Driver.getDriver().manage().logs().get(LogType.BROWSER).getAll();
                for (LogEntry entry : logs) {
                    log.warn("[{}] {}", entry.getLevel(), entry.getMessage());
                }
            } catch (Exception e) {
                log.warn(" No browser logs could be retrieved.");
            }
        } else {
            log.info(" Scenario PASSED: {}", scenario.getName());
        }

        Driver.quitDriver();
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] saveScreenshotToAllure() {
        return ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}


