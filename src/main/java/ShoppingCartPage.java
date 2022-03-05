import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage{


    @FindBy(className = "cart-col-inner-wrapper")
    WebElement izbrišiteButton;

    @FindBy(xpath = "//td[@colspan='2']")
    WebElement ukupnaCena;

    @FindBy(className = "product-item-prices")
    WebElement cartItemPrice;






    public ShoppingCartPage(ChromeDriver driver) {
        super(driver);
    }






}
