import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SocialNetworksTest extends BaseTests {

    /**
     * Click on Facebook/Instagram/YouTube button in footer, switch to new tab and return to Home page
     *
     * Steps:
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
     * 5. Verify that Home page is displayed.
     */
    @Test
    public void connectToSocialNetworks() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("Go to: 'https://www.tike.rs/'.");
            BasePage basePage = new BasePage(driver);

            //switch to Facebook tab, assert Tike/Facebook URL, close Facebook tab, switch to Home page tab
            basePage.openFacebookPage();

            print("Verify that Home page is displayed.");
            String actualUrl = driver.getCurrentUrl();
            assertUrl(actualUrl, Strings.HOME_PAGE_URL);


            //switch to Instagram tab, assert Tike/Instagram URL close Instagram tab, switch to Home page tab
            basePage.openInstagramPage();

            print("Verify that Home page is displayed.");
            String actualUrl1 = driver.getCurrentUrl();
            assertUrl(actualUrl1, Strings.HOME_PAGE_URL);


            //switch to YouTube tab, assert Tike/YouTube channel, close YouTube tab, switch to Home page tab
            basePage.openYouTubeChannel();

            print("Verify that Home page is displayed.");
            String actualUrl2 = driver.getCurrentUrl();
            assertUrl(actualUrl2, Strings.HOME_PAGE_URL);
        }finally {
            driver.quit();
        }
    }

}
