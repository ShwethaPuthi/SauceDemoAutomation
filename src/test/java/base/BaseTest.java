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
import java.time.Duration;

public class BaseTest {

    //ThreadLocal gives each test thread its own private copy of the WebDriver.
    //So, even if 5 tests run at once, each gets a separate, isolated browser.
    // Thread-safe WebDriver for parallel execution
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected Logger log = LoggerUtils.getLogger(BaseTest.class);
    protected SiteFactory siteFactory;
    protected BaseActions baseActions;

    @Parameters({"browser", "url"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("chrome") String browser,
                      @Optional(AppStrings.BASE_URL) String url) {

        //  Create and set driver for this thread
        driver.set(BrowserType.fromString(browser).createDriver());
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        log.info("Navigating to URL: {}", url);
        getDriver().get(url);

        // Initialize using getDriver()
        siteFactory = new SiteFactory(getDriver());
        baseActions = new BaseActions(siteFactory);
        log.info("Browser setup complete. Base Actions and SiteFactory initialized.");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); //  Important to clean up thread reference
            log.info("Browser closed successfully.");
        }
    }

    // Thread-safe getter
    public WebDriver getDriver() {
        return driver.get();
    }

    public BaseActions getBaseActions() {
        return baseActions;
    }
}

