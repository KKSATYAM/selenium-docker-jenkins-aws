package org.example.listeners;

import org.example.utils.Constants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListner implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        TakesScreenshot driver = (TakesScreenshot) result.getTestContext().getAttribute(Constants.DRIVER);
        String screenshot = driver.getScreenshotAs(OutputType.BASE64);
        String htmlImageFormat="<img width=1000px src='data:image/png;base64,%s' />'";
        String htmlImage=String.format(htmlImageFormat,screenshot);
        Reporter.log(htmlImage);

    }
}
