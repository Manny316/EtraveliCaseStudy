package PageObjects;

import enums.Sliders;
import org.openqa.selenium.By;
import Actions.BaseActions;

public class FiltersPO extends BaseActions {

    // no of stops
    private By nonStopButton = By.cssSelector("label[data-testid='MAX_STOPS-direct']");
    private By maxOneStopButton = By.cssSelector("label[data-testid='MAX_STOPS-max']");
    private By allStopsButton = By.cssSelector("label[data-testid='MAX_STOPS-all']");

    // airlines
    private By clearAllAirlinesButton = By.cssSelector("span[data-testid='resultPage-AIRLINESFilter-deselect-all-button']");
    private By selectAllAirlinesButton = By.cssSelector("span[data-testid='resultPage-AIRLINESFilter-select-all-button']");
    private By aegeanCheckbox = By.cssSelector("input#airlines-A3");

    // departure - arrival times
    private By goSliderButtonMax = By.cssSelector("div[data-testid='resultPage-departureArrivalFilter-departure0-slider'] div[data-testid='handle-1']");
    private By returnSliderButtonMin = By.cssSelector("div[data-testid='resultPage-departureArrivalFilter-departure1-slider'] div[data-testid='handle-0']");

    // travel time
    private By travelTimeSliderButton = By.cssSelector("div[data-testid='resultPage-TRAVEL_TIMEFilter-content'] div[data-testid='handle-0']");

    //price
    private By priceSliderButtonMin = By.cssSelector("div[data-testid='resultPage-PRICEFilter-content'] div[data-testid='handle-0']");
    private By priceSliderButtonMax = By.cssSelector("div[data-testid='resultPage-PRICEFilter-content'] div[data-testid='handle-1']");



    /***
     * Clicks Non Stop Flight Button at Filters
     * @return
     */
    public FiltersPO clickNonStopFlight() {
        waitForElementIsClickable(nonStopButton);
        click(nonStopButton);
        return this;
    }

    /***
     * Clicks All Stops Flight Button at Filters
     * @return
     */
    public FiltersPO clickAllStopsButton() {
        waitForElementIsClickable(allStopsButton);
        click(allStopsButton);
        return this;
    }

    /***
     * Clicks Max one stop Flight Button at Filters
     * @return
     */
    public FiltersPO clickMaxOneStopButton() {
        waitForElementIsClickable(maxOneStopButton);
        click(maxOneStopButton);
        return this;
    }

    /***
     * Clicks Clear All Airlines Button at Filters
     * @return
     */
    public FiltersPO clickClearAllAirlinesButton() {
        waitForElementIsClickable(clearAllAirlinesButton);
        click(clearAllAirlinesButton);
        return this;
    }

    /***
     * Clicks Select All Airlines Button at Filters
     * @return
     */
    public FiltersPO clickSelectAllAirlinesButton() {
        waitForElementIsClickable(selectAllAirlinesButton);
        click(selectAllAirlinesButton);
        return this;
    }

    /***
     * Clicks Aegean Airlines Checkbox at Filters
     * @return
     */
    public FiltersPO clickAegeanAirlinesCheckbox() throws InterruptedException {
        Thread.sleep(1000);
        click(aegeanCheckbox);
        return this;
    }


    /**
     * Slides elements
     * Slide from left -> right give positive offsets
     * Slide from right <- left give negative offsets
     * @param slider
     * @param xOffset
     * @param yOffset
     * @return
     */
    public FiltersPO slideFilters(Sliders slider, int xOffset, int yOffset) {
        switch (slider) {
            case minPrice:
                waitForElementIsClickable(priceSliderButtonMin);
                slideElement(priceSliderButtonMin, xOffset, yOffset);
                break;
            case maxPrice:
                waitForElementIsClickable(priceSliderButtonMax);
                slideElement(priceSliderButtonMax, xOffset, yOffset);
                break;
            case travelTime:
                scrollBottomPageJS();
                waitForElementIsClickable(travelTimeSliderButton);
                slideElement(travelTimeSliderButton, xOffset, yOffset);
                break;
            case goDepartureMax:
                waitForElementIsClickable(goSliderButtonMax);
                slideElement(goSliderButtonMax, xOffset, yOffset);
                break;
            case returnArrivalMin:
                scrollBottomPageJS();
                waitForElementIsClickable(returnSliderButtonMin);
                slideElement(returnSliderButtonMin, xOffset, yOffset);
                break;
            default:
        }
        return this;

    }
}
