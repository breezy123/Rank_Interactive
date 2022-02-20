package MyStore.examples.DDT;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class TC2 {

    WebDriver driver;
    WebElement element;

    @BeforeMethod
    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com/");
    }

    @Test(dataProvider = "MyonlineStore")
    public void TestCase2(String searchItem) {

        String value = "";
        boolean status = false;
        try {

            driver.findElement(By.id("search_query_top")).sendKeys(searchItem);
            driver.findElement(By.name("submit_search")).click();

            Thread.sleep(3000);

            switch (searchItem) {
                case "Casual Dress":
                    driver.findElement(By.xpath("//h5[@itemprop]//a[@title='Printed Summer Dress']")).click();
                    break;

                case "Blouse":
                    driver.findElement(By.xpath("//a[@class='product-name']/..//a[@title='Blouse']")).click();
                    break;

                case "Faded Short Sleeve T-shirts":
                    driver.findElement(By.xpath("//a[@class='product-name']/..//a[@title='Faded Short Sleeve T-shirts']")).click();
                    break;
            }

            Thread.sleep(3000);

            value = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();

            if (value.equalsIgnoreCase("Printed Summer Dress")) {
                status = true;

            } else if (value.equalsIgnoreCase("Blouse")) {
                status = true;
            }
            else if(value.equalsIgnoreCase("Faded Short Sleeve T-shirts")){
                status = true;
            }
            else
            {
                status =false;
            }

            Assert.assertTrue(status);

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @DataProvider(name = "MyonlineStore") //our data provider
    public Object[][] passData() {
        return new Object[][]{
                {"Casual Dress"},
                {"Blouse"},
                {"Faded Short Sleeve T-shirts"}
        };
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
