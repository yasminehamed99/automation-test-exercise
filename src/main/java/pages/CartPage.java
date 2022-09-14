package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{
    private By items= By.xpath("//tbody//tr");
    private By itemsQuantity=By.xpath("//tbody//button");
    private By itemsPrice=By.xpath("//tbody//td[@class=\"cart_price\"]//p");
    private By itemsTotalPrice=By.xpath("//tbody//td[@class=\"cart_total\"]//p");
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public int getTotalNumberOfItems(){
       return get_list_of_elements(items).size();
    }
    public String getItemQuantity(int itemPosition){
       return get_list_of_elements(itemsQuantity).get(itemPosition).getText();
    }
    public String getItemPrice(int itemPosition){
        return get_list_of_elements(itemsPrice).get(itemPosition).getText();
    }
    public String getItemTotalPrice(int itemPosition){
        return get_list_of_elements(itemsTotalPrice).get(itemPosition).getText();

    }
    public String  getItemTotalPriceAmount(int position){
        String productTotalPrice = String.valueOf(Integer.parseInt(getItemPrice(position).replace("Rs. ","")) * Integer.parseInt(getItemQuantity(position)));
        return productTotalPrice;
    }

}
