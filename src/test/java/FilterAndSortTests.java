import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

public class FilterAndSortTests extends BaseTests {


    /**
     * Testing filters on Inventory page: 'KATEGORIJE' category filter, 'POL' gender filter,
     * 'BRENDOVI' brand filter, 'VELICINA' size filter and 'SORTIRAJ' dropdown filter
     *
     * Steps:
     * 1. Go to: 'https://www.tike.rs'
     * 2. From NavBar select 'ODECA' category
     * 3. Select 'Dukserica' from 'Kategorije' list
     * 4. Select 'Za muškarce' from 'POL' checkbox list
     * 5. Select 'ADIDAS' from 'BREND' checkbox list
     * 6. Select 'XL' size from 'VELICINA' checkbox list
     * 7. Select 'Najjeftinije prvo' from 'SORTIRAJ' dropdown
     *
     * Expected results:
     * 2. Verify that 'ODECA' URL is displayed
     * 6. Verify that URL with all selected filters is displayed
     * 7. Verify that sorting is done correctly
     */

    @Test
    public void itemFiltersAndDropDownTest() {
        ChromeDriver driver = openChromeDriver();

        try {

            print("1. Go to: 'https://www.tike.rs'");
            HomePage homePage = new HomePage(driver);

            print("2. From NavBar select 'ODECA' category and verify that the correct URL is displayed");
            InventoryPage inventoryPage = homePage.openNavBarCategory(Strings.ODECA_NAVBAR_TITLE, Strings.ODECA_URL);

            print("3. Select 'Dukserica' from 'KATEGORIJE' list");
            inventoryPage.selectKategorijeFilter("Dukserica");

            print("4. Select 'Za muškarce' from 'POL' checkbox list");
            inventoryPage.selectGenderFromFilterList(Strings.MEN_CHECKBOX_TITLE);

            print("5. Select 'ADIDAS' from 'BREND' checkbox list");
            inventoryPage.selectBrandFromFilterList(Strings.ADIDAS_CHECKBOX_TITLE);

            print("6. Select 'XL' size from 'VELICINA' checkbox list");
            inventoryPage.selectSizeFromFilterList(Strings.XL_SIZE_CHECKBOX_TITLE);

            print("6. Verify that URL with all selected filters is displayed");
            String actualURL = driver.getCurrentUrl();
            assertUrl(actualURL, Strings.FILTER_DUKS_MUSKI_ADIDAS_XL_URL);

            ArrayList<Double> itemPricesBeforeSorting = inventoryPage.getAllItemPrices(Strings.ALL_ITEM_PRICES_XPATH);

            print("7. Select 'Najjeftinije prvo' from 'SORTIRAJ' dropdown");
            inventoryPage.selectDropDownFilter(inventoryPage.sortirajDropDown, Strings.LOW_TO_HIGH);

            ArrayList<Double> itemPricesAfterSorting = inventoryPage.getAllItemPrices(Strings.ALL_ITEM_PRICES_XPATH);
            Collections.sort(itemPricesBeforeSorting);

            print("7. Verify that sorting is done correctly");
            assert itemPricesAfterSorting.equals(itemPricesBeforeSorting) : "Error: Sorting didn't work";

        } finally {
            // driver.quit();
        }
    }


}

