import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FooterLinksTests extends BaseTests {

    /**
     * Opening and verifying footer information links: 'O nama', 'Reklamacija', 'Kako kupiti',
     * 'Nacin isporuke', 'Proveri status porudzbine' and returning to Home Page
     *
     * Steps:
     * 1. Go to: 'https://www.tike.rs/'
     * 2. Click on 'O nama' footer link
     * 3. Click on 'Reklamacije' link
     * 4. Click on 'Kako kupiti' link
     * 5. Click on 'Nacin isporuke' link
     * 6. Click on 'Proveri status porudzbine' link
     * 7. Go to Home page by clicking header logo 'Tike'
     *
     * Expected results:
     * 2. Verify that 'O nama' URL is displayed
     * 3. Verify that 'Reklamacije' link is displayed
     * 4. Verify that 'Kako kupiti' link is displayed
     * 5. Verify that 'Nacin isporuke' link is displayed
     * 6. Verify that 'Proveri status porudzbine' is displayed
     * 7. Verify that Home page is displayed
     */

    @Test
    public void selectLinkFromFooterLinksList() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs/'");
            BasePage basePage = new BasePage(driver);

            print("2. Click on 'O nama' footer link and verify that the correct URL is displayed");
            basePage.selectFooterLink(Strings.O_NAMA_FOOTER_TITLE, Strings.O_NAMA_URL);

            print("3. Click on 'Reklamacije' link and verify that the correct URL is displayed");
            basePage.selectFooterLink(Strings.REKLAMACIJE_FOOTER_TITLE, Strings.REKLAMACIJE_URL);

            print("4. Click on 'Kako kupiti' link and verify that the correct URL is displayed");
            basePage.selectFooterLink(Strings.KAKO_KUPITI_FOOTER_TITLE, Strings.KAKO_KUPITI_URL);

            print("5. Click on 'Nacin isporuke' link and verify that the correct URL is displayed");
            basePage.selectFooterLink(Strings.NACIN_ISPORUKE_FOOTER_TITLE, Strings.NACIN_ISPORUKE_URL);

            print("6. Click on 'Proveri status porudzbine' link and verify that the correct URL is displayed");
            basePage.selectFooterLink(Strings.PROVERI_STATUS_PORUDZBINE_FOOTER_TITLE, Strings.PROVERI_STATUS_PORUDZBINE_URL);

            print("7. Go to Home page by clicking header logo 'Tike'");
            HomePage homePage = new HomePage(driver);
            homePage.clickHeaderLogo();

            print("7. Verify that Home page is displayed");
            String actualUrl = driver.getCurrentUrl();
            assertUrl(actualUrl, Strings.HOME_PAGE_URL);
        }finally {
            driver.quit();
        }
    }



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


