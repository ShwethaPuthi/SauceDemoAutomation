package listeners;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import base.BaseTest;
import utils.ExtentManager;
import utils.ExtentTestManager;
import utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import utils.ReportUtils;

public class TestListeners implements ITestListener {
    private static final Logger log = LoggerUtils.getLogger(TestListeners.class);

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.startTest(
                result.getMethod().getMethodName(),
                result.getMethod().getDescription()
        );
        log.info("Starting test: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test passed successfully");
        log.info("Test passed: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failed: {}", result.getName());
        ExtentTestManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable());
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) testClass;
            WebDriver driver = baseTest.getDriver(); // ✅ Get actual driver for the current thread
            if (driver != null) {
                String path = ReportUtils.takeScreenshot(driver, result.getName());
                ExtentTestManager.getTest().addScreenCaptureFromPath(path);
                log.info("Screenshot attached to report: {}", path);
            } else {
                log.warn("Driver was null — could not take screenshot for {}", result.getName());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "Test skipped: " + result.getName());
        log.warn(" Test skipped: {}", result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
        String reportPath = ExtentManager.getReportPath();
        System.out.println("✅ Extent Report generated at: " + reportPath);
        log.info("Extent Report generated at: {}", reportPath);
    }
}
