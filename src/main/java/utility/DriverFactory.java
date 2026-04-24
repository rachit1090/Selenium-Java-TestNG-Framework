package utility;

import org.openqa.selenium.WebDriver;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory 
{
	public static volatile DriverFactory instance;
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	private DriverFactory() {}
	
	
	public static DriverFactory getInstance()
	{
		if(instance==null)
		{
			synchronized(DriverFactory.class)
			{
				if(instance == null)
                {
                    instance = new DriverFactory();
                }
			}
		}
		return instance;
	}
	
	@Parameters({"browser","headless"})
	public void driverInit(String browser, @Optional("") String headless)
	{
		boolean isHeadless = Boolean.parseBoolean(headless);
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(isHeadless)
			{
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-gpu");
			options.addArguments("--window-size=1920,1080");
			}
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver(options));
		}
		
		else if(browser.equalsIgnoreCase("firefox"))
		{
			FirefoxOptions options = new FirefoxOptions();
	        if(isHeadless)
	        {
	            options.addArguments("--headless");
	            options.addArguments("--width=1920");
	            options.addArguments("--height=1080");
	        }
			
			WebDriverManager.firefoxdriver().setup();
		    driver.set(new FirefoxDriver(options));
		}
		
		else if(browser.equalsIgnoreCase("edge"))
        {
			EdgeOptions options = new EdgeOptions();
	        if(isHeadless)
	        {
	            options.addArguments("--headless");
	            options.addArguments("--no-sandbox");
	            options.addArguments("--disable-dev-shm-usage");
	        }
			
            driver.set(new EdgeDriver(options));
            
        }
		
		else
		{
			throw new IllegalArgumentException("Browser not supported" + browser);
			
		}
		
		getWebDriver().manage().window().maximize();
        getWebDriver().get("https://automationexercise.com");
		
	}
	
	public static WebDriver getWebDriver()
	{
		
		return driver.get();
	}
	
	public static void quitDriver()
	{
		if(driver.get()!=null)
		{
			driver.get().quit();
			driver.remove();
		}
	}
	
}
