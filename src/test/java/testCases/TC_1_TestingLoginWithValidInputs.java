package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginAndRegistrationPage;
import pageObject.MyAccoutPage;
import testBase.BaseClass;

public class TC_1_TestingLoginWithValidInputs extends BaseClass{
	
	
	@Test(groups = {"master","sanity", "regression"})    
	public void testingLogin() {
		logger.info("Click on My Account Menu");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		
		
		LoginAndRegistrationPage lrp = new LoginAndRegistrationPage(driver);
		
		logger.info("Enter registered username in username textbox");
		lrp.enterEmailOrUsername(config.getProperty("email"));
		logger.info("Enter password in password textbox");
		lrp.enterPassward(config.getProperty("password"));
		logger.info("Click on login button");
		lrp.clickLoginBtn();
		
		//validation
		MyAccoutPage map = new MyAccoutPage(driver);
		
		if(map.varifyLogin()==true) {
			logger.info("Test pass");
			Assert.assertTrue(true);
		}else {
			Assert.fail();
			logger.error("Test fail");
		}
		
	}
	
	
	
}
