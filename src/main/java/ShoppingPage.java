import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingPage extends BasePage{


    @FindBy(xpath = "//button[@id = 'nb_addToCartButton']")
    WebElement dodajUKorpuButton;

    @FindBy(xpath = "//div[@class = 'product-favorite favorite product-wishlist-add']")
    WebElement sacuvajteUListiZelja;

   //TODO da li ovako ili kao tekst jer ne klikce
    @FindBy(xpath = "//button[@data-btn-text-no-state='Nema na stanju']")
    WebElement nemaNaStanjuTitle;




    public ShoppingPage(ChromeDriver driver) {
        super(driver);
    }


    public void clickAddToCartButton() {
        dodajUKorpuButton.click();
    }


    public Integer getNumberFromShoppingCartIcon() {
        String number = shoppingCartBadgeNumber.getText();
        return Integer.valueOf(number);
    }


    public void chooseProductSize(String productSize) {
        List<WebElement> availableProductSizes = driver.findElements(By.xpath("//ul[@class = 'product-attributes list-inline product-attributes-two-sizes']//li[@class='ease  ']//div"));
        for(WebElement selectSize : availableProductSizes) {
            print(selectSize.getText());
            if(selectSize.getText().equals(productSize))
                selectSize.click();

            assert availableProductSizes.size() != 0 : "There are no available sizes.";
        }
    }
}
