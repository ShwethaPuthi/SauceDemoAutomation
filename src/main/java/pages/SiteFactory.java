package pages;

import org.openqa.selenium.WebDriver;

public class SiteFactory {
    private WebDriver driver;

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public SiteFactory(WebDriver driver) {
        this.driver = driver;

        // Initialize all pages here
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    // Getters for pages
    public LoginPage getLoginPage() { return loginPage; }
    public InventoryPage getInventoryPage() { return inventoryPage; }
    public CartPage getCartPage() { return cartPage; }
    public CheckoutPage getCheckoutPage() { return checkoutPage; }
}

