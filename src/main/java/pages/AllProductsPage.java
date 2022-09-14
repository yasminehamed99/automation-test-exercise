package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AllProductsPage extends BasePage{
    private By searchProductField= By.id("search_product");
    private By searchButton=By.id("submit_search");
    private By allProductText=By.xpath("//h2[@class=\"title text-center\"]");
    private By allProductElements=By.xpath("//div[@class=\"col-sm-4\"]//div[@class=\"productinfo text-center\"]//p");
    private By AddToCartButtons=By.xpath("//div[@class=\"productinfo text-center\"]//a[@class=\"btn btn-default add-to-cart\"]");
    private By continueShopping=By.xpath("//button[@class='btn btn-success close-modal btn-block']");
    private By viewCartButton=By.xpath("//a[@href=\"/view_cart\"]//u");

    public AllProductsPage(WebDriver driver) {
        super(driver);
    }
    public ProductPage searchForProduct(String productName){
        TypeOnFields(searchProductField,productName);
        ElementClick(searchButton);
        return new ProductPage(driver);

    }
    public String getAllProductText(){
       return GetElementText(allProductText);
    }
public List<WebElement> get_all_products(){
    return get_list_of_elements(allProductElements);
}
public List<String> getProductsName(){
        List<String>allProducts=new ArrayList<>();
        for(int i=0;i<get_all_products().size();i++){
            allProducts.add(get_all_products().get(i).getText());
        }
        return allProducts;
}
public void addToCart(int position){

performMouseHover(get_all_products().get(position));
clickUsingJavaScript(get_list_of_elements(AddToCartButtons).get(position));
}
public void continueShopping(){
        clickUsingJavaScript(WaitElementToBeLocated(continueShopping));
}
public CartPage viewCart(){

    clickUsingJavaScript(WaitElementToBeLocated(viewCartButton));
    return new CartPage(driver);
}
}
