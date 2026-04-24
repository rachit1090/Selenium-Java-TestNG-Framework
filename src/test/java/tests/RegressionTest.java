package tests;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import commontests.BaseTest;
import commontests.RegressionBaseTest;
import pages.AccountCreatedPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.DeleteAccountPage;
import pages.HeaderPage;
import pages.LandingPage;
import pages.OrderPlacedPage;
import pages.PaymentPage;
import pages.RegisterationPage;
import pages.SignUpLoginPage;
import pages.SignUpPage;
import utility.Constants;
import utility.DataKeys;
import utility.DataProviders;
import utility.DriverFactory;
import utility.TestDataHelper;

public class RegressionTest extends RegressionBaseTest
{
	
	
	
	ThreadLocal <TestDataHelper> helper = new ThreadLocal <TestDataHelper>(); 
	
	@Test(groups="")
	public void verifyHomePageLoaded()
	{
		LandingPage landingPage = new LandingPage(DriverFactory.getWebDriver());
		String currentUrl = landingPage.getCurrentURL();
		String expectedUrl = Constants.homeUrl;
		Assert.assertEquals(expectedUrl, currentUrl);
	}
	
	@Test(groups="")
	public void verifyNavigationMenuItems()
	{
		HeaderPage headerPage = new HeaderPage(DriverFactory.getWebDriver());
		List<String> actualList = headerPage.getNavigationMenuItems();
		List<String> expectedList = Constants.expectedList;
		
		Assert.assertEquals(actualList.toString().replaceAll("[^a-zA-Z0-9/, ]", ""), 
				expectedList.toString().replaceAll("[^a-zA-Z0-9/, ]", ""));
	}
	
	@Test(groups="", dataProvider="testData", dataProviderClass=DataProviders.class)
	public void verifyNewUserRegistration(Map<String,Object> data)
	{
		helper.set(new TestDataHelper(data));
		HeaderPage headerPage = new HeaderPage(DriverFactory.getWebDriver());
		SignUpLoginPage signUpLoginPage = headerPage.clickLoginSignUpButton();
		RegisterationPage registerationPage = signUpLoginPage.signUp(helper.get().getString(DataKeys.USERNAME), Constants.getEmail());
		AccountCreatedPage accountCreatedPage = registerationPage.fillTheForm(helper.get().getString(DataKeys.TITLE), helper.get().getString(DataKeys.PASSWORD), helper.get().getString(DataKeys.DAY), helper.get().getString(DataKeys.MONTH), helper.get().getString(DataKeys.YEAR), helper.get().getString(DataKeys.FIRSTNAME), helper.get().getString(DataKeys.LASTNAME), helper.get().getString(DataKeys.COMPANY), helper.get().getString(DataKeys.ADDRESSLine1), helper.get().getString(DataKeys.ADDRESSLine2), helper.get().getString(DataKeys.COUNTRY), helper.get().getString(DataKeys.STATE), helper.get().getString(DataKeys.CITY), helper.get().getString(DataKeys.ZIPCODE), helper.get().getString(DataKeys.MOBILE), false, false);
		Assert.assertEquals(accountCreatedPage.getCurrentURL(), Constants.accountCreatedUrl);
	}
	
	@Test(groups="")
	public void verifyFeaturedProductsDisplayed()
	{
		LandingPage landingPage = new LandingPage(DriverFactory.getWebDriver());
		Assert.assertTrue(landingPage.areProductsDisplayed());
		Assert.assertTrue(landingPage.isNameDisplayed());
		Assert.assertTrue(landingPage.isPriceDisplayed());
		Assert.assertTrue(landingPage.isImageDisplayed());
	}
	
	@Test(groups="")
	public void verifySubscriptionSectionVisible()
	{
		LandingPage landingPage = new LandingPage(DriverFactory.getWebDriver());
		Assert.assertTrue(landingPage.isSubscribeDisplayed());
	}
	
