package windows_and_action_with_them.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Authorization {

    public static void authorization(WebDriver driver, WebDriverWait wait) {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector(".btn")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content")));
    }
}
