package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Actions.BaseActions;

import java.util.List;


public class ResultsPO extends BaseActions {

    private By resultsNumber = By.cssSelector("span[class=css-101o6u0]");
    private By companyName = By.cssSelector("img[data-testid='tripDetails-carrier-logo']");
    private By originTime = By.cssSelector("div[data-testid*='info-departureTime']");
    private By travelDuration = By.cssSelector("p[data-testid*='info-duration']");
    private By destinationTime = By.cssSelector("div[data-testid*='info-arrivalTime']");

    /**
     * Retrieves a list of WebElement objects representing the company names
     * on the web page. This method locates the elements based on the
     * provided companyName locator and returns them as a List.
     *
     * @return List of WebElement objects containing the company names.
     */
    public List<WebElement> getCompanyNames() {
        return findElements(companyName);
    }


    /**
     * Retrieves the number of results displayed on the web page as a String.
     * The method gets the text from the resultsNumber element, trims any
     * leading or trailing whitespace, and removes any colons (":") from the text.
     *
     * @return A String representing the number of results, with colons removed.
     */
    public String getResultsNumber() {
        return getText(resultsNumber).trim().replace(":", "");
    }

    /**
     * Retrieves a list of WebElement objects representing the origin time elements
     * on the web page. This method locates the elements using the originTime locator
     * and returns them as a List.
     *
     * @return List of WebElement objects containing the origin time elements.
     */

    public List<WebElement> getOriginTimeElements() {
        return findElements(originTime);
    }

    /**
     * Retrieves a list of WebElement objects representing the destination time elements
     * on the web page. The method locates these elements using the destinationTime locator
     * and returns them in a List.
     *
     * @return List of WebElement objects containing the destination time elements.
     */

    public List<WebElement> getDestinationTimeElements() {
        return findElements(destinationTime);
    }

    /**
     * Retrieves a list of WebElement objects representing the travel duration elements
     * on the web page. The method locates these elements using the travelDuration locator
     * and returns them in a List.
     *
     * @return List of WebElement objects containing the travel duration elements.
     */
    public List<WebElement> getTravelDurationElements() {
        return findElements(travelDuration);
    }
}
