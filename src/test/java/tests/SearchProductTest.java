package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AllProductsPage;
import pages.LoginPage;
import pages.NavigationBarPage;
import pages.ProductPage;

import java.util.List;

import static FilesManager.ReaderFromFiles.getJsonValueByKey;
import static FilesManager.ReaderFromFiles.getPropertyByKey;

public class SearchProductTest extends BaseTest{
    NavigationBarPage navigationBarPage;
    SoftAssert softAssert=new SoftAssert();
    String searchProductTestData = "searchProductTestData.json" ;
    String productName ;
    @BeforeClass
    public void loadTestData ()
    {
        productName = getJsonValueByKey ( searchProductTestData, "productName") ;
    }

    @BeforeMethod
    public void loginToApp(){
         navigationBarPage = new NavigationBarPage(driver);
        LoginPage loginPage = navigationBarPage.clickOnLogin();
        loginPage.login( getPropertyByKey(propertyFileName , "USER_EMAIL"),getPropertyByKey(propertyFileName,"USER_PASSWORD"));
    }
    @Test
    public void search_product_test() {
        softAssert.assertEquals(navigationBarPage.HomeButtonColor(), "rgba(255, 165, 0, 1)", "the home page is not visible successfully");
        AllProductsPage allProductsPage=navigationBarPage.goToProductsPage();
        List<String>allProducts=allProductsPage.getProductsName();

        softAssert.assertEquals(allProductsPage.getAllProductText(),"ALL PRODUCTS","the navigated page is not correct");

        ProductPage productPage= allProductsPage.searchForProduct(productName);

        softAssert.assertEquals(productPage.getSearchedProductText(),"SEARCHED PRODUCTS","The Searched Products Text Isn't Visible");
        List<String>searchResultProducts=allProductsPage.getProductsName();
        //verify that all product is visible
        boolean productsIsVisible=true;
        for(int i=0;i<allProductsPage.get_all_products().size();i++){
            if(allProductsPage.get_all_products().get(i).isDisplayed()==false)
                productsIsVisible=false;
        }
        softAssert.assertTrue(productsIsVisible,"there is an invisible product related to search");
        //verify that all products related to search is shown in search
        for(int i=0;i< allProducts.size();i++){

            if(allProducts.get(i).contains(productName)&&!searchResultProducts.contains(allProducts.get(i)))
                softAssert.assertTrue(false,"the element"+allProducts.get(i)+" is not in search result");
            else
                softAssert.assertTrue(true,"correct");

        }

        softAssert.assertAll();

    }
}
