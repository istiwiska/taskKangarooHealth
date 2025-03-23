package Managers;

import Enums.DriverType;
import Enums.EnvironmentType;
import Utilities.ExtentReportUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
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
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class AllDriverManager {
    private static final Logger LOGGER = Logger.getLogger(AllDriverManager.class.getName());
    private static WebDriver webDriver; // Singleton driver instance
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
                ChromeOptions options = new ChromeOptions();
                // Generate a unique temporary directory for each instance
                Path tempDir = Files.createTempDirectory("chrome-data-" + UUID.randomUUID().toString());
                String userDataDir = tempDir.toAbsolutePath().toString();
                LOGGER.info("Using Chrome user data directory: " + userDataDir);

                options.addArguments("--user-data-dir=" + userDataDir); // Unique per instance
                options.addArguments("--no-sandbox"); // Essential for CI
                options.addArguments("--disable-dev-shm-usage"); // Avoid shared memory issues
                options.addArguments("--disable-gpu"); // Disable GPU in headless mode
                options.addArguments("--remote-allow-origins=*"); // Avoid CORS issues
                options.addArguments("--window-size=1920,1080"); // Consistent window size

                // Enable headless mode in CI or if specified
                if (isRunningInCI() || "headless".equals(System.getProperty("browser"))) {
                    options.addArguments("--headless=new"); // Modern headless mode
                }

                webDriver = new ChromeDriver(options);
                LOGGER.info("ChromeDriver initialized with session: " + ((ChromeDriver) webDriver).getSessionId());
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isRunningInCI() || "headless".equals(System.getProperty("browser"))) {
                    firefoxOptions.addArguments("--headless");
                }
                webDriver = new FirefoxDriver(firefoxOptions);
                LOGGER.info("FirefoxDriver initialized");
                break;

            case EDGE:
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                LOGGER.info("EdgeDriver initialized");
                break;

            case SAFARI:
                webDriver = new SafariDriver();
                LOGGER.info("SafariDriver initialized");
                break;

            default:
                throw new IllegalArgumentException("Unsupported driver type: " + driverType);
        }

        // Apply timeouts from config
        long time = FileReaderManager.getInstance().getConfigFileReader().getTime();
        webDriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(time, TimeUnit.SECONDS);

        return webDriver;
    }

    private WebDriver createRemoteDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            webDriver = new RemoteWebDriver(new URL("http://localhost:8080/wd/hub"), capabilities);
            LOGGER.info("RemoteWebDriver initialized");
            return webDriver;
        } catch (MalformedURLException e) {
            LOGGER.severe("Invalid Selenium Grid hub URL: " + e.getMessage());
            throw new RuntimeException("Invalid Selenium Grid hub URL", e);
        } catch (Exception e) {
            LOGGER.severe("Remote WebDriver initialization failed: " + e.getMessage());
            throw new RuntimeException("Remote WebDriver initialization failed", e);
        }
    }

    private WebDriver createDriver() throws IOException {
        switch (environmentType) {
            case LOCAL:
                return createLocalDriver();
            case REMOTE:
                return createRemoteDriver();
            default:
                throw new IllegalArgumentException("Unsupported environment type: " + environmentType);
        }
    }

    public WebDriver getDriver() throws IOException {
        if (webDriver == null) {
            synchronized (AllDriverManager.class) { // Thread-safe initialization
                if (webDriver == null) {
                    webDriver = createDriver();
                }
            }
        }
        return webDriver;
    }

    public void closeDriver() {
        if (webDriver != null) {
            try {
                ExtentReportUtil.flushReport();
                webDriver.quit(); // Use quit() only, close() is redundant
                LOGGER.info("WebDriver quit successfully");
            } catch (Exception e) {
                LOGGER.warning("Error quitting WebDriver: " + e.getMessage());
            } finally {
                webDriver = null; // Ensure reference is cleared
                ExtentReportUtil.flushReport(); // Flush report after cleanup
            }
        }
    }

    private static boolean isRunningInCI() {
        return System.getenv("GITHUB_ACTIONS") != null;
    }
}

