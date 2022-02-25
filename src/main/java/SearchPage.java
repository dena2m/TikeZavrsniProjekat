import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage{

    @FindBy(xpath = "//div[@class=\"col-xs-12 col-sm-12 col-lg-12\"]//a")
    WebElement searchResultProductsList;

    public SearchPage(ChromeDriver driver) {
        super(driver);
    }


    public SearchPage searchByKeyword(String keyword) {
        print("Go to: https://www.tike.rs/");
        BasePage basePage = new BasePage(driver);
        basePage.clickOnSearchIcon();
        enterTextIntoSearchField(keyword);
        if(isElementPresent(searchResultProductsList)) {
            return new SearchPage(driver);
        }
        assert false : "Error: no products found.";
        return null;
    }
    //search by product type and find a specific product by checking all pages
    public ProductPage searchProductByName(String productType, String productName){
        SearchPage searchPage = searchByKeyword(productType);
        while(true) {
            List<WebElement> searchList = driver.findElements(By.xpath("//div[@class=\"col-xs-12 col-sm-12 col-lg-12\"]//a"));
            for (WebElement product : searchList) {
                if (product.getAttribute("title").equals(productName)) {
                    product.click();
                    return new ProductPage(driver);
                }
            }
            WebElement nextPageButton = driver.findElementByClassName("icon-caret-right");

            if(isElementPresent(nextPageButton)) {
                print("Click on next page button");
                nextPageButton.click();
            }
            else{
                break;
            }
        }
        assert false : "Error: There is no product with such name.";
        return null;
    }















}
