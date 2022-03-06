import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ShoppingTests extends BaseTests{


    /**
     * Search by item type: 'patike', go through several pages, find a
     * specific item: 'ADIDAS Patike SUPERTURF ADVENTURE SW' and buy it
     *
     * Steps:
     * 1. Go to: 'https://www.tike.rs/'
     * 2. Click on search icon
     * 3. Enter 'patike' into search field
     * 4. Find and click 'ADIDAS Patike SUPERTURF ADVENTURE SW' on search list
     * 5. Choose item size '40'
     * 6. Click 'add to cart' button
     * 7. Get number from shopping cart badge
     * 8. Click on shopping cart icon
     * 9. Click remove item from cart
     * 10. Click continue shopping button
     *
     *
     * Expected results:
     * 3. Verify that search result list isn't empty
     * 4. Verify that correct item page is displayed
     * 5. Verify that size is available
     * 7. Verify that number on shopping cart badge is correct
     * 8. Verify that correct item is in cart
     * 9. Verify that remove modal is present and click on remove button
     * 9. Verify that empty cart alert message is displayed
     * 10. Verify that Inventory page is displayed
     */
    @Test
    public void shoppingTestStartingFromSeaarchField() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs/'");
            InventoryPage inventoryPage = new InventoryPage(driver);

            print("2. Click on search icon");
            print("3. Enter 'patike' into search field");
            inventoryPage.searchItemTypeByKeyword("patike");

            print("4. Find and click 'ADIDAS Patike SUPERTURF ADVENTURE SW' on search list");
            print("4. Verify that correct item page is displayed");
            inventoryPage.findItemByName("ADIDAS Patike SUPERTURF ADVENTURE SW");

            InventoryItemPage inventoryItemPage = new InventoryItemPage(driver);
            print("5. Choose item size '40'");
            print("5. Verify that size is available");
            inventoryItemPage.chooseItemSize("40");

            Integer currentNumber = inventoryItemPage.getNumberFromShoppingCartIcon();

            print("6. Click 'add to cart' button");
            inventoryItemPage.clickAddToCartButton();

            print("7. Get number from shopping cart badge");
            inventoryItemPage.waitForShoppingBadgeNumber(currentNumber, 1);
            currentNumber = inventoryItemPage.getNumberFromShoppingCartIcon();

            print("7. Verify that number on shopping cart badge is correct");
            assert currentNumber == 1 : "Error: Wrong number of items. Expected: 1. Actual: " + currentNumber;

            print("8. Click on shopping cart icon");
            ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
            shoppingCartPage.clickShoppingCartIcon();

            print("8. Verify that correct item is in cart");
            shoppingCartPage.findCartItemByName("ADIDAS Patike SUPERTURF ADVENTURE SW");

            print("9. Click remove item from cart");
            shoppingCartPage.clickRemoveFromCartButton();

            print("9. Verify that remove modal is present and click on remove button");
            assert shoppingCartPage.isElementPresent(shoppingCartPage.removeFromCartModalText) : "Error: Remove from" +
                    "cart modal should be present.";
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

}
