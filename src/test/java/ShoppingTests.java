import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ShoppingTests extends BaseTests{


    @Test
    public void buyProductSelectedFromSearchListTest() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs/'");
            SearchPage searchPage = new SearchPage(driver);

            //click search icon, enter text,switching pages by clicking 'next page' button until product is found
            searchPage.searchProductByNameFromSearchResultList("patike", "ADIDAS Patike SUPERTURF ADVENTURE SW ");

        }
        finally {
            driver.quit();
        }
    }
}
