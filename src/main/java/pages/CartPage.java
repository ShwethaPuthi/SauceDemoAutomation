package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.AppStrings;
import utils.LoggerUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartPage {

    private WebDriver driver;
    private Logger log = LoggerUtils.getLogger(CartPage.class);

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(css = ".summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(css = ".checkout_button")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Add/Remove products
    public void addProductToCart(String productName) {
        String xpath = String.format(AppStrings.ADD_TO_CART_BUTTON_XPATH, productName);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void removeProductFromCart(String productName) {
        String xpath = String.format(AppStrings.REMOVE_FROM_CART_BUTTON_XPATH, productName);
        driver.findElement(By.xpath(xpath)).click();
    }

    // Verify cart badge
    public void verifyCartCount(int expectedCount) {
        int actualCount = (cartBadge != null && cartBadge.isDisplayed())
                ? Integer.parseInt(cartBadge.getText().trim())
                : 0;
        log.info("Expected Cart Count: {}, Actual Cart Count: {}", expectedCount, actualCount);
        Assert.assertEquals(actualCount, expectedCount, "Cart icon count mismatch!");
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
    }

    // Click checkout button
    public void clickCheckoutButton() {
        checkoutButton.click();
    }
}
