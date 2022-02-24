import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FooterLinksTests extends BaseTests {


    @Test
    public void openKorisnickiServis() {
        ChromeDriver driver = openChromeDriver();
        try {
            BasePage basePage = new BasePage(driver);
            basePage.clickOnKorisnickaPodrskaButton();
        }finally {
            driver.quit();
        }
    }

    @Test
    public void openONama() {
        ChromeDriver driver = openChromeDriver();
        try {
            BasePage basePage = new BasePage(driver);
            basePage.clickOnONamaButton();
        }finally {
            driver.quit();
        }
    }


    @Test
    public void openReklamacije() {
        ChromeDriver driver = openChromeDriver();
        try {
            BasePage basePage = new BasePage(driver);
            basePage.clickOnReklamacijeButton();
        }finally {
            driver.quit();
        }
    }


}


