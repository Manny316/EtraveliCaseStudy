package Factories;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class BrowserFactory {

    private BrowserFactory() {
    }

    /**
     * Returns a {@link WebDriver}
     *
     * @param browser - the browser type (e.g. chrome, firefox, etc)
     * @param timeout - the implicit wait of the driver
     * @return - the web driver
     */
    public static WebDriver setup(String browser, long timeout) {
        WebDriver webDriver = null;
        if ("chrome".equalsIgnoreCase(browser)) {
            webDriver = setUpChrome(timeout);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            webDriver = setUpFirefox(timeout);
        }
        return webDriver;
    }

    /**
     * Returns a local Chrome {@link WebDriver}
     *
     * @param timeout  - the implicit wait of the driver
     * @return - the Chrome web driver
     */
    private static WebDriver setUpChrome(long timeout) {
        // handle web driver installation
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headed");
        options.addArguments("--remote-allow-origins=*");
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        return webDriver;
    }

    /**
     * Returns a local Firefox {@link WebDriver}
     *
     * @param timeout - the implicit wait of the driver
     * @return - the Firefox web driver
     */
    private static WebDriver setUpFirefox(long timeout) {
        // handle web driver installation
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        WebDriver webDriver = new FirefoxDriver(options);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        return webDriver;
    }
}