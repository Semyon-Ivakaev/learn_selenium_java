package action_with_elements.function;

import action_with_elements.MainForExOne;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import sun.tools.jar.Main;

import java.util.List;

import static action_with_elements.MainForExOne.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Registration {

    //метод для регистрации
    public static void registration() throws InterruptedException {
        MainForExOne.wait.until(presenceOfElementLocated(By.cssSelector(".b-page__title")));
        MainForExOne.driver.findElement(By.xpath("//*[@class='b-form-row'][1] //input")).sendKeys(secondName);
        MainForExOne.driver.findElement(By.xpath("//*[@class='b-form-row'][2] //input")).sendKeys(firstName);
        MainForExOne.driver.findElement(By.xpath("//*[@class='b-form-row'][3] //input")).sendKeys(thirdName);

        Select selectDate  = new Select(driver.findElement(By.cssSelector("#reg_date")));
        selectDate.selectByIndex(28);

        Select selectMonth = new Select(driver.findElement(By.cssSelector("#reg_month")));
        selectMonth.selectByIndex(5);

        Select selectYear = new Select(driver.findElement(By.cssSelector("#reg_year")));
        selectYear.selectByIndex(73);

        MainForExOne.driver.findElement(By.cssSelector(".b-registration__col:nth-child(3) .b-form-row:nth-child(1) input"))
                .sendKeys(phoneNumber);
        MainForExOne.driver.findElement(By.cssSelector(".b-registration__col:nth-child(3) .b-form-row:nth-child(2) input"))
                .sendKeys(email);

        MainForExOne.driver.findElement(By.cssSelector(".b-registration__col:nth-child(4) .b-form-row:nth-child(1) input"))
                .sendKeys(password);
        MainForExOne.driver.findElement(By.cssSelector(".b-registration__col:nth-child(4) .b-form-row:nth-child(2) input"))
                .sendKeys(password);

        WebElement checkboxOne = driver.findElement(By.cssSelector("[name='aggreement']"));
        WebElement checkboxTwo = driver.findElement(By.cssSelector("[name='user[UF_SUBSCRIBE]']"));

        // checkBox() проверяет, что чекбокс пустой getAttribute("checked") = false
        if (!checkBox(checkboxOne)) {
            //wait.until(ExpectedConditions.elementToBeClickable(checkboxOne));
            // клик делаю не по самому чекбоксу, а по элементу обертке, так как чекбокс скрыт за ним
            driver.findElement(By.cssSelector(".b-registration__aggreement-field:nth-child(3)")).click();
        }

        if (!checkBox(checkboxTwo)) {
            //wait.until(ExpectedConditions.elementToBeClickable(checkboxTwo));
            driver.findElement(By.cssSelector(".b-registration__aggreement-field:nth-child(4)")).click();
        }

        MainForExOne.driver.findElement(By.cssSelector(".g-xs-hidden button")).click();

        // проверка, что текст после регистрации на странице соответствует нужному.
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#reg_ok_info")));
        Assert.assertTrue(checkReg(el));
    }

    // рандомное число плюсуем к почте, чтобы в тестах всегда было уникальное значение
    public static int randomInt() {
        return (int) (Math.random() * 1000);
    }

    // equals не хочет работать с null, поэтому если null, то bool = "false"
    public static boolean checkBox(WebElement element) {
        String bool = element.getAttribute("checked");
        if (bool == null) bool = "false";
        if (bool.equals("true")) {
            return true;
        } else return false;
    }

    public static boolean checkReg(WebElement element){
        if (element.isDisplayed()) return true;
        else return false;
    }

}
