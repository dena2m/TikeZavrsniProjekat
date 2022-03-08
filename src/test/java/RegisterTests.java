import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RegisterTests extends BaseTests{


    /**
     * Filling in the register modal with valid data
     *
     * Steps
     * 1. Go to: "https://www.tike.rs/"
     * 2. Click on header button 'Registracija'.
     * 3. Enter valid data into registration fields.
     * 4. Click on 'REGISTRACIJA' button.
     *
     * Expected results:
     * 4. Verify that registration is not possible and display an alert message.
     */

    @Test
    public void registerWithValidCredentials() {
        print("Go to: 'https://www.tike.rs/'");
        ChromeDriver driver = openChromeDriver();

        try {
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.clickHeaderRegisterButton();
            registerPage.waitForElement(registerPage.modalRegistracija);
            registerPage.fillRegisterModal();

            sleep(3);
            registerPage.isElementPresent(registerPage.alertMessage);
            String actualMessage = driver.findElement(By.xpath(Strings.ALERT_MESSAGE_CONTAINER_XPATH)).getText();
            print("assertAlertMessage (" + actualMessage + ", " + Strings.REGISTER_ALERT_MESSAGGE_TEXT + ")");
            assert actualMessage.equals(Strings.REGISTER_ALERT_MESSAGGE_TEXT) : "Wrong message. Expected: " + Strings.REGISTER_ALERT_MESSAGGE_TEXT + ". Actual: " + actualMessage;

            registerPage.waitForElement(registerPage.closeRegistracijaModal);
            registerPage.closeRegisterModal();
            print("Verify that Home page is displayed.");
            String actualUrl = driver.getCurrentUrl();
            assertUrl(actualUrl, Strings.HOME_PAGE_URL);

        } finally {
            driver.quit();
        }
    }



}
