package MyStore.examples.DDT;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

public class TC4 {

    WebDriver driver;

    String username = "afredericksdvt@gmail.com";
    String password = "2022-QA-Engineer";


    @BeforeMethod
    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com/");
    }

    @Test
    public void TestCase4() {

        boolean status =false;

        try {

            driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();

            driver.findElement(By.id("email")).isDisplayed();
            driver.findElement(By.id("email")).sendKeys(username);
            driver.findElement(By.id("passwd")).isDisplayed();
            driver.findElement(By.id("passwd")).sendKeys(password);
            driver.findElement(By.id("SubmitLogin")).isDisplayed();
            driver.findElement(By.id("SubmitLogin")).click();
//            driver.findElement(By.xpath("//p[@class='info-account']")).isDisplayed();
            Thread.sleep(5000);

            String myAccount = driver.findElement(By.xpath("//p[@class='info-account']")).getText();

            if(myAccount.equalsIgnoreCase("Welcome to your account. Here you can manage all of your personal information and orders.")){
                status =true;
            }

            Assert.assertTrue(status,"logged in successfully");
            System.out.print("User has logged in successfully.");

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
