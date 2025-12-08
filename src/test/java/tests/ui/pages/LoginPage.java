package tests.ui.pages;
import framework.core.DriverFactory;
import framework.core.BasePage;
import framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class LoginPage extends BasePage {
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton   = By.id("login-button");
    private final By errorMessage  = By.cssSelector("h3[data-test='error']");
    public void open() {
        openBaseUrl();
    }
    public void login(String username, String password) {
        WebElement user = WaitUtils.waitForVisible(usernameInput, 10);
        user.clear();
        user.sendKeys(username);
        WebElement pass = WaitUtils.waitForVisible(passwordInput, 10);
        pass.clear();
        pass.sendKeys(password);
        DriverFactory.getDriver().findElement(loginButton).click();
    }
    public String getErrorText() {
        return DriverFactory.getDriver().findElement(errorMessage).getText();
    }
}
