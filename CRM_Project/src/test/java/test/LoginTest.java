package test;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.Constants;
import page.LoginPage;
import utility.ExcelRead;

public class LoginTest extends BaseTest {
  @Test(groups= {"sanity","regression"},dataProvider = "logindataprovider")
  public void login(String username,String password) {
	  LoginPage loginpage=new LoginPage(driver);
	  loginpage.doLogin(username, password);
	  
  }
  @DataProvider
  public Object[][] logindataprovider() throws InvalidFormatException, IOException{
	 
	 Object[][] excelvalue=ExcelRead.getDataFromExcel(Constants.testData, "logindata");
	 return excelvalue;
  }
}
