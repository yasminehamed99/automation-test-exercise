package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        actions=new Actions(driver);

    }
    public WebElement WaitElementToBeLocated(By loactor){
        wait.until(ExpectedConditions.visibilityOfElementLocated(loactor));
        wait.until(ExpectedConditions.elementToBeClickable(loactor));
        return driver.findElement(loactor);

    }
    public List<WebElement> get_list_of_elements(By loactor){


        return driver.findElements(loactor);


    }
    public void ElementClick(By locator){
        WaitElementToBeLocated(locator).click();
    }
    public void TypeOnFields(By locator,String text){
        WaitElementToBeLocated(locator).sendKeys(text);
    }
    public Select SelectElement(By locator){
        Select select=new Select(WaitElementToBeLocated(locator));
        return select;
    }
    public String GetElementText(By locator){
        return WaitElementToBeLocated(locator).getText();
    }

    public String get_element_color(By locator){
       String color= WaitElementToBeLocated(locator).getCssValue("color");
       return color;
    }
    public void performMouseHover(WebElement element){
        actions.moveToElement(element).build().perform();

    }
    public void clickUsingJavaScript(WebElement element){
        JavascriptExecutor executor=(JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);

    }
}



