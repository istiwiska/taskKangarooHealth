package Managers;

import Enums.DriverType;
import Enums.EnvironmentType;
import Utilities.ExtentReportUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static Utilities.ExtentReportUtil.flushReport;

public class AllDriverManager {

    private WebDriver webDriver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;

    public AllDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigFileReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigFileReader().getEnvironment();
    }

    private WebDriver createLocalDriver() throws IOException {
        switch (driverType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Driver/chromedriver134.exe");
//                System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_PATH"));
                ChromeOptions options = new ChromeOptions();
                // Use a unique temporary directory for user data
                String tempUserDataDir = System.getProperty("java.io.tmpdir") + "/chrome-user-data-" + System.currentTimeMillis();
                options.addArguments("--user-data-dir=" + tempUserDataDir);
                // Optional: Run in headless mode for CI/CD
                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                webDriver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            case SAFARI:
                webDriver = new SafariDriver();
                break;
        }
        long time = FileReaderManager.getInstance().getConfigFileReader().getTime();

        webDriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(time, TimeUnit.SECONDS);
        return webDriver;
    }

    private WebDriver createRemoteDriver() {
        try {
            // Define desired capabilities (e.g., Chrome or Firefox)
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            WebDriver driver = new RemoteWebDriver(new URL("http://localhost:8080/wd/hub"), capabilities);

            return driver;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid hub URL", e);
        } catch (Exception e) {
            throw new RuntimeException("Remote WebDriver initialization failed", e);
        }
    }


    private WebDriver createDriver() throws IOException {
        switch (environmentType) {
            case LOCAL:
                try {
                    webDriver = createLocalDriver();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case REMOTE:
                webDriver = createRemoteDriver();
                break;
        }
        return webDriver;
    }

    public WebDriver getDriver() throws IOException {
        if (webDriver == null) webDriver = createDriver();
        return webDriver;
    }

    public void closeDriver() {
        webDriver.close();
        webDriver.quit();
        ExtentReportUtil.flushReport();
    }
}
