import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FooterLinksTests extends BaseTests {

    /**
     * Click on any footer information link e.g. 'Korisnicka podrska'
     * Steps:
     * 1. Go to: "https://www.tike.rs/".
     * 2. Click on footer link.
     * 3. Go to Home page by clicking header logo 'Tike'.
     * 4. Repeat steps 2-5 for
     *
     * Expected results:
     * 2. Verify that 'Korisnicka podrska' URL is displayed.
     */

    @Test
    public void selectLinkFromFooterLinksList() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("Go to: 'https://www.tike.rs'");
            BasePage basePage = new BasePage(driver);

            basePage.selectFooterLink(Strings.O_NAMA_FOOTER_TITLE, Strings.O_NAMA_URL);

            basePage.selectFooterLink(Strings.REKLAMACIJE_FOOTER_TITLE, Strings.REKLAMACIJE_URL);

            basePage.selectFooterLink(Strings.KAKO_KUPITI_FOOTER_TITLE, Strings.KAKO_KUPITI_URL);

            basePage.selectFooterLink(Strings.NACIN_ISPORUKE_FOOTER_TITLE, Strings.NACIN_ISPORUKE_URL);

            basePage.selectFooterLink(Strings.PROVERI_STATUS_PORUDZBINE_FOOTER_TITLE, Strings.PROVERI_STATUS_PORUDZBINE_URL);

            print("Click on Home page header logo 'Tike'");
            HomePage homePage = new HomePage(driver);
            homePage.clickHeaderLogo();
        }finally {
            driver.quit();
        }
    }








}


