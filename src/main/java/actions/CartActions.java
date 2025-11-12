package actions;

import org.testng.Assert;
import pages.SiteFactory;
import utils.AppStrings;
import utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import utils.ReportManager;

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
        log.info("Added item to cart: {}", productName);
        ReportManager.logInfo("Added item to cart: "+productName);
        return this;
    }

    public CartActions removeItem(String productName) {
        siteFactory.getCartPage().removeProductFromCart(productName);
        log.info("Removed item from cart: {} ", productName);
        ReportManager.logInfo("Removed item from cart: "+productName);
        return this;
    }

    public CartActions verifyCartBadgeCount(int expectedCount) {
        int actualCount = siteFactory.getCartPage().getCartCount();
        log.info("Expected Cart Count {}, Actual Cart count {},", expectedCount, actualCount);
        ReportManager.logInfo("Expected cart cound: "+expectedCount);
        ReportManager.logInfo("Actual Cart Count: " +actualCount);
        Assert.assertEquals(actualCount, expectedCount, AppStrings.CartCountError);
        return this;
    }

    public CartActions clickCartBadge() {
        siteFactory.getCartPage().clickCartIcon();
        log.info("Clicked on cart badge icon");
        ReportManager.logInfo("Clicked on cart badge icon");
        return this;
    }

    public CartActions proceedToCheckout() {
        cartProducts = siteFactory.getCartPage().getCartProductsWithPrices();
        siteFactory.getCartPage().clickCheckoutButton();
        log.info("Proceeded to checkout with items: {} " ,cartProducts);
        ReportManager.logInfo("Proceeded to checkout with items: "+cartProducts);
        return this;
    }

    public Map<String, Double> getCartProducts() {
        if (cartProducts == null || cartProducts.isEmpty()) {
            cartProducts = siteFactory.getCartPage().getCartProductsWithPrices();
        }
        return cartProducts;
    }
}



