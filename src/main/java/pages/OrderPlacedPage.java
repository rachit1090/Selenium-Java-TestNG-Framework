package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPlacedPage extends BasePage
{
	public OrderPlacedPage(WebDriver driver)
	{
		super(driver);
	}
	
	By orderPlaceMessageLocator = By.xpath("//h2[@data-qa='order-placed']/b");
	By continueLocator = By.xpath("//a[@data-qa='continue-button']");
	
	public String orderPlacedMessage()
	{
		waitWebElement(orderPlaceMessageLocator);
		return getText(orderPlaceMessageLocator);
	}
	
	public void clickContinue()
	{
		waitWebElement(continueLocator);
		click(continueLocator);
	}
}
