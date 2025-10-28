package actions;

import pages.SiteFactory;
import utils.LoggerUtils;
import org.apache.logging.log4j.Logger;

public class InventoryActions extends BaseActions {

    private Logger log = LoggerUtils.getLogger(InventoryActions.class);

    public InventoryActions(SiteFactory siteFactory) {
        super(siteFactory);
    }

    public InventoryActions selectSort(String sortOption) {
        siteFactory.getInventoryPage().selectSort(sortOption);
        log.info("Selected sort option: {}", sortOption);
        return this;
    }

    public InventoryActions verifyAscendingOrder() {
        siteFactory.getInventoryPage().verifyAscendingOrder();
        log.info("Verified items are sorted in ascending order");
        return this;
    }
}
