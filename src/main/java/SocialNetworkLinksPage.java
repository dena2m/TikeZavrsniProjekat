import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SocialNetworkLinksPage extends BasePage{


    @FindBy(xpath = "//a[@href = 'https://www.facebook.com/tikebelgrade/']")
    WebElement facebookLink;

    @FindBy(xpath = "//a[@href = 'https://www.instagram.com/tikebelgrade/?hl=en']")
    WebElement instagramLink;

    @FindBy(xpath = "//a[@href = 'https://www.youtube.com/channel/UCKCzJaG8KXV27C34VdvBIoQ']")
    WebElement youTubeLink;


    public SocialNetworkLinksPage(ChromeDriver driver) {
        super(driver);
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

}
