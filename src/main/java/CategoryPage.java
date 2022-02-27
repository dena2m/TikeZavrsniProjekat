import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoryPage extends BasePage{



    @FindBy(xpath = "//a[@href = 'https://www.tike.rs/proizvodi/adidas']")
    WebElement adidasLink;


    public CategoryPage(ChromeDriver driver) {
        super(driver);
    }



    //select brands from navbar, and on brands page select brand link
    public void selectBrandTitleLink(String brandTitle, String brandUrl) {
        HomePage homePage = new HomePage(driver);

        homePage.goToCategoryPageFromNavBar(Strings.BRENDOVI_NAVBAR_TITLE, Strings.BRENDOVI_URL);
        List<WebElement> brandLinks = driver.findElements(By.xpath("//a[@class = 'link']"));
        for(WebElement brandLink : brandLinks) {
            if(brandLink.getAttribute("title").equals(brandTitle)) {
                brandLink.click();
                String actualUrl = driver.getCurrentUrl();
                assertUrl(actualUrl, brandUrl);
                return;
            }
        }
        assert false : "Error: Brand title " + brandTitle + " not found.";
    }
}
