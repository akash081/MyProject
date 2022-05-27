/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.OrderSummary;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.utility.Log;

/**
 * @author Akash
 *
 */
public class OrderPageTest extends BaseClass{
	Action action= new Action();
	IndexPage indexPage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;
	OrderPage orderPage;
	
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
	public void verifyTotalPrice(String productName, String qty, String size) throws Throwable {
		Log.startTestCase("verifyTotalPrice");
		indexPage=new IndexPage();
		searchResultPage=indexPage.searchProduct(productName);
		addToCartPage=searchResultPage.clickOnProduct();
		action.switchToFrameByIndex(getDriver(), 0);
		addToCartPage.enterQuantity(qty);
		addToCartPage.selectSize(size);
		addToCartPage.clickOnAddToCart();
		action.switchToDefaultFrame(getDriver());
		orderPage=addToCartPage.clickOnCheckOut();
		
		Double unitPrice=orderPage.getUnitPrice(); 
		Double totalPrice=orderPage.getTotalPrice(); 
		Double totalExpectedPrice=(unitPrice*(Double.parseDouble(qty)))+2;
		Assert.assertEquals(totalPrice, totalExpectedPrice);
		Log.endTestCase("verifyTotalPrice");
		
		
		
		
	}

}
