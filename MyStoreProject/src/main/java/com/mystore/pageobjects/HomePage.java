/**
 * 
 */
package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

/**
 * @author Akash
 *
 */
public class HomePage extends BaseClass{
	
	Action action= new Action();
	
	@FindBy(xpath="//i[@class='icon-heart']")
	WebElement myWishList;
	
	@FindBy(xpath="//i[@class='icon-list-ol']")
	WebElement orderHistory;
	
	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean validateMyWishList() {
		return action.isDisplayed(getDriver(), myWishList);
	}
	
	public boolean validateOrderHistory() {
		return action.isDisplayed(getDriver(), orderHistory);
	}
	
	public String getCurrURL() throws Throwable {
		String homePageURL=action.getCurrentURL(getDriver());
		return homePageURL;
	}
	

}
