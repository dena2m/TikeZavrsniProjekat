import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FooterLinksPage extends BasePage{


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


    public FooterLinksPage(ChromeDriver driver) {
        super(driver);
    }

    //creat footer links list, click on one and asserting links url
    public FooterLinksPage selectFooterLink(String footerLinkTitle, String footerLinkUrl) {
        List<WebElement> footerLinks = driver.findElements(By.xpath("//nav[@class='row']//a"));
        for(WebElement linkTitle : footerLinks) {
            if(linkTitle.getAttribute("title").equals(footerLinkTitle)) {
                linkTitle.click();
                String actualUrl = driver.getCurrentUrl();
                assertUrl(actualUrl, footerLinkUrl);
                return new FooterLinksPage(driver);
            }
        }
        assert false : "Error: footer page " + footerLinkTitle + " not found.";
        return null;
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


}
