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
import com.mystore.pageobjects.IndexPage;
import com.mystore.utility.Log;

/**
 * @author Akash
 *
 */
public class IndexPageTest extends BaseClass {
	IndexPage indexPage;
	
	@Parameters("browser")
	@BeforeMethod(groups= {"Smoke","Sanity","Regression"})
	public void setup(String browserName) throws Exception {
		launchApp(browserName);
		
	}
	
	@AfterMethod(groups= {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups="Smoke")
	public void verifyLogo() throws Exception {
		Log.startTestCase("verifyLogo");
		indexPage=new IndexPage();
		boolean result =indexPage.validateLogo();
		Assert.assertTrue(result);
		Log.endTestCase("verifyLogo");
	}
	
	
	
	  @Test(groups="Smoke")
	  public void verifyTitle( ) throws Exception { 
		  Log.startTestCase("verifyTitle");
		  String actTitle=indexPage.getMyStoreTitle(); 
	      Assert.assertEquals(actTitle,"My Store1"); 
	      Log.endTestCase("verifyTitle");
       }
}
