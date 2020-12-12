package windows_and_action_with_them;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static windows_and_action_with_them.methods.Authorization.authorization;
import static windows_and_action_with_them.methods.CheckOpenNewWindow.checkOpenNewWindow;
import static windows_and_action_with_them.methods.OpenNewCountry.openNewCountry;

/*
* Сценарий:
* 1. Авторизоваться админом на странице со списком стран.
* 2. Перейти в раздел создания новой страны.
* 3. возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой -- они ведут на внешние страницы
* и открываются в новом окне, именно это и нужно проверить
* */

public class Main {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }

    @Test
    public void test_working_windows() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        authorization(driver, wait); // авторизация по админом
        openNewCountry(driver, wait); // открытие нужного модуля
        checkOpenNewWindow(driver, wait); // проверка открытия новой вкладки
    }

    @After
    public void end() {
        driver.quit();
        driver = null;
        wait = null;
    }
}
