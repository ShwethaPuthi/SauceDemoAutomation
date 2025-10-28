package base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.SiteFactory;
import actions.CartActions;
import actions.LoginActions;
import actions.CheckoutActions;
import actions.InventoryActions;
import utils.AppStrings;
import utils.LoggerUtils;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    protected Logger log = LoggerUtils.getLogger(BaseTest.class);

    // ✅ SiteFactory reference
    protected SiteFactory siteFactory;

    // ✅ Actions classes
    protected LoginActions loginActions;
    protected InventoryActions inventoryActions;
    protected CartActions cartActions;
    protected CheckoutActions checkoutActions;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void setup(@Optional("chrome") String browser,
                      @Optional(AppStrings.BASE_URL) String url) {
        // Initialize WebDriver
       switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
           case "safari":
               driver = new SafariDriver();
               break;
            default:
                throw new IllegalArgumentException("Unsupported Browser: " + browser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        log.info("Navigating to URL: {}", url);
        driver.get(url);

        // Initialize SiteFactory
        siteFactory = new SiteFactory(driver);

        // Initialize Action classes with SiteFactory
        loginActions = new LoginActions(siteFactory);
        inventoryActions = new InventoryActions(siteFactory);
        cartActions = new CartActions(siteFactory);
        checkoutActions = new CheckoutActions(siteFactory);
        log.info("Browser setup complete. SiteFactory and Action classes initialized.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("Browser closed successfully.");
        }
    }
}

