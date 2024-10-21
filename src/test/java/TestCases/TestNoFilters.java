package TestCases;

import TestBase.Base;
import org.testng.annotations.Test;

/***
 * Tests Verifies results
 * - Case 1 : No results exist for the selected filters
 * */

public class TestNoFilters extends Base {

    @Test(alwaysRun = true, description = "Search for a Flight from Athens to London}. Update Filters(Stops, Price, Travel Time). Verify no results are available.")
    public void testStep_1() throws InterruptedException {
        travelBO.searchFlightAndApplyFilters();
        travelBO.verifyNoFlights();
    }

}

