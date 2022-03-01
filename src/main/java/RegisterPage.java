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
    WebElement slazemSeSaPrvilimaPrvatnostiCheckBox;

    @FindBy(xpath = "//button[@class = 'btn btn-success confirm-loader']")
    WebElement registracijaButton;

    @FindBy(id = "register_modal")
    WebElement modalRegistracija;

    @FindBy(xpath = "//div[@id='register_modal']//div[@class = 'modal-header']//button")
    WebElement closeRegistracijaModal;






    public RegisterPage(ChromeDriver driver) {
        super(driver);
    }

    //TODO na koju stranu vodi, registruj se sa novim mejlom
    public RegisterPage fillRegistrationForm() {
        clickOnRegisterButton();
        assert isElementPresent(modalRegistracija);
        return new RegisterPage(driver);

    }

    public void fillRegisterModal() {
        imeRegistracija.sendKeys("natasa");
        prezimeRegistracija.sendKeys("delibasic");
        mailAdresaRegistracija.sendKeys("anemona.nate@gmail.com");
        telefonRegistracija.sendKeys("063");
        gradRegistracija.sendKeys("beograd");
        ulicaRegistracija.sendKeys("j.gagarina");
        brojUliceRegistracija.sendKeys("534534");
        postanskiBrojRegistracija.sendKeys("11000");
        lozinkaRegistracija.sendKeys("natasa123");
        ponoviLozinkuRegistracija.sendKeys("natasa123");
        zenskiRadioButton.click();
        antiSpamIzaberiDropDown.click();
        selectSpamValueDropDown();

        // Didn't work on check boxes with regular .click() function
        driver.executeScript("arguments[0].click();", slazemSeSaUslovimaKoriscenjaCheckBox);
        driver.executeScript("arguments[0].click();", slazemSeSaPrvilimaPrvatnostiCheckBox);
        registracijaButton.click();
    }

    public void closeRegisterModal() {
        closeRegistracijaModal.click();
    }

    public RegisterPage selectSpamValueDropDown() {
        //Select je klasa za upravljanje dropdown-ovima
        Select dropdown = new Select(izaberiVrednostAntiSpam);
        //ovako se bira opcija iz dropdowna po tekstu koji pise
        dropdown.selectByVisibleText("5");
        return this;
    }

}
