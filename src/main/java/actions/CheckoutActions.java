package actions;

import org.testng.Assert;
import pages.SiteFactory;
import utils.AppStrings;
import utils.LogHelper;
import utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import java.util.Map;

public class CheckoutActions extends BaseActions {

    private Logger log = LoggerUtils.getLogger(CheckoutActions.class);
    private Map<String, Double> cartProducts;

    public CheckoutActions(SiteFactory siteFactory) {
        super(siteFactory);
    }

    public CheckoutActions withCartProducts(Map<String, Double> cartProducts) {
        this.cartProducts = cartProducts;
        return this;
    }

    public CheckoutActions fillUserDetails(String firstName, String lastName, String postalCode) {
        siteFactory.getCheckoutPage().enterFirstName(firstName);
        siteFactory.getCheckoutPage().enterLastName(lastName);
        siteFactory.getCheckoutPage().enterPostalCode(postalCode);
        siteFactory.getCheckoutPage().clickContinueButton();
        log.info("Filled checkout details: {} {} {}", firstName, lastName, postalCode);
        return this;
    }

    public CheckoutActions verifySubtotalTaxTotal(Map<String, Double> cartProducts) {
        //1: Read displayed values from the PAGE
        double displayedSubtotal = siteFactory.getCheckoutPage().getDisplayedSubtotal();
        double displayedTax = siteFactory.getCheckoutPage().getDisplayedTax();
        double displayedTotal = siteFactory.getCheckoutPage().getDisplayedTotal();

        // Step 2: Calculate expected subtotal based on cart items
        double expectedSubtotal = cartProducts.values().stream().mapToDouble(Double::doubleValue).sum();
        double expectedTotal = expectedSubtotal + displayedTax; // total = subtotal + tax
        log.info("Expected Subtotal: {}, Displayed Subtotal: {}", expectedSubtotal, displayedSubtotal);
        log.info("Expected Total: {}, Displayed Total: {}, Displayed Tax: {}", expectedTotal, displayedTotal, displayedTax);
        Assert.assertEquals(displayedSubtotal, expectedSubtotal, 0.01, AppStrings.CheckoutSubTotalMsg);
        Assert.assertEquals(displayedTotal, expectedTotal, 0.01, AppStrings.TotalMismatch);
        log.info("Verified subtotal, tax, and total successfully!");
        return this;
    }

    public CheckoutActions finishCheckout() {
        siteFactory.getCheckoutPage().clickFinishButton();
        log.info("Finished checkout");
        return this;

    }
}