	@Test(groups="")
	public void verifyNewsletterSubscription()
	{
		LandingPage landingPage = new LandingPage(DriverFactory.getWebDriver());
		landingPage.subscribeToNewsLetter(Constants.getEmail());
		
	}
	
	@Test(groups="", dataProvider="testData", dataProviderClass=DataProviders.class)
	public void verifyNavigationToSignupLogin(Map<String, Object> data)
	{
		helper.set(new TestDataHelper(data));
		
		HeaderPage headerPage = new HeaderPage(DriverFactory.getWebDriver());
		SignUpLoginPage signUpLoginPage = headerPage.clickLoginSignUpButton();
		signUpLoginPage.signUp(helper.get().getString(DataKeys.USERNAME), Constants.getEmail());
	}
	
	
	@Test(groups="", dataProvider="testData", dataProviderClass=DataProviders.class)
	public void verifyRegistrationWithExistingEmail(Map<String, Object> data)
	{
		helper.set(new TestDataHelper(data));
		HeaderPage headerPage = new HeaderPage(DriverFactory.getWebDriver());
		SignUpLoginPage signUpLoginPage = headerPage.clickLoginSignUpButton();
		
		// signUpLoginPage.signUp(
			       // helper.get().getString(DataKeys.USERNAME),
			       // helper.get().getString(DataKeys.EMAIL));
		
		signUpLoginPage.signUp("Rachit", "rachit@test.com");
		 
		RegisterationPage registerationPage = new RegisterationPage(DriverFactory.getWebDriver());
		 
		Assert.assertEquals(registerationPage.getErrorSignup(), Constants.alreadyExistMsg);
	}
	
	
	@Test(groups="", dataProvider="testData", dataProviderClass=DataProviders.class)
	public void verifyMandatoryFieldsRegistration(Map<String, Object> data)
	{
		helper.set(new TestDataHelper(data));
		HeaderPage headerPage = new HeaderPage(DriverFactory.getWebDriver());
		SignUpLoginPage signUpLoginPage = headerPage.clickLoginSignUpButton();
		RegisterationPage registerationPage = signUpLoginPage.signUp(helper.get().getString(DataKeys.USERNAME), Constants.getEmail());
		AccountCreatedPage accountCreatedPage = registerationPage.fillTheFormMandatoryFields(helper.get().getString(DataKeys.PASSWORD), helper.get().getString(DataKeys.FIRSTNAME), helper.get().getString(DataKeys.LASTNAME), helper.get().getString(DataKeys.ADDRESSLine1), helper.get().getString(DataKeys.COUNTRY), helper.get().getString(DataKeys.STATE), helper.get().getString(DataKeys.CITY), helper.get().getString(DataKeys.ZIPCODE), helper.get().getString(DataKeys.MOBILE));
		Assert.assertEquals(accountCreatedPage.getCurrentURL(), Constants.accountCreatedUrl);
	}
	
	@Test(groups="", dataProvider="testData", dataProviderClass=DataProviders.class)
	public void verifyAccountDeletion(Map<String, Object> data)
	{
		helper.set(new TestDataHelper(data));
		
		HeaderPage headerPage = new HeaderPage(DriverFactory.getWebDriver());
	    SignUpLoginPage signUpLoginPage = headerPage.clickLoginSignUpButton();
	    RegisterationPage registerationPage = signUpLoginPage.signUp(
	        helper.get().getString(DataKeys.USERNAME), Constants.getEmail());
	    AccountCreatedPage accountCreatedPage = registerationPage.fillTheFormMandatoryFields(
	        helper.get().getString(DataKeys.PASSWORD),
	        helper.get().getString(DataKeys.FIRSTNAME),
	        helper.get().getString(DataKeys.LASTNAME),
	        helper.get().getString(DataKeys.ADDRESSLine1),
	        helper.get().getString(DataKeys.COUNTRY),
	        helper.get().getString(DataKeys.STATE),
	        helper.get().getString(DataKeys.CITY),
	        helper.get().getString(DataKeys.ZIPCODE),
	        helper.get().getString(DataKeys.MOBILE));
		//AccountCreatedPage accountCreatedPage = new AccountCreatedPage(DriverFactory.getWebDriver());
		accountCreatedPage.clickContinue();
		HeaderPage headerPage2 = new HeaderPage(DriverFactory.getWebDriver());
		DeleteAccountPage deleteAccountPage = headerPage2.clickDeleteAccountButton();
		Assert.assertEquals(deleteAccountPage.getDeletionText(), Constants.deletionText);
		//deleteAccountPage.clickContinue();
	}
	
