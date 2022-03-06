import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(id = "login_modal")
    WebElement loginModal;

    @FindBy(id = "login_email")
    WebElement loginEmailAdresaField;

    @FindBy(id = "login_password")
    WebElement loginLozinkaField;

    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement modalPrijavaButton;

    @FindBy(xpath = "//a[@class='btn']")
    WebElement modalRegistrujSeButton;

    @FindBy(xpath = "//div[@class = 'modal-header']//button")
    WebElement closePrijavaModal;

    @FindBy(xpath = "//li[@class = 'item item-username']")
    WebElement userHeaderLink;

    @FindBy(xpath = "//li[@class = 'item item-logout']")
    WebElement odjavaHeaderLink;

    @FindBy(xpath = "//div[@class = 'alert alert-danger']")
    WebElement alertMessageContainer;


    public LoginPage(ChromeDriver driver) {
        super(driver);
    }



    public void clickLogoutHeaderButton() {
        odjavaHeaderLink.click();
    }

    public void clickUserHeaderButton() {
        userHeaderLink.click();
    }



    public void openLoginModal(){
        clickHeaderLoginButton();
        waitForElement(loginModal);
        new LoginPage(driver);
    }

    public void enterTextIntoEmailField(String email) {
        loginEmailAdresaField.sendKeys(email);
    }

    public void enterTextIntoPasswordField(String password) {
        loginLozinkaField.sendKeys(password);
    }

    public void clickLoginModalButton() {
        modalPrijavaButton.click();
        new InventoryPage(driver);
    }

    public String verifyAlertMessage() {
        waitForElement(alertMessageContainer);
        String currentMessage = driver.findElement(By.xpath(Strings.ALERT_MESSAGE_CONTAINER_XPATH)).getText();
        assert currentMessage.equals(Strings.LOGIN_ALERT_MESSAGE_TEXT) : "Error: Wrong message. Expected: "
                + Strings.LOGIN_ALERT_MESSAGE_TEXT + ". Actual: " + currentMessage;
        return currentMessage;
    }

    public void closeLoginModal() {
        closePrijavaModal.click();
    }



}
