import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{


    @FindBy(id = "login_email")
    WebElement loginMailField;

    @FindBy(id = "login_password")
    WebElement loginLozinkaField;

    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement prijavaButton;

    @FindBy(xpath = "//a[@class='btn']")
    WebElement registrujSeButton;

    @FindBy(xpath = "//div[@id='login_modal']//div[@class = 'modal-header']//button")
    WebElement closePrijavaModal;

    @FindBy(id = "login_modal")
    WebElement modalLogin;





    public LoginPage(ChromeDriver driver) {
        super(driver);
    }


    public LoginPage openLoginModal(){
        clickOnLoginButton();
        isElementPresent(modalLogin);
        return new LoginPage(driver);
    }

    public void enterValidCredentials() {
        loginMailField.sendKeys("Natasa");
        loginLozinkaField.sendKeys("lozinka");

    }


    public void clickLoginButton() {
        prijavaButton.click();
    }


    public void closeLoginModal() {
        closePrijavaModal.click();
    }



}
