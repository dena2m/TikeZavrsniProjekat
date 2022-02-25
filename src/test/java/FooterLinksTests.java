import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FooterLinksTests extends BaseTests {

    /**
     * Test: Clicking on any information link on footer
     * Steps:
     * 1. Login to: "https://www.tike.rs"
     * 2. Click on an information link in footer.
     *
     * Expected results:
     * 2. Verify that you are redirected to correct url.
     */
    @Test
    public void goToFooterLink() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Login to: 'https://www.tike.rs'");
            FooterLinksPage footerLinksPage = new FooterLinksPage(driver);
            print("2. Click on an information link in footer.");
            footerLinksPage.selectFooterLink(Strings.KORISNICKA_PODRSKA_FOOTER_TITLE, Strings.KORISNICKA_PODRSKA_URL);

        }finally {
            driver.quit();
        }
    }


    @Test
    public void openKorisnickiServis() {
        ChromeDriver driver = openChromeDriver();
        try {
            FooterLinksPage footerLinksPage = new FooterLinksPage(driver);
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


