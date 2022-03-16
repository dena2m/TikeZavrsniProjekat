import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage {

        ChromeDriver driver;

        @FindBy(xpath = "//div[@class='container']//div[@class='block logo']")
        WebElement headerLogo;

        @FindBy(className = "register-btn")
        WebElement registrujSeHeaderButton;

        @FindBy(className = "login-btn")
        WebElement prijaviSeHeaderButton;

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

        public BasePage(ChromeDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public void clickHeaderLogo() {
            headerLogo.click();
        }


        public void clickSearchIcon(){
            searchIcon.click();
        }


        public void enterTextIntoSearchField(String text) {
            searchTextField.sendKeys(text);
            searchTextField.sendKeys(Keys.ENTER);
        }


        public void clickHeaderRegisterButton() {
            registrujSeHeaderButton.click();
        }


        public void clickHeaderLoginButton() {
            prijaviSeHeaderButton.click();
        }


        public ShoppingCartPage clickShoppingCartIcon() {
            shoppingCartIcon.click();
            return new  ShoppingCartPage(driver);
        }


        // Create navbar link list, select one by title and verify that correct URL is displayed
        public InventoryPage openNavBarCategory(String categoryTitle, String categoryUrl) {
            List<WebElement> allCategories = driver.findElements(By.xpath(Strings.NAVBAR_CATEGORY_LIST_XPATH));
            for(WebElement category : allCategories) {
                if(category.getAttribute("title").equals(categoryTitle)){
                    print("Click on: " + categoryTitle);
                    category.click();
                    print("Verify that " + categoryUrl + " URL is displayed.");
                    String actualUrl = driver.getCurrentUrl();
                    assertUrl(actualUrl, categoryUrl);
                    print("Selected category: " + categoryTitle + ".");
                    return new InventoryPage(driver);
                }
            }
            throw new AssertionError("Error: Navbar category " + categoryTitle + " not found");
        }


        // Create footer link list, select one by title and verify that correct URL is displayed
        public void selectFooterLink(String footerLinkTitle, String footerLinkUrl) {
            List<WebElement> footerLinks = driver.findElements(By.xpath(Strings.FOOTER_LINKS_LIST_XPATH));
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
            throw new AssertionError("Error: Footer page " + footerLinkTitle + " not found");
        }

        // Verify that Facebook link button is present, scroll down the Home page (alignToTop argument is set to false
        // because the navigation bar was covering some links, and they weren't clickable)
        public void clickOnFacebookLinkButton() {
            assert isElementPresent(facebookLink) : "Error. Facebook button is not displayed.";
            scrollToElement(facebookLink);
            facebookLink.click();
        }


        // Click on Facebook link, switch to Tike/Facebook tab, assert URL, close Facebook tab, switch to Home page tab
        public void openAndCloseFacebookPage() {
            waitForElement(facebookLink);
            print("Click on Facebook link button.");
            clickOnFacebookLinkButton();
            print("Switch to Facebook tab.");
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
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
            scrollToElement(instagramLink);
            instagramLink.click();
        }


        public void openAndCloseInstagramPage() {
            waitForElement(instagramLink);
            print("Click on Instagram link button.");
            clickOnInstagramLinkButton();
            print("Switch to Instagram tab.");
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
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
            scrollToElement(youTubeLink);
            youTubeLink.click();
        }


        public void openAndCloseYouTubeChannel() {
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


        public void scrollToElement(WebElement element) {
            JavascriptExecutor js = driver;
            js.executeScript("arguments[0].scrollIntoView(false);", element);
        }


        public void assertUrl(String actualUrl, String expectedUrl) {
            print("assertUrl (" + actualUrl + ", " + expectedUrl + ")");
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
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


        public static void print(String s) {
            System.out.println(s);
        }


        public void waitForElement(WebElement element) {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOf(element));
        }


        public void waitForElementToBeClickable(WebElement element) {
            WebDriverWait wait = new WebDriverWait(driver,5);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }


        // Wait for the page to refresh (after selecting a filter)
        public void waitForStalenessOfElement(WebElement element){
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.stalenessOf(element));
        }

        public void waitForItemListToReload(){
            WebDriverWait wait = new WebDriverWait(driver, 5);

            // Wait for item list to disappear from the page
            wait.until(ExpectedConditions.stalenessOf(driver.findElementByXPath(Strings.ALL_ITEM_LIST_XPATH)));

            // Wait for item list to appear again
            wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath(Strings.ALL_ITEM_LIST_XPATH)));
        }

         public static void sleep(int seconds) {
            try {
                Thread.sleep((long)seconds * 1000);
            }
            catch (Exception e) {
                print(e.getMessage());
            }
        }


    }

