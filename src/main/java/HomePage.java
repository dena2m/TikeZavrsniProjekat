import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{

        @FindBy(xpath = "//a[@href = 'https://www.tike.rs/patike']")
        WebElement patikeButton;

        @FindBy(xpath = "//a[@href = 'https://www.tike.rs/odeca']")
        WebElement odecaButton;

        @FindBy(xpath = "//a[@href = 'https://www.tike.rs/brendovi']")
        WebElement brendoviButton;

        @FindBy(xpath = "//a[@href = 'https://www.tike.rs/proizvodi/hot-sale']")
        WebElement saleButton;

        @FindBy(xpath = "//a[@href = 'https://www.tike.rs/magazin']")
        WebElement blogButton;


        public HomePage(ChromeDriver driver) {
        super(driver);
    }




    }
