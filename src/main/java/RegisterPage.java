import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{

    @FindBy(id = "reg_firstname")
    WebElement imeRegistracija;

    @FindBy(id = "reg_lastname")
    WebElement prezimeRegistracija;

    @FindBy(id = "reg_email")
    WebElement mailAdresaRegistracija;

    @FindBy(id = "reg_phone")
    WebElement telefonRegistracija;

    @FindBy(id = "reg_city_id")
    WebElement gradRegistracija;

    @FindBy(id = "reg_address_id")
    WebElement ulicaRegistracija;

    @FindBy(id = "reg_street_no")
    WebElement brojUliceRegistracija;

    @FindBy(id = "reg_postcode")
    WebElement postanskiBrojRegistracija;

    @FindBy(id = "reg_password")
    WebElement lozinkaRegistracija;

    @FindBy(id = "reg_password_repeat")
    WebElement ponoviLozinkuRegistracija;

    @FindBy(xpath = "//div[@class = 'iradio_flat icheck-item icheck[0mlza] checked']")
    WebElement muskiRadioButton;

    @FindBy(xpath = "//div[@class = 'iradio_flat icheck-item icheck[v6ki7]']")
    WebElement zenskiRadioButton;

    @FindBy(id = "reg_anti")
    WebElement antiSpamIzaberi;

    @FindBy(xpath = "//select[@id= 'reg_anti']")
    WebElement izaberiVrednostAntiSpam;

    @FindBy(id = "reg_confirm")
    WebElement slazemSeSaUslovimaKoriscenjaCheckBox;

    @FindBy(id = "reg_privacy_rules")
    WebElement slazemSeSaPrvilimaPrvatnostiCheckBox;

    @FindBy(id = "reg_sendNewsletter")
    WebElement zelimDaDobijamObavestenjaNaEmailSmsIViberCheckBox;

    @FindBy(id = "reg_callMeOnPhone")
    WebElement zelimDaDobijamObavestenjaNaTelefonCheckBox;

    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement registracijaButton;

    @FindBy(id = "register_modal")
    WebElement modalRegistracija;






    public RegisterPage(ChromeDriver driver) {
        super(driver);
    }

    //TODO na koju stranu vodi, registruj se sa novim mejlom
    public RegisterPage fillRegistrationForm() {
        clickOnRegisterButton();
        assert isElementPresent(modalRegistracija);
        return new RegisterPage(driver);

    }

}
