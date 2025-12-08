
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
            // Detect CI via env var or system property (from Maven: -Dci=true)
            boolean isCI = "true".equalsIgnoreCase(System.getenv("CI"))
                        || "true".equalsIgnoreCase(System.getProperty("ci"))
                        || "true".equalsIgnoreCase(System.getProperty("headless"));

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            if (isCI) {
                // Headless + stability flags for GitHub Actions (Linux)
                options.addArguments("--headless=new");      // if flaky, change to "--headless"
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--remote-debugging-port=9222");
                // Chrome binary path on ubuntu-latest runners
                options.setBinary("/usr/bin/google-chrome");
            }

            // Optional: collect verbose driver logs (we upload in CI)
            System.setProperty("webdriver.chrome.verboseLogging", "true");
            System.setProperty("webdriver.chrome.logfile", "chromedriver.log");

            driverThread.set(new ChromeDriver(options));

            try { driverThread.get().manage().window().maximize(); } catch (Exception ignored) {}
        }
    }

    public static WebDriver getDriver() { return driverThread.get(); }

    public static void quitDriver() {
        WebDriver d = driverThread.get();
        if (d != null) { d.quit(); driverThread.remove(); }
    }
}
