import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {



    @FindBy(className = "icon-caret-right")
    WebElement nextPageButton;

    @FindBy(id = "f-kategorije")
    WebElement kategorijeFilterButton;

    @FindBy(id = "f-pol")
    WebElement polFilterButton;

    @FindBy(id = "f-brend")
    WebElement brendFilterButton;

    @FindBy(id = "f-size")
    WebElement velicinaFilterButton;

    @FindBy(id = "sort")
    WebElement sortirajDropDown;


    public InventoryPage(ChromeDriver driver) {
        super(driver);

    }


    public void clickOnKategorijeFilter() {
        kategorijeFilterButton.click();
    }

    public void selectItemTypeFromKategorijeFilterList(String itemTypeTitle) {
        List<WebElement> itemTypeList = driver.findElementsByXPath(Strings.CATEGORY_FILTER_LIST_XPATH);
        for (WebElement itemType : itemTypeList) {
            if (itemType.getAttribute("title").equals(itemTypeTitle)) {
                itemType.click();
                return;
            }
        }
    }

    public InventoryPage selectKategorijeFilter(String itemTypeTitle){
        waitForElement(kategorijeFilterButton);
        clickOnKategorijeFilter();
        waitForElement(driver.findElementByXPath(Strings.CATEGORY_FILTER_LIST_XPATH));
        selectItemTypeFromKategorijeFilterList(itemTypeTitle);
        return new InventoryPage(driver);
    }


    public void clickFilterCheckboxForGenderBrandAndSize(String checkboxesXpath, String checkboxTitle) {
        List<WebElement> checkboxList = driver.findElementsByXPath(checkboxesXpath);
        for(WebElement checkbox : checkboxList) {
            // using 'for' attribute of a checkbox label because getText() would also return
            // the number of items available with that filter (e.g. "Za muskarce (16)")
            if(checkbox.getAttribute("for").equals(checkboxTitle)) {
                checkbox.click();
                return;
            }
        }
    }


    public void clickOnGenderFilterButton(){
        polFilterButton.click();
    }
    public void selectGenderFromFilterList(String checkboxTitle) {
        waitForElement(polFilterButton);
        clickOnGenderFilterButton();
        waitForElement(driver.findElementByXPath(Strings.GENDER_FILTER_LIST_XPATH));
        clickFilterCheckboxForGenderBrandAndSize(Strings.GENDER_FILTER_LIST_XPATH, checkboxTitle);
    }


    public void clickOnBrandFilterButton() {
        brendFilterButton.click();
    }
    public void selectBrandFromFilterList(String brand) {
        waitForElement(brendFilterButton);
        clickOnBrandFilterButton();
        waitForElement(driver.findElementByXPath(Strings.BRAND_FILTER_LIST_XPATH));
        clickFilterCheckboxForGenderBrandAndSize(Strings.BRAND_FILTER_LIST_XPATH, brand);
    }


    public void clickOnSizeFilterButton() {
        velicinaFilterButton.click();
    }
    public void selectSizeFromFilterList(String size) {
        clickOnSizeFilterButton();
        waitForElementToBeClickable(driver.findElementByXPath(Strings.SIZE_FILTER_LIST_XPATH));
        clickFilterCheckboxForGenderBrandAndSize(Strings.SIZE_FILTER_LIST_XPATH, size);
    }

     public void selectDropDownFilter(WebElement sortirajDropDown, String dropDownText) {
        waitForElement(sortirajDropDown);
        Select dropdown = new Select(sortirajDropDown);
        waitForElement(sortirajDropDown);
        dropdown.selectByVisibleText(dropDownText);
     }


     public void enterTextIntoSearchField(String text) {
        searchTextField.sendKeys(text);
        searchTextField.sendKeys(Keys.ENTER);

    }

    // search for item type e.g. 'T-shirt' and verify that correct list is displayed
    public void searchItemTypeByKeyword(String keyword) {
        BasePage basePage = new BasePage(driver);
        basePage.clickSearchIcon();
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
            // Going through all items on current page
            for(WebElement item : itemsList) {
                if(item.getAttribute("title").trim().equals(itemName)) {
                    item.click();

                    String pageTitle = driver.findElement(By.xpath(Strings.ALL_PAGES_TITLE_XPATH)).getText().trim();
                    assert pageTitle.equals(itemName.trim()) : "Error: wrong product";

                    return item;
                }
            }
            // If there are no items found on current page, click on next page button if it exists
            if(isElementPresent(nextPageButton)) {
                nextPageButton.click();
                // TODO stavi wait
                sleep(3);
            }
            // If there is no next page button we are on the last page and there is no item with such name
            else{
                break;
            }
        }
        assert false : "Error: There is no product with such name.";
        return null;
    }


    public ArrayList<Double> getAllItemPrices() {
        ArrayList<Double> itemPrices = new ArrayList<>();
        List<WebElement> stringPrices = driver.findElementsByXPath(Strings.ALL_ITEM_PRICES_XPATH);
        for(WebElement stringPrice : stringPrices){
            String priceWithRsd = stringPrice.getText();
            String price = priceWithRsd.substring(0, priceWithRsd.indexOf(",")).replace(".", "");
            print(price);
            itemPrices.add(Double.valueOf(price));
        }
        return itemPrices;
    }


    public List<WebElement> printAllItemsFromList() {
        List<WebElement> allItems = getAllItems();
        for(WebElement item : allItems) {
            String currentName = item.getText();
            print(allItems.indexOf(item) + ". " + currentName);
        }
        System.out.println(allItems.size());
        return allItems;
    }



















}





