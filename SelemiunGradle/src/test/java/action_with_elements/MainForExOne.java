package action_with_elements;

import action_with_elements.function.Login;
import action_with_elements.function.Registration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainForExOne {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static String secondName;
    public static String firstName;
    public static String thirdName;
    public static String phoneNumber;
    public static String email;
    public static String password;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        secondName = "test";
        firstName = "test";
        thirdName = "test";
        phoneNumber = "9998887766";
        email = "test123" + Registration.randomInt() + "@mail.ru";
        password = "Qweasdzxc";
    }

    @Test
    public void mainForExOne() throws InterruptedException {
        driver.get("https://shop.atributika.ru/personal/");
        Registration.registration();
        Login.loginAndLogout(email, password);
        Login.loginAndLogout(email, password);
    }

    @After
    public void end(){
        driver.quit();
        driver = null;
        wait = null;
    }


}
