package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Collection;
import java.util.Iterator;

public class AuthorizationPageObject {
    private WebDriverWait wait;
    private final WebDriver driver;

    // Подготовка элементов страницы.
    By body_locator = By.xpath("//body");
    By form_locator = By.xpath("//*[@id=\"main-panel\"]/div/form");
    By username_locator = By.name("j_username");
    By password_locator = By.name("j_password");
    By remember_me_locator = By.xpath("//*[@id=\"remember_me\"]");
    By submit_button_locator = By.xpath("/html/body/div/div/form/div[3]/input");
    By profile_link_locator = By.xpath("//*[@id=\"header\"]/div[2]/span/a[1]");

    public AuthorizationPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 5);

    }

    // проверяем, выполнена ли авторизация
    public boolean Logined() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Collection<WebElement> trs = driver.findElements(By.xpath("//*[@id=\"header\"]/div[2]/span/a[1]"));
        if (trs.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public AuthorizationPageObject setUsername(String name) {
        driver.findElement(username_locator).clear();
        driver.findElement(username_locator).sendKeys(name);
        return this;
    }

    public AuthorizationPageObject setPassword(String password) {
        driver.findElement(password_locator).clear();
        driver.findElement(password_locator).sendKeys(password);
        return this;
    }

    public AuthorizationPageObject setRememberMe(String rememberMe) {
        if (rememberMe.equals("yes")) {
            driver.findElement(remember_me_locator).click();
        }
        return this;
    }

    // Заполнение всех полей формы.
    public AuthorizationPageObject setFields(String username, String password, String rememberMe) {
        setUsername(username);
        setPassword(password);
        setRememberMe(rememberMe);
        return this;
    }

    // Отправка данных из формы.
    public AuthorizationPageObject submitForm() {
        driver.findElement(submit_button_locator).click();
        return this;
    }

    // Обёртка для упрощения отправки данных.
    public AuthorizationPageObject submitFilledForm(String username, String password, String rememberMe) {
        setFields(username, password, rememberMe);
        return submitForm();
    }

    // Надёжный поиск формы.
    public boolean FormPresentForReal() {

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Collection<WebElement> forms = driver.findElements(By.tagName("form"));
        if (forms.isEmpty()) {
            return false;
        }

        Iterator<WebElement> i = forms.iterator();
        boolean form_found = false;
        WebElement form = null;

        while (i.hasNext()) {
            form = i.next();
            if ((form.findElement(By.name("j_username")).getAttribute("type").equalsIgnoreCase("text"))
                    && (form.findElement(By.name("j_password")).getAttribute("type").equalsIgnoreCase("password"))
                    && (form.findElement(By.xpath("//*[@id=\"remember_me\"]")).getAttribute("type").equalsIgnoreCase("checkbox"))
                    && (form.findElement(By.xpath("/html/body/div/div/form/div[3]/input")).getAttribute("type").equalsIgnoreCase("button"))
                    )
            {
                form_found = true;
                break;
            }
        }

        return form_found;
    }

    // Проверка вхождения подстроки в текст страницы.
    public boolean pageTextContains(String search_string) {
        return driver.findElement(body_locator).getText().contains(search_string);
    }

    public String getUsername()
    {
        return driver.findElement(username_locator).getAttribute("value");
    }

    public String getPassword()
    {
        return driver.findElement(password_locator).getAttribute("value");
    }

    public String getRememberMe() {
        if (driver.findElement(remember_me_locator).isSelected()) {
            return "yes";
        } else {
            return "";
        }
    }

    public String getProfileLink() {
        return driver.findElement(profile_link_locator).getAttribute("href");
    }

    public By getProfileLinkLocator() {
        return profile_link_locator;
    }

    public String getErrorOnTextAbsence(String search_string) {
        if (!pageTextContains(search_string)) {
            return "No '" + search_string + "' is found inside page text!\n";
        } else {
            return "";
        }
    }
}
