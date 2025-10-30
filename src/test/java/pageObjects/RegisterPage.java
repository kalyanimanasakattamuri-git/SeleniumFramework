package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{

	//Constructor
	public RegisterPage(WebDriver driver){
		super(driver);
	}
	
	//Locators in Register page
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtPasswordConfirm;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPrivacy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement registerSuccessMessage;	

	//Action methods.......
	public void setFirstName(String fName) {
		txtFirstName.sendKeys(fName);
	}
	
	public void setLastName(String lName) {
		txtLastName.sendKeys(lName);
	}
	
	public void setEmail(String gmail) {
		txtEmail.sendKeys(gmail);
	}
	
	public void setTelephone(String phoneNum) {
		txtTelephone.sendKeys(phoneNum);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String cnfPwd) {
		txtPasswordConfirm.sendKeys(cnfPwd);
	}
	
	public void confirmPrivacy() {
		chkPrivacy.click();
	}
	
	public void clickContinue() {
		btnContinue.click();
	}
	
	public String checkSuccessMessage() {
		try {
			return (registerSuccessMessage.getText());
		}
		catch(Exception e){
			return (e.getMessage());
		}
	}
}
