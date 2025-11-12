package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ReportManager;

public class BasePage {
    protected WebDriver driver;
    protected ReportManager reportManager;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //this.reportManager = reportManager;
    }
}