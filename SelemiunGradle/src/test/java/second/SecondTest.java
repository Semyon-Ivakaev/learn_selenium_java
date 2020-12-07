package second;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class SecondTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void test() throws InterruptedException, AWTException {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        auth();
        wait.until(presenceOfElementLocated(By.cssSelector("#content")));
        int countAllCountries = driver.findElements(By.cssSelector("tbody tr")).size();

        for (int i = 1; i <= countAllCountries; i++) {
            driver.findElement(By.cssSelector(String.format("tbody tr:nth-child(%d) td:nth-child(3) a", i))).click();
            wait.until(presenceOfElementLocated(By.cssSelector(".panel-body")));

            int countSub = driver.findElements(By.cssSelector("table tbody tr")).size();

            for (int j = 1; j < countSub; j++) {
                String subCountry = driver.findElement(By.cssSelector(String.format("table tbody tr:nth-child(%d) td:nth-child(2)", j)))
                        .getAttribute("textContent").replaceAll(" ", "");
                String subCountryNext = driver.findElement(By.cssSelector(String.format("table tbody tr:nth-child(%d) td:nth-child(2)", j + 1)))
                        .getAttribute("textContent").replaceAll(" ", "");

                System.out.println(subCountry + " = " + subCountryNext);
                Assert.assertTrue(checkString(subCountry, subCountryNext));

                if (j + 1 == countSub) {
                    // только через робота удалось использовать комбинацию нажатия клавищ alt+left
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_LEFT);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.keyRelease(KeyEvent.VK_LEFT);
                }
            }
            wait.until(presenceOfElementLocated(By.cssSelector("#content")));
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


    public void auth(){
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector(".btn")).click();
    }

    public Boolean checkString(String a, String b) {
        int n = a.compareTo(b);
        return n < 0;
    }
}
