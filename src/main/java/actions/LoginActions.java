package actions;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.SiteFactory;
import utils.AppStrings;
import utils.LoggerUtils;

public class LoginActions extends BaseActions {

    private Logger log = LoggerUtils.getLogger(LoginActions.class);

    public LoginActions(SiteFactory siteFactory) {
        super(siteFactory);
    }//This calls the parent constructor in BaseActions. It initializes this.siteFactory (so you can access all pages like LoginPage, CartPage, etc.)
    public LoginActions loginToApp(String username, String password) {
        log.info("Performing login for user: {}", username);
        siteFactory.getLoginPage().login(username, password);
        return this;
    }

    public InventoryActions verifySuccessfulLogin() {
        siteFactory.getLoginPage().verifyLoginSuccess();
        log.info("Login successful -> Navigating to Inventory Page");
        Assert.assertEquals(getDriver().getCurrentUrl(), AppStrings.INVENTORY_PAGE_URL, AppStrings.ErrorMsg);
        return new InventoryActions(siteFactory); // Calling another action
    }

    public LoginActions verifyLoginFailure(String expectedMessage) {
        siteFactory.getLoginPage().verifyLoginFailed(expectedMessage);
        String actualMessage = siteFactory.getLoginPage().getErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage,AppStrings.LoginMismatchMsg );
        log.info("Verified login failed as expected");
        return this;
    }

    /*public String getLoginErrorMessage() {
        return siteFactory.getLoginPage().getErrorMessage();
    }*/

}
