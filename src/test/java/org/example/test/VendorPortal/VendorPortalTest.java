package org.example.test.VendorPortal;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.AbstractTest;
import org.example.pages.vendorportal.DashboardPage;
import org.example.pages.vendorportal.LoginPage;
import org.example.test.VendorPortal.model.VendorPortalTestData;
import org.example.utils.Config;
import org.example.utils.Constants;
import org.example.utils.JsonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    private VendorPortalTestData testData;
    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath) {
        this.loginPage=new LoginPage(this.driver);
        this.dashboardPage=new DashboardPage(this.driver);
        this.testData=JsonUtils.getTestData(testDataPath,VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
//        LoginPage loginPage=new LoginPage(driver);
        this.loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(this.loginPage.isAt());
        this.loginPage.login(this.testData.username(), this.testData.password());
    }


    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
//        DashboardPage dashboardPage=new DashboardPage(driver);
        Assert.assertTrue(this.dashboardPage.isAt());

        Assert.assertEquals(this.dashboardPage.getMonthlyEarning(),this.testData.monthlyEarning());
        Assert.assertEquals(this.dashboardPage.getannualEarning(),this.testData.annualEarning());
        Assert.assertEquals(this.dashboardPage.getProfitMargin(),this.testData.profitMargin());
        Assert.assertEquals(this.dashboardPage.getAvaliableInventory(),this.testData.availableInventory());



        this.dashboardPage.searchOrderHistoryByText(this.testData.searchKeyword());
        Assert.assertEquals(this.dashboardPage.getSearchResultCount(),this.testData.searchResultsCount());

        this.dashboardPage.logout();

        Assert.assertTrue(this.loginPage.isAt());
    }



}
