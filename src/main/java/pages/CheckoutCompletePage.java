package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LoggerUtils;

public class CheckoutCompletePage extends BasePage{

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }
    private Logger log = LoggerUtils.getLogger(CheckoutPage.class);

    @FindBy(id = "back-to-products")
    private WebElement backHomeBtn;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuBtn;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    public WebElement getBackHomeBtn() {
        return backHomeBtn;
    }

    public WebElement getBurgerMenuBtn() {
        return burgerMenuBtn;
    }

    public WebElement getLogoutLink() {
        return logoutLink;
    }
}