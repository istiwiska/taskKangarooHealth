package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());

            String screenshotDir = System.getProperty("user.dir") + "/reports/screenshots/";
            new File(screenshotDir).mkdirs();

            String screenshotPath = screenshotDir + screenshotName + "_" + timestamp + ".png";
            File destFile = new File(screenshotPath);
            FileUtils.copyFile(src, destFile);

//            System.out.println("Screenshot saved: " + screenshotPath);

            return screenshotPath;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
