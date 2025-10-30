package tests;
import base.BaseTest;
import enums.SortOptions;
import enums.Users;
import listeners.TestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ExtentTestManager;

@Listeners(TestListeners.class)
public class InventoryTest extends BaseTest {
    @Test(groups = {"Functional", "Smoke"}, description = "Verify inventory sorting by price (low to high)")
    public void inventoryFlowTest() {
        ExtentTestManager.getTest().assignCategory("Functional", "Smoke");

        getBaseActions()
                .getLoginActions()
                    .loginToApp(Users.STANDARD_USER.getUsername(), Users.STANDARD_USER.getPassword())
                .getInventoryActions()
                    .selectSort(SortOptions.PRICE_LOW_TO_HIGH.getOption())
                    .verifyAscendingOrder();

        log.info("Inventory sorting test completed successfully for user: {}", Users.STANDARD_USER.getUsername());

    }
}
