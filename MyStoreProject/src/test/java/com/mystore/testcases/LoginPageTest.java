/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;

/**
 * @author Akash
 *
 */
public class LoginPageTest extends BaseClass {
	IndexPage indexPage;
	LoginPage loginPage;
	HomePage homePage;
	
	@Parameters("browser")
	@BeforeMethod(groups= {"Smoke","Sanity","Regression"})
	public void setup(String browserName) throws Exception {
		launchApp(browserName);
		
	}
	
	@AfterMethod(groups= {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups={"Smoke","Sanity"},dataProvider = "credentials" ,dataProviderClass = DataProviders.class)
	public void loginTest(String uname,String pswd) throws Throwable {
		Log.startTestCase("loginTest");
		indexPage=new IndexPage();
		Log.info("User is going to click on SignIn");
		loginPage=indexPage.clickOnSignIn();
		Log.info("User will enter username and password ");
		//homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"), homePage);
		homePage=loginPage.login(uname,pswd,homePage);
		String acturalURL=homePage.getCurrURL();
		String expectedURL="http://automationpractice.com/index.php?controller=my-account";
		Log.info("Verifying if user able to login");
		Assert.assertEquals(acturalURL, expectedURL);
		Log.info("Login is success");
		Log.endTestCase("loginTest");
	}
	
}
