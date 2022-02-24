import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{

    @FindBy(xpath = "//i[@class = 'icon fa fa-search']")
    WebElement searchIcon;

    @FindBy(id = "search-text")
    WebElement searchTextField;

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


    public ProductsPage clickOnPatikeLink() {
        print("Click on 'Patike' link button.");
        patikeLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.PATIKE_URL);
        return new ProductsPage(driver);
    }

    public ProductsPage clickOnOdecaLink() {
        print("Click on 'Odeca' link button.");
        odecaLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.ODECA_URL);
        return new ProductsPage(driver);
    }

    public ProductsPage clickOnBrendoviLink() {
        print("Click on 'Brendovi' link button.");
        brendoviLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.BRENDOVI_URL);
        return new ProductsPage(driver);
    }

    public ProductsPage clickOnSaleLink() {
        print("Click on 'Sale' link");
        saleLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.HOT_SALE_URL);
        return new ProductsPage(driver);
    }

    public ProductsPage clickOnBlogLink() {
        print("Click on 'Blog' link.");
        blogLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertUrl(actualUrl, Strings.BLOG_URL);
        return new ProductsPage(driver);
    }

    public ProductsPage clickCategoryFromNavBar(String categoryTitle) {
        List<WebElement> allCategories = driver.findElements(By.xpath("//ul[@class='nav-main list-inline']//a"));
                for(WebElement category : allCategories) {
                        if(category.getAttribute("title").equals(categoryTitle)){
                            category.click();
                            return new ProductsPage(driver);
                    }
                }
                assert false : "Error: Navbar category " + categoryTitle + " not found.";
                return null;
    }






















}
