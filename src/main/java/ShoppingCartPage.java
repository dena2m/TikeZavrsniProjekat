import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartPage extends BasePage{



    @FindBy(className = "bootbox-body")
    WebElement removeFromCartModalText;

    @FindBy(xpath = "//button[@class = 'btn btn-primary']")
    WebElement potvrdiRemoveFromCartModalButton;

    @FindBy(xpath = "//a[@class = 'btn btn-success']")
    WebElement nastaviKupovinuButton;

    @FindBy(xpath = "//tr[@class='cart-total']//td[@class='text-right total-price']")
    WebElement ukupnaCenaTabela;

    @FindBy(xpath = "//div[@class='col-xs-12 col-sm-6 carrier-wrapper ncx-v2 cart-step']//ul//li[2]//div[@class='delivery-option-name']")
    WebElement regularnaIsporukaRadioButton;

    @FindBy(xpath = "//div[@class='col-xs-12 col-sm-6 cart-payment-wrapper ncx-v2 cart-step']//ul//li[2]//div[@class='delivery-option-name']")
    WebElement gotovinaRadioButton;

    @FindBy(xpath = "//iframe[@title = 'reCAPTCHA']")
    WebElement iFrame;


    public ShoppingCartPage(ChromeDriver driver) {
        super(driver);
    }



    public List<WebElement> getAllCartItems() {
        return driver.findElements(By.xpath(Strings.SHOPPING_CART_ITEM_LIST_XPATH));
    }


    public List<WebElement> getAllCartItemPrices(){
        return driver.findElements(By.xpath(Strings.SHOPPING_CART_ITEM_PRICES_LIST_XPATH));
    }


    public WebElement findCartItemByName(String name) {
        List<WebElement> cartItems = getAllCartItems();
        for(WebElement cartItem : cartItems) {
            String currentName = cartItem.getText();
            if(currentName.equals(name)) {
                print(currentName);
                return cartItem;
            }
        }return null;
    }


    public void removeItemFromCart(String name) {
        WebElement cartItem = findCartItemByName(name);
        assert cartItem != null : "Could not find item: " + name;
        cartItem.findElement(By.xpath(Strings.REMOVE_FROM_CART_BUTTON_XPATH)).click();
    }


    public void clickVerifyRemovingItemFromCartButton() {
        waitForElementToBeClickable(potvrdiRemoveFromCartModalButton);
        potvrdiRemoveFromCartModalButton.click();
    }


    public void clickContinueShoppingButton() {
        nastaviKupovinuButton.click();
    }


    public void clickReCaptcha() {
        driver.switchTo().frame(iFrame);
        iFrame.click();
        driver.switchTo().defaultContent();
    }


    public void printListOfAllCartItems() {
        List<WebElement> allItems = getAllCartItems();
        List<WebElement> allItemPrices = getAllCartItemPrices();
        print("Number of items: " + allItems.size());

        for(int i = 0; i < allItems.size(); i++) {
            String currentName = allItems.get(i).getText();
            String currentPrice = allItemPrices.get(i).getText();
            int index = i+1;
            print(index + ". " + currentName + ": " + currentPrice);
        }
    }


    public void clickRegularnaIsporukaRadioButton() {
        regularnaIsporukaRadioButton.click();
    }


    public void clickGotovinaRadioButton() {
        gotovinaRadioButton.click();
    }


    public void printUkupnoZaPlacanje() {
        print("Ukupno za placanje: " + ukupnaCenaTabela.getText());
    }

}
