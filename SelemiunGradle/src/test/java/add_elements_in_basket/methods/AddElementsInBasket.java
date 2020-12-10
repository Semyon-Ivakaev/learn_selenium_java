package add_elements_in_basket.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddElementsInBasket {

    public static void addElementsInBasket(WebDriver driver, WebDriverWait wait) throws InterruptedException {
        Actions actions = new Actions(driver);

        // цикл требуется для работы с 3мя товарами, на странице их до 32.
        for (int i = 1; i <= 3; i++) {
            // инт для ассерта, сравниваем первоначальное количество товаров в корзине перед добавлением товара
            int countBasket = Integer.parseInt(driver.findElement(By.cssSelector(".b-control-nav__link_basket span"))
                    .getAttribute("textContent"));
            // кнопка у элемента, на который будем наводить, его будем добавлять в корзину, тут пригодится i
            WebElement element = driver.findElement(
                    By.cssSelector(String.format("section [aria-live='polite'] .b-catalog__item:nth-child(%d) .b-item__view-btn", i)));
            // наводим на товар, чтобы появилась кнопка быстрого просмотра (окно поверх без редиректа в товар)
            actions.moveToElement(element).perform();
            // ждем, что воявилась кнопка быстрого просмотра
            wait.until(ExpectedConditions.visibilityOf(element));
            // ожидание перед кликом на кнопку, она скрывается
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            element.click();

            // ожидаем открытия окна быстрого просмотра товара
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".b-product__basket-btn-field")));
            // условие направлено на проверку, что у товара есть размер, например,
            // у кепки нет явного размера и его можно сразу добавить в корзину.
            // у футболки есть явный размер и ее нельзя добавить в корзину пока не выберешь размер.
            if (sizeIsPresent(driver, ".b-product__size-field")) {
                driver.findElement(By.cssSelector(".b-product__size-field .b-size__btn")).click();
            }

            // добавляем товар в корзину и закрываем окно быстрого просмотра товара
            driver.findElement(By.cssSelector(".b-product__basket-btn-field")).click();
            driver.findElement(By.cssSelector(".fancybox-close-small")).click();

            // инт для нового значения товара для ассерта
            int newCountBasket = Integer.parseInt(driver.findElement(By.cssSelector(".b-control-nav__link_basket span"))
                    .getAttribute("textContent"));
            assert countBasket == newCountBasket - 1: "Элемент не был добавлен в корзину";
        }
    }

    public static boolean sizeIsPresent(WebDriver driver, String locator){
        try { // if-else через try-catch, если при поиске находим элемент, то true, else false
            driver.findElement(By.cssSelector(locator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
