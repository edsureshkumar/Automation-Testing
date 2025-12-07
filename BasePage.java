package framework.core;
import framework.config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
public abstract class BasePage {
    protected WebDriver driver;
    public BasePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }
    public void openBaseUrl() {
        driver.get(ConfigManager.get("baseUrl"));
    }
    public String getTitle() {
        return driver.getTitle();
    }
}
