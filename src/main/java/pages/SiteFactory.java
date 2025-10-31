package pages;

import org.openqa.selenium.WebDriver;

public class SiteFactory {
    private WebDriver driver;

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutCompletePage checkoutCompletePage;

    public SiteFactory(WebDriver driver) {
        this.driver = driver;

        //PageFactory.initElements(driver, this);
        //Why not? Selenium tries to initialize every field annotated with @FindBy inside your SiteFactory object.
        //But SiteFactory has page objects (like LoginPage, CartPage) that themselves call PageFactory.initElements(driver, this)
        // inside their constructors (via super(driver)). So Selenium tries to initialize fields in SiteFactory, which initializes
        // pages, which call back into PageFactory, which sees SiteFactory again-infinite recursion loop

        // Initialize all pages here
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutCompletePage=new CheckoutCompletePage(driver);
    }

    // Getters for pages
    public LoginPage getLoginPage() { return loginPage; }
    public InventoryPage getInventoryPage() { return inventoryPage; }
    public CartPage getCartPage() { return cartPage; }
    public CheckoutPage getCheckoutPage() { return checkoutPage; }
    public CheckoutCompletePage getCheckoutCompletePage(){return checkoutCompletePage;}

    public WebDriver getDriver() {
        return driver;
    }
}

