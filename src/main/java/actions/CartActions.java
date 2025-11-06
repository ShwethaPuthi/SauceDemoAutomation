package actions;

import org.testng.Assert;
import pages.SiteFactory;
import utils.AppStrings;
import utils.LogHelper;
import utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

public class CartActions extends BaseActions{

    private Logger log = LoggerUtils.getLogger(CartActions.class);
    private Map<String, Double> cartProducts=new HashMap<>();

    public CartActions(SiteFactory siteFactory) {
        super(siteFactory);
    }

    public CartActions addItem(String productName) {
        siteFactory.getCartPage().addProductToCart(productName);
        LogHelper.info(log,"Added item to cart: " + productName);
        return this;
    }

    public CartActions removeItem(String productName) {
        siteFactory.getCartPage().removeProductFromCart(productName);
        LogHelper.info(log,"Removed item from cart: " + productName);
        return this;
    }

    public CartActions verifyCartBadgeCount(int expectedCount) {
        int actualCount = siteFactory.getCartPage().getCartCount();
        LogHelper.info(log,"Expected Cart Count: "+ expectedCount + " Actual Cart Count: "+ actualCount);
        Assert.assertEquals(actualCount, expectedCount, AppStrings.CartCountError);
        return this;
    }

    public CartActions clickCartBadge() {
        siteFactory.getCartPage().clickCartIcon();
        LogHelper.info(log,"Clicked on cart badge icon");
        return this;
    }

    public CartActions proceedToCheckout() {
        cartProducts = siteFactory.getCartPage().getCartProductsWithPrices();
        siteFactory.getCartPage().clickCheckoutButton();
        LogHelper.info(log,"Proceeded to checkout with items: " +cartProducts);
        return this;
    }

    public Map<String, Double> getCartProducts() {
        if (cartProducts == null || cartProducts.isEmpty()) {
            cartProducts = siteFactory.getCartPage().getCartProductsWithPrices();
        }
        return cartProducts;
    }
}



