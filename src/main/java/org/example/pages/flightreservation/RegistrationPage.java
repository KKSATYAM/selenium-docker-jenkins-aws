package org.example.pages.flightreservation;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends AbstractPage {

//    private WebDriver driver;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "email")
    private WebElement email;
    @FindBy(id = "password")
    private WebElement password;

    @FindBy(name = "street")
    private WebElement street;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "zip")
    private WebElement zipInput;

    @FindBy(id = "register-btn")
    private WebElement registerButton;


    public RegistrationPage(WebDriver driver) {
        super(driver);
//        this.driver.get(url);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.firstName));
        return this.firstName.isDisplayed();
    }

    public void goTo(String url) {
        this.driver.get(url);
    }

    public void enterUserdetails(String firstName, String lastName) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
    }

    public void enterUsercredentials(String email, String password) {
        this.email.sendKeys(email);
        this.password.sendKeys(password);
    }

    public void enterAddress(String street, String zip, String city) {
        this.street.sendKeys(street);
        this.zipInput.sendKeys(zip);
        this.city.sendKeys(city);
    }

    public void register() {
        this.registerButton.click();
    }


}
