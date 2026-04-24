package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.DriverFactory;

public class HeaderPage extends BasePage
{
	
	public HeaderPage(WebDriver driver)
	{
		super(driver);
	}
	
	By signUpLoginButtonLocator = By.xpath("//a[normalize-space()='Signup / Login']");
	By loginNameLocator = By.xpath("//*[contains(text(),'Logged in as')]//b");
	By cartButtonLocator = By.xpath("(//a[@href='/view_cart'])[1]");
	By headerLinksLocator = By.xpath("//ul[@class='nav navbar-nav']/li/a");
	By registerationPageLocator = By.xpath("//a[@href='/login']");
	By deleteAccountButtonLocator = By.xpath("//a[@href='/delete_account']");
	
	
	public SignUpLoginPage clickLoginSignUpButton()
	{
		click(signUpLoginButtonLocator);
		SignUpLoginPage signUpLoginPage = new SignUpLoginPage(driver);
		return signUpLoginPage;
	}
	
	public String loginName()
	{
		waitWebElement(loginNameLocator);
		return getText(loginNameLocator);
	}
	
	public List<String> getNavigationMenuItems()
	{
		List<WebElement> menuItems = driver.findElements(headerLinksLocator);
		List<String> menuTexts = new ArrayList<>();
		for(WebElement item : menuItems)
		{
			String text = item.getText().trim().replaceAll("\\s+", " ");
	        if(!text.isEmpty())
	        {
	        	menuTexts.add(text);
	        
	        }
		}
		return menuTexts;
	}
	
	public void scrollTo(By ele)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	}
	
	public CartPage clickCartButton()
	{
		click(cartButtonLocator);
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public DeleteAccountPage clickDeleteAccountButton()
	{
		waitWebElement(deleteAccountButtonLocator);
		click(deleteAccountButtonLocator);
		DeleteAccountPage deleteAccountPage = new DeleteAccountPage(DriverFactory.getWebDriver());
		return deleteAccountPage;
	}
	
	
}
