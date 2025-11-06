package actions;
import org.testng.Assert;
import org.apache.logging.log4j.Logger;
import pages.SiteFactory;
import utils.LogHelper;
import utils.LoggerUtils;

public class CheckoutCompleteActions extends BaseActions {

    private Logger log = LoggerUtils.getLogger(CheckoutActions.class);

    public CheckoutCompleteActions(SiteFactory siteFactory) {
        super(siteFactory);
    }

    public CheckoutCompleteActions clickBackHome() {
        siteFactory.getCheckoutCompletePage().getBackHomeBtn().click();
        LogHelper.info(log,"Clicked on Back Home button.");
        Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory"),
                "Failed to navigate back to inventory page after clicking Back Home.");
        LogHelper.info(log,"Successfully navigated back to inventory page.");
        return this;
    }

    public CheckoutCompleteActions clickBurgerMenuAndLogout() {
        siteFactory.getCheckoutCompletePage().getBurgerMenuBtn().click();
        LogHelper.info(log,"Clicked on Burger Menu button.");
        siteFactory.getCheckoutCompletePage().getLogoutLink().click();
        LogHelper.info(log,"Clicked on Logout link.");
        Assert.assertTrue(getDriver().getCurrentUrl().contains("saucedemo.com"),
                "Logout was not successful — not redirected to login page.");
        LogHelper.info(log,"Successfully logged out and redirected to login page.");
        return this;
    }
}