package MyStore.examples.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Menu {

    private WebDriver driver;
    private WebElement mainMenu;
    private Actions actions;

    private By mainCategoryWomen = By.xpath("//a[@title='Women']");
    private By subCategoryWomen = By.xpath("//div[@id='block_top_menu']/ul/li[1]/ul/li[1]/ul/li[2]/a");
    private By catalogLabel = By.xpath("//div[@id='layered_block_left']//p[text()='Catalog']");

    public Menu(WebDriver driver){
        this.driver = driver;
    }

    public void setActions(){
        actions = new Actions(driver);
    }

    public boolean hoverOverWomen(){

        try
        {
            mainMenu = driver.findElement(mainCategoryWomen);
            actions.moveToElement(mainMenu).perform();
            return true;
        }
        catch (Exception ex)
        {
            ex.getMessage();
            return false;
        }
    }

    public boolean clickOnSubCategory(){

        try
        {
            driver.findElement(subCategoryWomen).click();;
            return true;
        }
        catch (Exception ex)
        {
            ex.getMessage();
            return false;
        }
    }

    public boolean validateCatalog(){
        try
        {
            driver.findElement(catalogLabel).isDisplayed();
            return true;
        }
        catch (Exception ex)
        {
            ex.getMessage();
            return false;
        }
    }
}
