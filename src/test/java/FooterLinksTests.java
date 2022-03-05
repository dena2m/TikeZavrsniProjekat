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

            print("2. Click on 'O nama' footer link");
            print("2. Verify that 'O nama' URL is displayed");
            basePage.selectFooterLink(Strings.O_NAMA_FOOTER_TITLE, Strings.O_NAMA_URL);

            print("3. Click on 'Reklamacije' link");
            print("3. Verify that 'Reklamacije' link is displayed");
            basePage.selectFooterLink(Strings.REKLAMACIJE_FOOTER_TITLE, Strings.REKLAMACIJE_URL);

            print("4. Click on 'Kako kupiti' link");
            print("4. Verify that 'Kako kupiti' link is displayed");
            basePage.selectFooterLink(Strings.KAKO_KUPITI_FOOTER_TITLE, Strings.KAKO_KUPITI_URL);

            print("5. Click on 'Nacin isporuke' link");
            print("5. Verify that 'Nacin isporuke' link is displayed");
            basePage.selectFooterLink(Strings.NACIN_ISPORUKE_FOOTER_TITLE, Strings.NACIN_ISPORUKE_URL);

            print("6. Click on 'Proveri status porudzbine' link");
            print("6. Verify that 'Proveri status porudzbine' is displayed");
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

}


