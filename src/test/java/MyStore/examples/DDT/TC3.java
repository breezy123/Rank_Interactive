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
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TC3 {

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
    public void TestCase3() {

        try {

            boolean results =false;
            FileInputStream file = new FileInputStream("C:\\MyStoreexamples\\DDT\\data.xlsx");

            XSSFWorkbook _workBook = new XSSFWorkbook(file); //providing file path

            XSSFSheet sheet =  _workBook.getSheet("Sheet1"); // providing sheet name

            int noOfRows = sheet.getLastRowNum();

            System.out.println("NO. of Records in the Excel Sheet: "+noOfRows);

            XSSFRow currentRow = sheet.getRow(1);

            String searchItem = currentRow.getCell(0).getStringCellValue();

            driver.findElement(By.id("search_query_top")).sendKeys(searchItem);
            driver.findElement(By.name("submit_search")).click();
            driver.findElement(By.xpath("//h5[@itemprop]//a[@title='Printed Summer Dress']")).click();

            Thread.sleep(3000);

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
    }
}
