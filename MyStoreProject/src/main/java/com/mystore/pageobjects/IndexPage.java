package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;


public class IndexPage extends BaseClass{
	
	Action action= new Action();
	
	@FindBy(xpath="//a[@class='login']")
	WebElement signInBtn;
	
	@FindBy(xpath="//img[@class='logo img-responsive']")
	WebElement myStoreLogo;
	
	@FindBy(xpath="//input[@id='search_query_top']")
	WebElement searchProductBox;
	
	@FindBy(xpath="//button[@name='submit_search']")
	WebElement searchButton;
	
	public IndexPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	
	public LoginPage clickOnSignIn() throws Throwable {
		action.fluentWait(getDriver(), signInBtn, 10);
		action.click(getDriver(), signInBtn);
		return new LoginPage();
	
	} 
	
	public boolean validateLogo() {
		return action.isDisplayed(getDriver(), myStoreLogo);
	}
	
	public String getMyStoreTitle() {
		String myStoreTitle=getDriver().getTitle();
		return myStoreTitle;
	}
	
	public SearchResultPage searchProduct(String productName) throws Exception {
		action.type(searchProductBox, productName);
		action.click(getDriver(), searchButton);
		Thread.sleep(3000);
		//return new SearchResultPage();
		return new SearchResultPage();
		
	}
	
	
	

}
