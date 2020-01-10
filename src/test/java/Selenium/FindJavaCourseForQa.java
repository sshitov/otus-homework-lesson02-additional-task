package Selenium;

import Listener.ExecutionListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

@Listeners(ExecutionListener.class)
public class FindJavaCourseForQa {

    protected WebDriver driver;
    protected WebDriverWait wait;
    final static Logger logger = Logger.getLogger(FindJavaCourseForQa.class);
    protected static String baseUrl = "https://otus.ru/";

    // arrange start
    @BeforeSuite
    public static void webDriverSetup(){
        logger.debug("setup WebDriver");
        WebDriverManager.chromedriver().setup();

    }
    @BeforeTest
    public void createDriver(){
        logger.debug("Initialize webDriver and set headless");
        ChromeOptions options = new ChromeOptions().setHeadless(true);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public  void closeDriver(){
        if (driver != null){
            driver.quit();
        }
    }
    // arrange end

    @Test(description = "open main page")
    public void openMainPage(){
        String expectTitle = "Авторские онлайн‑курсы для профессионалов";

        driver.get(baseUrl);
        WebElement element = driver.findElement(By.tagName("h1"));
        String textToVerify = element.getText();
        if (!textToVerify.equals(expectTitle)){
            logger.debug("Main page title not meet to Expected");
        }
        Assert.assertEquals(textToVerify, expectTitle);
    }

    @Test(description = "go to courses page", dependsOnMethods = "openMainPage")
    public void goToLessons(){
        String expectTitle = "Программирование";

        WebElement lessonsButton = driver.findElement(By.cssSelector("body div.transitional-main__stat > div > div:nth-child(2) > a"));
        lessonsButton.click();
        //Подождать
        WebElement element = driver.findElement(By.tagName("h1"));
        String textToVerify = element.getText();
        if (!textToVerify.equals(expectTitle)){
            logger.debug("Default section not meet to Expected");
        }
        Assert.assertEquals(textToVerify, expectTitle);

    }

    @Test(description = "Filter by testing", dependsOnMethods = "goToLessons")
    public void filter(){
        String expectTitle = "Тестирование";

        WebElement testingTab = driver.findElement(By.cssSelector("body div.js-lessons > div:nth-child(2) > div a:nth-child(6)"));
        testingTab.click();
        //Подождать
        WebElement element = driver.findElement(By.tagName("h1"));
        String textToVerify = element.getText();
        if (!textToVerify.equals(expectTitle)){
            logger.debug("Choose section title not meet to Expected");
        }
        Assert.assertEquals(textToVerify, expectTitle);

    }

    @Test(description = "open Java QA Automation Engineer page", dependsOnMethods = "filter")
    public void openCoursePage() {
        WebElement courseChoose = driver.findElement(By.cssSelector("body div.container.container-lessons a:nth-child(1) div.lessons__new-item-title.lessons__new-item-title_with-bg.js-ellipse"));
        courseChoose.click();

    }

    @Test(description = "Checking the availability of the course page", dependsOnMethods = "openCoursePage")
    public void checkTheCoursePage(){
        String expectTitle = "Java QA Automation Engineer";

        WebElement element = driver.findElement(By.cssSelector("body h1"));
        String textToVerify = element.getText();
        if (!textToVerify.equals(expectTitle)){
            logger.error("Choose section title not meet to Expected");
        }
        Assert.assertEquals(textToVerify, expectTitle);

    }

}
