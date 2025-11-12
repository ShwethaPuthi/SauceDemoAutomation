package actions;
import org.testng.Assert;
import org.apache.logging.log4j.Logger;
import pages.SiteFactory;
import utils.LoggerUtils;
import utils.ReportManager;

public class CheckoutCompleteActions extends BaseActions {

    private Logger log = LoggerUtils.getLogger(CheckoutActions.class);

    public CheckoutCompleteActions(SiteFactory siteFactory) {
        super(siteFactory);
    }

    public CheckoutCompleteActions clickBackHome() {
        siteFactory.getCheckoutCompletePage().getBackHomeBtn().click();
        log.info("Clicked on Back Home button.");
        ReportManager.logInfo("Clicked on Back Home button.");
        Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory"),
                "Failed to navigate back to inventory page after clicking Back Home.");
        log.info("Successfully navigated back to inventory page.");
        ReportManager.logInfo("Successfully navigated back to inventory page.");
        return this;
    }

    public CheckoutCompleteActions clickBurgerMenuAndLogout() {
        siteFactory.getCheckoutCompletePage().getBurgerMenuBtn().click();
        log.info("Clicked on Burger Menu button.");
        ReportManager.logInfo("Clicked on Burger Menu button.");
        siteFactory.getCheckoutCompletePage().getLogoutLink().click();
        log.info("Clicked on Logout link.");
        ReportManager.logInfo("Clicked on Logout link.");
        Assert.assertTrue(getDriver().getCurrentUrl().contains("saucedemo.com"),
                "Logout was not successful — not redirected to login page.");
        log.info("Successfully logged out and redirected to login page.");
        ReportManager.logInfo("Successfully logged out and redirected to login page.");
        return this;
    }
}