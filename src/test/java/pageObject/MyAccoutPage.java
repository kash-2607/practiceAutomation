package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccoutPage extends PageBase{

	public MyAccoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//p[contains(text(),'Hello')]") WebElement welcomeMsg;
	@FindBy(xpath="//nav[@class='woocommerce-MyAccount-navigation']//li[last()]/a") WebElement logoutBtn;
	public boolean varifyLogin() {
		
		try {
		return welcomeMsg.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	public void clickLogoutBtn() {
		logoutBtn.click();
	}
}
