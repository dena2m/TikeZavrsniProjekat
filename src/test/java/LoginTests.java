import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests{


    @Test
    public void loginProba() {
        ChromeDriver driver = openChromeDriver();
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.openLoginModal();
            loginPage.enterValidCredentials();
            loginPage.clickLoginButton();

            // loginPage.waitForElement(loginPage.closePrijavaModal);
            // loginPage.closeLoginModal();
        }finally {
            //driver.quit();
        }
    }


}
