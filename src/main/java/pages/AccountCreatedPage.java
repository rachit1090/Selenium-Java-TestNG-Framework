package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage
{
	public AccountCreatedPage(WebDriver driver)
	{
		super(driver);
	}
	
	By countinueButtonLocator = By.xpath("//a[@data-qa='continue-button']");
	
	
	public LandingPage clickContinue()
	{
		waitWebElement(countinueButtonLocator);
		click(countinueButtonLocator);
		LandingPage landingPage = new LandingPage(driver);
		return landingPage;
	}
	
	
	
}
