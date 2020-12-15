package test_with_logging.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogCatalogPage extends BasePage{

    public CatalogCatalogPage(WebDriver driver) {
        super(driver);
    }

    public CatalogCatalogPage clickOnSection(String section) {
        click(By.xpath("//*[text()='" + section + "']"));
        return this;
    }

    public void checkOpenSection(String section) {
        isElementDisplayed(By.xpath("//*[text()='" + section + "']"));
    }

    // метод проверяет
    public void checkProducts() {
        int countProducts = getElements(By.cssSelector("tbody tr")).size();
        for (int i = 3; i <= countProducts; i++) {
            click(By.cssSelector(String.format("tbody tr:nth-child(%d) td:nth-child(3) a", i)));
            checkBrowserLogs();
            goBack();
            waitVisibility(By.cssSelector(String.format("tbody tr:nth-child(%d) td:nth-child(3) a", i)));
        }
    }
}
