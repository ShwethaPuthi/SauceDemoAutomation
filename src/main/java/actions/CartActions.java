package actions;

import pages.SiteFactory;
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
        log.info("Added item to cart: {}", productName);
        return this;
    }

    public CartActions removeItem(String productName) {
        siteFactory.getCartPage().removeProductFromCart(productName);
        log.info("Removed item from cart: {}", productName);
        return this;
    }

    public CartActions verifyCartBadgeCount(int expectedCount) {
        siteFactory.getCartPage().verifyCartCount(expectedCount);
        return this;
    }

    public CartActions clickCartBadge() {
        siteFactory.getCartPage().clickCartIcon();
        log.info("Clicked on cart badge icon");
        return this;
    }

    public CartActions proceedToCheckout() {
        cartProducts = siteFactory.getCartPage().getCartProductsWithPrices();
        siteFactory.getCartPage().clickCheckoutButton();
        log.info("Proceeded to checkout with items: {}", cartProducts);
        return this;
    }

    public Map<String, Double> getCartProducts() {
        if (cartProducts == null || cartProducts.isEmpty()) {
            cartProducts = siteFactory.getCartPage().getCartProductsWithPrices();
        }
        return cartProducts;
    }
}



