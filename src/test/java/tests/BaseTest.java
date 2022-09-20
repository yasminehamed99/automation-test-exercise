package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;


import static filesManager.ReaderFromFiles.getPropertyByKey;


public class BaseTest {
    public static String propertyFileName = "config.properties";
    WebDriver driver;


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(getPropertyByKey("config.properties", "WEB_URL"));

    }

    @AfterMethod
    public void aScreenShot(ITestResult iTestResult) {
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenShot, new File("./failedTestsScreenShots/" + iTestResult.getName() + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @AfterMethod
    public void quitBrowser() {
        driver.quit();
    }
}