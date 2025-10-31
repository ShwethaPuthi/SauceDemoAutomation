package tests;

import base.BaseTest;
import listeners.TestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AppStrings;
import utils.ExcelUtils;
import utils.ExtentTestManager;

@Listeners(TestListeners.class)
public class LoginTest extends BaseTest {

    // Valid Login Test
    @Test(dataProvider = "validData", dataProviderClass = ExcelUtils.class , groups = {"Smoke", "Functional"}, description = "Verify valid user login")
    public void validLoginTest(String username, String password, String expectedResult) {
        ExtentTestManager.getTest().assignCategory("Smoke", "Functional"); //Tags this test in the Extent Report under the categories Smoke and Functional.
        getBaseActions()
                .getLoginActions()
                .loginToApp(username, password)
                .verifySuccessfulLogin();
    }

    // Invalid Login Test
    @Test(dataProvider = "invalidData", dataProviderClass = ExcelUtils.class, groups = {"Sanity", "Functional"}, description = "Verify invalid login shows error message")
    public void invalidLoginTest(String username, String password, String expectedResult) {
        ExtentTestManager.getTest().assignCategory("Sanity", "Functional");
        getBaseActions()
                .getLoginActions()
                .loginToApp(username, password)
                .verifyLoginFailure(AppStrings.LOGIN_ERROR_MESSAGE);
    }

    // Empty Login Test
    @Test(dataProvider = "emptyData", dataProviderClass = ExcelUtils.class,groups = {"Sanity"}, description = "Verify empty login fields show validation error")
    public void blankLoginTest(String username, String password, String expectedResult) {
        ExtentTestManager.getTest().assignCategory("Sanity");
        getBaseActions()
                .getLoginActions()
                .loginToApp(username, password)
                .verifyLoginFailure(AppStrings.INVALID_LOGIN_MESSAGE);
    }
}
