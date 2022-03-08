import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NavigationBarTest extends BaseTests {

    /**
     * Opening and verifying all categories from navigation bar
     *
     * Steps:
     * 1. Go to: "https://www.tike.rs".
     * 2. On navigation bar click on 'PATIKE' category
     * 3. On navigation bar click on 'ODEĆA' category
     * 4. On navigation bar click on 'BRENDOVI' category
     * 5. On navigation bar click on 'SALE' category
     * 6. On navigation bar click on 'BLOG' category
     *
     * Expected results:
     * 2-6 Verify that you are redirected to correct category URL
     * 2-6 Verify the correct page title
     */

    @Test
    public void selectCategoryPageFromNavbar() {
        ChromeDriver driver = openChromeDriver();

        try {
            print("1. Go to: 'https://www.tike.rs'");
            HomePage homePage = new HomePage(driver);

            print("2. On navigation bar click on 'PATIKE' category and verify that the correct URL is displayed");
            homePage.openNavBarCategory(Strings.PATIKE_NAVBAR_TITLE, Strings.PATIKE_URL);

            print("2. Verify the correct page title");
            String currentPageTitle = driver.findElement(By.xpath(Strings.ALL_PAGES_TITLE_XPATH)).getText();
            assert currentPageTitle.equals(Strings.PATIKE_PAGE_TITLE) : "Error: Wrong page title. Expected: " +
                    Strings.PATIKE_PAGE_TITLE + ". Actual: " + currentPageTitle;


            print("3. On navigation bar click on 'ODEĆA' category and verify that the correct URL is displayed");
            homePage.openNavBarCategory(Strings.ODECA_NAVBAR_TITLE, Strings.ODECA_URL);

            print("3. Verify the correct page title");
            currentPageTitle = driver.findElement(By.xpath(Strings.ALL_PAGES_TITLE_XPATH)).getText();
            assert currentPageTitle.equals(Strings.ODECA_PAGE_TITLE) : "Error: Wrong page title. Expected: " +
                    Strings.ODECA_PAGE_TITLE + ". Actual: " + currentPageTitle;


            print("4. On navigation bar click on 'BRENDOVI' category and verify that the correct URL is displayed");
            homePage.openNavBarCategory(Strings.BRENDOVI_NAVBAR_TITLE, Strings.BRENDOVI_URL);

            print("4. Verify the correct page title");
            currentPageTitle = driver.findElement(By.xpath(Strings.ALL_PAGES_TITLE_XPATH)).getText();
            assert currentPageTitle.equals(Strings.BRENDOVI_PAGE_TITLE) : "Error: Wrong page title. Expected: " +
                    Strings.BRENDOVI_PAGE_TITLE + ". Actual: " + currentPageTitle;


            print("5. On navigation bar click on 'SALE' category and verify that the correct URL is displayed");
            homePage.openNavBarCategory(Strings.SALE_NAVBAR_TITLE, Strings.SALE_URL);

            print("5.Verify the correct page title");
            currentPageTitle = driver.findElement(By.xpath(Strings.ALL_PAGES_TITLE_XPATH)).getText();
            assert currentPageTitle.equals(Strings.SALE_PAGE_TITLE) : "Error: Wrong title. Expected: " +
                    Strings.SALE_PAGE_TITLE + ". Actual: " + currentPageTitle;


            print("6. On navigation bar click on 'BLOG' category and verify that the correct URL is displayed");
            homePage.openNavBarCategory(Strings.BLOG_NAVBAR_TITLE, Strings.BLOG_URL);

            print("6.Verify the correct page title");
            currentPageTitle = driver.findElement(By.xpath(Strings.ALL_PAGES_TITLE_XPATH)).getText();
            assert currentPageTitle.equals(Strings.BLOG_PAGE_TITLE) : "Error: Wrong title. Expected: " +
                    Strings.BLOG_PAGE_TITLE + ". Actual: " + currentPageTitle;

        }finally {
            driver.quit();
        }
    }














}

