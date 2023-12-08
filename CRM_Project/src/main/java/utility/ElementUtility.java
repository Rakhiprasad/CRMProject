package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import constants.Constants;

public class ElementUtility
{
	WebDriver driver;
	public ElementUtility(WebDriver driver)
	{
		this.driver=driver;
	}
	public static String getPropertyValue(String key) 
	{

		File src=new File(Constants.propertyConfig_File);//path defines
		Properties pro=new Properties();//Properties class allows loading and retrieving values from properties file
		try {
			FileInputStream fis = new FileInputStream (src);//file open
			
			pro.load(fis);//file load the property
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		String value=pro.get(key).toString();//return the value corresponding to the key
		return value;
	}
	
	public void doSendKeys(WebElement element,String value)
	{
		element.sendKeys(value);
	}
	public void doSubmitKeys(WebElement element)
	{
		element.click();
	}
	public void doDropDown(WebElement element,String value)
	{
		Select elementdrop=new Select(element);
		elementdrop.selectByVisibleText(value);
	}
	public void doRadioButton(List<WebElement> element,int value)
	{
		element.get(value).click();
	}
	public String dogetText(WebElement element)
	{
		String value=element.getText();
		return value;
	}
	public void doClearData(WebElement element)
	{
		element.clear();
	}
	public void doScroll(WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	public void doScrollBottom()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public int getTableDataRowCount(List<WebElement> tableRowData , String expectedValue)
	{
		int counter=0;
		for(int i=0;i<tableRowData.size();i++)
		{
			String value=tableRowData.get(i).getText();
			if(expectedValue.equalsIgnoreCase(value))
			{
				counter=i+1;
				break;
			}
		}
		return counter;

	}

}
