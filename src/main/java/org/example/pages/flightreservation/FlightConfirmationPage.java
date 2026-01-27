package org.example.pages.flightreservation;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);


    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement filghtConfirmationElement;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2) .fw-bold")
    private WebElement totalpriceElement;


    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(filghtConfirmationElement));
        return this.filghtConfirmationElement.isDisplayed();
    }

    public String getPrice() {
        String confirmation = this.filghtConfirmationElement.getText();
        String price = this.totalpriceElement.getText();
        log.info("Flight Confirmation :: {}", confirmation);
        log.info("Price :: {}", price);
        return this.totalpriceElement.getText();
    }
}
