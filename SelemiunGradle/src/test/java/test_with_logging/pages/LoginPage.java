package test_with_logging.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    public String ADMIN_URL = "http://localhost/litecart/admin/";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        driver.get(ADMIN_URL);
    }

    public LoginPage writeLogin(String text) {
        write(By.name("username"), text);
        return this;
    }

    public LoginPage writePassword(String text) {
        write(By.name("password"), text);
        return this;
    }

    public LoginPage clickOnButton() {
        click(By.cssSelector(".btn"));
        return this;
    }

    public void checkAuthorization() {
        isElementDisplayed(By.cssSelector("#box-apps-menu"));
    }
}
