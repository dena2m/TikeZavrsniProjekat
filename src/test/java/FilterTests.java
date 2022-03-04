import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FilterTests extends BaseTests {


    //proba svih filtera
    @Test
    public void allFiltersTest() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("Go to: 'https://www.tike.rs/'");
            HomePage homePage = new HomePage(driver);
            print("Select 'Odeca' category.");

            InventoryPage inventoryPage = homePage.openNavBarCategory(Strings.ODECA_NAVBAR_TITLE, Strings.ODECA_URL);
            print("Select 'Dukserica' from list of categories.");

            inventoryPage.selectKategorijeFilter("Dukserica");
            print("Click on 'Za muškarce' checkbox.");

            inventoryPage.selectGenderFromFilterList(Strings.MEN_CHECKBOX_TITLE);
            print("Click on 'ADIDAS' checkbox");

            inventoryPage.selectBrandFromFilterList(Strings.ADIDAS_CHECKBOX_TITLE);
            print("Click on 'XL' size checkbox");
           // sleep(3);
            inventoryPage.selectSizeFromFilterList(Strings.XL_SIZE_CHECKBOX_TITLE);
            print("Select 'Najjeftinije prvo' from 'SORTIRAJ' dropdown.");
           // sleep(3);
            inventoryPage.selectDropDownFilter(inventoryPage.sortirajDropDown, Strings.LOW_TO_HIGH);

            inventoryPage.printAllItemsFromList();
        } finally {
            driver.quit();
        }
    }
}

