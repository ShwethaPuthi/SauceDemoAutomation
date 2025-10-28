package actions;

import org.apache.logging.log4j.Logger;
import pages.SiteFactory;
import utils.LoggerUtils;

public class LoginActions extends BaseActions {

    private Logger log = LoggerUtils.getLogger(LoginActions.class);

    public LoginActions(SiteFactory siteFactory) {
        super(siteFactory);
    } //This calls the parent constructor in BaseActions.
    //It initializes this.siteFactory (so you can access all pages like LoginPage, CartPage, etc.)

    public LoginActions loginToApp(String username, String password) {
        log.info("Performing login for user: {}", username);
        siteFactory.getLoginPage().login(username, password);
        return this;
    }

    public InventoryActions verifySuccessfulLogin() {
        siteFactory.getLoginPage().verifyLoginSuccess();
        log.info("Login successful -> Navigating to Inventory Page");
        return new InventoryActions(siteFactory); // Calling another action
    }

    public LoginActions verifyLoginFailure(String expectedMessage) {
        siteFactory.getLoginPage().verifyLoginFailed(expectedMessage);
        log.info("Verified login failed as expected");
        return this;
    }

    public String getLoginErrorMessage() {
        return siteFactory.getLoginPage().getErrorMessage();
    }

}
