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
            print("Go to: 'https://www.tike.rs/'");
            InventoryPage productsPage = new InventoryPage(driver);

            //click search icon, enter text into search field
            productsPage.searchItemTypeByKeyword("patike");
            // switch pages by clicking 'next page' button until product is found
            productsPage.findItemByName("NIKE Patike Dunk Low Retro EMB");

            InventoryItemPage inventoryItemPage = new InventoryItemPage(driver);
            inventoryItemPage.waitForElement(inventoryItemPage.priductSizes);
            print("Choose product size.");
            inventoryItemPage.chooseItemSize("41");

            Integer currentNumber = inventoryItemPage.getNumberFromShoppingCartIcon();

            print("Click 'add to cart'");
            inventoryItemPage.clickAddToCartButton();

            print("Get number from shopping cart badge.");
            inventoryItemPage.waitForShoppingBadgeNumber(currentNumber, 1);
            currentNumber = inventoryItemPage.getNumberFromShoppingCartIcon();
            assert currentNumber == 1 : "Error: Wrong number of products. Expected: 1. Actual: " + currentNumber;
        }
        finally {
            driver.quit();
        }
    }





}
