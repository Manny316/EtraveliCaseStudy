package TestBase;

import BusinessObjects.TravelBO;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import Factories.BrowserFactory;
import Factories.WebDriverFactory;

public class Base {
    /**
     * Class contains logic that is executed before and after every test class
     */

    protected TravelBO travelBO;


    /**
     * Initialize browser
     *
     * @param url     provided in testng.xml
     * @param browser provided in testng.xml
     * @param timeout provided in testng.xml
     */
    @Parameters({"hostUrl", "browser", "timeout"})
    @BeforeClass
    public void setup(String url, String browser, long timeout) {
        WebDriver webDriver = BrowserFactory.setup(browser, timeout);
        webDriver.get(url);
        WebDriverFactory.setDriver(webDriver);
        travelBO = new TravelBO();
    }

    /**
     * Close browser
     */
    @AfterClass
    public void tearDown() {
        WebDriverFactory.quit();
    }

}

