package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage{
    private By searchedProductText= By.xpath("//h2[@class=\"title text-center\"]");
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    public String getSearchedProductText(){
       return getElementText(searchedProductText);
    }
}
