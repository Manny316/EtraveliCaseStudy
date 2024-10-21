package Actions;

import Factories.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BaseActions {
    // Timeout value for explicit waits in seconds
    private final int waitForElementTimeout = 20;

    /////////////////////// Basic Methods ///////////////////////

    /**
     * Returns a new {@link WebDriverWait} with the default {@link #waitForElementTimeout} duration
     *
     * @return - a new {@link WebDriverWait}
     */
    private WebDriverWait driverWait() {
        return driverWait(waitForElementTimeout);
    }
    /**
     * Returns a new {@link WebDriverWait} with the timeout duration given
     *
     * @param timeoutSeconds - the duration of timeout in seconds
     * @return - a new {@link WebDriverWait}
     */
    private WebDriverWait driverWait(int timeoutSeconds) {
        return new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(timeoutSeconds));
    }

    /**
     * Wait for Element
     *
     * @param locator
     */

    public WebElement waitForElementVisible(By locator) {
        return driverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for Element to be clickable
     *
     * @param locator
     */

    public void waitForElementIsClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(waitForElementTimeout));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Get text
     *
     * @param locator
     */
    public String getText(By locator) {
        return WebDriverFactory.getDriver().findElement(locator).getText().trim();
    }

    /**
     * Click element
     *
     * @param locator
     */
    public void click(By locator) {
        WebDriverFactory.getDriver().findElement(locator).click();
    }

    /**
     * Clears field
     * Writes field
     * @param locator
     */
    public void sendKeys(By locator, String text) {
        WebDriverFactory.getDriver().findElement(locator).clear();
        WebDriverFactory.getDriver().findElement(locator).sendKeys(text);
    }

    /**
     * Click Enter
     *
     * @param locator
     * @return
     */
    public void clickEnter(By locator){
        WebDriverFactory.getDriver().findElement(locator).sendKeys(Keys.ENTER);
    }

    /**
     * Scroll at the bottom of the page with Javascript
     */
    public void scrollBottomPageJS() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverFactory.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }


    /**
     * Returns List of Web Elements that Match locator
     * @param locator
     */
    public List<WebElement> findElements(By locator){
        return WebDriverFactory.getDriver().findElements(locator);
    }


    /**
     * Slide Element
     * @param locator
     * @param xOffset
     * @param yOffset
     */
    public void slideElement(By locator,int xOffset, int yOffset){
        WebElement slider = WebDriverFactory.getDriver().findElement(locator);
        Actions action = new Actions(WebDriverFactory.getDriver());
        action.dragAndDropBy(slider,xOffset,yOffset).perform();

    }
}
