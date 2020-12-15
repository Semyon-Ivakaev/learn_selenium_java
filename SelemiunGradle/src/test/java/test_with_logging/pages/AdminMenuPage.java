package test_with_logging.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminMenuPage extends BasePage{

    public AdminMenuPage(WebDriver driver) {
        super(driver);
    }

    public AdminMenuPage goToCatalog(){
        click(By.cssSelector("[data-code='catalog']"));
        return this;
    }
}
