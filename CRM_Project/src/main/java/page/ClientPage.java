package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.ElementUtility;
import utility.WaitUtility;

public class ClientPage {

	WebDriver driver;
	ElementUtility elementutil;
	WaitUtility waitUtility;
	
	@FindBy(xpath="//span[text()='Clients']")
	private WebElement clientClick;
	@FindBy(xpath="//input[@type='search']")
	WebElement clickSearchClient;
	@FindBy(xpath="//a[@class='btn btn-default']")
	WebElement clickAddClient;
	@FindBy(xpath="//input[@name='company_name']")
	WebElement AddCompName;
	@FindBy(xpath="//textarea[@name='address']")
	WebElement AddAddress;
	@FindBy(xpath="//input[@name='city']")
	WebElement AddCity;
	@FindBy(xpath="//input[@name='state']")
	WebElement AddState;
	@FindBy(xpath="//input[@name='zip']")
	WebElement AddZip;
	@FindBy(xpath="//input[@name='country']")
	WebElement AddCountry;
	@FindBy(xpath="//input[@name='phone']")
	WebElement AddPhone;
	@FindBy(xpath="//input[@name='website']")
	WebElement Addwebsite;
	@FindBy(xpath="//input[@name='vat_number']")
	WebElement Addvat;
	@FindBy(xpath="//div[@id='s2id_currency']")
	WebElement currency;
	@FindBy(xpath="//div[text()='AED']")
	WebElement currencySelect;
	@FindBy(xpath="//input[@name='currency_symbol']")
	WebElement currencySymbol;
	@FindBy(xpath="//input[@name='disable_online_payment']")
	WebElement checkbox;
	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement addClientSave;
	@FindBy(xpath="//table[@id='client-table']//tbody//tr[1]//td[2]//a")
	WebElement titleColumnData;
	@FindBy(xpath="//table[@id='client-table']//tbody//tr[1]//td[9]//a[1]")
	WebElement editData;
	@FindBy(xpath="//input[@name='company_name']")
	WebElement editCompName;
	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement editSave;
	@FindBy(xpath="//table[@id='client-table']//tbody//tr[1]//td[9]//a[2]")
	WebElement deleteButton;
	@FindBy(xpath="//button[@id='confirmDeleteButton']")
	WebElement deleteButtonConfirm;
	@FindBy(xpath="//button[@class='close']")
	WebElement clickSaveClose;
	@FindBy(xpath="//table[@id='client-table']//tbody//tr[1]//td[1]")
	WebElement deletetext;
	
	public ClientPage(WebDriver driver)
	{
		this.driver=driver;
		elementutil=new ElementUtility(driver);
		waitUtility=new WaitUtility(driver);
		PageFactory.initElements(driver,this );
	}
	
	public void doClickClient()
	{
		waitUtility.waitForClick(clientClick);
		elementutil.doSubmitKeys(clientClick);
	}
	public void doClickAddClient(String copname,String address,String city,String state,String zip,String contry,String phone,String  web,String vat,String sym)
	{
		waitUtility.waitForClick(clickAddClient);
		elementutil.doSubmitKeys(clickAddClient);
		waitUtility.waitForVisibility(AddCompName);
		elementutil.doSendKeys(AddCompName, copname);
		elementutil.doSendKeys(AddAddress, address);
		elementutil.doSendKeys(AddCity, city);
		elementutil.doSendKeys(AddState, state);
		elementutil.doSendKeys(AddZip, zip);
		elementutil.doSendKeys(AddCountry, contry);
		elementutil.doSendKeys(AddPhone, phone);
		elementutil.doScroll(checkbox);
		elementutil.doSendKeys(Addwebsite, web);
		elementutil.doSendKeys(Addvat, vat);
		elementutil.doSubmitKeys(currency);
		elementutil.doSubmitKeys(currencySelect);
		elementutil.doSendKeys(currencySymbol, sym);
		elementutil.doSubmitKeys(checkbox);
		elementutil.doSubmitKeys(addClientSave);
		waitUtility.waitForClick(clickSaveClose);
		elementutil.doSubmitKeys(clickSaveClose);
		waitUtility.waitForClick(clickAddClient);
		elementutil.doSubmitKeys(clientClick);
		
	}
	
	public String doClickSearchClient(String searchValue)
	{
		waitUtility.waitForClick(clientClick);
		elementutil.doSendKeys(clickSearchClient, searchValue);
		By locator=By.xpath("//table[@id='client-table']//tbody//tr//td//a[contains(text(),'"+searchValue+"')]");
		waitUtility.waitForVisibility(locator);
		List<WebElement> clienttable=driver.findElements(By.xpath("//table[@id='client-table']//tbody//tr//td//a[contains(text(),'"+searchValue+"')]"));
		waitUtility.waitForVisibility(clienttable);
		int row=elementutil.getTableDataRowCount(clienttable, searchValue);

		String actualmsg="";
if(row!=0) 
		{
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='client-table']//tbody//tr["+row+"]//td[2]"));
			actualmsg=tableRow.getText();
			System.out.println("Searched Element : " +actualmsg);
		}
		return actualmsg;

		
	}
	public void doEditClient(String compName)
	{
		elementutil.doSubmitKeys(editData);
		waitUtility.waitForVisibility(editCompName);
		elementutil.doClearData(editCompName);
		elementutil.doSendKeys(editCompName, compName);
		elementutil.doSubmitKeys(editSave);
		waitUtility.waitForClick(clickSaveClose);
		elementutil.doSubmitKeys(clickSaveClose);		
		
	}
	public String doDeletClient(String searchValue)
	{
		elementutil.doSubmitKeys(deleteButton);
		waitUtility.waitForClick(deleteButtonConfirm);
		elementutil.doSubmitKeys(deleteButtonConfirm);
		waitUtility.waitForVisibility(deletetext);
		waitUtility.waitForVisibility(clickSearchClient);
		elementutil.doClearData(clickSearchClient);
		elementutil.doSendKeys(clickSearchClient, searchValue);
		String deleteText=elementutil.dogetText(deletetext);
		return deleteText;
	}

}
