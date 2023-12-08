package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.ElementUtility;

public class LoginPage 
{
	WebDriver driver;
	ElementUtility elementutil;

	@FindBy(xpath="//input[@id='email']")
	WebElement userNameField;
	@FindBy(xpath="//input[@id='password']")
	WebElement passwordField;
	@FindBy(xpath="//button[text()='Sign in']")
	WebElement submitField;
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		elementutil=new ElementUtility(driver);
		PageFactory.initElements(driver,this );
		
		
	}
public void doLogin(String userName,String password) {
		
		
		elementutil.doSendKeys(userNameField, userName);
		
		
		elementutil.doSendKeys(passwordField, password);
		
		
		elementutil.doSubmitKeys(submitField);
	}

}
