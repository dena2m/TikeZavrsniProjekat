import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ShoppingTests extends BaseTests{


    /**
     * Select 'patike' from navigation bar, go through several pages, find a specific
     * item: 'ADIDAS Patike SUPERTURF ADVENTURE SW' add and remove from shopping cart
     *
     * Steps:
     * 1. Go to: 'https://www.tike.rs/'
     * 2. Select 'Patike' category from navigation bar
     * 3. Find and click 'ADIDAS Patike SUPERTURF ADVENTURE SW' on item list
     * 4. Choose item size '40'
     * 5. Click 'add to cart' button
     * 6. Get shopping cart badge number
     * 7. Click on shopping cart icon
     * 8. Click remove item from cart
     * 9. Click modal remove button
     * 10. Click continue shopping button
     *
     *
     * Expected results:
     * 2. Verify that 'Patike' URL is displayed
     * 3. Verify that correct item page is displayed
     * 4. Verify that size is available
     * 6. Verify that shopping cart badge number is correct
     * 7. Verify that correct item is in cart
     * 8. Verify that remove modal is present
     * 9. Verify that empty cart alert message is displayed
     * 10. Verify that Inventory page is displayed
     */
    @Test
    public void addAndRemoveItemFromCart() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs/'");
            HomePage homePage = new HomePage(driver);

            print("2. Verify that 'Patike' URL is displayed");
            InventoryPage inventoryPage = homePage.openNavBarCategory(Strings.PATIKE_NAVBAR_TITLE, Strings.PATIKE_URL);

            print("3. Find and click 'ADIDAS Patike SUPERTURF ADVENTURE SW' on item list");
            print("3. Verify that correct item page is displayed");
            InventoryItemPage inventoryItemPage = inventoryPage.findItemByName(Strings.ADIDAS_PATIKE_TITLE);

            print("4. Choose item size '40'");
            print("4. Verify that size is available");
            inventoryItemPage.chooseItemSize("40");

            Integer currentNumber = inventoryItemPage.getNumberFromShoppingCartIcon();

            print("5. Click 'add to cart' button");
            inventoryItemPage.clickAddToCartButton();

            print("6. Get shopping cart badge number");
            inventoryItemPage.waitForShoppingBadgeNumber(currentNumber, 1);
            currentNumber = inventoryItemPage.getNumberFromShoppingCartIcon();

            print("6. Verify that shopping cart badge number is correct");
            assert currentNumber == 1 : "Error: Wrong number of items. Expected: 1. Actual: " + currentNumber;

            print("7. Click on shopping cart icon");
            ShoppingCartPage shoppingCartPage = inventoryItemPage.clickShoppingCartIcon();

            print("7. Verify that correct item is in cart");
            shoppingCartPage.findCartItemByName("ADIDAS Patike SUPERTURF ADVENTURE SW");

            print("8. Click remove item from cart");
            shoppingCartPage.clickRemoveFromCartButton();
            sleep(3);
            print("8. Verify that remove modal is present ");
            assert shoppingCartPage.isElementPresent(shoppingCartPage.removeFromCartModalText) : "Error: Remove from" +
                    "cart modal should be present.";

            print("9. Click on modal remove button");
            shoppingCartPage.clickVerifyRemovingItemFromCartButton();
            sleep(3);
            print("9. Verify that empty cart alert message is displayed");
            String currentMessage = driver.findElement(By.xpath(Strings.EMPTY_CART_MESSAGE_XPATH)).getText().trim();
            assert currentMessage.equals(Strings.EMPTY_CART_MESSAGE) : "Error: Empty cart message should be displayed";

            print("10. Click continue shopping button");
            shoppingCartPage.clickContinueShoppingButton();

            print("10. Verify that Inventory page is displayed");
            InventoryPage inventoryPage1 = new InventoryPage(driver);
            String actualUrl = driver.getCurrentUrl();
            inventoryPage1.assertUrl(actualUrl, Strings.PROIZVODI_URL);
        }
        finally {
            driver.quit();
        }
    }

    /**
     * Buy two items and fill checkout data
     * 1. Go to: 'https://www.tike.rs/'
     * 2. Select 'Odeca' category from navigation bar
     * 3. Select 'Za zene' gender filter
     * 4. On first page select 7th item
     * 5. Select size '38'
     * 6. Click add to cart
     * 7. Get shopping cart badge number
     * 8. Select 'Patike' category from navigation bar
     * 9. Select: 'NIKE Patike Waffle One'
     * 10. Select size'38'
     * 11. Click add to cart
     * 12. Get shopping cart badge number
     * 13. Click shopping cart icon
     * 14.
     *
     */
    @Test
    public void buyTwoItemsAndCheckout() {
        ChromeDriver driver = openChromeDriver();
        try {
            print("1. Go to: 'https://www.tike.rs/'");
            HomePage homePage = new HomePage(driver);

            print("2. Select 'Odeca' category from navigation bar");
            InventoryPage inventoryPage = homePage.openNavBarCategory(Strings.ODECA_NAVBAR_TITLE, Strings.ODECA_URL);

            print("3. Select 'Za zene' gender filter");
            inventoryPage.selectGenderFromFilterList(Strings.GENDER_FILTER_LIST_XPATH, inventoryPage.zaZeneCheckbox);

            print("4. On first page select 7th item");
            InventoryItemPage inventoryItemPage = inventoryPage.getItemByIndex(6);

            print("5. Select size 'M'");
            inventoryItemPage.chooseItemSize("M");
            sleep(3);
            Integer currentNumber = inventoryItemPage.getNumberFromShoppingCartIcon();

            print("6. Click add to cart");
            inventoryItemPage.clickAddToCartButton();
            sleep(3);
            print("7. Get shopping cart badge number");
            inventoryItemPage.waitForShoppingBadgeNumber(currentNumber, 1);
            currentNumber = inventoryItemPage.getNumberFromShoppingCartIcon();

            print("7. Verify that shopping cart badge number is correct");
            assert currentNumber == 1 : "Error: Wrong number of items. Expected: 1. Actual: " + currentNumber;

            print("8. Select 'Patike' category from navigation bar");
            InventoryPage inventoryPage1 = inventoryItemPage.openNavBarCategory(Strings.PATIKE_NAVBAR_TITLE, Strings.PATIKE_URL);

            print("9. Select: 'NIKE Patike Waffle One'");
            InventoryItemPage inventoryItemPage1 = inventoryPage1.findItemByName(Strings.NIKE_PATIKE_TITLE);

            print("10. Select size'38'");
            inventoryItemPage.chooseItemSize("38");

            print("11. Click add to cart");
            inventoryItemPage1.clickAddToCartButton();
            sleep(3);
            print("12. Get shopping cart badge number");
            inventoryItemPage1.waitForShoppingBadgeNumber(currentNumber, 1);
            currentNumber = inventoryItemPage.getNumberFromShoppingCartIcon();

            print("12. Verify that shopping cart badge number is correct");
            assert currentNumber == 2 : "Error: Wrong number of items. Expected: 2. Actual: " + currentNumber;

            print("12. Click shopping cart icon");
            ShoppingCartPage shoppingCartPage = inventoryItemPage1.clickShoppingCartIcon();

            inventoryPage.getAllItemPrices(Strings.SHOPPING_CART_ITEM_LIST_XPATH);


        }finally {
            //driver.quit();
        }







    }

}
