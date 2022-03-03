import org.openqa.selenium.*;
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
        @FindBy(xpath = "//div[@class='container']//div[@class='block logo']")
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

        @FindBy(xpath = "//a[@title = 'Korpa']")
        WebElement shoppingCartIcon;

        @FindBy(className = "header-carthor-total")
        WebElement shoppingCartBadgeNumber;

        @FindBy(xpath = "//a[@href = 'https://www.facebook.com/tikebelgrade/']")
        WebElement facebookLink;

        @FindBy(xpath = "//a[@href = 'https://www.instagram.com/tikebelgrade/?hl=en']")
        WebElement instagramLink;

        @FindBy(xpath = "//a[@href = 'https://www.youtube.com/channel/UCKCzJaG8KXV27C34VdvBIoQ']")
        WebElement youTubeLink;


    //Superclass constructor
        public BasePage(ChromeDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }



        public void closeCookies() {
            waitForElement(cookiesCloseButton);
            cookiesCloseButton.click();
        }


        public HomePage clickHeaderLogo() {
            headerLogo.click();
            return new HomePage(driver);
        }


        public void clickOnSearchIcon(){
            print("Click on the searh icon");
            searchIcon.click();
        }

        public RegisterPage clickOnRegisterButton() {
            registerButton.click();
            return new RegisterPage(driver);
        }

        public LoginPage clickOnLoginButton() {
            loginButton.click();
            return new LoginPage(driver);
        }


        public CartPage clickOnShoppingCartIcon() {
            shoppingCartIcon.click();
            return new CartPage(driver);
        }


        //creat footer links list, click on one and asserting links url
        public void selectFooterLink(String footerLinkTitle, String footerLinkUrl) {
            List<WebElement> footerLinks = driver.findElements(By.xpath("//nav[@class='row']//a"));
            for(WebElement linkTitle : footerLinks) {
                if(linkTitle.getAttribute("title").equals(footerLinkTitle)) {
                    print("Click on: " + footerLinkTitle);
                    linkTitle.click();
                    print("Verify that " + footerLinkTitle + " URL is displayed.");
                    String actualUrl = driver.getCurrentUrl();
                    assertUrl(actualUrl, footerLinkUrl);
                    return;
                }
            }
            assert false : "Error: footer page " + footerLinkTitle + " not found.";
        }


    // assert Facebook link button is present, scroll down the Home page (alignToTop argument is set to false because
    // the navigation bar was covering some links, and they weren't clickable)
    public void clickOnFacebookLinkButton() {
        assert isElementPresent(facebookLink) : "Error. Facebook button is not displayed.";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", facebookLink);
        facebookLink.click();
    }
    // click on Facebook link, switch to Tike/Facebook tab, close Facebook tab, switch to Home page tab
    public void openFacebookPage() {
        waitForElement(facebookLink);
        print("Click on Facebook link button.");
        clickOnFacebookLinkButton();
        print("Switch to Facebook tab.");
        List<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        print("Verify that Facebook URL is displayed.");
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.FACEBOOK_URL);
        print("Close Facebook tab.");
        driver.close();
        print("Switch to Home page tab.");
        driver.switchTo().window(tabs.get(0));
    }

    public void clickOnInstagramLinkButton() {
        assert isElementPresent(instagramLink) : "Error. Instagram button is not displayed.";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", instagramLink);
        instagramLink.click();
    }
    public void openInstagramPage() {
        waitForElement(instagramLink);
        print("Click on Instagram link button.");
        clickOnInstagramLinkButton();
        print("Switch to Instagram tab.");
        List<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        print("Verify that Instagram URL is displayed.");
        String actualUrl =driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.INSTAGRAM_URL);
        print("Close Instagram tab.");
        driver.close();
        print("Switch to Home page tab.");
        driver.switchTo().window(tabs.get(0));
    }

    public void clickOnYouTubeButton() {
        assert isElementPresent(youTubeLink) : "Error. YouTube button is not displayed.";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", youTubeLink);
        youTubeLink.click();
    }
    public void openYouTubeChannel() {
        waitForElement(youTubeLink);
        print("Click on YouTube link button.");
        clickOnYouTubeButton();
        print("Switch to YouTube tab.");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        print("Verify that YouTube URL is displayed.");
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.YOUTUBE_URL);
        print("Close YouTube tab.");
        driver.close();
        print("Switch to Home page tab.");
        driver.switchTo().window(tabs.get(0));
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
        //TODO prebaciti kasnije na pogodniju stranicu
        public void waitForShoppingBadgeNumber(Integer currentNumber, Integer x) {
            WebDriverWait wait = new WebDriverWait(driver, 3);
            currentNumber += x;
            String number = "" + currentNumber;
            wait.until(ExpectedConditions.textToBe(By.className("header-carthor-total"), number));
        }


    public void  sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            print(e.getMessage());
        }
    }








    }

