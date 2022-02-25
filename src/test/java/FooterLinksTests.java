import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FooterLinksTests extends BaseTests {


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


