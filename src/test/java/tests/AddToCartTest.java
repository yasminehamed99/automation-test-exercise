package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AllProductsPage;
import pages.CartPage;
import pages.LoginPage;
import pages.NavigationBarPage;

import static filesManager.ReaderFromFiles.getPropertyByKey;


public class AddToCartTest extends BaseTest {
    NavigationBarPage navigationBarPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void loginToApp() {

        navigationBarPage = new NavigationBarPage(driver);
        LoginPage loginPage = navigationBarPage.clickOnLogin();
        loginPage.login(getPropertyByKey(propertyFileName, "USER_EMAIL"), getPropertyByKey(propertyFileName, "USER_PASSWORD"));

    }

    @Test
    public void add_to_cart_test() {

        AllProductsPage allProductsPage = navigationBarPage.goToProductsPage();
        allProductsPage.scrollToSpecificProduct(600);
        allProductsPage.addToCart(0);
        allProductsPage.continueShopping();
        allProductsPage.addToCart(1);
        CartPage cartPage = allProductsPage.viewCart();

        //verify that two items are added
        softAssert.assertEquals(String.valueOf(cartPage.getTotalNumberOfItems()), "2", "The items didn't added correctly");
        //verify products quantities
        softAssert.assertFalse(cartPage.getItemQuantity(0).isEmpty(), "the quantity is empty");
        softAssert.assertFalse(cartPage.getItemQuantity(1).isEmpty(), "the quantity is empty");
        //verify products prices
        softAssert.assertEquals(cartPage.getItemPrice(0), "Rs. 500", "The product price is not correct, it should be Rs. 500");
        softAssert.assertEquals(cartPage.getItemPrice(1), "Rs. 400", "The product price is not correct, it should be Rs. 400");
        //verify products total prices
        softAssert.assertFalse(cartPage.getItemTotalPrice(0).isEmpty(), "the product total price is empty");
        softAssert.assertFalse(cartPage.getItemTotalPrice(1).isEmpty(), "the product total price is empty");
        //check the total price is true
        softAssert.assertEquals(cartPage.getItemTotalPrice(0), "Rs. " + cartPage.getItemTotalPriceAmount(0));
        softAssert.assertEquals(cartPage.getItemTotalPrice(1), "Rs. " + cartPage.getItemTotalPriceAmount(1));
        softAssert.assertAll();

    }
}