	@Test(groups="", dataProvider="testData", dataProviderClass=DataProviders.class)
	public void verifyLoginWithValidCredentials(Map<String, Object> data)
	{
		
		helper.set(new TestDataHelper(data));
		HeaderPage headerPage = new HeaderPage(DriverFactory.getWebDriver());
		SignUpLoginPage signUpLoginPage = headerPage.clickLoginSignUpButton();
		signUpLoginPage.signIn("rachit@test.com", "Test@1234");
		Assert.assertEquals(
		        headerPage.loginName(),
		        "Rachit Vashisht");
	}
	
	@Test(groups="", dataProvider="testData", dataProviderClass=DataProviders.class)
	public void verifyLogoutFunctionality(Map<String, Object> data)
	{
		data.put(DataKeys.EMAIL, BaseTest.email.get());
		helper.set(new TestDataHelper(data));
		HeaderPage headerPage = new HeaderPage(DriverFactory.getWebDriver());
		SignUpLoginPage signUpLoginPage = headerPage.clickLoginSignUpButton();
		signUpLoginPage.signIn("rachit@test.com", "Test@1234");
		LandingPage landingPage = new LandingPage(DriverFactory.getWebDriver());
		landingPage.clickLogoutButton();
		Assert.assertEquals(landingPage.getTitle(), Constants.landingPageTitle);
	}
	
	
	
	
	@Test(groups="", dataProvider="testData", dataProviderClass=DataProviders.class)
	public void verifyLogin(Map<String, Object> data)
	{
		
		helper.set(new TestDataHelper(data));
		
		HeaderPage headerPage = new HeaderPage(DriverFactory.getWebDriver());
		SignUpLoginPage signUpLoginPage = headerPage.clickLoginSignUpButton();
		signUpLoginPage.signIn("rachit@test.com", "Test@1234");
		String expected = helper.get().getString(DataKeys.USERNAME);
		String actual = headerPage.loginName();
		Assert.assertEquals(expected, actual);
		
		LandingPage landingPage = new LandingPage(DriverFactory.getWebDriver());
		landingPage.addProduct(helper.get().getString(DataKeys.PRODUCT_NAME));
		
		String expectedText = Constants.addedText;
		String actualText = landingPage.productAddedMessage();
		Assert.assertEquals(expectedText, actualText);
		CartPage cartPage = landingPage.clickContinueToCart();
		
		String expectedUrl = Constants.cartPageUrl;
		String actualUrl = cartPage.getCurrentURL();
		Assert.assertEquals(expectedUrl, actualUrl);
		
		String expectedCartProductName = Constants.productName;
		String actualCartProductName = cartPage.getProductName();
		Assert.assertEquals(expectedCartProductName, actualCartProductName);
		
		CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();
		//checkoutPage.getCurrentURL();
		Assert.assertEquals(Constants.checkoutPageTitle,checkoutPage.getTitle());
		
		PaymentPage paymentPage = checkoutPage.clickPlaceOrder();
		Assert.assertEquals(Constants.paymentPageTitle, paymentPage.getTitle());
		
		OrderPlacedPage orderPlacedPage = paymentPage.submitCardDetails(helper.get().getString(DataKeys.CARD_NAME), helper.get().getString(DataKeys.CARD_NUMBER), helper.get().getString(DataKeys.CARD_CVC), helper.get().getString(DataKeys.MONTH), helper.get().getString(DataKeys.CARD_YEAR ));
		Assert.assertEquals(Constants.orderPlacedText, orderPlacedPage.orderPlacedMessage());
		
	}
}
