/*package utils;

import com.aventstack.extentreports.ExtentReports;  //the central report manager object
import com.aventstack.extentreports.ExtentTest;     //represents an individual test in the report
import com.aventstack.extentreports.reporter.ExtentSparkReporter;   //the HTML generator for Extent (creates the .html file).
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
    private static final ConcurrentHashMap<Long, ExtentTest> testMap = new ConcurrentHashMap<>();  //a thread-safe map that holds an ExtentTest per thread.

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

        extent = new ExtentReports();  //creates the main manager
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
        // stores the test object in the map keyed by current thread id
        // this is how the manager knows which ExtentTest belongs to which running thread.
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

    //Logging Utilities
    public static synchronized void logInfo(String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.info(message);
        }
    }

    public static synchronized void logPass(String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.pass(message);
        }
    }

    public static synchronized void logFail(String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.fail(message);
        }
    }
}*/
package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportPath;

    private static final String REPORT_FOLDER = System.getProperty("user.dir") + "/reports/";
    private static final String SCREENSHOT_FOLDER = REPORT_FOLDER + "screenshots/";

    // ---------------- Create and Get Extent Instance ----------------
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance(REPORT_FOLDER + "ExtentReport_" + System.currentTimeMillis() + ".html");
        }
        return extent;
    }

    public static ExtentReports createInstance(String fileName) {
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

    // ---------------- Test Creation ----------------
    public static void startTest(String testName, String description) {
        test = getInstance().createTest(testName, description);
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void endTest() {
        if (extent != null) {
            extent.flush();
        }
    }

    // ---------------- Screenshot Handling ----------------
    public static String takeScreenshot(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String relativePath = "screenshots/" + testName + "_" + timestamp + ".png";
        String fullPath = SCREENSHOT_FOLDER + testName + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(srcFile, new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relativePath;
    }

    public static void attachScreenshot(WebDriver driver, String testName) {
        String relativePath = takeScreenshot(driver, testName);
        getTest().addScreenCaptureFromPath(relativePath);
    }

    // ---------------- Logging Utilities ----------------
    public static void logInfo(String message) {
        if (test != null) {
            test.info(message);
        }
    }

    public static void logPass(String message) {
        if (test != null) {
            test.pass(message);
        }
    }

    public static void logFail(String message) {
        if (test != null) {
            test.fail(message);
        }
    }
    public void flush(){

    }
}