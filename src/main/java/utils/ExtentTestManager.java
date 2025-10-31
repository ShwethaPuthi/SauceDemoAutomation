package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    //Creates a static map where:
    //Key = Thread ID (unique number for each test thread)
    //Value = ExtentTest object for that thread’s test.
    static ExtentReports extent = ExtentManager.getInstance();

    public static synchronized ExtentTest getTest() { //Returns the current thread’s test.
        return extentTestMap.get((int) (long) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) (long) Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized void endTest() {
        extent.flush();
    } //It writes all collected logs, system info, and test results into the HTML file.
}