import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BaseTests {

    public ChromeDriver openChromeDriver() {
        print("Opening Chrome Driver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        ChromeDriver driver = new ChromeDriver(options);
        driver.get(Strings.HOME_PAGE_URL);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type = 'button']")));
        driver.findElement(By.xpath("//span[@aria-hidden ='true']")).click();
        return driver;
    }


    public static void print(String s) {
        System.out.println(s);
    }

    public void  sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            print(e.getMessage());
        }
    }

    public void assertUrl(String actualUrl, String expectedUrl) {
        print("assertUrl (" + actualUrl + ", " + expectedUrl + ")");
        assert actualUrl.equals(expectedUrl) : "Wrong URL. Expected: " + expectedUrl + ". Actual: " + actualUrl;
    }

    public void loginWithValidUser(ChromeDriver driver) {
        print("Login as valid user");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterTextIntoEmailField(Strings.EMAIL);
        loginPage.enterTextIntoPasswordField(Strings.PASSWORD);
        loginPage.clickLoginButtonSuccess();
    }




}
