package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpLoginPage extends BasePage
{
	
	public SignUpLoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	By loginEmail = By.xpath("//input[@data-qa='login-email']");
	By loginPassword = By.xpath("//input[@data-qa='login-password']");
	By loginButton = By.xpath("//button[@data-qa='login-button']");
	By signUpName = By.xpath("//input[@data-qa='signup-name']");
	By signUpEmail = By.xpath("//input[@data-qa='signup-email']");
	By signUpButton = By.xpath("//button[@data-qa='signup-button']");
	By signUpErrorLocator = By.xpath("//p[text()='Email Address already exist!']");
	
	public LandingPage signIn(String email, String password)
	{
		waitWebElement(loginEmail);
		sendKeys(loginEmail, email);
		sendKeys(loginPassword, password);
		click(loginButton);
		LandingPage landingPage = new LandingPage(driver);
		return landingPage;
	}
	
	public RegisterationPage signUp(String name, String email)
	{
		waitWebElement(signUpEmail);
		sendKeys(signUpName, name);
		sendKeys(signUpEmail, email);
		click(signUpButton);
		RegisterationPage registerationPage =new RegisterationPage(driver);
		return registerationPage;
	}
	
	
	
}
