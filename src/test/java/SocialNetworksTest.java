import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SocialNetworksTest extends BaseTests {

    @Test
    public void conectToSocialNetworks() {
        ChromeDriver driver = openChromeDriver();

        try {
            SocialNetworkLinksPage socialNetworkLinksPage = new SocialNetworkLinksPage(driver);
            socialNetworkLinksPage.connectToFacebook();
            socialNetworkLinksPage.connectToInstagram();
            socialNetworkLinksPage.connectToYouTube();
        }finally {
            driver.quit();
        }
    }



}
