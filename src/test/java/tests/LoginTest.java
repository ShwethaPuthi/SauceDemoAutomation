package tests;

import base.BaseTest;
import listeners.TestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AppStrings;
import utils.ExcelUtils;

@Listeners(TestListeners.class)
public class LoginTest extends BaseTest {

    // Valid Login Test
    @Test(dataProvider = "validData", dataProviderClass = ExcelUtils.class)
    public void validLoginTest(String username, String password, String expectedResult) {
        //baseActions.getLoginActions().loginToApp(username, password).verifySuccessfulLogin();
        getBaseActions().getLoginActions().loginToApp(username, password).verifySuccessfulLogin();
    }

    // Invalid Login Test
    @Test(dataProvider = "invalidData", dataProviderClass = ExcelUtils.class)
    public void invalidLoginTest(String username, String password, String expectedResult) {
        //baseActions.getLoginActions().loginToApp(username, password).verifyLoginFailure(AppStrings.LOGIN_ERROR_MESSAGE);
        getBaseActions().getLoginActions().loginToApp(username, password).verifyLoginFailure(AppStrings.LOGIN_ERROR_MESSAGE);
    }

    // Empty Login Test
    @Test(dataProvider = "emptyData", dataProviderClass = ExcelUtils.class)
    public void blankLoginTest(String username, String password, String expectedResult) {
        //baseActions.getLoginActions().loginToApp(username, password).verifyLoginFailure(AppStrings.INVALID_LOGIN_MESSAGE);
        getBaseActions().getLoginActions().loginToApp(username, password).verifyLoginFailure(AppStrings.INVALID_LOGIN_MESSAGE);
    }
}
