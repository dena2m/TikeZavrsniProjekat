import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryItemPage extends BasePage{

    @FindBy(xpath = "//button[@id = 'nb_addToCartButton']")
    WebElement dodajUKorpuButton;

    @FindBy(xpath = "//div[@class = 'product-favorite favorite product-wishlist-add']")
    WebElement sacuvajteUListiZelja;

    //TODO da li ovako ili kao tekst jer ne klikce
    @FindBy(xpath = "//button[@data-btn-text-no-state='Nema na stanju']")
    WebElement nemaNaStanjuTitle;

    @FindBy(xpath = "//li[@class='ease  ']//div")
    WebElement priductSizes;


    public InventoryItemPage(ChromeDriver driver) {
        super(driver);
    }


    public void clickAddToCartButton() {
        dodajUKorpuButton.click();
    }


    public Integer getNumberFromShoppingCartIcon() {
        String number = shoppingCartBadgeNumber.getText();
        return Integer.valueOf(number);
    }


    public boolean isShoppingCartBadgeNumberPresent() {
        return isElementPresent(shoppingCartBadgeNumber);
    }


    public ShoppingCartPage clickOnShoppingCartIcon() {
        shoppingCartIcon.click();
        return new ShoppingCartPage(driver);
    }


    public void chooseItemSize(String itemSize) {
        List<WebElement> availableItemSizes = driver.findElements(By.xpath(Strings.ITEM_SIZES_LIST_XPATH));
        for(WebElement selectSize : availableItemSizes) {
            if(selectSize.getText().equals(itemSize))
                selectSize.click();

            assert availableItemSizes.size() != 0 : "There are no available sizes.";
        }
    }
}
