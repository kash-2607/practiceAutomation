package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginAndRegistrationPage;
import pageObject.MyAccoutPage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtility;

public class TC_3_TestingLogin_DDT extends BaseClass{
	
	@Test (dataProvider="LoginData", dataProviderClass = DataProviders.class)
	public void testingLoginDDT(String email, String pwd, String exp) throws IOException {
		logger.info("Click on My Account Menu");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();

		LoginAndRegistrationPage lrp = new LoginAndRegistrationPage(driver);
		
		logger.info("Enter registered username in username textbox");
		lrp.enterEmailOrUsername(email);
		logger.info("Enter password in password textbox");
		lrp.enterPassward(pwd);
		logger.info("Click on login button");
		lrp.clickLoginBtn();
		//validation
		MyAccoutPage map = new MyAccoutPage(driver);
		boolean targetPage= map.varifyLogin();
		
		String path = ".//testData/LoginData.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);
		
		
		
		
		if(exp.equalsIgnoreCase("valid")) {
			
			if(targetPage==true) {
				logger.info("Test pass with email: "+email+" and passward: "+ pwd);
				Assert.assertTrue(true);
				logger.info("Clicking on Logout Button");
				map.clickLogoutBtn();
			}else {
				Assert.fail();
				logger.error("Test fail with email: "+email+" and passward: "+ pwd);
			}
		}else{
			
			if(targetPage==true) {
				logger.error("Test fail with email: "+email+" and passward: "+ pwd);
				Assert.fail();
				logger.info("Clicking on Logout Button");
				map.clickLogoutBtn();
			}else{
				logger.info("Test pass with email: "+email+" and passward: "+ pwd);
				Assert.assertTrue(true);
			}
			
		}
		
		
		
		
	}
}
