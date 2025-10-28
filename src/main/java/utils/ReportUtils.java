    package utils;

    import org.openqa.selenium.OutputType;
    import org.openqa.selenium.TakesScreenshot;
    import org.openqa.selenium.WebDriver;

    import java.io.File;
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Paths;

    public class ReportUtils {

        private static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/screenshots/";

        // Capture screenshot and return path
        public static String takeScreenshot(WebDriver driver, String testName) {
            try {
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String destFile = SCREENSHOT_PATH + testName + "_" + System.currentTimeMillis() + ".png";
                Files.createDirectories(Paths.get(SCREENSHOT_PATH));
                Files.copy(srcFile.toPath(), Paths.get(destFile));
                return destFile;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        // Custom logging
        public static void log(String message) {
            System.out.println("[CUSTOM LOG] " + message);
        }
    }
