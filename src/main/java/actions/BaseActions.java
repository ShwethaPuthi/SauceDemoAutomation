package actions;

import pages.SiteFactory;

public class BaseActions {
    protected SiteFactory siteFactory;
    protected LoginActions loginActions;
    protected InventoryActions inventoryActions;
    protected CartActions cartActions;
    protected CheckoutActions checkoutActions;

    public BaseActions(SiteFactory siteFactory) {
        this.siteFactory = siteFactory;
        this.loginActions = new LoginActions(siteFactory);
        this.inventoryActions = new InventoryActions(siteFactory);
        this.cartActions = new CartActions(siteFactory);
        this.checkoutActions = new CheckoutActions(siteFactory);
    }

    // ✅ Getter methods
    public LoginActions getLoginActions() {
        return loginActions;
    }

    public InventoryActions getInventoryActions() {
        return inventoryActions;
    }

    public CartActions getCartActions() {
        return cartActions;
    }

    public CheckoutActions getCheckoutActions() {
        return checkoutActions;
    }
}