package Utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ExtentReportUtil {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void startTestReport(String testName) {
        if (extent == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("reports/TestReport.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        test = extent.createTest(testName);
    }

    public static void logInfoWithScreenshot(WebDriver driver, String message) throws IOException {
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, "Info");

        if (screenshotPath != null) {
            File screenshotFile = new File(screenshotPath);
            String relativePath = Paths.get("reports", "screenshots", screenshotFile.getName())
                    .toString()
                    .replace("\\", "/");

            String screenshotURL = "http://localhost:63342/taskKangarooHealth/" + relativePath;

            System.out.println("Extent Report Screenshot URL: " + screenshotURL);

            String imgTag = "<img src='" + screenshotURL + "' style='width: 800px; height: 500px;'>";

            test.info(message + "<br>" + imgTag);
        } else {
            test.info(message + " (Screenshot failed)");
        }
    }


    public static void logPassWithScreenshot(WebDriver driver, String message) throws IOException {
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, "Pass");
        test.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

    public static void logFailWithScreenshot(WebDriver driver, String message) throws IOException {
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, "Fail");
        test.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void clearScreenshotsFolder() {
        String screenshotsFolderPath = System.getProperty("user.dir") + "/reports/screenshots/";
        File screenshotsFolder = new File(screenshotsFolderPath);

        if (screenshotsFolder.exists() && screenshotsFolder.isDirectory()) {
            for (File file : screenshotsFolder.listFiles()) {
                if (file.isFile()) {
                    file.delete();
                }
            }
            System.out.println("All file screenshot successfull deleted");
        } else {
            System.out.println("Folder screenshots can not found");
        }
    }
}
