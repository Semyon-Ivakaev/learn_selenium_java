package windows_and_action_with_them.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class CheckOpenNewWindow {
    public static void checkOpenNewWindow(WebDriver driver, WebDriverWait wait) {
        // список всех полей со стрелкой для новой вкладки
        List<WebElement> list = driver.findElements(By.cssSelector("label a[target='_blank']"));

        for (WebElement element: list) {
            // запоминаем первую страницу для возврата на нее
            String startWindow = driver.getWindowHandle();
            // открываем первую вкладку
            element.click();
            // ждем, когда вкладок в браузере будет 2. По сути это уже есть проверка!
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            // получаем множество всех открытых вкладок
            Set<String> oldWindows = driver.getWindowHandles();
            // находим id новой вкладки
            String newWindow = checkWindows(startWindow, oldWindows);

            // переходим на новую вкладку, после закрываем ее и возвращаемся на стартовую вкладку.
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(startWindow);
        }
    }

    // суть метода в том, чтобы пройти по множеству и выкинуть новый id, он будет отличен от первичного id actionWindow
    public static String checkWindows(String actionWindow, Set<String> oldWindows) {

        for (String element: oldWindows) {
            if (!element.equals(actionWindow)) return element;
        }
        return null;
    }
}
