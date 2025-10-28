package enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public enum BrowserType {
    CHROME,
    EDGE,
    FIREFOX,
    SAFARI;

    public static BrowserType fromString(String browserName) {
        try {
            return BrowserType.valueOf(browserName.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unsupported browser: " + browserName);
        }
    }

    public WebDriver createDriver() {
        switch (this) {
            case CHROME:
                return new ChromeDriver();
            case EDGE:
                return new EdgeDriver();
            case FIREFOX:
                return new FirefoxDriver();
            case SAFARI:
                return new SafariDriver();
            default:
                throw new IllegalArgumentException("Unsupported Browser: " + this);
        }
    }
}