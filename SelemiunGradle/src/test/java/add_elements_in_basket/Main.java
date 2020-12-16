package add_elements_in_basket;

/*
По заданию:
1) Тест должен заходить на сайт.
2) Находить на главной странице 3 товара и добавить их в корзину.
3) После каждого добавления проверять, что товар появился в корзине.
4) Удалить все элементы из корзины
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static add_elements_in_basket.methods.AddElementsInBasket.addElementsInBasket;
import static add_elements_in_basket.methods.RemoveElementsFromBasket.removeElementsFromBasket;

public class Main {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }

    @Test
    public void testAddElementsInBasket() throws InterruptedException {
        driver.get("https://shop.atributika.ru/");
        addElementsInBasket(driver, wait);
        removeElementsFromBasket(driver, wait);
    }

    @After
    public void end(){
        driver.quit();
        driver = null;
        wait = null;
    }
}
