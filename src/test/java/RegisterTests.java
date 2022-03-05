import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RegisterTests extends BaseTests{


    /**
     * Filling in the register modal with valid data
     *
     * Steps
     * 1. Go to: "https://www.tike.rs/"
     * 2. Clic on header 'Registracija' button.
     * 3. Enter valid data into registration fields.
     * 4. Click on 'REGISTRACIJA' button.
     *
     * Expected results:
     * 4. Verify that register is not possible and alert message is displayed.
     */
    @Test
    public void registerWithWalidCredentials() {
        print("Go to: 'https://www.tike.rs/'");
        ChromeDriver driver = openChromeDriver();

        try {
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.clickRegisterButton();
            registerPage.waitForElement(registerPage.modalRegistracija);
            registerPage.fillRegisterModal();

            sleep(3);
            registerPage.isElementPresent(registerPage.alertMessage);
            String actualMessage = driver.findElement(By.xpath(Strings.ALERT_MESSAGE_CONTAINER_XPATH)).getText();
            print("assertAlertMessage (" + actualMessage + ", " + Strings.REGISTER_ALERT_MESSAGGE_TEXT + ")");
            assert actualMessage.equals(Strings.REGISTER_ALERT_MESSAGGE_TEXT) : "Wrong message. Expected: " + Strings.REGISTER_ALERT_MESSAGGE_TEXT + ". Actual: " + actualMessage;

            registerPage.waitForElement(registerPage.closeRegistracijaModal);
            HomePage homePage = registerPage.closeRegisterModal();
            print("Verify that Home page is displayed.");
            String actualUrl = driver.getCurrentUrl();
            assertUrl(actualUrl, Strings.HOME_PAGE_URL);

        } finally {
            driver.quit();
        }
    }



}
