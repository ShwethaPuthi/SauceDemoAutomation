package tests;
import enums.UserDetails;
import enums.Users;
import listeners.TestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.BaseTest;
import utils.AppStrings;

@Listeners(TestListeners.class)
public class CheckoutTest extends BaseTest {
    @Test
    public void cartToCheckoutFlowTest() {
        loginActions.loginToApp(Users.STANDARD_USER.getUsername(), Users.STANDARD_USER.getPassword());
        cartActions
                .addItem(AppStrings.SECOND_PRODUCT)
                .clickCartBadge()
                .proceedToCheckout();

        checkoutActions.withCartProducts(cartActions.getCartProducts())
                .fillUserDetails(UserDetails.DETAILS.getFirstname(), UserDetails.DETAILS.getLastname(), UserDetails.DETAILS.getPinCode())
                .verifySubtotalTaxTotal(cartActions.getCartProducts())
                .finishCheckout();

        log.info("Completed Checkout flow for user: {}", Users.STANDARD_USER.getUsername() );
    }
}

