package windows_and_action_with_them.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import windows_and_action_with_them.Main;

public class OpenNewCountry extends Main {

    public static void openNewCountry(WebDriver driver, WebDriverWait wait) {
        driver.findElement(By.cssSelector("a.btn.btn-default")).click();

        wait.until(ExpectedConditions.titleContains("Add New Country | My Store"));
    }
}
