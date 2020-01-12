package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public abstract class DSL {
    // initialize properties
    MyConfig myConfig = ConfigFactory.create(MyConfig.class, System.getProperties());
    // initialize logger
    public static final Logger logger = LogManager.getLogger(FindJavaCourseForQa.class.getName());

    // common test variables
    protected WebDriver driver;
    protected WebDriverWait wait;
    String baseUrl = myConfig.baseUrl();

    // test methods

    public static void webDriverManagerInitialize(){
        logger.debug("setup WebDriver");
        WebDriverManager.chromedriver().setup();
    }

    public void webDriverInitializeAndConfig(){
        logger.debug("Initialize webDriver and set headless");
        ChromeOptions options = new ChromeOptions().setHeadless(true);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void closeWebDriver(){
        if (driver != null){
            driver.quit();
        }
    }

    public void openPage(String url){
        driver.get(url);
    }

    public String findPageElementToVerify(Object locator){
        return driver.findElement((By) locator).getText();
    }

    public WebElement findPageElementAndAct(Object locator){
        return driver.findElement((By) locator);
    }

}
