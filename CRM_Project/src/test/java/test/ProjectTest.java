package test;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.Constants;
import page.LoginPage;
import page.ProjectPage;
import utility.ExcelRead;

public class ProjectTest extends BaseTest {
	
	@Test(priority=1,groups= {"regression","sanity"},retryAnalyzer = generaltests.Retry.class,dataProvider="logindataprovider")
	  public void verifyUserIsAbleToAddProject(String username,String password) {
		  LoginPage loginPage=new LoginPage(driver);
		  loginPage.doLogin(username, password);
		  ProjectPage projectPage=new ProjectPage(driver);
		  projectPage.doClickAllProject();
		  projectPage.doClickAddProject(ExcelRead.getString(1, 0, "projectdata"), ExcelRead.getString(1, 1, "projectdata"), ExcelRead.getNumeric(1, 2, "projectdata"),ExcelRead.getString(1, 3, "projectdata"));
		  projectPage.doClickAllProject();
		  String TitleAddedInsideTable=projectPage.doClickSearchProject(ExcelRead.getString(1, 0, "projectdata"));
		  Assert.assertEquals(TitleAddedInsideTable,ExcelRead.getString(1, 0, "projectdata"), ExcelRead.getString(1, 6, "projectdata"));
		  
	  }
	
  @Test(priority=2,groups= {"regression"},retryAnalyzer = generaltests.Retry.class,dataProvider="logindataprovider")
  public void verifyUserIsAbleToSearchProject(String username,String password) {
	  LoginPage loginPage=new LoginPage(driver);
	  loginPage.doLogin(username, password);
	  ProjectPage projectPage=new ProjectPage(driver);
	  projectPage.doClickAllProject();
	  String TitleSearchInsideTable=projectPage.doClickSearchProject(ExcelRead.getString(1, 0, "projectdata"));
	  Assert.assertEquals(TitleSearchInsideTable, ExcelRead.getString(1, 0, "projectdata"), ExcelRead.getString(2, 6, "projectdata"));
	  
  }
  @Test(priority=3,groups= {"regression"},retryAnalyzer = generaltests.Retry.class,dataProvider="logindataprovider")
  public void verifyUserIsAbleToEditProject(String username,String password) {
	  LoginPage loginPage=new LoginPage(driver);
	  loginPage.doLogin(username, password);
	  ProjectPage projectPage=new ProjectPage(driver);
	  projectPage.doClickAllProject();
	  String TitleToBeEditedInsideTable=projectPage.doClickSearchProject(ExcelRead.getString(1, 0, "projectdata"));
	  projectPage.doEditProject(ExcelRead.getString(1, 4, "projectdata"));
	  projectPage.doClickAllProject();
	  String TitleEditedInsideTable=projectPage.doClickSearchProject(ExcelRead.getString(1, 4, "projectdata"));
	  Assert.assertEquals(TitleEditedInsideTable, ExcelRead.getString(1, 4, "projectdata"), ExcelRead.getString(3, 6, "projectdata"));
	  
}
  
  @DataProvider
  public Object[][] logindataprovider() throws InvalidFormatException, IOException{
	 
	 Object[][] excelvalue=ExcelRead.getDataFromExcel(Constants.testData, "logindata");
	 return excelvalue;
  }
}
