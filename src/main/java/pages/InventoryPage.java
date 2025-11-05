package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage{
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> prices;

    @FindBy(css = ".product_sort_container")
    private WebElement sortDropdown;

    public InventoryPage selectSort(String sortOption) {
        sortDropdown.sendKeys(sortOption);
        return this;
    }

    public List<Double> getItemPrices() {
        List<Double> priceList = new ArrayList<>();
        for (WebElement price : prices) {
            priceList.add(Double.parseDouble(price.getText().replace("$", "")));
        }
        return priceList;
    }

    public boolean isSortedAscending(List<Double> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) return false;
        }
        return true;
    }
}