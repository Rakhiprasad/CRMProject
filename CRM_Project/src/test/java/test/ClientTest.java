package test;


import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sun.net.httpserver.Authenticator.Retry;

import constants.Constants;
import page.ClientPage;
import page.LoginPage;
import utility.ExcelRead;
import utility.FakerUtility;


public class ClientTest extends BaseTest {
	
	

	@Test(priority=1,groups= {"regression","sanity"},retryAnalyzer = generaltests.Retry.class,dataProvider="logindataprovider")
	  public void verifyUserIsAbleToAddClient(String username,String password) {
		  LoginPage loginPage=new LoginPage(driver);
		  loginPage.doLogin(username, password);
		  ClientPage clientPage=new ClientPage(driver);
		  clientPage.doClickClient();
		  clientPage.doClickAddClient(ExcelRead.getString(1, 0, "clientdata"), ExcelRead.getString(1, 1, "clientdata"), ExcelRead.getString(1, 2, "clientdata"), FakerUtility.state(), ExcelRead.getNumeric(1, 3, "clientdata"), ExcelRead.getString(1, 4, "clientdata"), FakerUtility.phoneNumber(), FakerUtility.emailID(), ExcelRead.getString(1, 5, "clientdata"), ExcelRead.getString(1, 6, "clientdata"));
		  clientPage.doClickClient();
		  String companyNameAddedInsideTable=clientPage.doClickSearchClient(ExcelRead.getString(1, 0, "clientdata"));
		  Assert.assertEquals(companyNameAddedInsideTable, ExcelRead.getString(1, 0, "clientdata"), ExcelRead.getString(1, 9, "clientdata"));
		  
	  }
	
  @Test(priority=2,groups= {"regression"},retryAnalyzer = generaltests.Retry.class,dataProvider="logindataprovider")
  public void verifyUserIsAbleToSearchClient(String username,String password) {
	  LoginPage loginPage=new LoginPage(driver);
	  loginPage.doLogin(username, password);
	  ClientPage clientPage=new ClientPage(driver);
	  clientPage.doClickClient();
	  String companyNameSearchInsideTable=clientPage.doClickSearchClient(ExcelRead.getString(1, 0, "clientdata"));
	  Assert.assertEquals(companyNameSearchInsideTable, ExcelRead.getString(1, 0, "clientdata"), ExcelRead.getString(2, 9, "clientdata"));
	  
  }
	
  @Test(priority=3,groups= {"regression"},retryAnalyzer = generaltests.Retry.class,dataProvider="logindataprovider")
  public void verifyUserIsAbleToEditClient(String username,String password) {
	  LoginPage loginPage=new LoginPage(driver);
	  loginPage.doLogin(username, password);
	  ClientPage clientPage=new ClientPage(driver);
	  clientPage.doClickClient();
	  String companyNameInsideTableToEdit=clientPage.doClickSearchClient(ExcelRead.getString(1, 0, "clientdata"));
	  clientPage.doEditClient(ExcelRead.getString(1, 7, "clientdata"));
	  clientPage.doClickClient();
	  String companyNameEditedInsideTable=clientPage.doClickSearchClient(ExcelRead.getString(1, 7, "clientdata"));
	  Assert.assertEquals(companyNameEditedInsideTable, ExcelRead.getString(1, 7, "clientdata"), ExcelRead.getString(3, 9, "clientdata"));
	  
  }
  @Test(priority=4,groups= {"regression"},retryAnalyzer = generaltests.Retry.class,dataProvider="logindataprovider")
  public void verifyUserIsAbleToDeletClient(String username,String password) {
	  LoginPage loginPage=new LoginPage(driver);
	  loginPage.doLogin(username, password);
	  ClientPage clientPage=new ClientPage(driver);
	  clientPage.doClickClient();
	  String companyNameInsideTableToDelet=clientPage.doClickSearchClient(ExcelRead.getString(1, 7, "clientdata"));
	  String deleteText=clientPage.doDeletClient(ExcelRead.getString(1, 7, "clientdata"));
	  Assert.assertEquals(deleteText, ExcelRead.getString(1, 8, "clientdata"), ExcelRead.getString(4, 9, "clientdata"));
	  
	  
	  
  }
  
  
  @DataProvider
  public Object[][] logindataprovider() throws InvalidFormatException, IOException{
	 
	 Object[][] excelvalue=ExcelRead.getDataFromExcel(Constants.testData, "logindata");
	 return excelvalue;
  }
}
