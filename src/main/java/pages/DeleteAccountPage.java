package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteAccountPage extends BasePage
{
	public DeleteAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	By countinueButtonLocator = By.xpath("//a[@data-qa='continue-button']");
	By deletionTextLocator = By.xpath("//h2[@class='title text-center']//b");
	
	public String getDeletionText()
	{
		waitWebElement(deletionTextLocator);
		return getText(deletionTextLocator);
		
	}
	
	public LandingPage clickContinue()
	{
		waitWebElement(countinueButtonLocator);
		click(countinueButtonLocator);
		LandingPage landingPage = new LandingPage(driver);
		return landingPage;
	}
}
