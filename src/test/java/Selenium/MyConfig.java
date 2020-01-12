package Selenium;
import org.aeonbits.owner.Config;

@Config.Sources("classpath:MyConfig.properties")
public interface MyConfig extends Config {

    @Key("site.baseUrl")
    String baseUrl();
}
