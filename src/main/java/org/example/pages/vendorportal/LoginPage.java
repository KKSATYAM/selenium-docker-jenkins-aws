package org.example.pages.vendorportal;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {


    @FindBy(id="username")
    private WebElement userName;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(id="login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.loginButton));
        return this.loginButton.isDisplayed();
    }

    public void goTo(String url){
        this.driver.get(url);
    }

    public void login(String userName, String password){
        this.userName.sendKeys(userName);
        this.password.sendKeys(password);
        this.loginButton.click();
    }
}
