package tests;
import base.BaseTest;
import enums.SortOptions;
import enums.Users;
import listeners.TestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListeners.class)
public class InventoryTest extends BaseTest {

    @Test
    public void inventoryFlowTest() {
        getBaseActions()
                .getLoginActions()
                    .loginToApp(Users.STANDARD_USER.getUsername(), Users.STANDARD_USER.getPassword())
                .getInventoryActions()
                    .selectSort(SortOptions.PRICE_LOW_TO_HIGH.getOption())
                    .verifyAscendingOrder();

        log.info("Inventory sorting test completed successfully for user: {}", Users.STANDARD_USER.getUsername());

    }
}
