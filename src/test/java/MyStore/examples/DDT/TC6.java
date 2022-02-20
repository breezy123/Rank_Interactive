package MyStore.examples.DDT;

import MyStore.examples.PageObject.Menu;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TC6 {

    WebDriver driver;

    @BeforeMethod
    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com/");
    }

    @Test
    public void TestCase6() {

        try {

            Menu mainMenu =new Menu(driver);
            mainMenu.setActions();

            mainMenu.hoverOverWomen();
            Thread.sleep(1000);

            mainMenu.clickOnSubCategory();

            mainMenu.validateCatalog();

            Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Blouses - My Store"),"The correct Main and Subcategory were selected.");

            System.out.println("Subcategory Page Title: "+driver.getTitle().toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
