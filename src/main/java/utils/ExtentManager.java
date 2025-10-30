package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportPath;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            createInstance("reports/ExtentReport_" + System.currentTimeMillis() + ".html");
        }
        return extent;
    }

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Shwetha V Puthi");

        reportPath = fileName; // ✅ Store report name
        return extent;
    }

    public static String getReportPath() {
        return reportPath;
    }
}