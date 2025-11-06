package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.AppStrings;
import utils.LogHelper;
import utils.LoggerUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartPage extends BasePage{

     public CartPage(WebDriver driver) {
        super(driver);
    }
    private Logger log = LoggerUtils.getLogger(CartPage.class);

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(css = ".checkout_button")
    private WebElement checkoutButton;

    // Add/Remove products
    public void addProductToCart(String productName) {
        String xpath = String.format(AppStrings.ADD_TO_CART_BUTTON_XPATH, productName);
        driver.findElement(By.xpath(xpath)).click();
        LogHelper.info(log,"Clicked 'Add to Cart' for:" + productName);
    }

    public void removeProductFromCart(String productName) {
        String xpath = String.format(AppStrings.REMOVE_FROM_CART_BUTTON_XPATH, productName);
        driver.findElement(By.xpath(xpath)).click();
        LogHelper.info(log,"Clicked 'Remove' for:" + productName);
    }

    public int getCartCount() {
        if (cartBadge != null && cartBadge.isDisplayed()) {
            return Integer.parseInt(cartBadge.getText().trim());
        }
        return 0;
    }

    public Map<String, Double> getCartProductsWithPrices() {
        Map<String, Double> cartProducts = new HashMap<>();
        for (WebElement item : cartItems) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            double price = Double.parseDouble(
                    item.findElement(By.className("inventory_item_price")).getText().replace("$", "")
            );
            cartProducts.put(name, price); //Add the name–price pair to the map
        }
        return cartProducts;
    }

    // Click cart badge icon
    public void clickCartIcon() {
        cartLink.click();
        LogHelper.info(log,"Clicked on cart icon");
    }

    // Click checkout button
    public void clickCheckoutButton() {
        checkoutButton.click();
        LogHelper.info(log,"Clicked on Checkout button");
    }
}

