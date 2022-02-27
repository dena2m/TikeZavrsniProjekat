import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{





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



    public CategoryPage clickOnPatikeLink() {
        print("Click on 'Patike' link button.");
        patikeLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.PATIKE_URL);
        return new CategoryPage(driver);
    }

    public CategoryPage clickOnOdecaLink() {
        print("Click on 'Odeca' link button.");
        odecaLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.ODECA_URL);
        return new CategoryPage(driver);
    }

    public CategoryPage clickOnBrendoviLink() {
        print("Click on 'Brendovi' link button.");
        brendoviLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.BRENDOVI_URL);
        return new CategoryPage(driver);
    }

    public CategoryPage clickOnSaleLink() {
        print("Click on 'Sale' link");
        saleLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.HOT_SALE_URL);
        return new CategoryPage(driver);
    }

    public CategoryPage clickOnBlogLink() {
        print("Click on 'Blog' link.");
        blogLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.BLOG_URL);
        return new CategoryPage(driver);
    }

    public CategoryPage goToCategoryPageFromNavBar(String categoryTitle, String categoryUrl) {
        List<WebElement> allCategories = driver.findElements(By.xpath("//ul[@class='nav-main list-inline']//a"));
        for(WebElement category : allCategories) {
            if(category.getAttribute("title").equals(categoryTitle)){
                category.click();
                String actualUrl = driver.getCurrentUrl();
                assertUrl(actualUrl, categoryUrl);
                return new CategoryPage(driver);
            }
        }
        assert false : "Error: Navbar category " + categoryTitle + " not found.";
        return null;
    }






















}
