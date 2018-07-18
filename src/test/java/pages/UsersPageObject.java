package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class UsersPageObject extends Authorization{

    private WebDriverWait wait;
    private final WebDriver driver;
    String url = "http://localhost:8080/securityRealm/";


    @FindBy(xpath = "//a[@href='user/someuser/delete']")
    private WebElement hrefDelete;

    @FindBy(xpath = "//a[@href='user/admin/delete']")
    private WebElement hrefDeleteAdmin;

    @FindBy(xpath = "//*[@id=\"tasks\"]/div[3]/a[2]")
    private WebElement hrefCreateUser;

    @FindBy(xpath = "//tr//td//*[text()='someuser']")
    private WebElement tdSomeuser;

    // для поиска элемента на странице
    @FindBy(xpath = "//*[@id=\"tasks\"]/div[3]/a[2]")
    private List<WebElement> hrefsCreateUser;

    @FindBy(xpath = "//tr//td//*[text()='someuser']")
    private List<WebElement> tdsSomeuser;

    @FindBy(xpath = "//a[@href='user/someuser/delete']")
    private List<WebElement> hrefsDelete;

    @FindBy(xpath = "//a[@href='user/admin/delete']")
    private List<WebElement> hrefsDeleteAdmin;


    public UsersPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 5);
    }

    public UsersPageObject clickHrefDelete() {
        hrefDelete.click();
        return this;
    }

    public UsersPageObject clickHrefCreteUser() {
        hrefCreateUser.click();
        return this;
    }

    public boolean existsHrefCreateUser() {
        return existsElement(hrefsCreateUser);
    }

    public boolean existsTdSomeuser() {
        return existsElement(tdsSomeuser);
    }

    public boolean existsHrefDelete() {
        return existsElement(hrefsDelete);
    }

    public boolean existsHrefDeleteAdmin() {
        return existsElement(hrefsDeleteAdmin);
    }
}
