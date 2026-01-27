package org.example;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.listeners.TestListner;
import org.example.utils.Config;
import org.example.utils.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;

@Listeners(TestListner.class)
public class AbstractTest {

    public static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected WebDriver driver;


    @BeforeSuite
    public void setUpConfig(){
        Config.Initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext ctx) throws Exception {

        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        this.driver.manage().window().maximize();
        ctx.setAttribute(Constants.DRIVER,this.driver);
    }

    private WebDriver getRemoteDriver() throws Exception {
        Capabilities capabilities = new ChromeOptions();

        if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER)))
            capabilities = new FirefoxOptions();

        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);

        log.info("grid url :: {}", url);

        return new RemoteWebDriver(new URL(url), capabilities);

    }


    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quit() {
        this.driver.quit();
    }

//    @AfterMethod
//    public void sleep(){
//        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
//    }
}
