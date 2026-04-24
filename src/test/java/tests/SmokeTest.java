package tests;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import commontests.BaseTest;
import commontests.SmokeBaseTest;
import pages.AccountCreatedPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HeaderPage;
import pages.LandingPage;
import pages.OrderPlacedPage;
import pages.PaymentPage;
import pages.RegisterationPage;
import pages.SignUpLoginPage;
import utility.Constants;
import utility.DataKeys;
import utility.DataProviders;
import utility.DriverFactory;
import utility.TestDataHelper;

public class SmokeTest extends SmokeBaseTest
{
	
	
	ThreadLocal<TestDataHelper> helper = new ThreadLocal<>();
	
	@Test(priority=0)
	public void verifyHomePageLoaded()
	{
		
		LandingPage landingPage = new LandingPage(DriverFactory.getWebDriver());
		String currentUrl = landingPage.getCurrentURL();
		String expectedUrl = Constants.homeUrl;
		Assert.assertEquals(expectedUrl, currentUrl);
		
	}
	
	@Test(priority=1)
	public void verifyNavigationMenuItems()
	{
		HeaderPage headerPage = new HeaderPage(DriverFactory.getWebDriver());
		
		List<String> actualList = headerPage.getNavigationMenuItems();
		List<String> expectedList = Constants.expectedList;
		
		
		Assert.assertEquals(
			    actualList.toString()
			              .replaceAll("[^a-zA-Z0-9/, ]", ""),
			    expectedList.toString()
			                .replaceAll("[^a-zA-Z0-9/, ]", "")
			);
	}
	
	@Test(priority=2)
	public void verifyFeaturedProductsDisplayed()
	{
		LandingPage landingPage = new LandingPage(DriverFactory.getWebDriver());
		Assert.assertTrue(landingPage.areProductsDisplayed());
		Assert.assertTrue(landingPage.isNameDisplayed());
		Assert.assertTrue(landingPage.isPriceDisplayed());
		Assert.assertTrue(landingPage.isImageDisplayed());
	}
	
	@Test(priority=3)
	public void verifySubscriptionSectionVisible()
	{
		LandingPage landingPage = new LandingPage(DriverFactory.getWebDriver());
		Assert.assertTrue(landingPage.isSubscribeDisplayed());
	}
	
	/*
	public void verifyNavigationToCartButton()
	{
		HeaderPage headerPage = new HeaderPage(DriverFactory.getWebDriver());
		CartPage cartPage =headerPage.clickCartButton();
		Assert.assertEquals(cartPage.getCurrentURL(), Constants.cartPageUrl);
	}*/
	
	@Test(priority=4, dataProvider="testData", dataProviderClass=DataProviders.class)
	public void verifyNavigationToSignupLogin(Map<String, Object> data)
	{
		helper.set(new TestDataHelper(data));;
		
		HeaderPage headerPage = new HeaderPage(DriverFactory.getWebDriver());
		SignUpLoginPage signUpLoginPage = headerPage.clickLoginSignUpButton();
		signUpLoginPage.signUp(helper.get().getString(DataKeys.USERNAME), Constants.getEmail());
	}
	
	@Test(priority=5, dataProvider="testData", dataProviderClass=DataProviders.class)
	public void verifyNewUserRegistration(Map<String, Object> data)
	{
		data.put(DataKeys.EMAIL, BaseTest.email.get());
		helper.set(new TestDataHelper(data));
		RegisterationPage registerationPage = new RegisterationPage(DriverFactory.getWebDriver());
		AccountCreatedPage accountCreatedPage = registerationPage.fillTheForm(helper.get().getString(DataKeys.TITLE), helper.get().getString(DataKeys.PASSWORD), helper.get().getString(DataKeys.DAY), helper.get().getString(DataKeys.MONTH), helper.get().getString(DataKeys.YEAR), helper.get().getString(DataKeys.FIRSTNAME), helper.get().getString(DataKeys.LASTNAME), helper.get().getString(DataKeys.COMPANY), helper.get().getString(DataKeys.ADDRESSLine1), helper.get().getString(DataKeys.ADDRESSLine2), helper.get().getString(DataKeys.COUNTRY), helper.get().getString(DataKeys.STATE), helper.get().getString(DataKeys.CITY), helper.get().getString(DataKeys.ZIPCODE), helper.get().getString(DataKeys.MOBILE), false, false);
		Assert.assertEquals(accountCreatedPage.getCurrentURL(), Constants.accountCreatedUrl);
		accountCreatedPage.clickContinue();
		  
	}
	
