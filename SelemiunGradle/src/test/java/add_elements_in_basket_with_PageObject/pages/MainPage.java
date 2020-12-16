package add_elements_in_basket_with_PageObject.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MainPage extends BasePage{
    Actions actions = new Actions(driver);

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        driver.get("https://shop.atributika.ru/");
    }

    public MainPage addElementsInBasket() {
        for (int i = 1; i <= 3; i++ ){
            int countBasket = Integer.parseInt(getElement(By.cssSelector(".b-control-nav__link_basket span")).getAttribute("textContent"));
            WebElement element = getElement(By.cssSelector
                    (String.format("section [aria-live='polite'] .b-catalog__item:nth-child(%d) .b-item__view-btn", i)));
            actions.moveToElement(element).perform();
            isElementDisplayed(By.cssSelector
                    (String.format("section [aria-live='polite'] .b-catalog__item:nth-child(%d) .b-item__view-btn", i)));
            element.click();

            if (sizeIsPresent(".b-product__size-field")) {
                click(By.cssSelector(".b-product__size-field .b-size__btn"));
            }

            click(By.cssSelector(".b-product__basket-btn-field"));
            driver.findElement(By.cssSelector(".fancybox-close-small")).click();
            //click(By.cssSelector(".fancybox-close-small"));

            isElementDisplayed(By.cssSelector(".b-control-nav__link_basket span"));
            int newCountBasket = Integer.parseInt(getElement(By.cssSelector(".b-control-nav__link_basket span")).getAttribute("textContent"));
            equalsCountElements(countBasket, newCountBasket - 1);
        }
        return this;
    }

    public MainPage removeElementsFromBasket(){
        click(By.cssSelector(".b-control-nav__link_basket"));
        int countBasket = getElements(By.cssSelector(".b-basket-popup__item")).size();
        for (int i = 1; i <= countBasket; i++) {
            click(By.cssSelector(".b-basket-popup__item:nth-child(1) .b-basket-popup__item-remove-field"));
            try {
                Thread.sleep(3000);
            } catch (Exception e) {

            }
        }

        int newCountBasket = Integer.parseInt(getElement(By.cssSelector(".b-control-nav__link_basket span"))
                .getAttribute("textContent"));
        equalsCountElements(newCountBasket, 0);
        return this;
    }

    public boolean sizeIsPresent(String locator){
        try { // if-else через try-catch, если при поиске находим элемент, то true, else false
            isElementDisplayed(By.cssSelector(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
