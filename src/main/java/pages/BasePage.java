package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.DriverFactory;


public class BasePage 
{
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void click(By by)
	{
		driver.findElement(by).click();
	}
	
	public void sendKeys(By by, String ele)
	{
		driver.findElement(by).sendKeys(ele);
	}
	
	public void waitWebElement(By ele)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
	}
	
	public Boolean isDisplayed(By ele)
	{
		return driver.findElement(ele).isDisplayed();
	}
	
	public String getText(By ele)
	{
		return driver.findElement(ele).getText();
	}
	
	public String getCurrentURL()
	{
		
		return driver.getCurrentUrl();
	}
	
	public String getTitle()
	{
		return driver.getTitle();
	}
	
	public void scrollTo(By by)
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(by)).perform();
	}
	
	
	public void goToProduct(By prodNameEle, By addToCartEle, String productName)
	{
		List<WebElement> productsName = driver.findElements(prodNameEle);
	    Actions action = new Actions(driver);

	    for(int i = 0; i < productsName.size(); i++)
	    {
	        String product = productsName.get(i).getText();
	        if(product.equals(productName))
	        {
	            ((JavascriptExecutor) driver)
	                .executeScript("arguments[0].scrollIntoView(true);", productsName.get(i));

	            try { Thread.sleep(500); } catch(InterruptedException e) {}

	            action.moveToElement(productsName.get(i))
	                  .pause(500)
	                  .perform();

	            List<WebElement> addToCart = driver.findElements(addToCartEle);

	            ((JavascriptExecutor) driver)
	                .executeScript("arguments[0].click();", addToCart.get(i));
	            break;
	        }
	    }
	}
}
