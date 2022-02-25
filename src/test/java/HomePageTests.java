import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTests {


@Test
    public void clickCategoryPageFromNavbar() {
        ChromeDriver driver = openChromeDriver();

        try {
            HomePage homePage = new HomePage(driver);
            homePage.goToCategoryPageFromNavBar(Strings.ODECA_NAVBAR_TITLE, Strings.ODECA_URL);
        }finally {
            driver.quit();
        }
    }












}

