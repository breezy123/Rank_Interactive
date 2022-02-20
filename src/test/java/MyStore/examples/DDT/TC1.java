package MyStore.examples.DDT;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TC1 {

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
    public void TestCase1() {
        try {

            ArrayList<String> foundList = new ArrayList<>();
            boolean results = false;
            driver.findElement(By.id("search_query_top")).sendKeys("Casual Dress");
            driver.findElement(By.name("submit_search")).click();
            driver.findElement(By.xpath("//h5[@itemprop]//a[@title='Printed Summer Dress']")).click();

            Thread.sleep(5000);
            String value= driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();

            System.out.print(value);

            if(value.equalsIgnoreCase("Printed Summer Dress"))
            {
                results = true;
            }
            else
            {
                results = false;
            }

            Assert.assertTrue(results,"Searched items found");

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        ;
    }
}
