package utils;

public class AppStrings {

    //ExcelFile
    public static final String EXCEL_PATH = "/Users/shwetha/Downloads/LoginData.xlsx";
    // URLs
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    // Error messages
    public static final String INVALID_LOGIN_MESSAGE =
            "Epic sadface: Username is required";
    public static final String LOGIN_ERROR_MESSAGE = "Epic sadface: Username and password do not match any user in this service";

    // Product names
    public static final String FIRST_PRODUCT = "Sauce Labs Backpack";
    public static final String SECOND_PRODUCT = "Sauce Labs Bike Light";

    // XPath templates for CartPage
    // XPaths for Cart Buttons
    public static final String ADD_TO_CART_BUTTON_XPATH = "//div[text()='%s']/ancestor::div[@class='inventory_item']//button[contains(text(),'Add to cart')]";
    public static final String REMOVE_FROM_CART_BUTTON_XPATH = "//div[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Remove']";

    public static final String CheckoutSubTotalMsg="Subtotal mismatch!";
    public static final String TotalMismatch="Total mismatch!";

    public static final String ErrorMsg="Login failed!";
    public static final String LoginMismatchMsg="Login error message mismatch!";

    public static final String CartCountError="Cart icon count mismatch!";

}
