package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBarPage extends BasePage {
    private By homeButton = By.className("fa-home");
    private By productsButton = By.xpath("//a[@href=\"/products\"]");
    private By loginButton = By.xpath("//a[@href=\"/login\"]");

    public NavigationBarPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickOnLogin() {
        elementClick(loginButton);
        return new LoginPage(driver);


    }

    public AllProductsPage goToProductsPage() {
        elementClick(productsButton);
        return new AllProductsPage(driver);
    }

    public String HomeButtonColor() {
        return get_element_color(homeButton);
    }

}
