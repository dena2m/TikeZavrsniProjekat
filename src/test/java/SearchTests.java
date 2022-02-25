import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SearchTests extends BaseTests{

@Test
    public void searchTest() {
        ChromeDriver driver = openChromeDriver();

        try {
            SearchPage searchPage = new SearchPage(driver);
            searchPage.searchProductByName("patike", "ADIDAS Patike KF FORUM LO BENCHCMATE");
        }finally {
            driver.quit();
        }

    }
}
