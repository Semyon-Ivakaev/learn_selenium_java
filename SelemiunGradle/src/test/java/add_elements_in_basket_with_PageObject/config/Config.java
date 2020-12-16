package add_elements_in_basket_with_PageObject.config;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileWriter;
import java.util.Date;

public class Config {
    public EventFiringWebDriver driver;

    public static class MyListener extends AbstractWebDriverEventListener {
        public int countLogs = 0;

        @Override
        public void afterClickOn(WebElement element, WebDriver driver) {
            FileWriter fileWriter = null;
            try {
                countLogs++;
                fileWriter = new FileWriter("C:\\Users\\Vertigo\\Desktop\\java\\Selenium\\learn_selenium_java\\SelemiunGradle\\src\\test\\java\\add_elements_in_basket_with_PageObject\\config\\logs.txt", true);
                fileWriter.write(new Date() + " - " + "Driver click on: " + element + "\n");
                fileWriter.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            FileWriter fileWriter = null;
            try {
                countLogs++;
                fileWriter = new FileWriter("C:\\Users\\Vertigo\\Desktop\\java\\Selenium\\learn_selenium_java\\SelemiunGradle\\src\\test\\java\\add_elements_in_basket_with_PageObject\\config\\logs.txt", true);
                fileWriter.write(new Date() + " - " + "Exception: " + throwable + "\n");
                fileWriter.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Before
    public void start() {
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());
        driver.manage().window().maximize();
    }

    @After
    public void end() {
        driver.quit();
    }
}
