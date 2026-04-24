package utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import commontests.BaseTest;

public class Listeners implements ITestListener
{
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result)
    {
        

        String browserName =
            BaseTest.browser.get() != null
                ? BaseTest.browser.get()
                           .toUpperCase()
                : "BROWSER";

        if(BaseTest.reports != null)
        {
            test = BaseTest.reports.createTest(
                browserName + " | "
                + result.getName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        if(test!= null)
            test.pass("Test passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
    	if (test != null)
            test.fail(result.getThrowable());

        if (DriverFactory.getWebDriver() != null)
            BaseTest.captureScreenShot(result.getName());
        else
            System.out.println("Driver null - skipping screenshot");
    }

    @Override
    public void onFinish(ITestContext context)
    {
        if(BaseTest.reports != null)
            BaseTest.reports.flush();
    }
}
