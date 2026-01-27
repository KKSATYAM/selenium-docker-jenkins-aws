package org.example.pages.flightreservation;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RegistrationConfirmationPage extends AbstractPage {


    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightSearchButton;

    @FindBy(css = "div#registration-confirmation-section p b")
    private WebElement firstName;

    @FindBy( css = "div#registration-confirmation-section p b")
    private WebElement confirmationName;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightSearchButton));
        return this.goToFlightSearchButton.isDisplayed();
    }

    public void goToFlightSearch() {
        this.goToFlightSearchButton.click();
    }

    public String getConfirmedName(){
        return this.confirmationName.getText();
    }

}
