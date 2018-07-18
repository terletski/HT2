package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;


public class Authorization {

    WebDriver driver = null;
    String baseURL = "http://localhost:8080";
    StringBuffer verificationErrors = new StringBuffer();

    public Authorization(WebDriver driver)
    {
        this.driver = driver;
    }

    // конструктор без параметров
    public Authorization() {
    }

    // авторизация
    public void login() {

        driver.get(baseURL + "/login?from=%2F");
        AuthorizationPageObject page = new AuthorizationPageObject(driver);
        Assert.assertFalse(page.FormPresentForReal(), "No suitable forms found!");

        page.setUsername("terletski");
        Assert.assertEquals(page.getUsername(), "terletski");
        page.setPassword("seadogs89");
        Assert.assertEquals(page.getPassword(), "seadogs89", "Unable to fill 'Пароль' field");

        page.setRememberMe("");
        Assert.assertEquals(page.getRememberMe(), "", "Unable select 'Запомнить меня' gender");

        page.submitForm();

        // проверка авторизации
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(page.getProfileLinkLocator()));
        Assert.assertEquals(page.getProfileLink(), baseURL + "/user/terletski");

    }

    // поиск элемента на странице
    public boolean existsElement(List<WebElement> list) {

        if (list.size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    // поиск элемента на странице
    public boolean existsElementByLocator(By locator) {
        try {
            driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
