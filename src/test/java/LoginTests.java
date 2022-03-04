import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests{

    /**
     * Successful login from Home page with valid credentials
     * Steps:
     * 1. Go to: "https://www.tike.rs"
     * 2. Click on 'Prijavi se' header button to open login modal
     * 3. Enter valid mail address
     * 4. Enter valid password
     * 5. Click 'PRIJAVA' login button
     * Expected results:
     * 5. Verify that current URL is displayed
     * 5. Verify that 'Prijavi se' header link has changed into user link
     * 5. Verify that 'Registrujte se' header link has changed into 'Odjava' link
     */
    @Test
    public void loginFromAnyPageWithValidCredentials() {
        ChromeDriver driver = openChromeDriver();
        try {
            print("1. Go to: https://www.tike.rs");
            LoginPage loginPage = new LoginPage(driver);

            String currentUrl = driver.getCurrentUrl();

            print("2. Click on 'Prijavi se' header button to open login modal");
            loginPage.openLoginModal();

            print("3. Enter valid username");
            loginPage.enterTextIntoEmailField(Strings.EMAIL);
            print("4. Enter valid password");
            loginPage.enterTextIntoPasswordField(Strings.PASSWORD);
            print("5. Click 'PRIJAVA' login button");
            loginPage.clickLoginButtonSuccess();

            print("5. Verify that current URL is displayed");
            String actualUrl = driver.getCurrentUrl();
            assertUrl(actualUrl, currentUrl);

            loginPage.waitForElement(loginPage.userHeaderLink);
            print("5. Verify that 'Prijavi se' header link has changed into user link");
            loginPage.isElementPresent(loginPage.userHeaderLink);

            print("5. Verify that 'Registrujte se' header link has changed into 'Odjava' link");
            loginPage.isElementPresent(loginPage.odjavaHeaderLink);
        }finally {
            driver.quit();
        }
    }


    /**
     * Login with valid Email address and invalid password
     * Steps:
     * 1. Go to: "https://www.tike.rs"
     * 2. Click on 'Prijavi se' header button to open login modal
     * 3. Enter valid mail address
     * 4. Enter invalid password
     * 5. Click 'PRIJAVA' login button
     * 6. Click close button
     *
     * Expected results:
     * 5. Verify that alert message is shown
     * 6. Verify that current URL is displayed
     */
    @Test
    public void loginFromAnyPageWithValidEmaiAndInvalidPassword() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: https://www.tike.rs");
            LoginPage loginPage = new LoginPage(driver);

            String currentUrl = driver.getCurrentUrl();

            print("2. Click on 'Prijavi se' header button to open login modal");
            loginPage.openLoginModal();

            print("3. Enter valid username");
            loginPage.enterTextIntoEmailField(Strings.EMAIL);
            print("4. Enter valid password");
            loginPage.enterTextIntoPasswordField(Strings.INVALID_PASSWORD);
            print("5. Click 'PRIJAVA' login button");
            loginPage.clickLoginButtonFailure();

            print("5. Verify that alert message is shown");
            loginPage.verifyAlertMessage();

            loginPage.closeLoginModal();

            print("6. Verify that current URL is displayed");
            String actualUrl = driver.getCurrentUrl();
            assertUrl(actualUrl, currentUrl);
        }finally {
            driver.quit();
        }
    }


}
