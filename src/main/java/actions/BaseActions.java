package actions;

import org.openqa.selenium.WebDriver;
import pages.SiteFactory;

public class BaseActions {
    protected SiteFactory siteFactory;
    public BaseActions(SiteFactory siteFactory) {
        this.siteFactory = siteFactory;
    }
    protected WebDriver getDriver() {
        return siteFactory.getDriver();
    }
    public LoginActions getLoginActions() {
        return new LoginActions(siteFactory);
    }

    public InventoryActions getInventoryActions() {
        return new InventoryActions(siteFactory);
    }

    public CartActions getCartActions() {
        return new CartActions(siteFactory);
    }

    public CheckoutActions getCheckoutActions() {
        return new CheckoutActions(siteFactory);
    }

    public CheckoutCompleteActions getCheckoutCompleteActions(){
        return new CheckoutCompleteActions(siteFactory);
    }
}