	@Test(priority=7)
	public void verifyLoggedInUsernameVisible()
	{
		//helper.set(new TestDataHelper(data));
		HeaderPage headerPage = new HeaderPage(
		        DriverFactory.getWebDriver());

		Assert.assertEquals(
		        headerPage.loginName(),
		        helper.get().getString(DataKeys.USERNAME));
	}
	
	@Test(priority=7, dataProvider = "testData", dataProviderClass = DataProviders.class)
	public void verifyAddToCartFromHomePage(Map<String, Object> data)
	{
		helper.set(new TestDataHelper(data));
		LandingPage landingPage = new LandingPage(DriverFactory.getWebDriver());
		landingPage.addProduct(helper.get().getString(DataKeys.PRODUCT_NAME));
		landingPage.productAddedMessage();
		Assert.assertEquals(landingPage.productAddedMessage(), Constants.addedText);
		landingPage.clickContinueToCart();
	}
	
	@Test(priority=8, dataProvider = "testData", dataProviderClass = DataProviders.class)
	public void verifyProductsInCart(Map<String, Object> data)
	{
		helper.set(new TestDataHelper(data));
		CartPage cartPage = new CartPage(DriverFactory.getWebDriver());
		Assert.assertEquals(cartPage.getProductName(), helper.get().getString(DataKeys.PRODUCT_NAME));
	}
	
	@Test(priority=9)
	public void verifyProceedToCheckout()
	{
		CartPage cartPage = new CartPage(DriverFactory.getWebDriver());
		CheckoutPage checkoutPage = cartPage.clickProceedToCheckout(); 
		Assert.assertEquals(checkoutPage.getTitle(), Constants.checkoutPageTitle);
		
	}
	
	@Test(priority=10)
	public void verifyPaymentPage()
	{
		CheckoutPage checkoutPage =  new CheckoutPage(DriverFactory.getWebDriver());
		PaymentPage paymentPage = checkoutPage.clickPlaceOrder();
		//paymentPage.submitCardDetails("Rachit", "123456", "123", "10", "2032");
		Assert.assertEquals(paymentPage.getTitle(), Constants.paymentPageTitle);
	}
	
	@Test(priority=11, dataProvider = "testData", dataProviderClass = DataProviders.class)
	public void verifyOrderIsPlaced(Map<String, Object> data)
	{
		helper.set(new TestDataHelper(data));
		PaymentPage paymentPage = new PaymentPage(DriverFactory.getWebDriver());
		OrderPlacedPage orderPlacedPage = paymentPage.submitCardDetails(helper.get().getString(DataKeys.CARD_NAME), helper.get().getString(DataKeys.CARD_NUMBER), helper.get().getString(DataKeys.CARD_CVC), helper.get().getString(DataKeys.MONTH), helper.get().getString(DataKeys.CARD_CVC));
		Assert.assertEquals(orderPlacedPage.orderPlacedMessage(), Constants.orderPlacedText);
		orderPlacedPage.clickContinue();
	}
	
	@Test(priority=12)
	public void verifyLogOut()
	{
		LandingPage landingPage = new LandingPage(DriverFactory.getWebDriver());
		landingPage.clickLogoutButton();
		Assert.assertEquals(landingPage.getTitle(), Constants.landingPageTitle);
	}
	
	@Test(priority=13, dataProvider = "testData", dataProviderClass = DataProviders.class)
	public void verifyDeleteAccount(Map<String, Object> data)
	{
		helper.set(new TestDataHelper(data));
		HeaderPage headerPage = new HeaderPage(DriverFactory.getWebDriver());
		SignUpLoginPage signUpLoginPage = headerPage.clickLoginSignUpButton();
		signUpLoginPage.signIn(Constants.getEmail(), helper.get().getString(DataKeys.PASSWORD));
		headerPage.clickDeleteAccountButton();
		
		
	}
	
	
	
	
	
}
