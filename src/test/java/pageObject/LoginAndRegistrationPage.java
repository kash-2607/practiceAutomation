package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginAndRegistrationPage extends PageBase{

	public LoginAndRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//input[@id='username']") WebElement unOrEmailInput;
	@FindBy(xpath="//input[@id='password']") WebElement password;
	@FindBy(xpath="//input[@name='login']") WebElement loginBtn;
	@FindBy(xpath="//ul[@class='woocommerce-error']") WebElement loginError;
	
	
	public void enterEmailOrUsername (String ue) {
		unOrEmailInput.sendKeys(ue);
	}
	
	public void enterPassward(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickLoginBtn() {
		loginBtn.click();
	}
	
	public boolean loginErrorMsg() {
		return loginError.isDisplayed();
		}
}
