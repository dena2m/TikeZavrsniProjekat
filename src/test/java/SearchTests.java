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
            searchPage.searchProductByName("patike", "ADIDAS Patike SUPERTURF ADVENTURE SW ");
        }
        finally {
            driver.quit();
        }
    }







}
