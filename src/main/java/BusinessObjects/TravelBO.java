package BusinessObjects;

import PageObjects.FiltersPO;
import PageObjects.HomePagePO;
import PageObjects.ResultsPO;
import dtos.Travel;
import enums.Sliders;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TravelBO {
    private HomePagePO homePagePO;
    private FiltersPO filtersPO;
    private ResultsPO resultsPO;

    public TravelBO() {

        homePagePO = new HomePagePO();
        filtersPO = new FiltersPO();
        resultsPO = new ResultsPO();
    }

    /**
     * Search Flight
     *
     * @return
     */
    public void searchForFlight(Travel travel) throws InterruptedException {
        homePagePO.clickAcceptCookiesButton();
        Thread.sleep(2000);
        homePagePO.fromInput(travel.fromCity)
                .toInput(travel.toCity)
                .clickSearchFlightButton();
    }

    /**
     * Search for Flight Ath - Thes
     * with return
     * for one person
     */
    public void searchFlight() throws InterruptedException {
        Travel travel1 = new Travel();
        searchForFlight(travel1);
    }

    /**
     * Filter flight with :
     * - Non Stop
     * - Only Aegean Airline
     * - Max price = 714
     * - Max departure 08:30
     * - Min return 10:00
     * - Max travel time 2:00h
     */
    public void filterFlight() throws InterruptedException {
        filtersPO.clickNonStopFlight()
                .clickClearAllAirlinesButton()
                .clickAegeanAirlinesCheckbox()
                .slideFilters(Sliders.maxPrice, -150, 0) // set max price 714
                .slideFilters(Sliders.goDepartureMax, -150, 0) // set max departure 08:30
                .slideFilters(Sliders.returnArrivalMin, 100, 0) // set min return 10:00
                .slideFilters(Sliders.travelTime, -220, 0); // set max travel time 2:00h
    }

    /**
     * Search for Flight Ath - London
     * with return
     * for one person
     */
    public void searchFlightAndApplyFilters() throws InterruptedException {
        Travel travel2 = new Travel();
        travel2.toCity = "London";

        searchForFlight(travel2);
        filtersPO.clickMaxOneStopButton();
        filtersPO.slideFilters(Sliders.maxPrice, -160, 0) // set max price 1.647
                .slideFilters(Sliders.travelTime, -600, 0);// set max travel time 3h 30m

    }

    /**
     * Verify Company Name from Results rows
     */
    public TravelBO verifyCompanyName(String name) {
        List<WebElement> rows = Collections.emptyList();
        rows = resultsPO.getCompanyNames();
        for (int i = 0; i < rows.size(); i++) {
            Assert.assertEquals(rows.get(i).getAttribute("alt").trim(), name, "Row: " + i + " Expected:" + name + "VS Actual: " + rows.get(i).getText());
        }
        return this;
    }

    /**
     * Verify number of flights
     *
     * @return
     */
    public TravelBO verifyNumberOfFlights(String expectedFlights) {
        Assert.assertEquals(resultsPO.getResultsNumber(), expectedFlights);
        return this;
    }

    /**
     * Verify zero number of flights
     *
     * @return
     */
    public TravelBO verifyNoFlights() throws InterruptedException {
        Thread.sleep(3000);
        String[] values = resultsPO.getResultsNumber().trim().split(" ");
        int flightsNumber = Integer.parseInt(values[1]);
        Assert.assertEquals(flightsNumber, 0);
        return this;
    }

    /**
     * Verify Departure Time ,in Results rows, is withing the filter range
     * even rows : outbound flights departure time
     * odd rows : inbound flights departure time
     *
     * @param outgoingTime
     * @param incomingTime
     */
    public TravelBO verifyDepartureTime(Integer outgoingTime, Integer incomingTime) {
        List<WebElement> rows = Collections.emptyList();
        rows = resultsPO.getOriginTimeElements();
        for (int i = 0; i < rows.size(); i++) {
            if (i % 2 == 0) {
                String[] values = rows.get(i).getText().split(":");
                int hour = Integer.parseInt(values[0]);
                Assert.assertTrue(hour < outgoingTime, "Row: " + i + " Expected max:" + outgoingTime + "VS Actual: " + hour);
            } else {
                String[] values = rows.get(i).getText().split(":");
                int hour = Integer.parseInt(values[0]);
                Assert.assertTrue(hour > incomingTime, "Row: " + i + " Expected min:" + incomingTime + "VS Actual: " + hour);
            }
        }
        return this;
    }

    /**
     * Verify Arrival Time ,in Results rows, is withing the filter range
     * even rows : outbound flights departure time
     * odd rows : inbound flights departure time
     *
     * @param outgoingTime
     * @param incomingTime
     */
    public TravelBO verifyArrivalTime(Integer outgoingTime, Integer incomingTime) {
        List<WebElement> rows = Collections.emptyList();
        rows = resultsPO.getDestinationTimeElements();
        for (int i = 0; i < rows.size(); i++) {
            if (i % 2 == 0) {
                String[] values = rows.get(i).getText().split(":");
                int hour = Integer.parseInt(values[0]);
                Assert.assertTrue(hour < outgoingTime, "Row: " + i + " Expected max:" + outgoingTime + "VS Actual: " + hour);
            } else {
                String[] values = rows.get(i).getText().split(":");
                int hour = Integer.parseInt(values[0]);
                Assert.assertTrue(hour > incomingTime, "Row: " + i + " Expected max:" + incomingTime + "VS Actual: " + hour);
            }
        }
        return this;
    }

    /**
     * Verify Duration Time ,in Results rows, is withing the filter range
     *
     * @param expectedDuration
     * @param shortFlight
     */
    public TravelBO verifyDurationTime(Integer expectedDuration, Boolean shortFlight) {
        List<WebElement> rows = Collections.emptyList();
        rows = resultsPO.getTravelDurationElements();
        for (int i = 0; i < rows.size(); i++) {
            if (shortFlight) {
                String[] parts = rows.get(i).getText().replaceAll("\\W", "").split("min"); // Split the string into parts based on min string
                int minutes = Integer.parseInt(parts[0]); // Extract the minutes;
                Assert.assertTrue(minutes < expectedDuration, "Row: " + i + " Expected max:" + expectedDuration + "VS Actual: " + minutes);

            } else {
                String[] parts = rows.get(i).getText().replaceAll("\\W", "").split(" "); // Split the string into parts based on space
                int hours = Integer.parseInt(parts[0].replace("h", "")); // Extract the hours and remove the "h" character
                int minutes = Integer.parseInt(parts[1].replace("min", "")); // Extract the minutes and remove the "min" character
                Assert.assertTrue(hours < expectedDuration, "Row: " + i + " Expected max:" + expectedDuration + "VS Actual: " + hours);

            }
        }
        return this;
    }


}
