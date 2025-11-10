package tests;
import enums.UserDetails;
import enums.Users;
import listeners.TestListeners;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.BaseTest;
import utils.AppStrings;
import utils.LogHelper;
import utils.LoggerUtils;
import utils.ReportManager;

@Listeners(TestListeners.class)
public class CheckoutTest extends BaseTest {
    private static final Logger log = LoggerUtils.getLogger(CheckoutTest.class);
    @Test(groups = {"Functional"}, description = "Verify complete flow for a valid user")
    public void cartToCheckoutFlowTest() {
        ReportManager.getTest().assignCategory("Functional");
        getBaseActions()
                .getLoginActions()
                .loginToApp(Users.STANDARD_USER.getUsername(), Users.STANDARD_USER.getPassword())
                .getCartActions()
                .addItem(AppStrings.SECOND_PRODUCT)
                .clickCartBadge()
                .proceedToCheckout()
                .getCheckoutActions()
                .fillUserDetails(UserDetails.DETAILS.getFirstname(), UserDetails.DETAILS.getLastname(), UserDetails.DETAILS.getPinCode())
                .verifySubtotalTaxTotal(getBaseActions().getCartActions().getCartProducts())
                .finishCheckout()
                .getCheckoutCompleteActions()
                .clickBackHome()
                .clickBurgerMenuAndLogout();
        log.info("Completed Checkout flow for user: {} " ,Users.STANDARD_USER.getUsername() );
    }
}

