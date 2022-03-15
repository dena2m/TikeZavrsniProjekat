import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SearchTests extends BaseTests{


    /**
     * Search by keyword: 'patike', go through several pages, find a
     * specific item: 'NIKE Patike Waffle One' and buy it
     *
     * Steps:
     * 1. Go to: 'https://www.tike.rs/'
     * 2. Click on search icon
     * 3. Enter 'patike' into search field
     * 4. Find and click 'NIKE Patike Waffle One' on search list
     *
     *
     * Expected results:
     * 3. Verify that search result list isn't empty
     * 4. Verify that correct item page is displayed
     */

    @Test
    public void searchByItemTypeAndName() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs/'");
            InventoryPage inventoryPage = new InventoryPage(driver);
            print("2. Click on search icon");
            print("3. Enter 'patike' into search field");
            inventoryPage.searchItemTypeByKeyword("patike");

            print("4. Find and click 'NIKE Patike Waffle One");
            print("4. Verify that correct item page is displayed");
            inventoryPage.findItemByName(Strings.NIKE_PATIKE_TITLE);
        }
        finally {
            // driver.quit();
        }
    }


    /**
     * Enter invalid item in search field and verify the alert message
     *
     * Steps:
     * 1. Go to: 'https://www.tike.rs/'
     * 2. Click on search icon
     * 3. Enter 'ljute papricice' into search field
     *
     * Expected results:
     * 3. Verify that search URL is displayed
     * 3. Verify that alert message: 'Za izabrane kriterijume nisu pronađeni proizvodi! is displayed'
     */

    @Test
    public void searchForInvalidItem() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs/'");
            InventoryPage inventoryPage = new InventoryPage(driver);

            print("2. Click on search icon");
            inventoryPage.clickSearchIcon();

            print("3. Enter 'ljute papricice' into search field");
            inventoryPage.enterTextIntoSearchField("ljute papricice");


            print("3. Verify that search URL is displayed");
            String actualUrl = driver.getCurrentUrl();
            assertUrl(actualUrl, Strings.SEARCH_LJUTE_PAPRICICE_URL);

            print("3. Verify that alert message: 'Za izabrane kriterijume nisu pronađeni proizvodi!' iz displayed");
            String currentMessage = driver.findElementByXPath(Strings.SEARCH_ALERT_MESSAGE_XPATH).getText();
            assert currentMessage.equals(Strings.SEARCH_ALERT_MESSAGE_TEXT) : "Error: Wrong message. Expected: " +
                    Strings.SEARCH_ALERT_MESSAGE_TEXT + ". Actual: " + currentMessage;

        }finally {
            // driver.quit();
        }
    }



}
