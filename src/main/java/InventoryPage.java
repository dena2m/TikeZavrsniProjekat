import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @FindBy(xpath = "//label[@for = '28_adidas']")
    WebElement adidasCheckbox;

    @FindBy(xpath = "//label[@for = '13_za-muskarce']")
    WebElement zaMuskarceCheckbox;

    @FindBy(xpath = "//label[@for = '13_za-zene']")
    WebElement zaZeneCheckbox;

    @FindBy(xpath = "//label[@for = 'f_eusize_XL']")
    WebElement xlVelicina;



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

    public void selectKategorijeFilter(String itemTypeTitle){
        waitForElementToBeClickable(kategorijeFilterButton);
        clickOnKategorijeFilter();
        //waitForElementToBeClickable(driver.findElementByXPath(Strings.CATEGORY_FILTER_LIST_XPATH));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        selectItemTypeFromKategorijeFilterList(itemTypeTitle);
    }


    public void clickFilterCheckbox(String checkboxesXpath, String checkboxTitle) {
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

    public void selectGenderFromFilterList(String checkboxTitle, WebElement genderCheckbox) {
        waitForElementToBeClickable(polFilterButton);
        clickOnGenderFilterButton();
        //todo treba wait za checkboxTitle
        waitForElement(genderCheckbox);
        clickFilterCheckbox(Strings.GENDER_FILTER_LIST_XPATH, checkboxTitle);
    }


    public void clickOnBrandFilterButton() {
        brendFilterButton.click();
    }

    public void selectBrandFromFilterList(String brand, WebElement brandCheckbox) {
        waitForElementToBeClickable(brendFilterButton);
        clickOnBrandFilterButton();
       //todo treba wait za brend
        waitForElement(brandCheckbox);
        clickFilterCheckbox(Strings.BRAND_FILTER_LIST_XPATH, brand);
    }


    public void clickOnSizeFilterButton() {
        velicinaFilterButton.click();
    }

    public void selectSizeFromFilterList(String size, WebElement sizeCheckbox) {
        waitForElement(velicinaFilterButton);
        clickOnSizeFilterButton();
        //todo treba wait za size
        waitForElement(sizeCheckbox);
        clickFilterCheckbox(Strings.SIZE_FILTER_LIST_XPATH, size);
    }


     public void selectDropDownFilter(WebElement sortirajDropDown, String dropDownText) {
        waitForElement(sortirajDropDown);
        waitForElementToBeClickable(sortirajDropDown);
        Select dropdown = new Select(sortirajDropDown);
        // todo koji wait ovde treba waitForElement(sortirajDropDown);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dropdown.selectByVisibleText(dropDownText);
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


    public InventoryItemPage findItemByName(String itemName) {
        while (true){
            List<WebElement> itemsList = getAllItems();
            // Going through all items on current page
            for(WebElement item : itemsList) {
                if(item.getAttribute("title").trim().equals(itemName)) {
                    item.click();

                    String pageTitle = driver.findElement(By.xpath(Strings.ALL_PAGES_TITLE_XPATH)).getText().trim();
                    assert pageTitle.equals(itemName.trim()) : "Error: wrong product";

                    return new InventoryItemPage(driver);
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


    public ArrayList<Double> getAllItemPrices(String listXpath) {
        ArrayList<Double> itemPrices = new ArrayList<>();
        List<WebElement> stringPrices = driver.findElementsByXPath(listXpath);
        for(WebElement stringPrice : stringPrices){
            String priceWithRsd = stringPrice.getText();
            String price = priceWithRsd.substring(0, priceWithRsd.indexOf(",")).replace(".", "");
            print(price);
            itemPrices.add(Double.valueOf(price));
        }
        return itemPrices;
    }

   public InventoryItemPage getItemByIndex(int index) {
        List<WebElement> allItems = getAllItems();
        WebElement item = allItems.get(index);
        item.click();
        return new InventoryItemPage(driver);
   }

}





