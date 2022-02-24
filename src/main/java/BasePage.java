import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage {

    ChromeDriver driver = null;

        //webelements
        @FindBy(xpath = "//a[@href = 'https://www.tike.rs']")
        WebElement headerLogo;

        @FindBy(xpath = "//span[@aria-hidden ='true']")
        WebElement cookiesCloseButton;

        @FindBy(className = "register-btn")
        WebElement registerButton;

        @FindBy(className = "login-btn")
        WebElement loginButton;

        @FindBy(xpath = "//a[@href = 'https://www.facebook.com/tikebelgrade/']")
        WebElement facebookLink;

        @FindBy(xpath = "//a[@href = 'https://www.instagram.com/tikebelgrade/?hl=en']")
        WebElement instagramLink;

        @FindBy(xpath = "//a[@href = 'https://www.youtube.com/channel/UCKCzJaG8KXV27C34VdvBIoQ']")
        WebElement youTubeLink;

        @FindBy(xpath = "//a[@href = 'https://fms.omega.rs/sportvision/survey.php?hash=61962a77ccffb165961962a77ccffd']")
        WebElement korisnickaPodrskaLink;

        @FindBy(id = "surveyTitle")
        WebElement pageTitleKorisnickaPodrska;

        @FindBy(xpath = "//a[@href = 'https://www.tike.rs/o-nama']")
        WebElement oNama;

        @FindBy(xpath = "//span[contains(text(), 'O nama')]/parent::h1")
        WebElement pageTitleONama;

        @FindBy(xpath = "//a[@href = 'https://www.tike.rs/reklamacije']")
        WebElement reklamacije;

        @FindBy(xpath = "//span[contains(text(), 'Reklamacije')]/parent::h1")
         WebElement pageTitleReklamacije;



        //konstruktor nadklase
        public BasePage(ChromeDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }


        public void closeCookies() {
            waitForElement(cookiesCloseButton);
            cookiesCloseButton.click();
        }



        public void clickHeaderLogo() {
            headerLogo.click();
        }


        public void clickOnFacebookLinkButton() {
            assert isElementPresent(facebookLink) : "Error. Facebook button is not displayed.";
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(false);", facebookLink);
            facebookLink.click();
        }

        public void connectToFacebook() {
            waitForElement(facebookLink);
            clickOnFacebookLinkButton();
            List<String> tabs = new ArrayList(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            String actualUrl = driver.getCurrentUrl();
            assertUrl(actualUrl, Strings.FACEBOOK_URL);
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }


        public void clickOnInstagramLinkButton() {
            assert isElementPresent(instagramLink) : "Error. Instagram button is not displayed.";
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(false);", instagramLink);
            instagramLink.click();
        }

        public void connectToInstagram() {
            waitForElement(instagramLink);
            clickOnInstagramLinkButton();
            List<String> tabs = new ArrayList(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            String actualUrl =driver.getCurrentUrl();
            assertUrl(actualUrl, Strings.INSTAGRAM_URL);
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }


        public void clickOnYouTubeButton() {
            assert isElementPresent(youTubeLink) : "Error. YouTube button is not displayed.";
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(false);", youTubeLink);
            youTubeLink.click();
        }

        public void connectToYouTube() {
            waitForElement(youTubeLink);
            clickOnYouTubeButton();
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            String actualUrl = driver.getCurrentUrl();
            assertUrl(actualUrl, Strings.YOUTUBE_URL);
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }


        public void clickOnKorisnickaPodrskaButton() {
            assert isElementPresent(korisnickaPodrskaLink) : "Error. 'Korisnicka podrska' button is not displayed.";
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(false);", korisnickaPodrskaLink);
            korisnickaPodrskaLink.click();
            waitForElement(pageTitleKorisnickaPodrska);
            assert pageTitleKorisnickaPodrska.getText().equals(Strings.KORISNICKA_PODRSKA_PAGE_TITLE) : "Error. Wrong title. Expected: " + Strings.KORISNICKA_PODRSKA_PAGE_TITLE;
        }

        public void clickOnONamaButton() {
            assert isElementPresent(oNama) : "Error. 'O nama' button is not displayed.";
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(false);", oNama);
            oNama.click();
            waitForElement(pageTitleONama);
            assert pageTitleONama.getText().contains("O nama") : "Error. Wrong title. Expected: " + Strings.O_NAMA_PAGE_TITLE;
        }

        public void clickOnReklamacijeButton() {
            assert isElementPresent(reklamacije) : "Error. 'Reklamacije' button is not displayed.";
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(false);", reklamacije);
            reklamacije.click();
            waitForElement(pageTitleReklamacije);
            assert pageTitleReklamacije.getText().contains("Reklamacije") : "Error. Wrong title. Expected: " + Strings.REKLAMACIJE_PAGE_TITLE;
        }


    public void assertUrl(String actualUrl, String expectedUrl) {
        print("assertUrl (" + actualUrl + ", " + expectedUrl + ")");
        assert actualUrl.equals(expectedUrl) : "Wrong URL. Expected: " + expectedUrl + ". Actual: " + actualUrl;
    }



        public boolean isElementPresent(WebElement element){
            try {
                boolean isPresent = element.isDisplayed();
                return true;
            }catch (Exception e) {
                print(e.getMessage());
                print("Element is not present");
                return false;
            }
        }


        public void print(String s) {
            System.out.println(s);
        }


        public void waitForElement(WebElement element) {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until((ExpectedConditions.visibilityOf(element)));
        }


        public void  sleep(int seconds){
            try {
                Thread.sleep(seconds*1000);
            }
            catch (Exception e) {
                print(e.getMessage());
            }
        }








    }

