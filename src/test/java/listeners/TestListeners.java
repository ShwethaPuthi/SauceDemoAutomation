package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import base.BaseTest;
import utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import utils.ReportUtils;

public class TestListeners implements ITestListener {
    private Logger log = LoggerUtils.getLogger(TestListeners.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Starting test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failed: " + result.getName());
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            String path = ReportUtils.takeScreenshot(((BaseTest) testClass).driver, result.getName());
            log.error("Screenshot saved at: " + path);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {}
}
