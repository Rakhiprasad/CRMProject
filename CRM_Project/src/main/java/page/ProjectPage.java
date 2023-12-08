package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.ElementUtility;
import utility.WaitUtility;

public class ProjectPage {
	WebDriver driver;
	ElementUtility elementutil;
	WaitUtility waitUtility;
	
	@FindBy(xpath="//span[text()='Projects']")
	WebElement projectClick;
	@FindBy(xpath="//span[text()='All Projects']")
	WebElement allProjectClick;
	@FindBy(xpath="//a[@class='btn btn-default']")
	WebElement clickAddProject;
	@FindBy(xpath="//input[@name='title']")
	WebElement addTitle;
	@FindBy(xpath="//textarea[@name='description']")
	WebElement addDescription;
	@FindBy(xpath="//input[@name='start_date']")
	WebElement clickAddStartDate;
	@FindBy(xpath="//table[@class='table-condensed']//tbody//tr[2]//td[5]")
	WebElement addStartDate;
	@FindBy(xpath="//input[@name='deadline']")
	WebElement ClickAddDeadline;
	@FindBy(xpath="//table[@class='table-condensed']//tbody//tr[3]//td[3]")
	WebElement addDeadline;
	@FindBy(xpath="//input[@name='price']")
	WebElement addprice;
	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement addProjectSave;
	@FindBy(xpath="//input[@type='search']")
	WebElement clickSearchProject;
	@FindBy(xpath="//table[@id='project-table']//tbody//td[9]//a[1]")
	WebElement editData;
	@FindBy(xpath="//input[@id='title']")
	WebElement editTitle;
	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement editSave;
	@FindBy(xpath="//button[@class='close']")
	WebElement clickSaveClose;
	
	
	public ProjectPage(WebDriver driver)
	{
		this.driver=driver;
		elementutil=new ElementUtility(driver);
		waitUtility=new WaitUtility(driver);
		PageFactory.initElements(driver,this );
	}
	
	
	public void doClickAllProject()
	{
		waitUtility.waitForClick(projectClick);
		elementutil.doSubmitKeys(projectClick);
		waitUtility.waitForClick(allProjectClick);
		elementutil.doSubmitKeys(allProjectClick);
	}
	
	public void doClickAddProject(String title,String description,String price,String label)
	{
		waitUtility.waitForClick(clickAddProject);
		elementutil.doSubmitKeys(clickAddProject);
		waitUtility.waitForVisibility(addTitle);
		elementutil.doSendKeys(addTitle, title);
		elementutil.doSendKeys(addDescription, description);
		elementutil.doSubmitKeys(clickAddStartDate);
		elementutil.doSubmitKeys(addStartDate);
		elementutil.doSubmitKeys(ClickAddDeadline);
		elementutil.doSubmitKeys(addDeadline);
		elementutil.doSubmitKeys(addProjectSave);
		waitUtility.waitForClick(clickSaveClose);
		waitUtility.waitForClick(projectClick);
		elementutil.doSubmitKeys(projectClick);	
		
	}
	
	public String doClickSearchProject(String searchValue)
	{
		waitUtility.waitForClick(allProjectClick);
		elementutil.doSubmitKeys(allProjectClick);
		elementutil.doSendKeys(clickSearchProject, searchValue);
		By locator=By.xpath("//table[@id='project-table']//tbody//tr//td//a[contains(text(),'"+searchValue+"')]");
		waitUtility.waitForVisibility(locator);
		List<WebElement> clienttable=driver.findElements(By.xpath("//table[@id='project-table']//tbody//tr//td//a[contains(text(),'"+searchValue+"')]"));
		waitUtility.waitForVisibility(clienttable);
		int row=elementutil.getTableDataRowCount(clienttable, searchValue);

		String actualmsg="";
if(row!=0) 
		{
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='project-table']//tbody//tr["+row+"]//td[2]"));
			actualmsg=tableRow.getText();
			System.out.println("Searched Element : " +actualmsg);
		}
		return actualmsg;

		
	}
	public void doEditProject(String titlevalue)
	{
		elementutil.doSubmitKeys(editData);
		waitUtility.waitForVisibility(editTitle);
		elementutil.doClearData(editTitle);
		elementutil.doSendKeys(editTitle, titlevalue);
		elementutil.doSubmitKeys(editSave);
		waitUtility.waitForClick(projectClick);
		elementutil.doSubmitKeys(projectClick);	
		
	}

}
