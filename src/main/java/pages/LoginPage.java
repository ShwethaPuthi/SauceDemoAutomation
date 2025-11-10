package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LogHelper;
import utils.LoggerUtils;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
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

    public LoginPage enterUsername(String username) {
        log.info("Entering username: {}", username);
        usernameInput.clear();
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        log.info("Entering password: {}", password );
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        log.info("Clicking login button");
        loginButton.click();
        return this;
    }

    // Utility getters (used by Action class for validation)
    public String getErrorMessage() {
        return errorMessage.getText().trim();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
