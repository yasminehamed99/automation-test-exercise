package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    public WebElement waitElementToBeLocated(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);

    }
    public List<WebElement> get_list_of_elements(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        return driver.findElements(locator);


    }
    public void elementClick(By locator){
        waitElementToBeLocated(locator).click();
    }
    public void typeOnFields(By locator, String text){
        waitElementToBeLocated(locator).sendKeys(text);
    }
    public String getElementText(By locator){
        return waitElementToBeLocated(locator).getText();
    }

    public String get_element_color(By locator){
       String color= waitElementToBeLocated(locator).getCssValue("color");
       return color;
    }
    public void performMouseHover(WebElement element){
        actions.moveToElement(element).build().perform();

    }
    public void scrollVertically(int y_dim){
        actions.scrollByAmount(0,y_dim).perform();

    }
    public void clickUsingJavaScript(WebElement element){
        JavascriptExecutor executor=(JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);

    }
}



