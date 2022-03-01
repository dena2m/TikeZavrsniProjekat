import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ProductTests extends BaseTests{


@Test
    public void categoriesFilterTest() {
    ChromeDriver driver = openChromeDriver();

    try {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectCategoryFilter();
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
         productsPage.selectGenderFilter(Strings.WOMEN_FILTER);
     }finally {
        // driver.quit();
     }



}






}
