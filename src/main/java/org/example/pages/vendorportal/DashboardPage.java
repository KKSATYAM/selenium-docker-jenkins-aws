package org.example.pages.vendorportal;

import org.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log= LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id="monthly-earning")
    private WebElement monthlyEarning;

    @FindBy(id="annual-earning")
    private WebElement annualEarning;

    @FindBy(id="profit-margin")
    private WebElement profitMargin;

    @FindBy(id="available-inventory")
    private WebElement availableInventory;


    @FindBy(css = "#dataTable_filter input")
    private WebElement searchBox;

    @FindBy(id="dataTable_info")
    private WebElement searchResultCountElement;

    @FindBy( css = ".img-profile")
    private WebElement userProfilePicture;

    @FindBy(css="div.show:nth-child(2) a:nth-child(5)")
    private WebElement logoutLink;

    @FindBy(css="a.btn-primary")
    private WebElement logoutButton;
    public DashboardPage(WebDriver driver) {

        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarning));
        return this.monthlyEarning.isDisplayed();
    }

    public String getMonthlyEarning(){
        return this.monthlyEarning.getText();
    }

    public String getProfitMargin(){
        return this.profitMargin.getText();
    }

    public String getannualEarning(){
        return this.annualEarning.getText();
    }
    public String getAvaliableInventory(){
        return this.availableInventory.getText();
    }

    public void searchOrderHistoryByText(String searchText){
        this.searchBox.sendKeys(searchText);
    }

    public int getSearchResultCount(){
        String[] textMessage = this.searchResultCountElement.getText().split(" ");
        log.info("Result count is :: {}",Integer.parseInt(textMessage[5]));
        return Integer.parseInt(textMessage[5]);
    }

    public void logout(){
        this.userProfilePicture.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutButton));
        this.logoutButton.click();
    }
}
