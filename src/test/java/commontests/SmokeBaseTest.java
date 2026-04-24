package commontests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utility.DriverFactory;
import utility.ExtentReporterUtil;

public class SmokeBaseTest extends BaseTest
{
	

	
@Parameters({"browser", "headless"})
	
	@BeforeClass
	public void setup(String browser, @Optional("false") String headless)
	{
	    try
	    {
	        // set browser first
	        BaseTest.browser.set(browser);

	        // then generate email using browser
	        BaseTest.email.set("rachit_"
	            + browser
	            + "_"
	            + System.currentTimeMillis()
	            + "@gmail.com");

	       

	        DriverFactory.getInstance().driverInit(browser, headless);
	    }
	    catch(Exception e)
	    {
	        System.out.println("FAILED: " + e.getMessage());
	        e.printStackTrace();
	        throw e;
	    }
	}

@BeforeSuite
public void report()
{
	reports = ExtentReporterUtil.getExtentReport();
}

@BeforeMethod
public void setUp()
{
    hideAds(); // ← works because both in same class
}

@AfterClass
public void quit()
{
	DriverFactory.quitDriver();
}
}
