
package framework.core;

import framework.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static void initDriver() {
        if (driverThread.get() == null) {
            String browser = ConfigManager.get("browser");
            // Detect CI via env or system properties (from Maven: -Dci=true)
            boolean isCI = "true".equalsIgnoreCase(System.getenv("CI"))
                        || "true".equalsIgnoreCase(System.getProperty("ci"))
                        || "true".equalsIgnoreCase(System.getProperty("headless"));

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            if (isCI) {
                // Headless + stability flags for GitHub Actions (Linux runner)
                options.addArguments("--headless=new");     // if this ever fails, switch to "--headless"
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--remote-debugging-port=9222");
                // Explicit Chrome binary path on ubuntu-latest
                options.setBinary("/usr/bin/google-chrome");
            }

            // Diagnostics: capture ChromeDriver verbose logs
            System.setProperty("webdriver.chrome.verboseLogging", "true");
            System.setProperty("webdriver.chrome.logfile", "chromedriver.log");

            System.out.println("[DriverFactory] CI=" + System.getenv("CI")
                    + ", sys.ci=" + System.getProperty("ci")
                    + ", sys.headless=" + System.getProperty("headless"));
            System.out.println("[DriverFactory] chrome options: " + options.asMap());

            driverThread.set(new ChromeDriver(options));

            try {
                driverThread.get().manage().window().maximize();
            } catch (Exception ignored) { /* maximize may be a no-op in headless */ }
        }
    }

    public static WebDriver getDriver() { return driverThread.get(); }

    public static void quitDriver() {
        WebDriver d = driverThread.get();
        if (d != null) {
            d.quit();
            driverThread.remove();
        }
    }
}
