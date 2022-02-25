import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

        @FindBy(xpath = "//i[@class = 'icon fa fa-search']")
        WebElement searchIcon;

        @FindBy(id = "search-text")
        WebElement searchTextField;



        //Superclass constructor
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

        public void clickOnSearchIcon(){
            print("Click on the searh icon");
            searchIcon.click();
        }

        public SearchPage enterTextIntoSearchField(String text) {
            print("Enter text into search field");
            searchTextField.sendKeys(text);
            searchTextField.sendKeys(Keys.ENTER);
            return new SearchPage(driver);
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

