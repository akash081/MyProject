/**
 * 
 */
package com.mystore.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.AddressPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.OrderConfirmationPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.OrderSummary;
import com.mystore.pageobjects.PaymentPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.pageobjects.ShippingPage;
import com.mystore.utility.Log;

/**
 * @author Akash
 *
 */

public class EndToEndTest extends BaseClass{
	Action action= new Action();
	private IndexPage indexPage;
	private SearchResultPage searchResultPage;
	private AddToCartPage addToCartPage;
	private OrderPage orderPage;
	private LoginPage loginPage;
	private AddressPage addressPage;
	private ShippingPage shippingPage;
	private PaymentPage paymentPage;
	private OrderSummary orderSummary;
	private OrderConfirmationPage orderConfirmationPage;
	private HomePage homePage;
	
	@Parameters("browser")
	@BeforeMethod(groups= {"Smoke","Sanity","Regression"})
	public void setup(String browserName) throws Exception {
		launchApp(browserName);
		
	}
	
	@AfterMethod(groups= {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
    
	@Test(groups="Regression",dataProvider = "getProduct", dataProviderClass = DataProviders.class)
	public void endToEnd(String productName, String qty, String size) throws Throwable {
		Log.startTestCase("endToEndTest");
		indexPage=new IndexPage();
		searchResultPage=indexPage.searchProduct(productName);
		addToCartPage=searchResultPage.clickOnProduct();
		action.switchToFrameByIndex(getDriver(), 0);
		addToCartPage.enterQuantity(qty);
		addToCartPage.selectSize(size);
		addToCartPage.clickOnAddToCart();
		action.switchToDefaultFrame(getDriver());
		orderPage=addToCartPage.clickOnCheckOut();
		loginPage=orderPage.clickOnCheckOut();
		
		
		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		WebElement emailBox = getDriver().findElement(By.xpath("//input[@id='email']"));
		jse.executeScript("arguments[0].scrollIntoView()", emailBox);
		addressPage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"),addressPage);
		shippingPage=addressPage.clickOnCheckOut();
		shippingPage.checkTheTerms();
		paymentPage=shippingPage.clickOnProceedToCheckOut();
		orderSummary=paymentPage.clickOnPaymentMethod();
		orderConfirmationPage=orderSummary.clickOnconfirmOrderBtn();
		String actualMessage=orderConfirmationPage.validateConfirmMessage();
		String expectedMessage="Your order on My Store is complete.";
		Assert.assertEquals(actualMessage, expectedMessage);
		Log.endTestCase("endToEndTest");
		
		
	
	}
}