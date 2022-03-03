import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {



    @FindBy(className = "icon-caret-right")
    WebElement nextPageButton;

    @FindBy(id = "f-kategorije")
    WebElement kategorijeFilter;

    @FindBy(id = "f-pol")
    WebElement polFilter;

    @FindBy(id = "f-brend")
    WebElement brendFilter;

    @FindBy(id = "f-size")
    WebElement velicinaFilter;

    @FindBy(id = "sort")
    WebElement sortirajDropDown;


    public ProductsPage(ChromeDriver driver) {
        super(driver);

    }


    public void clickOnCategoryFilter() {
        kategorijeFilter.click();
    }

    //TODO DA LI JE OVO NEPOTREBNO, sadrzi gornju i donju metodu
    public ProductsPage selectKategorijeFilter(String categoryTitle){
       clickOnCategoryFilter();
       waitForElement(driver.findElementByXPath(Strings.CATEGORY_FILTER_LIST_XPATH));
       selectCategoryFromFilterList(categoryTitle);
       return new ProductsPage(driver);
    }

    public void selectCategoryFromFilterList(String categoryTitle) {
        List<WebElement> categoryList = driver.findElementsByXPath(Strings.CATEGORY_FILTER_LIST_XPATH);
        for (WebElement category : categoryList) {
            if (category.getAttribute("title").equals(categoryTitle)) {
                category.click();
                return;
            }
        }
    }


    public void clickOnGenderFilterButton(){
        polFilter.click();
    }

    //TODO isto kao gore, da li je potrebno
    public void selectGenderFromFilterList(String checkboxTitle) {
        clickOnGenderFilterButton();
        waitForElement(driver.findElementByXPath(Strings.GENDER_FILTER_LIST_XPATH));
        clickFilterCheckbox(Strings.GENDER_FILTER_LIST_XPATH, checkboxTitle);
    }


    public void clickFilterCheckbox(String checkboxesXpath, String checkboxTitle) {
        List<WebElement> checkboxList = driver.findElementsByXPath(checkboxesXpath);
        for(WebElement checkbox : checkboxList) {
            // Checking 'for' attribute of a checkbox label because getText() would also return
            // the number of items available with that filter (e.g. "Za muskarce (16)")
            if(checkbox.getAttribute("for").equals(checkboxTitle)) {
                checkbox.click();
                return;
            }
        }
    }

    public void clickOnBrandFilterButton() {
        brendFilter.click();
    }
    //TODO isto kao gore
    public void selectBrandFromFilterList(String brand) {
        clickOnBrandFilterButton();
        waitForElement(driver.findElementByXPath(Strings.BRAND_FILTER_LIST_XPATH));
        clickFilterCheckbox(Strings.BRAND_FILTER_LIST_XPATH, brand);
    }

    public void clickOnSizeFilterButton() {
        velicinaFilter.click();
    }
    //TODO isto kao gore
    public void selectSizeFromFilterList(String size) {
        clickOnSizeFilterButton();
        waitForElement(driver.findElementByXPath(Strings.SIZE_FILTER_LIST_XPATH));
        clickFilterCheckbox(Strings.SIZE_FILTER_LIST_XPATH, size);
    }

    //TODO da li moze da bude void ili mora sa pejdzom zbog returna.
     public ProductsPage selectDropDownFilter(WebElement dropDownName, String dropDownText) {
        Select dropdown = new Select(dropDownName);
        waitForElement(dropDownName);
        dropdown.selectByVisibleText(dropDownText);
        return new ProductsPage(driver);
     }


     public void enterTextIntoSearchField(String text) {
        print("Enter text into search field");
        searchTextField.sendKeys(text);
        searchTextField.sendKeys(Keys.ENTER);

    }

    // search for item type e.g. 'T-shirt' and verify that correct list is displayed
    public void searchByKeyword(String keyword) {
        BasePage basePage = new BasePage(driver);
        basePage.clickOnSearchIcon();
        enterTextIntoSearchField(keyword);
        List<WebElement> searchResults = getAllItems();

        assert searchResults.size() != 0 : "No results found";
    }


    public List<WebElement> getAllItems() {
        return driver.findElementsByXPath(Strings.ALL_ITEM_LIST_XPATH);
    }



    public WebElement findItemByName(String itemName) {
        while (true){
            List<WebElement> itemsList = getAllItems();
            for(WebElement item : itemsList) {
                // Product titles sometimes have space in the end, so I used trim command
                if(item.getAttribute("title").trim().equals(itemName)) {
                    item.click();

                    String pageTitle = driver.findElement(By.xpath(Strings.ALL_PAGES_TITLE_XPATH)).getText().trim();
                    assert pageTitle.equals(itemName.trim()) : "Error: wrong product";

                    return item;
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


    public ArrayList<Double> getAllItemPrices() {
        List<WebElement> allItems = getAllItems();
        ArrayList<Double> itemPrices = new ArrayList<>();
        for(WebElement item : allItems) {
            String itemPrice = item.findElement(By.xpath(Strings.ALL_ITEM_PRICES_XPATH)).getText();
            Double price = Double.valueOf(itemPrice);
            print("Item price is: " + price);
            itemPrices.add(price);
        }
        return itemPrices;
    }


    public List<WebElement> printAllItemsFromList() {
        List<WebElement> allItems = getAllItems();
        for(WebElement item : allItems) {
            String currentName = item.getText();
            System.out.println(allItems.indexOf(item) + ". " + currentName);
        }
        System.out.println(allItems.size());
        return allItems;
    }



















}





