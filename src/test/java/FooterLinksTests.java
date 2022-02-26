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
     *
     * Expected results:
     * 4. Verify that 'Korisnicka podrska' URL is displayed.
     */
    @Test
    public void goToFooterLink() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs'");
            FooterLinksPage footerLinksPage = new FooterLinksPage(driver);

            print("2. Click on 'Korisnicka podrska link in footer.");
            // assert 'Korisnicka podrska' link is present, scroll down the Home page (alignToTop argument is set to false
            // because the navigation bar was covering some links, and they weren't clickable on laptop), verify links URL
            footerLinksPage.selectFooterLink(Strings.KORISNICKA_PODRSKA_FOOTER_TITLE, Strings.KORISNICKA_PODRSKA_URL);

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
            // because the navigation bar was covering some links, and they weren't clickable on laptop), verify links URL
            footerLinksPage.clickOnKorisnickaPodrskaButton();
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
        }finally {
            driver.quit();
        }
    }




}


