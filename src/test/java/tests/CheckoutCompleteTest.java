package tests;

import base.BaseTest;
import enums.UserDetails;
import enums.Users;
import org.testng.annotations.Test;
import utils.AppStrings;
import utils.ExtentTestManager;

public class CheckoutCompleteTest extends BaseTest {
    @Test//(groups = {"Functional"})
    public void CompleteCheckoutFlowTest(){
        //ExtentTestManager.getTest().assignCategory("Functional");

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
