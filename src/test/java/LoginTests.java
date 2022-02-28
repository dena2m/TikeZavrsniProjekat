import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests{


    @Test
    public void loginProba() {
        ChromeDriver driver = openChromeDriver();
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginWithValidCredentialsProba();
        }finally {
            driver.quit();
        }
    }


}
