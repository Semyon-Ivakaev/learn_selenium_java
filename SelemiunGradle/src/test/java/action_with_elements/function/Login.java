package action_with_elements.function;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static action_with_elements.MainForExOne.*;

public class Login {
    public static void loginAndLogout(String email, String password) throws InterruptedException {
        // driver.navigate().refresh(); нужен для обхода первого бага. После логаута кнопки входа в лк на главной
        // странице нет, приходится обновлять страницу
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".b-header__main [data-src = '#login-window']")));
        } catch (Exception e) {
            driver.navigate().refresh();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".b-header__main [data-src = '#login-window']")));
        }

        driver.findElement(By.cssSelector(".b-header__main [data-src = '#login-window']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".b-popup-window__content:nth-child(2)")));

        driver.findElement(By.cssSelector(".b-login-window.b-popup-window [title='E-mail']")).sendKeys(email);
        driver.findElement(By.cssSelector(".b-login-window.b-popup-window [name='PASSWORD']")).sendKeys(password);
        driver.findElement(By.cssSelector(".b-login-window__footer-row .g-btn")).click();

        // тут находим второй баг. После перезахода в ЛК не происходит редирект в ЛК.
        // продолжаем висеть на главной странице.
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".b-control-nav__item_logout")));
        Assert.assertTrue(checkLogout(element));

        element.click();
    }

    public static boolean checkLogout(WebElement element) {
        if (element.isDisplayed()) return true;
        else return false;
    }
}
