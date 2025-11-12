package actions;

import org.testng.Assert;
import pages.SiteFactory;
import utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import utils.ReportManager;

import java.util.List;

public class InventoryActions extends BaseActions {

    private Logger log = LoggerUtils.getLogger(InventoryActions.class);

    public InventoryActions(SiteFactory siteFactory) {
        super(siteFactory);
    }

    public InventoryActions selectSort(String sortOption) {
        siteFactory.getInventoryPage().selectSort(sortOption);
        log.info("Selected sort option: {}" , sortOption);
        ReportManager.logInfo("Selected sort option: "+sortOption);
        return this;
    }

    public InventoryActions verifyAscendingOrder() {
        List<Double> prices = siteFactory.getInventoryPage().getItemPrices();
        boolean sorted = siteFactory.getInventoryPage().isSortedAscending(prices);
        log.info("Validating ascending order for prices: {}" ,prices);
        ReportManager.logInfo("Validating ascending order for prices: " +prices );
        Assert.assertTrue(sorted, "Prices are NOT sorted in ascending order!");
        log.info("Verified items are sorted in ascending order");
        ReportManager.logInfo("Verified items are sorted in ascending order");
        return this;
    }
}
