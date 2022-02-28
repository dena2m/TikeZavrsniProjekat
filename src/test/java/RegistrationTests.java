import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTests{




    @Test
    public void clickRegisterButtonProba() {
        ChromeDriver driver = openChromeDriver();

        try {
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.clickOnRegisterButton();
        } finally {
            driver.quit();
        }
    }



}
