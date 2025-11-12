package actions;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.SiteFactory;
import utils.AppStrings;
import utils.LogHelper;
import utils.LoggerUtils;

public class LoginActions extends BaseActions {

    private Logger log = LoggerUtils.getLogger(LoginActions.class);

    public LoginActions(SiteFactory siteFactory) {
        super(siteFactory);
    }//This calls the parent constructor in BaseActions. It initializes this.siteFactory (so you can access all pages like LoginPage, CartPage, etc.)

    public LoginActions loginToApp(String username, String password) {
        log.info("Performing login for user: {}", username);
        siteFactory
                .getLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
        return this;
    }

    public InventoryActions verifySuccessfulLogin() {
        String actualUrl = siteFactory.getLoginPage().getCurrentUrl();
        log.info("Verifying login success. Current URL: {}", actualUrl);
        Assert.assertEquals(actualUrl, AppStrings.INVENTORY_PAGE_URL, AppStrings.ErrorMsg);
        log.info( "Login verified successfully. Navigating to Inventory Page.");
        return new InventoryActions(siteFactory);
    }

    public LoginActions verifyLoginFailure(String expectedMessage) {
        String actualMessage = siteFactory.getLoginPage().getErrorMessage();
        log.info("Verifying login failure. Expected: '{}', Actual: '{}'", expectedMessage, actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage, AppStrings.LoginMismatchMsg);
        log.info("Verified login failed with expected error message.");
        return this;
    }
}
