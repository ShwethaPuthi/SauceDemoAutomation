package tests;
import base.BaseTest;
import enums.UserDetails;
import enums.Users;
import listeners.TestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AppStrings;
import utils.LoggerUtils;
import org.apache.logging.log4j.Logger;

@Listeners(TestListeners.class)
public class CartTest extends BaseTest {

    private static final Logger log = LoggerUtils.getLogger(CartTest.class);

    @Test
    public void CartFlowTest() {
        loginActions.loginToApp(Users.STANDARD_USER.getUsername(), Users.STANDARD_USER.getPassword());

        cartActions
                .addItem(AppStrings.FIRST_PRODUCT)
                .addItem(AppStrings.SECOND_PRODUCT)
                .verifyCartBadgeCount(2)
                .removeItem(AppStrings.FIRST_PRODUCT)
                .verifyCartBadgeCount(1)
                .clickCartBadge()
                .proceedToCheckout();

        checkoutActions
                .fillUserDetails(UserDetails.DETAILS.getFirstname(), UserDetails.DETAILS.getLastname(), UserDetails.DETAILS.getPinCode())
                .verifySubtotalTaxTotal(cartActions.getCartProducts())
                .finishCheckout();

        log.info("Completed Cart to Checkout flow for user: {}", Users.STANDARD_USER.getUsername());

    }
}

