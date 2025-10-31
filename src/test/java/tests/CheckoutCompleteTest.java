package tests;

import base.BaseTest;
import enums.UserDetails;
import enums.Users;
import listeners.TestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AppStrings;
import utils.ExtentTestManager;

@Listeners(TestListeners.class)
public class CheckoutCompleteTest extends BaseTest {
    @Test(groups = {"Functional"}, description = "FAILED TEST CASE")
    public void CompleteCheckoutFlowTest(){
        ExtentTestManager.getTest().assignCategory("Functional");

        getBaseActions()
                .getLoginActions()
                .loginToApp(Users.STANDARD_USER.getUsername(), Users.STANDARD_USER.getPassword())
                .getCartActions()
                .addItem(AppStrings.FIRST_PRODUCT)
                .clickCartBadge()
                .proceedToCheckout()
                .getCheckoutActions()
                .fillUserDetails(UserDetails.DETAILS.getFirstname(),
                UserDetails.DETAILS.getLastname(),
                UserDetails.DETAILS.getPinCode()).finishCheckout()
                .getCheckoutCompleteActions()
                .clickBackHome()
                .clickBurgerMenuAndLogout();

    }
}
