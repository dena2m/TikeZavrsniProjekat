import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage{

    @FindBy(id = "reg_firstname")
    WebElement imeRegistracija;

    @FindBy(id = "reg_lastname")
    WebElement prezimeRegistracija;

    @FindBy(id = "reg_email")
    WebElement mailAdresaRegistracija;

    @FindBy(id = "reg_phone")
    WebElement telefonRegistracija;

    @FindBy(id = "reg_city")
    WebElement gradRegistracija;

    @FindBy(id = "reg_address")
    WebElement ulicaRegistracija;

    @FindBy(id = "reg_street_no")
    WebElement brojUliceRegistracija;

    @FindBy(id = "reg_postcode")
    WebElement postanskiBrojRegistracija;

    @FindBy(id = "reg_password")
    WebElement lozinkaRegistracija;

    @FindBy(id = "reg_password_repeat")
    WebElement ponoviLozinkuRegistracija;

    @FindBy(id = "reg_gender_1")
    WebElement muskiRadioButton;

    @FindBy(xpath = "//label[@for=\"reg_gender_2\"]")
    WebElement zenskiRadioButton;

    @FindBy(id = "reg_anti")
    WebElement antiSpamIzaberiDropDown;

    @FindBy(xpath = "//select[@id= 'reg_anti']")
    WebElement izaberiVrednostAntiSpam;

    @FindBy(xpath = "//div[@class='col-xs-12 reg-check-werapper'][1]//input")
    WebElement slazemSeSaUslovimaKoriscenjaCheckBox;

    @FindBy(xpath = "//div[@class='col-xs-12 reg-check-werapper'][2]//input")
    WebElement slazemSeSaPravilimaPrvatnostiCheckBox;

    @FindBy(xpath = "//button[@class = 'btn btn-success confirm-loader']")
    WebElement registracijaButton;

    @FindBy(id = "register_modal")
    WebElement modalRegistracija;

    @FindBy(xpath = "//div[@id='register_modal']//div[@class = 'modal-header']//button")
    WebElement closeRegistracijaModal;

    @FindBy(xpath = "//div[@class = 'alert alert-danger']")
    WebElement alertMessage;

    @FindBy(id = "registration_modal")
    WebElement registracijaSuccessModal;

    @FindBy(xpath = "//form[@id = 'registration_modal']//div[@class = 'modal-header']//button")
    WebElement closeRegistracijaSuccessModal;

    @FindBy(xpath = "//div[@class = 'alert alert-success']")
    WebElement successAlertMessage;



    public RegisterPage(ChromeDriver driver) {
        super(driver);
    }



    public RegisterPage fillRegistrationForm() {
        clickRegisterButton();
        assert isElementPresent(modalRegistracija);
        return new RegisterPage(driver);

    }

    public void enterTextIntoFirstNameField(String ime){
        imeRegistracija.sendKeys(ime);
    }

    public void enterTextIntoLastnameField(String prezime) {
        prezimeRegistracija.sendKeys(prezime);
    }

    public void enterTextIntoMailField(String mail) {
        mailAdresaRegistracija.sendKeys(mail);
    }

    public void enterNumberIntoPhoneNumberField(String telefon) {
        telefonRegistracija.sendKeys(telefon);
    }

    public void enterTextIntoCityField(String grad) {
        gradRegistracija.sendKeys(grad);
    }

    public void enterTextIntoStreetField(String ulica) {
        ulicaRegistracija.sendKeys(ulica);
    }

    public void enterNumberIntoStreetNumberField(String brojUlice) {
        brojUliceRegistracija.sendKeys(brojUlice);
    }

    public void enterNumberIntoPostalCodeField(String postanskiBroj) {
        postanskiBrojRegistracija.sendKeys(postanskiBroj);
    }

    public void enterTextIntoPasswordField(String lozinka) {
        lozinkaRegistracija.sendKeys(lozinka);
    }

    public void enterTextIntoRepeatPasswordField(String lozinka) {
        ponoviLozinkuRegistracija.sendKeys(lozinka);
    }

    public void clickOnMaleRadioButton() {
        muskiRadioButton.click();
    }

    public void clickOnFemaleRadioButton() {
        zenskiRadioButton.click();
    }

    public void clickOnAntiSpamDropDown() {
        antiSpamIzaberiDropDown.click();
    }


    public void fillRegisterModal() {
        print("Enter first name.");
        enterTextIntoFirstNameField(Strings.FIRST_NAME);
        print("Enter last name.");
        enterTextIntoLastnameField(Strings.LAST_NAME);
        print("Enter Email");
        enterTextIntoMailField(Strings.EMAIL);
        print("Enter phone number.");
        enterNumberIntoPhoneNumberField(Strings.PHONE_NUMBER);
        print("Enter city.");
        enterTextIntoCityField(Strings.CITY);
        print("Enter street.");
        enterTextIntoStreetField(Strings.STREET);
        print("Enter street number.");
        enterNumberIntoStreetNumberField(Strings.STREET_NUMBER);
        print("Enter zip code.");
        enterNumberIntoPostalCodeField(Strings.POSTAL_CODE);
        print("Enter password.");
        enterTextIntoPasswordField(Strings.PASSWORD);
        print("Repeat password.");
        enterTextIntoRepeatPasswordField(Strings.PASSWORD);
        print("Click on female radio button.");
        clickOnFemaleRadioButton();
        print("Click on anti spam drop down.");
        clickOnAntiSpamDropDown();
        print("Select value from drop down list");
        selectValueFromSpamDropDown();
        // Didn't work on check boxes with regular .click() function
        print("Check 'Slazem se sa uslovima koriscenja' check box.");
        driver.executeScript("arguments[0].click();", slazemSeSaUslovimaKoriscenjaCheckBox);
        print("Check 'Slazem se sa pravilima privatnosti' check box");
        driver.executeScript("arguments[0].click();", slazemSeSaPravilimaPrvatnostiCheckBox);
        print("Click on 'REGISTRACIJA' button.");
        registracijaButton.click();
    }

    public HomePage closeRegisterModal() {
        closeRegistracijaModal.click();
        return new HomePage(driver);
    }


    public RegisterPage selectValueFromSpamDropDown() {
        Select dropdown = new Select(izaberiVrednostAntiSpam);
        dropdown.selectByVisibleText("5");
        return this;
    }

    public void clickCloseSuccessRegistrationModal() {
        isElementPresent(registracijaSuccessModal);
        String currentMessage = driver.findElement(By.id("registration_modal")).getText();
        assert currentMessage.equals(Strings.REGISTRATION_SUCCESS_TEXT) : "Error: wrong message. Exected: " + Strings.REGISTRATION_SUCCESS_TEXT + ". Actual: " + currentMessage;
        closeRegistracijaSuccessModal.click();
    }

   // public HomePage verifyRegistrationFromMailLink() {
    //    Strings.REGISTRATION_SUCCESS_URL
    //}







}
