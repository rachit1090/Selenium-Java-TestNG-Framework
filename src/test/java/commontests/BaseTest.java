package commontests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;

import utility.DriverFactory;
import utility.ExtentReporterUtil;

public class BaseTest 
{
	
	protected WebDriver driver;
	
	
	public static ExtentReports reports;
	 static
	    {
	        try
	        {
	            reports = ExtentReporterUtil
	                .getExtentReport();
	            
	        }
	        catch(Exception e)
	        {
	            System.out.println(
	                "Reports init FAILED: " 
	                + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	public static ThreadLocal<String> browser = new ThreadLocal<>();
	public static ThreadLocal<String> email = new ThreadLocal<>();
	
	//@Parameters({"browser", "headless"})
	
	
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
	
	
	
	
	public void report()
	{
		reports = ExtentReporterUtil.getExtentReport();
	}
	
	public void hideAds()
	{
	    try
	    {
	        ((JavascriptExecutor) DriverFactory.getWebDriver())
	            .executeScript(
	                "var ads = document.querySelectorAll(" +
	                "'ins.adsbygoogle, " +
	                "iframe[src*=\"google\"]," +
	                "div[id*=\"google_ads\"]," +
	                "div[class*=\"adsbygoogle\"]');" +
	                "for(var i=0; i<ads.length; i++){" +
	                "  ads[i].style.display='none';" +
	                "}");
	    }
	    catch(Exception e)
	    {
	        System.out.println("No ads found");
	    }
	}
	
	
	
	
	
	public void setUp()
	{
	    hideAds(); // ← works because both in same class
	}
	
	
	public static void captureScreenShot(String testName)
	{
		TakesScreenshot screenShot = (TakesScreenshot) DriverFactory.getWebDriver();
		File source = screenShot.getScreenshotAs(OutputType.FILE);
		File destination = new File(
			    System.getProperty("user.dir") 
			    + "/reports/screenshot" + testName + ".png");
		
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void quit()
	{
		DriverFactory.quitDriver();
	}
	
}
