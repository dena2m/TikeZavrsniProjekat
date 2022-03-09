import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InventoryItemPage extends BasePage{

    @FindBy(xpath = "//button[@id = 'nb_addToCartButton']")
    WebElement dodajUKorpuButton;

    @FindBy(xpath = "//div[@class = 'product-favorite favorite product-wishlist-add']")
    WebElement sacuvajteUListiZelja;

    @FindBy(xpath = "//button[@data-btn-text-no-state='Nema na stanju']")
    WebElement nemaNaStanjuTitle;

    @FindBy(xpath = "//li[@class='ease  ']//div")
    WebElement itemSizes;

    @FindBy(className = "toggle-banner")
    WebElement pomocToggleBanner;

    @FindBy(xpath = "//div[@class = 'toggle-button expand']")
    WebElement closePomocToggleBanner;

    @FindBy(className = "toggle-button")
    WebElement openPomocToggleBanner;


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


    public void waitForShoppingBadgeNumber(Integer currentNumber, Integer x) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        currentNumber += x;
        String number = "" + currentNumber;
        wait.until(ExpectedConditions.textToBe(By.className("header-carthor-total"), number));
    }


    public void chooseItemSize(String itemSize) {
        waitForElement(itemSizes);
        List<WebElement> availableItemSizes = driver.findElements(By.xpath(Strings.ITEM_SIZES_LIST_XPATH));
        for(WebElement selectSize : availableItemSizes) {
            if(selectSize.getText().equals(itemSize))
                selectSize.click();

            assert availableItemSizes.size() != 0 : "There are no available sizes.";
        }
    }

    public void chooseFirstAvailableItemSize() {
        List<WebElement> availableItemSizes = driver.findElements(By.xpath(Strings.ITEM_SIZES_LIST_XPATH));
        availableItemSizes.get(0).click();
    }

    public void closeClosePomocToggleBanner() {
        isElementPresent(pomocToggleBanner);
        closePomocToggleBanner.click();
    }


}
