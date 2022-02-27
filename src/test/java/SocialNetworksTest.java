import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SocialNetworksTest extends BaseTests {

    /**
     * Click on Facebook/Instagram/YouTube button in footer, switch to new tab and return to Home page
     * Steps:
     *
     * 1. Go to: "https://www.tike.rs/".
     * 2. Scroll down the Home page and click on Facebook footer link.
     * 3. Switch to the new, Facebook tab.
     * 4. Close the Facebook tab.
     * 5. Switch to the Home page tab.
     * 6. Repeat steps 2-5 for the Instagram footer link.
     * 7. Repeat steps 2-5 for the YouTube link.
     *
     * Expected results:
     * 3. Verify that social networks URL is displayed in the new tab.
     */
    @Test
    public void connectToSocialNetworks() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs/'.");
            SocialNetworkLinksPage socialNetworkLinksPage = new SocialNetworkLinksPage(driver);

            print("2. Scroll down the Home page and click on Facebook footer link.");
            //switch to Facebook tab, assert Tike/Facebook URL, close Facebook tab, switch to Home page tab
            socialNetworkLinksPage.openFacebookPage();

            print("2. Scroll down the Home page and click on Instagram footer link.");
            //switch to Instagram tab, assert Tike/Instagram URL close Instagram tab, switch to Home page tab
            socialNetworkLinksPage.openInstagramPage();

            print("2. Scroll down the Home page and click on YouTube footer link.");
            //switch to YouTube tab, assert Tike/YouTube channel, close YouTube tab, switch to Home page tab
            socialNetworkLinksPage.openYouTubeChannel();
        }finally {
            driver.quit();
        }
    }



}
