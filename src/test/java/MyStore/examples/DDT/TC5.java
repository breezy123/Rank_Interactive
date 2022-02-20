package MyStore.examples.DDT;

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

public class TC5 {

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
    public void TestCase5() {

        String unitPrice="", quantity="";
        try {


            driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();

            driver.findElement(By.id("email")).isDisplayed();
            driver.findElement(By.id("email")).sendKeys(username);
            driver.findElement(By.id("passwd")).isDisplayed();
            driver.findElement(By.id("passwd")).sendKeys(password);
            driver.findElement(By.id("SubmitLogin")).isDisplayed();
            driver.findElement(By.id("SubmitLogin")).click();
            driver.findElement(By.xpath("//p[@class='info-account']")).isDisplayed();
            driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li[3]/a")).isDisplayed();
            driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li[3]/a")).click();//clicks T-Shirt
//            Thread.sleep(5000);
            driver.findElement(By.xpath("//div[@id='layered_block_left']//p[text()='Catalog']")).isDisplayed();

            //scroll down to Compare (0)
            JavascriptExecutor js = (JavascriptExecutor)driver;
            WebElement Element = driver.findElement(By.xpath("//div[@id='center_column']/div[2]/div[2]/form/button"));
            js.executeScript("arguments[0].scrollIntoView(true);", Element);
            Thread.sleep(500);

            driver.findElement(By.xpath("//h5//a[@title='Faded Short Sleeve T-shirts']")).isDisplayed();
            driver.findElement(By.xpath("//h5//a[@title='Faded Short Sleeve T-shirts']")).click();//clicks T-Shirt
            driver.findElement(By.xpath("//button[@name='Submit']//span")).isDisplayed();
            driver.findElement(By.xpath("//button[@name='Submit']//span")).click();//clicks T-Shirt

            driver.findElement(By.xpath("//span[contains(text(),'There is 1 item')]")).isDisplayed();

            driver.findElement(By.xpath("//span[contains(text(),'Proceed to checkout')]")).click();

            driver.findElement(By.id("cart_title")).isDisplayed();

            driver.findElement(By.xpath("//span//i[@class='icon-plus']")).isDisplayed();
            driver.findElement(By.xpath("//span//i[@class='icon-plus']")).click();

            Thread.sleep(5000);

            driver.findElement(By.xpath("//span//i[@class='icon-plus']")).isDisplayed();
            driver.findElement(By.xpath("//span//i[@class='icon-plus']")).click();

            Thread.sleep(8000);

            Calculate(unitPrice,quantity);

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void Calculate(String unitPrice, String quantity){

        unitPrice  = driver.findElement(By.xpath("//span[contains(text(),'$16.51')]")).getText();
        quantity = driver.findElement(By.xpath("//td[@class='cart_quantity text-center']//input[@name='quantity_1_1_0_642033_hidden']")).getAttribute("value");

        String totalShipping = driver.findElement(By.id("total_shipping")).getText();

        double dblUnitPrice = Double.parseDouble(unitPrice.substring(1));
        int intQuantity = Integer.parseInt(quantity);
        double dblTotalShipping = Double.parseDouble(totalShipping.substring(1));

        double totalCost = (dblUnitPrice*intQuantity)+dblTotalShipping; // grant total calculation

        System.out.println("Unit Price: "+dblUnitPrice+" Quantity: " +intQuantity +" Shipping Cost: "+ dblTotalShipping);
        System.out.println("Grand Total: "+ totalCost);

        unitPrice = String.valueOf("$"+totalCost);

        totalShipping = driver.findElement(By.id("total_price_without_tax")).getText();

        Assert.assertEquals(unitPrice,totalShipping);

        System.out.println("Convert Grant Total Calculation: "+ unitPrice+" and  UI Grant Total: "+totalShipping);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
