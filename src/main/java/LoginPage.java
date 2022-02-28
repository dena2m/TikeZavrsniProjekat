import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{


    @FindBy(id = "login_email")
    WebElement loginMail;

    @FindBy(id = "login_password")
    WebElement loginLozinka;

    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement loginPrijava;

    @FindBy(xpath = "//a[@class='btn']")
    WebElement registrujSe;

    @FindBy(xpath = "//div[@class = 'modal-header']//button")
    WebElement closePrijavaModal;

    @FindBy(id = "login_modal")
    WebElement modalLogin;





    public LoginPage(ChromeDriver driver) {
        super(driver);
    }


    public LoginPage loginWithValidCredentialsProba(){
        clickOnLoginButton();
        isElementPresent(modalLogin);
        return new LoginPage(driver);
    }


}
