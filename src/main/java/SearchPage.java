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


    //webelements
    //@FindBy(xpath = "//div[@class='title']//a")
    //List<WebElement> searchResultProductsList;

    @FindBy(className = "icon-caret-right")
    WebElement nextPageButton;

    @FindBy(xpath = "//h1//span")
    WebElement productTitle;


    //subclass constructor
    public SearchPage(ChromeDriver driver) {
        super(driver);
    }


    public void enterTextIntoSearchField(String text) {
        print("Enter text into search field");
        searchTextField.sendKeys(Keys.CLEAR);
        searchTextField.sendKeys(text);
        searchTextField.sendKeys(Keys.ENTER);
    }

    // search for product type e.g. 'T=shirt' and verify that list of searched product type is displayed
    public void searchByKeyword(String keyword) {
        BasePage basePage = new BasePage(driver);
        basePage.clickOnSearchIcon();
        enterTextIntoSearchField(keyword);
        List<WebElement> searchResults = driver.findElementsByXPath("//div[@class='title']//a");

        assert searchResults.size() != 0 : "No results found";
    }


    //search by product type e.g. 'majica' and find a specific product by checking all search pages
    public ProductsPage searchProductByNameFromSearchResultList(String productType, String productName){
        SearchPage searchPage = new SearchPage(driver);
        searchByKeyword(productType);

        while(true) {
            List<WebElement> searchList = driver.findElements(By.xpath("//div[@class='title']//a"));
            for (WebElement product : searchList) {
                if (product.getAttribute("title").equals(productName)) {
                    product.click();
                    //product titles have space in the end, so I used trim command
                    String pageTitle = driver.findElement(By.xpath("//h1//span")).getText().trim();
                    assert pageTitle.equals(productName.trim()) : "Error: wrong product";

                    return new ProductsPage(driver);
                }
            }
            if(isElementPresent(nextPageButton)) {
                print("Click on next page button");
                nextPageButton.click();
                // TODO stavi wait
                sleep(3);
            }
            else{
                break;
            }
        }
        assert false : "Error: There is no product with such name.";
        return null;
    }















}
