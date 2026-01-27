package org.example.test.FlightReservation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.AbstractTest;
import org.example.pages.flightreservation.*;
import org.example.test.FlightReservation.model.FlightReservationTestData;
import org.example.utils.Config;
import org.example.utils.Constants;
import org.example.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;

public class FlightReservationTests extends AbstractTest {
    private String expectedPrice;
    private String noOfPassenger;

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath) {
        this.testData = JsonUtils.getTestData(testDataPath, FlightReservationTestData.class);
    }


    @Test
    public void userRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserdetails(this.testData.firstName(), this.testData.lastName());
        registrationPage.enterUsercredentials(this.testData.email(), this.testData.password());
        registrationPage.enterAddress(this.testData.street(), this.testData.zip(), this.testData.city());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getConfirmedName(), this.testData.firstName());
        registrationConfirmationPage.goToFlightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest() {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);

        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassenger(this.testData.passengerCount());
        flightSearchPage.searchFlight();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest() {
        FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);

        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlight();
        flightSelectionPage.confirmFlight();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmationTest() {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);

        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), this.testData.expectedPrice());
    }
}
