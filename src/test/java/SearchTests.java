import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SearchTests extends BaseTests{


    /**
     * Search by item type: 'patike', go through several pages, find a
     * specific item: 'ADIDAS Patike SUPERTURF ADVENTURE SW' and buy it
     *
     * Steps:
     * 1. Go to: 'https://www.tike.rs/'
     * 2. Click on search icon
     * 3. Enter 'patike' into search field
     * 4. Find and click 'ADIDAS Patike SUPERTURF ADVENTURE SW' on search list
     *
     *
     * Expected results:
     * 3. Verify that search result list isn't empty
     * 4. Verify that correct item page is displayed
     */

    @Test
    public void shoppingTestStartingFromSeaarchField() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs/'");
            InventoryPage inventoryPage = new InventoryPage(driver);

            print("2. Click on search icon");
            print("3. Enter 'patike' into search field");
            inventoryPage.searchItemTypeByKeyword("patike");

            print("4. Find and click 'ADIDAS Patike SUPERTURF ADVENTURE SW' on search list");
            print("4. Verify that correct item page is displayed");
            inventoryPage.findItemByName("ADIDAS Patike SUPERTURF ADVENTURE SW");
        }
        finally {
            driver.quit();
        }
    }

}
