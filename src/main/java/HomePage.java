import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{


        @FindBy(xpath = "//a[@href = 'https://www.tike.rs/patike']")
        WebElement patikeButton;

        @FindBy(xpath = "//a[@href = 'https://www.tike.rs/odeca']")
        WebElement odecaButton;

        @FindBy(xpath = "//a[@href = 'https://www.tike.rs/brendovi']")
        WebElement brendoviButton;

        @FindBy(xpath = "//a[@href = 'https://www.tike.rs/proizvodi/hot-sale']")
        WebElement saleButton;

        @FindBy(xpath = "//a[@href = 'https://www.tike.rs/magazin']")
        WebElement blogButton;


        public HomePage(ChromeDriver driver) {
        super(driver);
    }




        public InventoryPage openNavBarCategory(String categoryTitle, String categoryUrl) {
            List<WebElement> allCategories = driver.findElements(By.xpath(Strings.NAVBAR_CATEGORY_LIST_XPATH));
            for(WebElement category : allCategories) {
                if(category.getAttribute("title").equals(categoryTitle)){
                    category.click();
                    String actualUrl = driver.getCurrentUrl();
                    assertUrl(actualUrl, categoryUrl);
                    print("Selected category: " + categoryTitle + ".");
                    return new InventoryPage(driver);
                }
            }
            assert false : "Error: Navbar category " + categoryTitle + " not found.";
            return null;
        }






    }
