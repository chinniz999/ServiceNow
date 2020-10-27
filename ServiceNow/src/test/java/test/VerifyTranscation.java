package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.CheckoutSummary;
import pageObjects.HomePage;
import seleniumLib.DriverManager;

public class VerifyTranscation {

	WebDriver driver = null;

	@BeforeTest
	public void beforeTest() {
		DriverManager manger = new DriverManager();
		driver = manger.initDriver("chrome");
		
	}

	@Test
	public void VerifySuccessfullTranscation() {

		HomePage hompageHomePage = new HomePage(driver);
		hompageHomePage.searchText("printed");
		//hompageHomePage.filterProductsBy("Price: Lowest first");
		CheckoutSummary orderSummary =hompageHomePage.selectLowPriceProduct();
		orderSummary.verifyCartAndproceedCheckout();
		orderSummary.signIn("zarmanshah@malomiesed.com", "Testing123");
		orderSummary.address();
		orderSummary.shipping();
		orderSummary.verifyOrderDetailsCartVsShipping();
		orderSummary.verifyTranscationCompleteDetails();
		
		
	}
	
	@AfterTest
	public void afterTest() {
		
		//driver.quit();
	}
	

}
