package page;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.ElementUtility;
import utility.WaitUtility;

public class NotePage  {
	
	WebDriver driver;
	ElementUtility elementutil;
	WaitUtility waitUtility;
	
	@FindBy(xpath="//span[text()='Notes']")
	WebElement noteClick;
	@FindBy(xpath="//a[text()=' Add note']")
	WebElement addNoteClick;
	@FindBy(xpath="//input[@name='title']")
	WebElement addTitle;
	@FindBy(xpath="//textarea[@name='description']")
	WebElement addDescription;
	@FindBy(xpath="//button[@type='submit']")
	WebElement clickSave;
	@FindBy(xpath="//button[@class='close']")
	WebElement clickSaveClose;
	@FindBy(xpath="//input[@type='search']")
	WebElement clickSearch;
	@FindBy(xpath="//table[@id='note-table']//tbody//tr[1]//td[2]//a")
	WebElement titleColumnData;
	@FindBy(xpath="//table[@id='note-table']//tbody//tr[1]//td[4]//a[1]")
	WebElement editData;
	@FindBy(xpath="//input[@name='title']")
	WebElement editTitle;
	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement editSave;
	@FindBy(xpath="//table[@id='note-table']//tbody//tr[1]//td[4]//a[2]")
	WebElement deleteButton;
	@FindBy(xpath="//button[@id='confirmDeleteButton']")
	WebElement deleteButtonConfirm;
	@FindBy(xpath="//button[@id='confirmDeleteButton']")
	WebElement deleteCheck;	
	//@FindBy(xpath="//table[@id='note-table']//tbody//tr[1]//td[1]")
	@FindBy(xpath="//div[@class='app-alert-message']")
	WebElement deletetext;
	
	
	
	
	
	public NotePage(WebDriver driver) {
		this.driver=driver;
		elementutil=new ElementUtility(driver);
		waitUtility=new WaitUtility(driver);
		PageFactory.initElements(driver,this );
	}	

	public void doClickNote() 
	{
		waitUtility.waitForClick(noteClick);
		elementutil.doSubmitKeys(noteClick);

	}
	public void doAddNoteClick() 
	{
		waitUtility.waitForClick(addNoteClick);
		elementutil.doSubmitKeys(addNoteClick);

	}
	public void doAddNote(String title,String description) 
	{		
		waitUtility.waitForVisibility(addTitle);
		elementutil.doSendKeys(addTitle, title);
		elementutil.doSendKeys(addDescription, description);
		elementutil.doSubmitKeys(clickSave);
		waitUtility.waitForClick(clickSaveClose);
		elementutil.doSubmitKeys(clickSaveClose);

	}
	
	public String doSearchValue(String searchValue) 
	{		
		waitUtility.waitForVisibility(clickSearch);
		elementutil.doSendKeys(clickSearch, searchValue);
		
		By locator=By.xpath("//table[@id='note-table']//tbody//tr//td//a[contains(text(),'"+searchValue+"')]");
		waitUtility.waitForVisibility(locator);
		List<WebElement> notetable=driver.findElements(By.xpath("//table[@id='note-table']//tbody//tr//td//a[contains(text(),'"+searchValue+"')]"));
		waitUtility.waitForVisibility(notetable);
		int row=elementutil.getTableDataRowCount(notetable, searchValue);

		String actualmsg="";
if(row!=0) 
		{
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='note-table']//tbody//tr["+row+"]//td[2]"));
			actualmsg=tableRow.getText();
			System.out.println("Searched Element : " +actualmsg);
		}
		return actualmsg;

	}
	
	
	public void doEdit(String title) 
	{		
		
		elementutil.doSubmitKeys(editData);
		waitUtility.waitForVisibility(addTitle);
		elementutil.doClearData(addTitle);
		elementutil.doSendKeys(editTitle, title);
		elementutil.doSubmitKeys(editSave);
		waitUtility.waitForClick(clickSaveClose);
		elementutil.doSubmitKeys(clickSaveClose);
		//elementutil.doClearData(clickSearch);

	}
	public String doDelet(String searchValue) 
	{		
		
		elementutil.doSubmitKeys(deleteButton);
		waitUtility.waitForClick(deleteButtonConfirm);
		elementutil.doSubmitKeys(deleteButtonConfirm);
		waitUtility.waitForVisibility(deletetext);
		waitUtility.waitForVisibility(clickSearch);
		elementutil.doClearData(clickSearch);
		elementutil.doSendKeys(clickSearch, searchValue);
		String deleteTexts=elementutil.dogetText(deletetext);
		return deleteTexts;
	}
		 	
		

	}
	



