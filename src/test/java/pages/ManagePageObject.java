package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ManagePageObject extends Authorization {
    private WebDriverWait wait;
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/div[17]/a/dl/dt")
    private List<WebElement> dtTextManageUsers;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/div[17]/a/dl/dd[1]")
    private List<WebElement> ddText;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/div[17]/a/dl/dt")
    private WebElement hrefManageUsers;

    public ManagePageObject(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 5);
    }

    public boolean existsDtTextManageUsers() {
        return existsElement(dtTextManageUsers);
    }

    public boolean existsDdText() {
        return existsElement(ddText);
    }

    public ManagePageObject clickManageUsers() {
        hrefManageUsers.click();
        return this;
    }

}
