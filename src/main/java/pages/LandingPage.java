package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.DriverFactory;

public class LandingPage extends BasePage
{
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
	}
	
	By productCardLocator = By.xpath("//div[@class='col-sm-4']");
	By productNameLocator = By.xpath("//div[@class='productinfo text-center']/p");
	By productPriceLocator = By.xpath("//div[@class='productinfo text-center']/h2");
	By productImageLocator = By.xpath("//div[@class='productinfo text-center']/img");
	By addToCartLocator = By.xpath("//a[@class='btn btn-default add-to-cart']");
	By popupLocator = By.xpath("//div[@class='modal-content']");
	By addedTextLocator = By.xpath("//h4[@class='modal-title w-100']");
	By continueShoppingLocator = By.xpath("//button[@class='btn btn-success close-modal btn-block']");
	By continueToCartLocator = By.xpath("//u[normalize-space()='View Cart']");
	By subscribeSectionLocator = By.xpath("//div[@class='single-widget']");
	By logoutButtonLocator = By.xpath("//a[@href='/logout']");
	By subscribtionEmailLocator= By.id("susbscribe_email");
	By subscribeButtonLocator = By.id("subscribe");
	
	
	public void addProduct(String product)
	{
		waitWebElement(addToCartLocator);
		//hoverOverText(productCardLocator,product);
		goToProduct(productNameLocator, addToCartLocator, product);
		
		
	}
	
	public String productAddedMessage()
	{
		waitWebElement(popupLocator);
		return getText(addedTextLocator);
	}
	
	public void clickContinueShopping()
	{
		click(continueShoppingLocator);
	}
	
	public CartPage clickContinueToCart()
	{
		click(continueToCartLocator);
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public Boolean areProductsDisplayed()
	{
		waitWebElement(productCardLocator);
		return isDisplayed(productCardLocator);
	}
	
	public Boolean isNameDisplayed()
	{
		waitWebElement(productNameLocator);
		return isDisplayed(productNameLocator);
	}
	
	public Boolean isPriceDisplayed()
	{
		waitWebElement(productPriceLocator);
		return isDisplayed(productPriceLocator);
	}
	
	public Boolean isImageDisplayed()
	{
		waitWebElement(productImageLocator);
		return isDisplayed(productImageLocator);
	}
	
	public Boolean isSubscribeDisplayed()
	{
		waitWebElement(subscribeSectionLocator);
		return isDisplayed(subscribeSectionLocator);
	}
	
	public void clickLogoutButton()
	{
		waitWebElement(logoutButtonLocator);
		click(logoutButtonLocator);
	}
	
	public void subscribeToNewsLetter(String eMail)
	{
		waitWebElement(subscribtionEmailLocator);
		scrollTo(subscribtionEmailLocator);
		sendKeys(subscribtionEmailLocator, eMail);
		click(subscribeButtonLocator);
	}
	
	
	
	
	
}
