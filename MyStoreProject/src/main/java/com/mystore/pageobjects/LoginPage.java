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
public class LoginPage extends BaseClass{
	
	Action action= new Action();
	
	@FindBy(xpath="//input[@id='email']")
	WebElement userName;
	
	@FindBy(xpath="//input[@id='passwd']")
	WebElement password;
	
	@FindBy(xpath="//button[@name='SubmitLogin']")
	private WebElement signInBtn;
	
	@FindBy(xpath="//input[@id='email_create']")
	private WebElement emailForNewAccount;
	
	@FindBy(xpath="//button[@name='SubmitCreate']")
	private WebElement createNewAccountBtn;
	
	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	
	public HomePage login(String uname,String pswd,HomePage homePage) throws Exception {
		action.scrollByVisibilityOfElement(getDriver(), userName);
		action.type(userName, uname);
		action.type(password, pswd);
		action.JSClick(getDriver(), signInBtn);
		Thread.sleep(2000);
		homePage = new HomePage();
		return homePage;
		
	}
	
	public AddressPage login(String uname, String pswd,AddressPage addressPage) throws Throwable {
		action.scrollByVisibilityOfElement(getDriver(), userName);
		action.type(userName, uname);
		action.type(password, pswd);
		action.click(getDriver(), signInBtn);
		Thread.sleep(2000);
		addressPage=new AddressPage();
		return addressPage;
	}
	
	public AccountCreationPage createNewAccount(String newEmail) throws Exception {
		action.type(emailForNewAccount, newEmail);
		action.click(getDriver(), createNewAccountBtn);
		Thread.sleep(2000);
		return new AccountCreationPage();
		
	}


}
