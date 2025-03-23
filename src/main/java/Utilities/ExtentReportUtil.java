package Utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ExtentReportUtil {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void startTestReport(String testName) {
        if (extent == null) {
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/TestReport.html");
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

//            System.out.println("Extent Report Screenshot URL: " + screenshotURL);

            String imgTag = "<img src='" + screenshotURL + "' style='width: 100px; height: 100px;'>";

            test.info(message + "<br>" + imgTag);
        } else {
            test.info(message + " (Screenshot failed)");
        }
    }


    public static void logPassWithScreenshot(WebDriver driver, String message) throws IOException {
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, "Pass");
        if (screenshotPath != null) {
            File screenshotFile = new File(screenshotPath);
            String relativePath = Paths.get("reports", "screenshots", screenshotFile.getName())
                    .toString()
                    .replace("\\", "/");

            String screenshotURL = "http://localhost:63342/taskKangarooHealth/" + relativePath;

//            System.out.println("Extent Report Screenshot URL: " + screenshotURL);

            String imgTag = "<img src='" + screenshotURL + "' style='width: 100px; height: 100px;'>";

            test.pass(message + "<br>" + imgTag);
        } else {
            test.pass(message + " (Screenshot failed)");
        }
    }

    public static void logFailWithScreenshot(WebDriver driver, String message) throws IOException {
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, "Fail");
        if (screenshotPath != null) {
            File screenshotFile = new File(screenshotPath);
            String relativePath = Paths.get("reports", "screenshots", screenshotFile.getName())
                    .toString()
                    .replace("\\", "/");

            String screenshotURL = "http://localhost:63342/taskKangarooHealth/" + relativePath;

//            System.out.println("Extent Report Screenshot URL: " + screenshotURL);

            String imgTag = "<img src='" + screenshotURL + "' style='width: 100px; height: 100px;'>";

            test.fail(message + "<br>" + imgTag);
        } else {
            test.fail(message + " (Screenshot failed)");
        }
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

    public static void logFail(String message) {
        test.fail(message);
    }
}
