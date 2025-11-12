package base;

import actions.*;
import enums.BrowserType;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.SiteFactory;
import utils.AppStrings;
import utils.LoggerUtils;
import utils.ReportManager;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log = LoggerUtils.getLogger(BaseTest.class);
    protected SiteFactory siteFactory;
    protected BaseActions baseActions;
    protected ReportManager reportManager;

    @Parameters({"browser", "url"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("chrome") String browser,
                      @Optional(AppStrings.BASE_URL) String url) {

        driver=BrowserType.fromString(browser).createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        reportManager=new ReportManager();
        log.info("Navigating to URL: {}", url);
        //ReportManager.logInfo("Navigating to URL: "+url);
        driver.get(url);

        // Initialize using getDriver()
        siteFactory = new SiteFactory(driver, reportManager);
        baseActions = new BaseActions(siteFactory);

        log.info("Browser setup complete. Base Actions and SiteFactory initialized.");
        //ReportManager.logInfo("Browser setup complete. Base Actions and SiteFactory initialized.");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver!= null) {
            driver.quit();
            log.info("Browser closed successfully.");
            ReportManager.logInfo("Browser closed successfully.");
        }
        if (reportManager != null) {
            reportManager.flush();
        }
    }
    public WebDriver getDriver() {
        return driver;
    }
    public ReportManager getReportManager() {
        return reportManager;
    }

    public BaseActions getBaseActions() {
        return baseActions;
    }
}




