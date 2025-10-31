package utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

    public class ReportUtils {

        public static String takeScreenshot(WebDriver driver, String testName) {
            // Capture screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Timestamp for unique file names
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // Folder relative to your report
            String relativePath = "screenshots/" + testName + "_" + timestamp + ".png";
            String fullPath = System.getProperty("user.dir") + "/reports/" + relativePath;

            try {
                FileUtils.copyFile(srcFile, new File(fullPath));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Return relative path (so ExtentReport can find it)
            return relativePath;
        }
    }
