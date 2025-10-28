package tests;
import base.BaseTest;
import listeners.TestListeners;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AppStrings;
import utils.ExcelUtils;

@Listeners(TestListeners.class)
public class LoginTest extends BaseTest {

    // Valid Login Test
    @Test(dataProvider = "validData", dataProviderClass = ExcelUtils.class)
    public void validLoginTest(String username, String password, String expectedResult) {
        loginActions
                .loginToApp(username, password)
                .verifySuccessfulLogin();
        Assert.assertEquals(driver.getCurrentUrl(), AppStrings.INVENTORY_PAGE_URL, "Login failed!");
    }

    // Invalid Login Test
    @Test(dataProvider = "invalidData", dataProviderClass = ExcelUtils.class)
    public void invalidLoginTest(String username, String password, String expectedResult) {
        loginActions
                .loginToApp(username, password)
                .verifyLoginFailure(AppStrings.LOGIN_ERROR_MESSAGE);
        String actualmessage=loginActions.getLoginErrorMessage();
        Assert.assertEquals(actualmessage, AppStrings.LOGIN_ERROR_MESSAGE, "Login error message mismatch!");
    }

    // Empty Login Test
    @Test(dataProvider = "emptyData", dataProviderClass = ExcelUtils.class)
    public void blankLoginTest(String username, String password, String expectedResult) {
        loginActions
                .loginToApp(username, password)
                .verifyLoginFailure(AppStrings.INVALID_LOGIN_MESSAGE);
        String actualmessage=loginActions.getLoginErrorMessage();
        Assert.assertEquals(actualmessage, AppStrings.INVALID_LOGIN_MESSAGE, "Login error message mismatch!");
    }
}
