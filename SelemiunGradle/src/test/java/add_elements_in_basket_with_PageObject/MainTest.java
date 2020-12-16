package add_elements_in_basket_with_PageObject;

/*
* Задание: Переделать старый тест из пакета add_elements_in_basket по модели PageObject.
* Config - настройки для запуска теста + вложенный класс для логирования (Пишу каждый клик драйвера + исключения).
* BasePage - класс для общих методов.
* MainPage - класс для методов на основной странице.
* MainTest - класс для теста.
* */
import add_elements_in_basket_with_PageObject.config.Config;
import add_elements_in_basket_with_PageObject.pages.MainPage;
import org.junit.Test;

public class MainTest extends Config {
    @Test
    public void testAddElementsInBasket(){
        MainPage mainPage = new MainPage(driver);
        mainPage.goTo();
        mainPage.addElementsInBasket().removeElementsFromBasket();
    }
}
