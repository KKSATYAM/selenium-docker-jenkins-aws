package org.example.pages.flightreservation;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage {

    @FindBy(name="departure-flight")
    private List<WebElement> departureFlightOption;

    @FindBy(name="arrival-flight")
    private List<WebElement> arrivalFlightOption;

    @FindBy(id="confirm-flights")
    private WebElement confirmFlightButton;


    public FlightSelectionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightButton));
        return this.confirmFlightButton.isDisplayed();
    }


    public void selectFlight(){
        int random = ThreadLocalRandom.current().nextInt(0,departureFlightOption.size());
        this.departureFlightOption.get(random).click();
        this.arrivalFlightOption.get(random).click();
    }

    public void confirmFlight(){
        this.confirmFlightButton.click();
    }
}
