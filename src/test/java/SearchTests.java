import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.Callable;

public class SearchTests extends BaseTests{


    /**
     * Search by product type e.g. 'patike' and find a specific product e.g. 'ADIDAS Patike SUPERTURF ADVENTURE SW'
     * by checking all pages
     * Steps:
     * 1. Go to: 'https://www.tike.rs/'
     * 2. Click on search icon
     * 3. Enter 'patike' into search field
     * 4. Find and click 'ADIDAS Patike SUPERTURF ADVENTURE SW' on search list
     *
     * Expected results:
     * 3. Verify that search result list isn't empty
     * 4. Verify that correct product page is displayed
     */

    @Test
    public void searchTest() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs/'");
            SearchPage searchPage = new SearchPage(driver);

            //click search icon, enter text, switch pages by clicking 'next page' button until product is found
            searchPage.searchProductByNameFromSearchResultList("patike", "NIKE Patike Dunk Low Retro EMB ");
            ShoppingPage shoppingPage = new ShoppingPage(driver);
           // sleep(2);
            shoppingPage.waitForElement(shoppingPage.priductSizes);
            print("Choose product size.");
            shoppingPage.chooseProductSize("41");

            Integer currentNumber = shoppingPage.getNumberFromShoppingCartIcon();

            print("Click 'add to cart'");
            shoppingPage.clickAddToCartButton();

            print("Get number from shopping cart badge.");
            shoppingPage.waitForShoppingBadgeNumber(currentNumber, 1);
            currentNumber = shoppingPage.getNumberFromShoppingCartIcon();
            assert currentNumber == 1 : "Error: Wrong number of products. Expected: 1. Actual: " + currentNumber;
        }
        finally {
            driver.quit();
        }
    }





}
