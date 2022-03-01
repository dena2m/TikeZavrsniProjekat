import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RegisterTests extends BaseTests{




    @Test
    public void clickRegisterButtonProba() {
        ChromeDriver driver = openChromeDriver();

        try {
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.clickOnRegisterButton();
            registerPage.waitForElement(registerPage.modalRegistracija);
            registerPage.fillRegisterModal();
            //registerPage.waitForElement(registerPage.closeRegistracijaModal);
            //registerPage.closeRegisterModal();

        } finally {
            //driver.quit();
        }
    }



}
