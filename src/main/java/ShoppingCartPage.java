import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartPage extends BasePage{


    @FindBy(xpath = "//div[@class = 'cart-col-inner-wrapper']/a")
    WebElement izbrišiteFromCartButton;

    @FindBy(className = "bootbox-body")
    WebElement removeFromCartModalText;

    @FindBy(xpath = "//button[@class = 'btn btn-primary']")
    WebElement potvrdiRemoveFromCartModalButton;

    @FindBy(xpath = "//div[@class = class= 'alert alert-danger']")
    WebElement emptyCartMessage;

    @FindBy(xpath = "//a[@class = 'btn btn-success']")
    WebElement nastaviKupovinuButton;

    @FindBy(xpath = "//td[@colspan='2']")
    WebElement ukupnaCenaCartTabela;

    @FindBy(className = "product-item-prices")
    WebElement cenaItemaCartTabela;

    @FindBy(xpath = "//td//div[@class='product-item-title text-left']/a")
    WebElement cartItemListTabela;

    @FindBy(id = "carierId_2")
    WebElement regularnaIsporukaRadioButton;

    @FindBy(id = "typepayment_post")
    WebElement placanjeGotovinomRadioButton;

    @FindBy(id = "onepage-product-price-value")
    WebElement ukupnoZaPlacanje;

    @FindBy(id = "cart_onepage_terms_of_use")
    WebElement usloviKoriscenjaIProdajeCheckBox;

    @FindBy(id = "submit_order_one_page")
    WebElement potvrdiKupovinuButton;

    @FindBy(className = "recaptcha-checkbox-checkmark")
    WebElement recaptchaCheckBox;

    @FindBy(xpath = "//iframe[@title = 'reCAPTCHA']")
    WebElement iFrame;




    public ShoppingCartPage(ChromeDriver driver) {
        super(driver);
    }



    public List<WebElement> getAllCartItems() {
        return driver.findElements(By.xpath(Strings.SHOPPING_CART_ITEM_LIST_XPATH));
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

    public ShoppingCartPage removeItemFromCart(String name) {
        WebElement cartItem = findCartItemByName(name);
        assert cartItem != null : "Could not find item: " + name;
        cartItem.findElement(By.xpath("//div[@class = 'cart-col-inner-wrapper']/a")).click();
        return this;
    }

    public ShoppingCartPage clickVerifyRemovingItemFromCartButton() {
        potvrdiRemoveFromCartModalButton.click();
        return new ShoppingCartPage(driver);
    }

    public ShoppingCartPage clickRemoveFromCartButton() {
        izbrišiteFromCartButton.click();
        return new ShoppingCartPage(driver);
    }

    public InventoryPage clickContinueShoppingButton() {
        nastaviKupovinuButton.click();
        return new InventoryPage(driver);
    }






}
