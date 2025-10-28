package actions;

import org.openqa.selenium.WebDriver;
import pages.SiteFactory;

public class BaseActions {
    protected SiteFactory siteFactory;
    protected LoginActions loginActions;
    protected InventoryActions inventoryActions;
    protected CartActions cartActions;
    protected CheckoutActions checkoutActions;

    public BaseActions(SiteFactory siteFactory) {
        this.siteFactory = siteFactory;
       // this.loginActions = new LoginActions(siteFactory);
       // this.inventoryActions = new InventoryActions(siteFactory);
       // this.cartActions = new CartActions(siteFactory);
        //this.checkoutActions = new CheckoutActions(siteFactory);
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
}