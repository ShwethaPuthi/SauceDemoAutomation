package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class ReportManager {

    private static ExtentReports extent;
    private static String reportPath;
    private static final ConcurrentHashMap<Long, ExtentTest> testMap = new ConcurrentHashMap<>();

    private static final String REPORT_FOLDER = System.getProperty("user.dir") + "/reports/";
    private static final String SCREENSHOT_FOLDER = REPORT_FOLDER + "screenshots/";

    // Initialize Extent Report
    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            createInstance(REPORT_FOLDER + "ExtentReport_" + System.currentTimeMillis() + ".html");
        }
        return extent;
    }


    private static synchronized ExtentReports createInstance(String fileName) {
        ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("SauceDemo Test Execution Report");
        reporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Shwetha V Puthi");

        reportPath = fileName;
        return extent;
    }

    public static String getReportPath() {
        return reportPath;
    }


    // ---------------- Start / Get / End Test ----------------
    public static synchronized ExtentTest startTest(String testName, String description) {
        ExtentTest test = getInstance().createTest(testName, description);
        testMap.put(Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return testMap.get(Thread.currentThread().getId());
    }

    public static synchronized void endTest() {
        if (extent != null) {
            extent.flush();
        }
    }

    // ---------------- Screenshot Utilities ----------------
    public static synchronized String takeScreenshot(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String relativePath = "screenshots/" + testName + "_" + timestamp + ".png";
        String fullPath = SCREENSHOT_FOLDER + testName + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(srcFile, new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return relative path for ExtentReport embedding
        return relativePath;
    }

    public static synchronized void attachScreenshot(WebDriver driver, String testName) {
        String relativePath = takeScreenshot(driver, testName);
        getTest().addScreenCaptureFromPath(relativePath);
    }
    // ---------------- Register ExtentAppender ----------------
    public static synchronized void initExtentAppender() {
        if (extent == null) {
            getInstance(); // ensure extent is initialized
        }

        org.apache.logging.log4j.core.LoggerContext context =
                (org.apache.logging.log4j.core.LoggerContext) org.apache.logging.log4j.LogManager.getContext(false);
        org.apache.logging.log4j.core.config.Configuration config = context.getConfiguration();

        // Prevent multiple registrations if already added
        if (config.getAppenders().get("ExtentAppender") == null) {
            ExtentAppender appender = new ExtentAppender(
                    "ExtentAppender",
                    null,
                    PatternLayout.createDefaultLayout(),
                    true
            );
            appender.start();
            config.addAppender(appender);
            config.getRootLogger().addAppender(appender, null, null);
            context.updateLoggers();
        }
    }
}