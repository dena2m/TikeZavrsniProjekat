import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

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

            //click search icon, enter text,switching pages by clicking 'next page' button until product is found
            searchPage.searchProductByNameFromSearchResultList("patike", "ADIDAS Patike SUPERTURF ADVENTURE SW ");
            ShoppingPage shoppingPage = new ShoppingPage(driver);
            shoppingPage.chooseProductSize("40");
            shoppingPage.clickAddToCartButton();

            waitForElement(shoppingPage.shoppingCartBadgeNumber);
            Integer currentNumber = shoppingPage.getNumberFromShoppingCartIcon();
            assert currentNumber.equals(1) : "Error: Wrong number of products. Expected: 1. Actual: " + currentNumber;


        }
        finally {
            driver.quit();
        }
    }







}
