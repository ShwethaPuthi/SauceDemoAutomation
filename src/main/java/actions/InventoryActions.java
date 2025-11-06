package actions;

import org.testng.Assert;
import pages.SiteFactory;
import utils.LogHelper;
import utils.LoggerUtils;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class InventoryActions extends BaseActions {

    private Logger log = LoggerUtils.getLogger(InventoryActions.class);

    public InventoryActions(SiteFactory siteFactory) {
        super(siteFactory);
    }

    public InventoryActions selectSort(String sortOption) {
        siteFactory.getInventoryPage().selectSort(sortOption);
        LogHelper.info(log,"Selected sort option: " + sortOption);
        return this;
    }

    public InventoryActions verifyAscendingOrder() {
        List<Double> prices = siteFactory.getInventoryPage().getItemPrices();
        boolean sorted = siteFactory.getInventoryPage().isSortedAscending(prices);
        LogHelper.info(log,"Validating ascending order for prices: " + prices);
        Assert.assertTrue(sorted, "Prices are NOT sorted in ascending order!");
        LogHelper.info(log," Verified items are sorted in ascending order");
        return this;
    }
}
