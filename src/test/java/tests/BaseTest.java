package tests;
import filesReader.FilesReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


import static utilis.LoadData.loadData;
import static utilis.LoadData.url;

public class BaseTest {
    WebDriver driver;
    @BeforeTest
    public void getPropertiesData(){
        loadData();
    }

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);

    }
    @AfterMethod
    public void aScreenShot(ITestResult iTestResult){
        if(iTestResult.getStatus()==ITestResult.FAILURE){
            File screenShot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenShot,new File("./failedTestsScreenShots/"+iTestResult.getName()+".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
    @AfterMethod
    public void quitBrowser(){
        driver.quit();
    }
}