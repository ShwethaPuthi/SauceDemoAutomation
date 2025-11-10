package tests;

import base.BaseTest;
import enums.UserDetails;
import enums.Users;
import listeners.TestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AppStrings;
import utils.LogHelper;
import utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import utils.ReportManager;

@Listeners(TestListeners.class)
public class CartTest extends BaseTest {

    private static final Logger log = LoggerUtils.getLogger(CartTest.class);

    @Test(groups = {"Functional"}, description = "Verify adding and removing items from the cart, and checkout initiation")
    public void CartFlowTest() {
        ReportManager.getTest().assignCategory("Functional");
        getBaseActions()
                .getLoginActions()
                .loginToApp(Users.STANDARD_USER.getUsername(), Users.STANDARD_USER.getPassword())
                .getCartActions()
                .addItem(AppStrings.FIRST_PRODUCT)
                .addItem(AppStrings.SECOND_PRODUCT)
                .verifyCartBadgeCount(2)
                .removeItem(AppStrings.FIRST_PRODUCT)
                .verifyCartBadgeCount(1)
                .clickCartBadge()
                .proceedToCheckout()
                .getCheckoutActions()
                .fillUserDetails(UserDetails.DETAILS.getFirstname(), UserDetails.DETAILS.getLastname(), UserDetails.DETAILS.getPinCode())
                .verifySubtotalTaxTotal(getBaseActions().getCartActions().getCartProducts())
                .finishCheckout()
                .getCheckoutCompleteActions()
                .clickBackHome()
                .clickBurgerMenuAndLogout();
        log.info("Completed Cart to Checkout flow for user: {}  " ,Users.STANDARD_USER.getUsername());
    }
}

