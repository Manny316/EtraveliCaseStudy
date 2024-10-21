package PageObjects;

import org.openqa.selenium.By;
import Actions.BaseActions;

public class HomePagePO extends BaseActions {

    //Home Page Locators
    private By acceptCookiesButton = By.cssSelector("button[data-testid='cookieBanner-confirmButton']");
    private By destinationFromInput = By.cssSelector("input#searchForm-singleBound-origin-input");
    private By destinationToInput = By.cssSelector("input#searchForm-singleBound-destination-input");
    private By destinationInput = By.cssSelector("div[data-testid='etiDropdownOption']");
    private By searchFlightButton = By.cssSelector("button[data-testid='searchForm-searchFlights-button']");


    /**
     * Clicks Accept Cookies Button
     *
     * @return
     */
    public HomePagePO clickAcceptCookiesButton() {
        waitForElementVisible(acceptCookiesButton);
        click(acceptCookiesButton);
        return this;
    }

    /**
     * Type country of Departure
     *
     * @return
     */
    public HomePagePO fromInput(String country) {
        waitForElementVisible(destinationFromInput);
        sendKeys(destinationFromInput, country);
        waitForElementVisible(destinationInput);
        clickEnter(destinationFromInput);
        return this;
    }

    /**
     * Type country of Arrival
     *
     * @return
     */
    public HomePagePO toInput(String country) throws InterruptedException {
        waitForElementVisible(destinationToInput);
        sendKeys(destinationToInput, country);
        waitForElementVisible(destinationToInput);
        Thread.sleep(2000);
        clickEnter(destinationToInput);
        return this;
    }

    /**
     * Clicks Search Flight Button
     *
     * @return
     */
    public HomePagePO clickSearchFlightButton() {
        waitForElementIsClickable(searchFlightButton);
        click(searchFlightButton);
        return this;
    }


}