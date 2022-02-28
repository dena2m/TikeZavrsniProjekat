import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FooterLinksTests extends BaseTests {

    /**
     * Click on any footer information link e.g. 'Korisnicka podrska'
     * Steps:
     * 1. Go to: "https://www.tike.rs/".
     * 2. Assert 'Korisnicka podrska' link is present.
     * 3. Scroll down the Home page.
     * 4. Click on 'Korisnicka podrska' link in footer.
     * 5. Go to Home page by clicking header logo 'Tike'.
     *
     * Expected results:
     * 4. Verify that 'Korisnicka podrska' URL is displayed.
     */

    @Test
    public void selectLinkFromFooterLinksList() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs'");
            FooterLinksPage footerLinksPage = new FooterLinksPage(driver);

            print("2. Click on 'Korisnicka podrska link in footer.");
            // assert 'Korisnicka podrska' link is present, scroll down the Home page (alignToTop argument is set to false
            // because the navigation bar was covering some links, and they weren't clickable on laptop), verify links URL
            footerLinksPage.selectFooterLink(Strings.KORISNICKA_PODRSKA_FOOTER_TITLE, Strings.KORISNICKA_PODRSKA_URL);

            print("5. Go to Home page by clicking header logo 'Tike'.");
            HomePage homePage = new HomePage(driver);
            homePage.clickHeaderLogo();
        }finally {
            driver.quit();
        }
    }

    /**
     * Click on 'Korisnicki podrska' link and assert links URL
     * Steps:
     * 1. Go to: https://www.tike.rs/
     * 2. Assert 'Korisnicka podrska' link is present.
     * 3. Scroll down the Home page.
     * 4. Click on 'Korisnicka podrska' link.
     * 5. Go to Home page by clicking header logo 'Tike'.
     *
     * Expected result:
     * 4. Verify that 'Korisnicka podrska' URL is displayed.
     */

    @Test
    public void openKorisnickiServis() {

        ChromeDriver driver = openChromeDriver();
        try {
            print("1. Go to: 'https://www.tike.rs'.");
            FooterLinksPage footerLinksPage = new FooterLinksPage(driver);

            // assert 'Korisnicka podrska' link is present, scroll down the Home page (alignToTop argument is set to false
            // because the navigation bar was covering some links, and they weren't clickable on laptop), verify 'Korisnicka
            // podrska' URL
            footerLinksPage.clickOnKorisnickaPodrskaButton();

            print(" Go to Home page by clicking header logo 'Tike'.");
            HomePage homePage = new HomePage(driver);
            homePage.clickHeaderLogo();
        }finally {
            driver.quit();
        }
    }

    @Test
    public void openONama() {
        ChromeDriver driver = openChromeDriver();
        try {
            FooterLinksPage footerLinksPage = new FooterLinksPage(driver);
            footerLinksPage.clickOnONamaButton();
            footerLinksPage.waitForElement(footerLinksPage.headerLogo);
            HomePage homePage = new HomePage(driver);
            homePage.clickHeaderLogo();
        }finally {
            driver.quit();
        }
    }

    @Test
    public void openReklamacije() {
        ChromeDriver driver = openChromeDriver();
        try {
            FooterLinksPage footerLinksPage = new FooterLinksPage(driver);
            footerLinksPage.clickOnReklamacijeButton();
            HomePage homePage = new HomePage(driver);
            homePage.clickHeaderLogo();
        }finally {
            driver.quit();
        }
    }




}


