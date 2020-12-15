package test_with_logging;

/*
Проверьте отсутствие сообщений в логе браузера
Сделайте сценарий, который проверяет, не появляются ли в логе браузера сообщения при открытии страниц в учебном приложении, а именно -- страниц товаров в каталоге в административной панели.
Сценарий должен состоять из следующих частей:
1) зайти в админку
2) открыть каталог, категорию, которая содержит товары (страница http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1)
3) последовательно открывать страницы товаров и проверять, не появляются ли в логе браузера сообщения (любого уровня)
*/

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import test_with_logging.pages.AdminMenuPage;
import test_with_logging.pages.BasePage;
import test_with_logging.pages.CatalogCatalogPage;
import test_with_logging.pages.LoginPage;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class CatalogTest {

    public EventFiringWebDriver driver;
    public LoginPage loginPage;
    public AdminMenuPage adminMenuPage;
    public CatalogCatalogPage catalogCatalogPage;

    // вложенный класс для логирования действий драйвераа.
    // логируются поиск элемент и нахождение элемента. а так же исключения.
    public static class MyListener extends AbstractWebDriverEventListener {

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("logs.txt", true);
                fileWriter.write(new Date() + " Search: " + by + "\n");
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Logs |beforeFindBy| don`t write");
                try {
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("logs.txt", true);
                fileWriter.write(new Date() + " Find: " + by + "\n");
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Logs |afterFindBy| don`t write");
                try {
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("logs.txt", true);
                fileWriter.write(new Date() + " ERROR: " + throwable + "\n");
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("ERROR |onException| don`t write");
                try {
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    @Before
    public void start() {
        // драйвер в логирующей обертке
        driver = new EventFiringWebDriver(new ChromeDriver());
        // запускаем лоигрование
        driver.register(new MyListener());
        loginPage = new LoginPage(driver);
        adminMenuPage = new AdminMenuPage(driver);
        catalogCatalogPage = new CatalogCatalogPage(driver);
    }

    @Test
    public void catalogTest() {
        // проверяем успешную авторизацию.
        loginPage.goTo();
        loginPage.writeLogin("admin").writePassword("admin").clickOnButton().checkAuthorization();
        // переходим в нужный модуль( в каталог).
        adminMenuPage.goToCatalog();
        // проверяем тип товара - ducks и проверяем, что список раскрылся.
        catalogCatalogPage.clickOnSection("ducks").checkOpenSection("ducks");
        // заходим в каждый продукт и проверяем, что логи в консоли браузера пустые
        catalogCatalogPage.checkProducts();
    }


    @After
    public void end() {
        driver.quit();
    }
}
