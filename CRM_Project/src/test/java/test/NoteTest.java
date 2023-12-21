package test;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.Constants;
import page.LoginPage;
import page.NotePage;
import utility.ExcelRead;


public class NoteTest extends BaseTest  {
	
  @Test(priority=1,groups= {"regression","sanity"},retryAnalyzer = generaltests.Retry.class,dataProvider="logindataprovider")
  public void VerifyUserIsAbleToAddNote(String username,String password) {
	  LoginPage loginPage=new LoginPage(driver);
	  loginPage.doLogin(username, password);
	  NotePage notePage=new NotePage(driver);
	  notePage.doClickNote();
	  notePage.doAddNoteClick();
	  notePage.doAddNote(ExcelRead.getString(1, 0, "notedata"), ExcelRead.getString(1, 1, "notedata"));
	  notePage.doClickNote();
	  String TitleAddedInsideTable=notePage.doSearchValue(ExcelRead.getString(1, 0, "notedata"));
	  Assert.assertEquals(TitleAddedInsideTable, ExcelRead.getString(1, 0, "notedata"), ExcelRead.getString(1, 3, "notedata"));
	  
	  
  }
  @Test(priority=2,groups= {"regression"},retryAnalyzer = generaltests.Retry.class,dataProvider="logindataprovider")
  public void VerifyUserIsAbleTosearchNote(String username,String password) {
	  LoginPage loginPage=new LoginPage(driver);
	  loginPage.doLogin(username, password);
	  NotePage notePage=new NotePage(driver);
	  notePage.doClickNote();
	  String TitleSearchInsideTable=notePage.doSearchValue(ExcelRead.getString(1, 0, "notedata"));
	  Assert.assertEquals(TitleSearchInsideTable, ExcelRead.getString(1, 0, "notedata"), ExcelRead.getString(2, 3, "notedata"));
	  
	  
	  
  }
  @Test(priority = 3,groups= {"regression"},retryAnalyzer = generaltests.Retry.class,dataProvider="logindataprovider")//re-exicute if failed
  public void VerifyUserIsAbleToEditNote(String username,String password) {
	  LoginPage loginPage=new LoginPage(driver);
	  loginPage.doLogin(username, password);
	  NotePage notePage=new NotePage(driver);
	  notePage.doClickNote();
	  String TitleToBeEditedInsideTable=notePage.doSearchValue(ExcelRead.getString(1, 0, "notedata"));
	  notePage.doEdit(ExcelRead.getString(1, 2, "notedata"));	
	  notePage.doClickNote();
 	  String TitleEditedInsideTable=notePage.doSearchValue(ExcelRead.getString(1, 2, "notedata"));
	  Assert.assertEquals(TitleEditedInsideTable, ExcelRead.getString(1, 2, "notedata"), ExcelRead.getString(3, 3, "notedata"));
	  
	  
	  
  }
  @Test(priority=4,groups= {"regression"},retryAnalyzer = generaltests.Retry.class,dataProvider="logindataprovider")
  public void VerifyUserIsAbleToDeletNote(String username,String password) {
	  LoginPage loginPage=new LoginPage(driver);
	  loginPage.doLogin(username, password);
	  NotePage notePage=new NotePage(driver);
	  notePage.doClickNote();
	  String TitleToBeDeletedInsideTable=notePage.doSearchValue(ExcelRead.getString(1, 2, "notedata"));
	  String deletTextss=notePage.doDelet(ExcelRead.getString(1, 2, "notedata"));
	  Assert.assertEquals(deletTextss, ExcelRead.getString(1, 4, "notedata"), ExcelRead.getString(4, 3, "notedata"));
	  
	  
	  
	  
  }
  @DataProvider
  public Object[][] logindataprovider() throws InvalidFormatException, IOException{
	 
	 Object[][] excelvalue=ExcelRead.getDataFromExcel(Constants.testData, "logindata");
	 return excelvalue;
  }
  
  
}
