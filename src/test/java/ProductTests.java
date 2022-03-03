import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.Callable;

public class ProductTests extends BaseTests{


    @Test
     public void categoriesFilterTest() {
        ChromeDriver driver = openChromeDriver();

        try {
         ProductsPage productsPage = new ProductsPage(driver);
         productsPage.selectKategorijeFilter("Dukserica");
         } finally {
            driver.quit();
    }
}


    @Test
      public void genderFilterTest() {
    ChromeDriver driver = openChromeDriver();

     try {
         HomePage homePage = new HomePage(driver);
         homePage.openNavBarCategory(Strings.ODECA_NAVBAR_TITLE, Strings.ODECA_URL);
         ProductsPage productsPage = new ProductsPage(driver);
         productsPage.selectGenderFromFilterList(Strings.WOMEN_CHECKBOX_TITLE);
     }finally {
        // driver.quit();
     }
}



//proba svih filtera
    @Test
    public void allFiltersTest() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("Go to: 'https://www.tike.rs/'");
            HomePage homePage = new HomePage(driver);
            print("Select 'Odeca' category.");
            sleep(2);
            ProductsPage productsPage = homePage.openNavBarCategory(Strings.ODECA_NAVBAR_TITLE, Strings.ODECA_URL);
            print("Select 'Dukserica' from list of categories.");
            sleep(2);
            productsPage.selectKategorijeFilter("Dukserica");
            print("Click on 'Za muškarce' checkbox.");
            sleep(2);
            productsPage.selectGenderFromFilterList(Strings.MEN_CHECKBOX_TITLE);
            print("Click on 'ADIDAS' checkbox");
            sleep(2);
            productsPage.selectBrandFromFilterList(Strings.ADIDAS_CHECKBOX_TITLE);
            print("Click on 'XL' size checkbox");
            sleep(2);
            productsPage.selectSizeFromFilterList(Strings.XL_SIZE_CHECKBOX_TITLE);
            print("Select 'Najjeftinije prvo' from 'SORTIRAJ' dropdown.");
            sleep(2);
            productsPage.selectDropDownFilter(productsPage.sortirajDropDown, Strings.LOW_TO_HIGH);
            sleep(2);
            productsPage.printAllItemsFromList();
            }finally {
            //driver.quit();
        }
    }






}
