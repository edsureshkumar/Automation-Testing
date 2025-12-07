package framework.core;
import framework.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class DriverFactory {
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    public static void initDriver() {
        if (driverThread.get() == null) {
            String browser = ConfigManager.get("browser");
            switch (browser.toLowerCase()) {
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driverThread.set(new ChromeDriver());
            }
            getDriver().manage().window().maximize();
        }
    }
    public static WebDriver getDriver() {
        return driverThread.get();
    }
    public static void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}
