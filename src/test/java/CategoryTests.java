import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CategoryTests extends BaseTests{

    /**
     * Selecting Adidas brand from brands page
     * 1. Go to: "https://www.tike.rs/".
     * 2. From navbar click on 'BRENDOVI'.
     * 3. On 'BRENDOVI' page click on 'Adidas' link title.
     *
     * Expected results:
     * 2. Verify that correct URL: "https://www.tike.rs/brendovi" is displayed.
     * 3. Verify that correct URL: "https://www.tike.rs/proizvodi/adidas" is displayed.
     */

    @Test
    public void selectBrandLinkFromBrandsPage() {
        ChromeDriver driver = openChromeDriver();

        try {
            CategoryPage categoryPage = new CategoryPage(driver);
            categoryPage.selectBrandTitleLink(Strings.ADIDAS_BRAND_TITLE, Strings.ADIDAS_URL);
        }
        finally {
            driver.quit();
        }
    }


}
