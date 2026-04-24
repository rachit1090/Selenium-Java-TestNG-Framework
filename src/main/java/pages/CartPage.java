package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage
{
	public CartPage(WebDriver driver)
	{
		super(driver);
		
	}
	
	By productAddedLocator = By.xpath("//td[@class='cart_description']/h4");
	By proceedToCheckoutLocator = By.xpath("//a[@class='btn btn-default check_out']");
	
	
	public String getProductName()
	{
		waitWebElement(productAddedLocator);
		return getText(productAddedLocator);
	}
	
	public CheckoutPage clickProceedToCheckout()
	{
		waitWebElement(proceedToCheckoutLocator);
		click(proceedToCheckoutLocator);
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
}
