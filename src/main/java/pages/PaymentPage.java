package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BasePage
{
	public PaymentPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	By cardFormLocator = By.id("payment-form");
	By nameOnCardLocator = By.xpath("//input[@name='name_on_card']");
	By cardNumberLocator = By.xpath("//input[@name='card_number']");
	By cvvLocator = By.xpath("//input[@name='cvc']");
	By monthLocator = By.xpath("//input[@name='expiry_month']");
	By expirationYearLocator = By.xpath("//input[@name='expiry_year']");
	By confirmLocator = By.id("submit");
	
	public OrderPlacedPage submitCardDetails(String name, String cardNumber, String cvv, String expirationMonth, String expirationYear)
	{
		waitWebElement(cardFormLocator);
		sendKeys(nameOnCardLocator, name);
		sendKeys(cardNumberLocator, cardNumber);
		sendKeys(cvvLocator, cvv);
		sendKeys(monthLocator, expirationMonth);
		sendKeys(expirationYearLocator, expirationYear);
		click(confirmLocator);
		OrderPlacedPage orderPlacedPage = new OrderPlacedPage(driver);
		return orderPlacedPage;
	}
	
	
}
