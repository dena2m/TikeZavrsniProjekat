import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {


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


    public void clickOnKategorijeFilter() {
        kategorijeFilter.click();
    }

    public ProductsPage selectCategoryFilter(){
       clickOnKategorijeFilter();
       selectCategoryFromKategorijeFilterList("Jakna");
       return new ProductsPage(driver);
    }

    public void selectCategoryFromKategorijeFilterList(String categoryTitle) {
        List<WebElement> categoryList = driver.findElementsByXPath(Strings.CATEGORY_FILTER_LIST_XPATH);
        for (WebElement category : categoryList) {
            if (category.getAttribute("title").equals(categoryTitle)) {
                category.click();
                return;
            }
        }
    }


    public void clickOnGenderFilter(){
        polFilter.click();
    }

    public void selectGenderFilter(String filter) {
        clickOnGenderFilter();
        sleep(3);
        clickFilterCheckbox(Strings.GENDER_FILTER_LIST_XPATH, filter);
    }


    public void clickFilterCheckbox(String checkboxesXpath, String checkboxTitle) {
        List<WebElement> checkboxList = driver.findElementsByXPath(checkboxesXpath);
        for(WebElement checkbox : checkboxList) {
            if(checkbox.getAttribute("for").equals(checkboxTitle)) {
                checkbox.click();
                return;
            }
        }
    }











   // public ProductsPage selectFromKategorijeDropDownFilter() {
     //   Select dropdown = new Select(kategorijeDropDownFilter);
       // dropdown.deselectByIndex(5);
        //return this;
   // }
}





