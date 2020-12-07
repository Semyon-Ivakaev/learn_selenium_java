package second;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class MyFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    // Проверка для названия двух стран, они должны быть в алфавитном порядке.
    public Boolean checkString(String a, String b) {
        int n = a.compareTo(b);
        return n < 0;
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() throws InterruptedException, AWTException {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector(".btn")).click();

        WebElement findCount = new WebDriverWait(driver, 5)
                .until(presenceOfElementLocated(By.cssSelector("tbody td:nth-child(5) a")));

        // инт нужен для цикла, чтобы сравнить все страны (их около 243)
        int countCountries = driver.findElements(By.cssSelector("tbody td:nth-child(5) a")).size();

        for (int i = 1; i < countCountries; i++) {
            // элемент нужен для клика по нему, если у него регионы != 0
            WebElement country = driver.findElement(By.cssSelector(String.format("tbody tr:nth-child(%d) td:nth-child(5) a", i)));
            // строка для сравнения текущей строки с i + 1 строкой
            String countryString = country.getAttribute("textContent");
            // инт для получения количества регионов
            int countCountrySub = Integer.parseInt(driver.findElement(By.cssSelector(String.format("tbody tr:nth-child(%d) .text-center", i)))
                    .getAttribute("textContent"));
            String countryNext = driver.findElement(By.cssSelector(String.format("tbody tr:nth-child(%d) td:nth-child(5) a", i + 1)))
                    .getAttribute("textContent");

            // сравнение двух строк
            boolean check = checkString(countryString, countryNext);

            Assert.assertTrue(check);

            // если есть у страны регионы, то открыть эту страну и сравнить регионы на аолфавитный порядок
            if (countCountrySub != 0) {
                country.click();
                new WebDriverWait(driver, 5).until(presenceOfElementLocated(By.tagName("tbody")));
                int countSub = driver.findElements(By.cssSelector("tbody td:nth-child(3)")).size();
                for (int j = 1; j < countSub; j++) {
                    String countrySub = driver.findElement(By.cssSelector(String.format("tbody tr:nth-child(%d) td:nth-child(3) input", j)))
                            .getAttribute("value");
                    String countrySubNext = driver.findElement(By.cssSelector(String.format("tbody tr:nth-child(%d) td:nth-child(3) input", j + 1)))
                            .getAttribute("value");
                    boolean checkSub = checkString(countrySub, countrySubNext);

                    Assert.assertTrue(checkSub);
                    // цикл до предпоследнего элемента j < countSub, но сверяю до j + 1
                    if (j + 1 == countSub) {
                        // только через робота удалось использовать комбинацию нажатия клавищ alt+left
                        Robot robot = new Robot();
                        robot.keyPress(KeyEvent.VK_ALT);
                        robot.keyPress(KeyEvent.VK_LEFT);
                        robot.keyRelease(KeyEvent.VK_ALT);
                        robot.keyRelease(KeyEvent.VK_LEFT);
//                        driver.findElement(By.tagName("html"))
//                                .sendKeys(Keys.LEFT_ALT + Keys.LEFT);
                    }
                }
                new WebDriverWait(driver, 10)
                        .until(presenceOfElementLocated(By.cssSelector("tbody td:nth-child(5) a")));
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
