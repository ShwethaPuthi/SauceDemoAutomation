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

    public WebDriver driver;
    protected Logger log = LoggerUtils.getLogger(BaseTest.class);

    protected SiteFactory siteFactory;
    protected BaseActions baseActions;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void setup(@Optional("chrome") String browser,
                      @Optional(AppStrings.BASE_URL) String url) {
        driver= BrowserType.fromString(browser).createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        log.info("Navigating to URL: {}", url);
        driver.get(url);

        // Initialize SiteFactory
        siteFactory = new SiteFactory(driver);
        baseActions=new BaseActions(siteFactory);
        log.info("Browser setup complete. Base Actions and SiteFactory initialized.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("Browser closed successfully.");
        }
    }
    public BaseActions getBaseActions(){
        return baseActions;
    }
    public SiteFactory getSiteFactory(){
        return siteFactory;
    }
}

