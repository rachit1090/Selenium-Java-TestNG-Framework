package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage
{
	public SignUpPage(WebDriver driver)
	{
		super(driver);
	}
	
	By emailExistsLocator = By.xpath("//p[text()='Email Address already exist!']");
	
	
	public String getErrorSignup()
	{
		waitWebElement(emailExistsLocator);
		return getText(emailExistsLocator);
	}
}
