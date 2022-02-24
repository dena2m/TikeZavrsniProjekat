import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SocialNetworksTest extends BaseTests {

    @Test
    public void conectToSocialNetworks() {

        ChromeDriver driver = openChromeDriver();

        try {
            BasePage basePage = new BasePage(driver);
            basePage.connectToFacebook();
            basePage.connectToInstagram();
            basePage.connectToYouTube();
        }finally {
            driver.quit();
        }
    }



}
