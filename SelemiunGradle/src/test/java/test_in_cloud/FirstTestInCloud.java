package test_in_cloud;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstTestInCloud {
    public static final String USERNAME = "username";
    public static final String AUTOMATE_KEY = "key";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    DesiredCapabilities caps;
    WebDriver driver;

    @Before
    public void start() throws MalformedURLException {
        caps = new DesiredCapabilities();

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "87.0");
        caps.setCapability("name", "autoqa3's First Test");
        driver = new RemoteWebDriver(new URL(URL), caps);
    }

    @Test
    public void test(){
        driver.get("https://shop.atributika.ru/");
    }

    @After
    public void end() {
        driver.quit();
    }
}
