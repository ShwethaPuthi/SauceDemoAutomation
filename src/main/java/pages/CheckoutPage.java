package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.LoggerUtils;
import org.testng.Assert;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    private Logger log = LoggerUtils.getLogger(CheckoutPage.class);

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(css = ".cart_button")
    private WebElement continueButton;

    @FindBy(css = ".summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(css = ".summary_tax_label")
    private WebElement taxLabel;

    @FindBy(css = ".summary_total_label")
    private WebElement totalLabel;

    @FindBy(css = ".cart_button") // Finish button on last page
    private WebElement finishButton;

    // Fill details
    public void enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        postalCodeInput.sendKeys(postalCode);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void clickFinishButton() {
        finishButton.click();
    }

    // Get displayed prices
    public double getDisplayedSubtotal() {
        String text = subtotalLabel.getText().replace("Item total: $", "").trim();
        return Double.parseDouble(text);
    }

    public double getDisplayedTax() {
        String text = taxLabel.getText().replace("Tax: $", "").trim();
        return Double.parseDouble(text);
    }

    public double getDisplayedTotal() {
        String text = totalLabel.getText().replace("Total: $", "").trim();
        return Double.parseDouble(text);
    }
}
