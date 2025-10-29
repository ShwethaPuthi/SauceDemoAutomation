package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.AppStrings;
import utils.LoggerUtils;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        //this.driver=driver;
        //PageFactory.initElements(driver, this);
        super(driver);
    }
    private Logger log = LoggerUtils.getLogger(LoginPage.class);

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    public  String getErrorMessage(){
        return errorMessage.getText().trim();
    }

    public LoginPage login(String username, String password) {
        usernameInput.clear();
        log.info("Entering username: {}", username);
        usernameInput.sendKeys(username);
        passwordInput.clear();
        log.info("Entering password: {}", password);
        passwordInput.sendKeys(password);
        loginButton.click();
        log.info("Clicking login button");
        return this;
    }

    public void verifyLoginSuccess() {
        log.info("Verifying login success. Current URL: {}, Expected URL: {}", driver.getCurrentUrl(), AppStrings.INVENTORY_PAGE_URL);
    }

    public void verifyLoginFailed(String expectedMessage) {
        log.info("Verifying login failure. Actual message: '{}', Expected message: '{}'", errorMessage.getText().trim(), expectedMessage);
    }
}
