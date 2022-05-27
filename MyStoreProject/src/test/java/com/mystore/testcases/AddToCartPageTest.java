/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.utility.Log;
import com.mystore.actiondriver.Action;
/**
 * @author Akash
 *
 */
public class AddToCartPageTest extends BaseClass{
	Action action= new Action();
	IndexPage indexPage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;
	
	@Parameters("browser")
	@BeforeMethod(groups= {"Smoke","Sanity","Regression"})
	public void setup(String browserName) throws Exception {
		launchApp(browserName);
		
	}
	
	@AfterMethod(groups= {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}

	@Test(groups={"Sanity","Regression"},dataProvider = "getProduct", dataProviderClass = DataProviders.class)
	public void addToCart(String productName, String qty, String size) throws Throwable {
		Log.startTestCase("addToCartTest");
		indexPage= new IndexPage();
		searchResultPage=indexPage.searchProduct(productName);
		addToCartPage=searchResultPage.clickOnProduct();
		action.switchToFrameByIndex(getDriver(), 0);
		addToCartPage.enterQuantity(qty);;
		addToCartPage.selectSize(size);
		addToCartPage.clickOnAddToCart();
		action.switchToDefaultFrame(getDriver());
		boolean result=addToCartPage.validateAddtoCart();
		Assert.assertTrue(result);
		Log.endTestCase("addToCartTest");
		
	}
}
