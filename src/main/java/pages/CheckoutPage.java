package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage
{
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
	}
	
	By placeOrderLocator = By.xpath("//a[@class='btn btn-default check_out']");
	
	public PaymentPage clickPlaceOrder()
	{
		waitWebElement(placeOrderLocator);
		click(placeOrderLocator);
		PaymentPage paymentPage = new PaymentPage(driver);
		return paymentPage;
	}
}
