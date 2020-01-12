package Selenium;
import org.aeonbits.owner.Config;

@Config.Sources("classpath:MyConfig.properties")
public interface MyConfig extends Config {

    @Key("site.baseUrl")
    String baseUrl();

    @Key("database.name")
    String dbName();

    @Key("database.Url")
    String dbUrl();

    @Key("database.login")
    @DefaultValue("root")
    String dbLogin();

    @Key("database.password")
    @DefaultValue("root")
    String dbPassword();

}
