package actions;

import pages.SiteFactory;

public class BaseActions {

    protected SiteFactory siteFactory;  // accessible in all child actions

    // Constructor to initialize siteFactory
    public BaseActions(SiteFactory siteFactory) {
        this.siteFactory = siteFactory;
    }
}
