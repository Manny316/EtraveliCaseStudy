package TestCases;

import TestBase.Base;
import org.testng.annotations.Test;


public class TestUsingFilters extends Base {
    @Test(alwaysRun = true, description = "Search for a Flight from Athens to Thessaloniki. Verify number of flights with all filters applied in default state.")
    public void testStep_1() throws InterruptedException {
        travelBO.searchFlight();
        travelBO.verifyNumberOfFlights("Showing 387 of 387 flights");
    }


    @Test(alwaysRun = true, description = "Update Filters(Number of Stops,Price,Airlines,Departure Time,Arrival Time,Travel Time),Verify results include only selected Flight Company", dependsOnMethods = "testStep_1")
    public void testStep_2() throws InterruptedException {
        travelBO.filterFlight();
        travelBO.verifyCompanyName("Aegean Airlines");
    }

    @Test(alwaysRun = true, description = "Update Filters(Number of Stops,Price,Airlines,Departure Time,Arrival Time,Travel Time),Verify results include only selected range of Departure Times", dependsOnMethods = "testStep_2")
    public void testStep_3() {
        // outgoing flight ATH - THES : max 08:30 departure time -> verify departure time < 9
        // ingoing flight THES - ATH : min 10:00 arrival time -> verify departure time > 9
        travelBO.verifyDepartureTime(9, 9);
    }


    @Test(alwaysRun = true, description = "Update Filters(Number of Stops,Price,Airlines,Departure Time,Arrival Time,Travel Time),Verify results include only selected range of Flight Duration", dependsOnMethods = "testStep_3")
    public void testStep_4(){
        // max flight duration 2:00h
        travelBO.verifyDurationTime(120, true);
    }
}
