import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenshotListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result){

        Object currentClass = result.getInstance();
        ChromeDriver driver = ((BaseTests) currentClass).getDriver();

        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Formatting screenshot name
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            String scrName = formatter.format(calendar.getTime()) + ".png";

            // Making screenshot path
            String currentPath = Paths.get(System.getProperty("user.dir")).toString();
            Path destPath = Paths.get(currentPath, "test-output", "screenshots", scrName);
            File destFile = new File(destPath.toString());

            FileUtils.copyFile(screenshotFile, destFile);

            String src = "screenshots/" + scrName;
            Reporter.log("<br> <a href='"+src+ "' target='_blank'> <img src='"+src+ "' width='40%'/> </a> <br>");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
