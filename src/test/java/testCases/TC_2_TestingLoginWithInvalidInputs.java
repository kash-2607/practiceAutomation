package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginAndRegistrationPage;
import testBase.BaseClass;

public class TC_2_TestingLoginWithInvalidInputs extends BaseClass  {
	
	@Test(groups = {"regression"})
	public void testingLoginWithInvalidInputs() {
		logger.info("Click on My Account Menu");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		
		
		LoginAndRegistrationPage lrp = new LoginAndRegistrationPage(driver);
		
		logger.info("Enter registered username in username textbox");
		lrp.enterEmailOrUsername(randomEmailGenerator());
		logger.info("Enter password in password textbox");
		lrp.enterPassward(randomAlphaNumaricStringGenerator());
		logger.info("Click on login button");
		lrp.clickLoginBtn();
		
		//validation
		
		if(lrp.loginErrorMsg()==true) {
			logger.info("Test pass");			
			Assert.assertTrue(true);
		}else {
			logger.error("Test fail");			
			Assert.fail();
		}
		
	}
	
}
