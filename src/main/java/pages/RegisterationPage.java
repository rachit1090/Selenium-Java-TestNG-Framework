package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterationPage extends BasePage
{
	//calling driver
	public RegisterationPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	
	By loginFormLocator = By.xpath("//div[@class='login-form']"); //Form locator
	By selectMaleLocator = By.xpath("//label[@for='id_gender1']"); //Mr. Radio Button
	By selectFemaleLocator = By.xpath("//label[@for='id_gender2']"); //Mrs. Radio Button
	By passwordLocator = By.id("password"); //Password Radio Button
	//By dateOfBirthDayLocator= By.xpath("//select[@id='days']");
	WebElement dateOfBirthDayLocator = driver.findElement(By.id("days")); 
	By dateOfBirthMonthLocator  = By.id("months");
	By dateOfBirthYearLocator   = By.id("years");
	By firstNameLocator         = By.id("first_name");
	By lastNameLocator          = By.id("last_name");
	By companyLocator           = By.id("company");
	By address1Locator          = By.id("address1");
	By address2Locator          = By.id("address2");
	By countryLocator           = By.id("country");
	By stateLocator             = By.id("state");
	By cityLocator              = By.id("city");
	By zipcodeLocator           = By.id("zipcode");
	By mobileNumberLocator      = By.id("mobile_number");
	By newsletterLocator        = By.id("newsletter");
	By specialOffersLocator     = By.id("optin");
	By createAccountButton 		= By.xpath("//button[@data-qa='create-account']");
	
	
	//Already existing locators
	By emailExistsLocator = By.xpath("//p[text()='Email Address already exist!']");
	
	//Choosing Mr. or Mrs.
	public void selectTitle(String title)
	{
		if(title.equals("Mr."))
		{
			click(selectMaleLocator);
		}
		
		else if(title.equals("Mrs."))
		{
			click(selectFemaleLocator);
		}
		else
		{
			throw new IllegalArgumentException("Invalid title: '" + title + "'. Expected 'Mr.' or 'Mrs.'"); //if unavailable option is entered
		}
	}
	
	public AccountCreatedPage fillTheForm(String title, String password, String dobDay, String dobMonth, String dobYear,
            String firstName, String lastName,
            String company, String address1, String address2,
            String country, String state, String city,
            String zipcode, String mobileNumber,
            boolean newsletter, boolean specialOffers)
	{
		waitWebElement(loginFormLocator); //wait for form to be displayed
		
		selectTitle(title);
		sendKeys(passwordLocator, password);
		Select selectDay = new Select(dateOfBirthDayLocator);
		selectDay.selectByValue(dobDay);
		 Select selectMonth = new Select(driver.findElement(dateOfBirthMonthLocator));
		    selectMonth.selectByValue(dobMonth);

		    Select selectYear = new Select(driver.findElement(dateOfBirthYearLocator));
		    selectYear.selectByValue(dobYear);

		    if (newsletter)
		        click(newsletterLocator);

		    if (specialOffers)
		        click(specialOffersLocator);

		    sendKeys(firstNameLocator, firstName);
		    sendKeys(lastNameLocator, lastName);
		    sendKeys(companyLocator, company);
		    sendKeys(address1Locator, address1);
		    sendKeys(address2Locator, address2);

		    Select selectCountry = new Select(driver.findElement(countryLocator));
		    selectCountry.selectByVisibleText(country);

		    sendKeys(stateLocator, state);
		    sendKeys(cityLocator, city);
		    sendKeys(zipcodeLocator, zipcode);
		    sendKeys(mobileNumberLocator, mobileNumber);
		    
		    click(createAccountButton);
		    
		   AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
		   return accountCreatedPage;
	}
	
	public AccountCreatedPage fillTheFormMandatoryFields(String password, String firstName, String lastName, String address1,  String country, String state, String city,
            String zipcode, String mobileNumber)
	{
		waitWebElement(loginFormLocator); //wait for form to be displayed
		sendKeys(passwordLocator, password);
		sendKeys(firstNameLocator, firstName);
	    sendKeys(lastNameLocator, lastName);
	    sendKeys(address1Locator, address1);
	    
	    Select selectCountry = new Select(driver.findElement(countryLocator));
	    selectCountry.selectByVisibleText(country);
	    sendKeys(stateLocator, state);
	    sendKeys(cityLocator, city);
	    sendKeys(zipcodeLocator, zipcode);
	    sendKeys(mobileNumberLocator, mobileNumber);
	    
	    click(createAccountButton);
	    AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
		   return accountCreatedPage;
	    
	}
	
	public String getErrorSignup()
	{
		waitWebElement(emailExistsLocator);
		return getText(emailExistsLocator);
	}
	
	
}
