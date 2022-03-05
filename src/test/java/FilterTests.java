import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

public class FilterTests extends BaseTests {


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

            print("2. From NavBar select 'ODECA' category");
            print("2. Verify that 'ODECA' URL is displayed");
            InventoryPage inventoryPage = homePage.openNavBarCategory(Strings.ODECA_NAVBAR_TITLE, Strings.ODECA_URL);

            print("3. Select 'Dukserica' from 'KATEGORIJE' list");
            inventoryPage.selectKategorijeFilter("Dukserica");

            //sleep(3);
            print("4. Select 'Za muškarce' from 'POL' checkbox list");
            inventoryPage.selectGenderFromFilterList(Strings.MEN_CHECKBOX_TITLE);

            //sleep(3);
            print("5. Select 'ADIDAS' from 'BREND' checkbox list");
            inventoryPage.selectBrandFromFilterList(Strings.ADIDAS_CHECKBOX_TITLE);

            sleep(3);
            print("6. Select 'XL' size from 'VELICINA' checkbox list");

            sleep(3);
            inventoryPage.selectSizeFromFilterList(Strings.XL_SIZE_CHECKBOX_TITLE);

            sleep(3);
            print("6. Verify that URL with all selected filters is displayed");
            String actualURL = driver.getCurrentUrl();
            assertUrl(actualURL, Strings.FILTER_DUKS_MUSKI_ADIDAS_XL_URL);

            ArrayList<Double> itemPricesBeforeSorting = inventoryPage.getAllItemPrices();
            print("7. Select 'Najjeftinije prvo' from 'SORTIRAJ' dropdown");
            sleep(3);
            inventoryPage.selectDropDownFilter(inventoryPage.sortirajDropDown, Strings.LOW_TO_HIGH);
            sleep(3);
            ArrayList<Double> itemPricesAfterSorting = inventoryPage.getAllItemPrices();
            sleep(3);
            Collections.sort(itemPricesBeforeSorting);

            print("7. Verify that sorting is done correctly");
            assert itemPricesAfterSorting.equals(itemPricesBeforeSorting) : "Error: Sort is not working";
        } finally {
            driver.quit();
        }
    }
}

