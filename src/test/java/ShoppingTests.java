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
            InventoryPage inventoryPage = new InventoryPage(driver);

            print("2. Verify that 'Patike' URL is displayed");
            inventoryPage.openNavBarCategory(Strings.PATIKE_NAVBAR_TITLE, Strings.PATIKE_URL);

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
            shoppingCartPage.removeItemFromCart(Strings.ADIDAS_PATIKE_TITLE);

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
     * 5. Close 'Pomoc' toggle banner if open
     * 6. Select size '38'
     * 7. Click add to cart
     * 8. Get shopping cart badge number
     * 9. Select 'Patike' category from navigation bar
     * 10. Select: 'NIKE Patike Waffle One'
     * 11. Select size'38'
     * 12. Click add to cart
     * 13. Get shopping cart badge number
     * 14. Click shopping cart icon
     * 15. Print shopping cart items with prices
     * 16. Print final price
     * 17. Select 'Regularna isporuka' radio button
     *
     * Expected results:
     * 2. Verify that 'Odeca' URL is displayed
     * 8. Verify that number '1' is displayed on shopping cart badge
     * 9. Verify that 'Patike' URL is displayed
     * 10. Verify that 'NIKE Patike Waffle One' item page is displayed
     * 13. Verify that number '2' is displayed on shopping cart badge
     * 14. Verify that shopping cart URL is displayed
     */

    @Test
    public void buyTwoItemsAndCheckout() {
        ChromeDriver driver = openChromeDriver();
        try {
            print("1. Go to: 'https://www.tike.rs/'");
            HomePage homePage = new HomePage(driver);

            login(driver, Strings.EMAIL, Strings.PASSWORD);
            sleep(2);

            print("2. Select 'Odeca' category from navigation bar");
            print("2. Verify that 'Odeca' URL is displayed");
            InventoryPage inventoryPage = homePage.openNavBarCategory(Strings.ODECA_NAVBAR_TITLE, Strings.ODECA_URL);

            print("3. Select 'Za zene' gender filter");
            inventoryPage.selectGenderFromFilterList(Strings.WOMEN_CHECKBOX_TITLE);
            sleep(3);
            print("4. On first page select 7th item");
            InventoryItemPage inventoryItemPage = inventoryPage.getItemByIndex(7);

            print("5. Close 'Pomoc' toggle banner");
            inventoryItemPage.closeClosePomocToggleBanner();

            print("6. Select size 'M'");
            inventoryItemPage.chooseFirstAvailableItemSize();
            sleep(3);
            Integer currentNumber = inventoryItemPage.getNumberFromShoppingCartIcon();

            print("7. Click add to cart");
            inventoryItemPage.clickAddToCartButton();
            sleep(3);

            print("8. Get shopping cart badge number");
            inventoryItemPage.waitForShoppingBadgeNumber(currentNumber, 1);
            currentNumber = inventoryItemPage.getNumberFromShoppingCartIcon();

            print("8. Verify that number '1' is displayed on shopping cart badge");
            assert currentNumber == 1 : "Error: Wrong number of items. Expected: 1. Actual: " + currentNumber;

            print("9. Select 'Patike' category from navigation bar");
            print("9. Verify that 'Patike' URL is displayed");
            InventoryPage inventoryPage1 = inventoryItemPage.openNavBarCategory(Strings.PATIKE_NAVBAR_TITLE, Strings.PATIKE_URL);

            print("10. Select: 'NIKE Patike Waffle One'");
            print("10. Verify that 'NIKE Patike Waffle One' item page is displayed");
            InventoryItemPage inventoryItemPage1 = inventoryPage1.findItemByName(Strings.NIKE_PATIKE_TITLE);

            print("11. Select size'38'");
            inventoryItemPage.chooseItemSize("38");

            print("12. Click add to cart");
            inventoryItemPage1.clickAddToCartButton();
            sleep(3);

            print("13. Get shopping cart badge number");
            inventoryItemPage1.waitForShoppingBadgeNumber(currentNumber, 1);
            currentNumber = inventoryItemPage.getNumberFromShoppingCartIcon();

            print("13. Verify that number '2' is displayed on shopping cart badge");
            assert currentNumber == 2 : "Error: Wrong number of items. Expected: 2. Actual: " + currentNumber;

            print("14. Click shopping cart icon");
            ShoppingCartPage shoppingCartPage = inventoryItemPage1.clickShoppingCartIcon();

            print("14. Verify that shopping cart URL is displayed");
            String actualUrl = driver.getCurrentUrl();
            assertUrl(actualUrl, Strings.SHOPPING_CART_PAGE_URL);

            print("15. Print shopping cart items with prices");
            shoppingCartPage.printListOfAllCartItems();

            print("16. Print final price");
            shoppingCartPage.printUkupnoZaPlacanje();
            sleep(3);

            print("17. Select 'Regularna isporuka' delivery option");
            shoppingCartPage.clickRegularnaIsporukaRadioButton();

            print("18. Select 'Gotovina' payment option");
            shoppingCartPage.clickGotovinaRadioButton();

            //todo
            //shoppingCartPage.clickReCaptcha();

        }finally {
           driver.quit();
        }



    }

}
