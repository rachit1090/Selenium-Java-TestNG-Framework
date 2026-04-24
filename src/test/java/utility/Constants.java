package utility;

import java.util.Arrays;
import java.util.List;

import commontests.BaseTest;

public class Constants 
{
	
	public static final String homeUrl ="https://automationexercise.com/";
	public static final String cartPageUrl = "https://automationexercise.com/view_cart";
	public static final String accountCreatedUrl = "https://automationexercise.com/account_created";
	public static final String productName = "Blue Top";
	public static final String addedText = "Added!";
	public static final String checkoutPageTitle = "Automation Exercise - Checkout";
	public static final String paymentPageTitle = "Automation Exercise - Payment";
	public static final String orderPlacedText = "ORDER PLACED!";
	public static final String landingPageTitle = "Automation Exercise - Signup / Login";
	public static final String deletionText = "ACCOUNT DELETED!";
	public static final String alreadyExistMsg = "Email Address already exist!";
	
	public static  final List<String> expectedList = Arrays.asList(
			"Home",
	        " Products",
	        "Cart",
	        "Signup / Login",
	        "Test Cases",
	        "API Testing",
	        "Video Tutorials",
	        "Contact us"
			);
			
	
	public static String getEmail()
	{
		return BaseTest.email.get();
		
	}
	
	
}
