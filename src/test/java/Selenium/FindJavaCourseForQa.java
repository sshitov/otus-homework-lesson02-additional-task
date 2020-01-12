package Selenium;
import Listener.ExecutionListener;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(ExecutionListener.class)
public class FindJavaCourseForQa extends DSL{

    @BeforeSuite
    public static void Setup(){
        webDriverManagerInitialize();
    }

    @BeforeTest
    public void createWebDriver(){
        webDriverInitializeAndConfig();
    }

    @AfterClass
    public void tearDown(){
        closeWebDriver();
    }

    @Test(description = "open main page")
    public void openMainPage(){
        // test arrange and act
        String expectTitle = "Авторские онлайн‑курсы для профессионалов";

        openPage(baseUrl);
        String factTitle = findPageElementToVerify(By.tagName("h1"));

        // test assert
        Assert.assertEquals(factTitle, expectTitle,"Main page title not meet to Expected");
    }

    @Test(description = "go to courses page", dependsOnMethods = "openMainPage")
    public void goToLessons(){
        // test arrange and act
        String expectTitle = "Программирование";

        findPageElementAndAct(By.cssSelector("body div.transitional-main__stat > div > div:nth-child(2) > a")).click();
        String factTitle = findPageElementToVerify(By.tagName("h1"));

        // test assert
        Assert.assertEquals(factTitle, expectTitle,"Default section not meet to Expected");

    }

    @Test(description = "Filter by testing", dependsOnMethods = "goToLessons")
    public void filter(){
        // test arrange and act
        String expectTitle = "Тестирование";

        findPageElementAndAct(By.cssSelector("body div.js-lessons > div:nth-child(2) > div a:nth-child(6)")).click();
        String factTitle = findPageElementToVerify(By.tagName("h1"));

        // test assert
        Assert.assertEquals(factTitle, expectTitle,"Choose section title not meet to Expected");

    }

    @Test(description = "Checking the availability of the course page", dependsOnMethods = "filter")
    public void checkTheCoursePage(){
        // test arrange and act
        String expectTitle = "Углубленная автоматизация тестирования на стеке Java";

        findPageElementAndAct(By.cssSelector("body div.container.container-lessons a:nth-child(1) div.lessons__new-item-title.lessons__new-item-title_with-bg.js-ellipse")).click();
        String factTitle = findPageElementToVerify(By.cssSelector("body  div.course-header__title > div"));

        // test assert
        Assert.assertEquals(factTitle, expectTitle, "Choose section title not meet to Expected");

    }

}
