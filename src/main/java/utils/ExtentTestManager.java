package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentManager.getInstance();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (long) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) (long) Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized void endTest() {
        extent.flush();
    }
}