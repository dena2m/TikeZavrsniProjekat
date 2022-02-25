import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SocialNetworksTest extends BaseTests {

    /**
     * Clicking on social network buttons in footer, switching to new tab and returning on home page
     *
     * 1. Go to: "https://www.tike.rs/".
     * 2. Scroll down the page and click on the Facebook footer link.
     * 3. Switch to the new Facebook tab.
     * 4. Close the Facebook tab.
     * 5. Switch to the Home page tab.
     * 6. Repeat steps 2-5 for the Instagram footer link.
     * 7. Repeat steps 2-5 for the YouTube link.
     *
     * Expected results:
     * 3. Verify that social networks url is displayed in the new tab.
     */
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
