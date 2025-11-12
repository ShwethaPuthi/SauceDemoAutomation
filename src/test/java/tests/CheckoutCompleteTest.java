package tests;

import base.BaseTest;
import enums.UserDetails;
import enums.Users;
import listeners.TestListeners;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AppStrings;
import utils.LoggerUtils;
import utils.ReportManager;

@Listeners(TestListeners.class)
public class CheckoutCompleteTest extends BaseTest {
    private static final Logger log = LoggerUtils.getLogger(CheckoutCompleteTest.class);
    @Test(groups = {"Functional"}, description = "FAILED TEST CASE")
    public void CompleteCheckoutFlowTest(){
        //ReportManager.getTest().assignCategory("Functional");
        getBaseActions()
                .getLoginActions()
                .loginToApp(Users.STANDARD_USER.getUsername(), Users.STANDARD_USER.getPassword())
                .getCartActions()
                .addItem(AppStrings.FIRST_PRODUCT)
                .clickCartBadge()
                .proceedToCheckout()
                .getCheckoutActions()
                .fillUserDetails(UserDetails.DETAILS.getFirstname(), UserDetails.DETAILS.getLastname(), UserDetails.DETAILS.getPinCode()).finishCheckout()
                .getCheckoutCompleteActions()
                .clickBackHome()
                .clickBurgerMenuAndLogout();
        log.info("Completed Complete flow for user: {}" ,Users.STANDARD_USER.getUsername());
        ReportManager.logInfo("Completed Complete flow for user: "+Users.STANDARD_USER.getUsername());
    }
}
