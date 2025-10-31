package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter; //Spark Reporter is a specific reporter type in ExtentReports.

public class ExtentManager {
    private static ExtentReports extent;//Declares a static variable to hold the single instance of ExtentReports.
    private static String reportPath;

    public static synchronized ExtentReports getInstance() {
        //synchronized ensures thread safety, so even if multiple tests run in parallel, only one thread can create or access the report at a time.
        if (extent == null) {
            createInstance("reports/ExtentReport_" + System.currentTimeMillis() + ".html");
        }
        return extent;
    }

    public static ExtentReports createInstance(String fileName) { //A helper method that creates and configures the Extent Report.
        ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Shwetha V Puthi");

        reportPath = fileName; //  Store report name
        return extent;
    }

    public static String getReportPath() {
        return reportPath;
    }
}