import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTests {

    /**
     * Click on a category button from navigation bar
     * Steps:
     * 1. Go to: "https://www.tike.rs".
     * 2. On navigation bar click on a category e.g. 'Odeca'.
     *
     * Expected results:
     * 2. Verify that you are redirected to correct category URL: "https://www.tike.rs/odeca".
     */

    @Test
    public void clickCategoryPageFromNavbar() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs'.");
            HomePage homePage = new HomePage(driver);
            print("2. On navigation bar click on a category e.g. 'Odeca'");
            homePage.openNavBarCategory(Strings.ODECA_NAVBAR_TITLE, Strings.ODECA_URL);
        }finally {
            driver.quit();
        }
    }














}

