import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{



    @FindBy(xpath = "//a[@title = 'Korpa']")
    WebElement shoppingCartIcon;

    @FindBy(className = "header-carthor-total")
    WebElement shoppingCartNumber;

    @FindBy(xpath = "//a[@href = 'https://www.tike.rs/patike']")
    WebElement patikeLink;

    @FindBy(xpath = "//a[@href = 'https://www.tike.rs/odeca']")
    WebElement odecaLink;

    @FindBy(xpath = "//a[@href = 'https://www.tike.rs/brendovi']")
    WebElement brendoviLink;

    @FindBy(xpath = "//a[@href = 'https://www.tike.rs/proizvodi/hot-sale']")
    WebElement saleLink;

    @FindBy(xpath = "//a[@href = 'https://www.tike.rs/magazin']")
    WebElement blogLink;



    public HomePage(ChromeDriver driver) {
        super(driver);
    }


    public void clickOnPatikeLink() {
        print("Click on 'Patike' link button.");
        patikeLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.PATIKE_URL);
    }

    public void clickOnOdecaLink() {
        print("Click on 'Odeca' link button.");
        odecaLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.ODECA_URL);
    }

    public void clickOnBrendoviLink() {
        print("Click on 'Brendovi' link button.");
        brendoviLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.BRENDOVI_URL);
    }

    public void clickOnSaleLink() {
        print("Click on 'Sale' link");
        saleLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.HOT_SALE_URL);
    }

    public void clickOnBlogLink() {
        print("Click on 'Blog' link.");
        blogLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.BLOG_URL);
    }

    public void goToCategoryPageFromNavBar(String categoryTitle, String categoryUrl) {
        List<WebElement> allCategories = driver.findElements(By.xpath("//ul[@class='nav-main list-inline']//a"));
        for(WebElement category : allCategories) {
            if(category.getAttribute("title").equals(categoryTitle)){
                category.click();
                String actualUrl = driver.getCurrentUrl();
                assertUrl(actualUrl, categoryUrl);
                return;
            }
        }
        assert false : "Error: Navbar category " + categoryTitle + " not found.";
    }






















}
