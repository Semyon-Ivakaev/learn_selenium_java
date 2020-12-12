package add_elements_in_basket.methods;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RemoveElementsFromBasket {
    public static void removeElementsFromBasket(WebDriver driver, WebDriverWait wait) {
        // раскрываем окно корзины
        driver.findElement(By.cssSelector(".b-control-nav__link_basket")).click();
        // считываем все элементы, которые есть в корзине
        int count = driver.findElements(By.cssSelector(".b-basket-popup__item")).size();

        // проходим по каждому элементу и удаляем его
        // так как при удалении элемент смещается, то просто удалем всегда первый элемент
        // sleep использую вместо ожидания по той причине, что почему-то ожидания не срабатывают
        // буду разбираться в причине!
        for (int i = 1; i <= count; i++) {
            driver.findElement(By.cssSelector(".b-basket-popup__item:nth-child(1) .b-basket-popup__item-remove-field")).click();
            try {
                Thread.sleep(3000);
            } catch (Exception e) {

            }
        }

        // после удаления всех товаров ждем, что число обновится до 0
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        int countBasket = Integer.parseInt(driver.findElement(By.cssSelector(".b-control-nav__link_basket span"))
                .getAttribute("textContent"));

        Assert.assertTrue(countBasket == 0);
    }
}
