package listeners;

import com.aventstack.extentreports.Status;
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
    private Logger log = LoggerUtils.getLogger(TestListeners.class);

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.startTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        log.info("Starting test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test passed successfully ");
        log.info("Test passed: " + result.getName());
        /*Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            String path = ReportUtils.takeScreenshot(((BaseTest) testClass).driver, result.getName());
            log.error("Screenshot is saved at: " + path);
        }*/
    }

    @Override
    public void onTestFailure(ITestResult result) {
        /*log.error("Test failed: " + result.getName());
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            String path = ReportUtils.takeScreenshot(((BaseTest) testClass).driver, result.getName());
            log.error("Screenshot saved at: " + path);
        }*/
        log.error("Test failed: {}", result.getName());
        ExtentTestManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable());

        // Capture screenshot & add to report
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            String path = ReportUtils.takeScreenshot(((BaseTest) testClass).driver, result.getName());
            ExtentTestManager.getTest().addScreenCaptureFromPath(path);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "Test skipped: " + result.getName());
        log.warn("Test skipped: {}", result.getName());
    }

    /*@Override
    public void onStart(ITestContext context) {
        log.info("All tests finished. Flushing Extent Report...");
        ExtentManager.getInstance().flush();
    }*/
    @Override
    public void onFinish(ITestContext context) {
        ExtentTestManager.endTest();}
}
