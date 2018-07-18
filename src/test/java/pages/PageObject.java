package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

    private WebDriverWait wait;
    private final WebDriver driver;

    @FindBy (xpath = "//*[@id=\"tasks\"]/div[4]/a[2]")
    private WebElement hrefManage;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 5);
    }

    public PageObject clickManage() {
        hrefManage.click();
        return this;
    }
}
