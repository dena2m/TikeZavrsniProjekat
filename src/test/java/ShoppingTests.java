import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ShoppingTests extends BaseTests{


    @Test
    public void buyProductSelectedFromSearchListTest() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs/'");
            InventoryPage productsPage = new InventoryPage(driver);

            //click search icon, enter text
            productsPage.searchItemTypeByKeyword("patike");
            // switching pages by clicking 'next page' button until product is found
            productsPage.findItemByName("ADIDAS Patike SUPERTURF ADVENTURE SW");
        }
        finally {
            driver.quit();
        }
    }
}
