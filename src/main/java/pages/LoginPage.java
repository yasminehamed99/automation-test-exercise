package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    private By emailField= By.name("email");
    private By passwordField=By.name("password");
    private By loginBtn=By.xpath("//button[@data-qa=\"login-button\"]");
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void login(String email,String password){
        typeOnFields(emailField,email);
        typeOnFields(passwordField,password);
        elementClick(loginBtn);

    }
}
