package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	@Test(groups={"sanity", "master"})
	public void registration() {
		logger.info("Execution of TC001_AccountRegistrationTest started.......");
		try {
		HomePage myAccObj = new HomePage(driver);
		myAccObj.clickMyAccount();
		logger.info("Clicked on MyAccount link");
		myAccObj.clickRegister();
		logger.info("Clicked on Register link");
		
		logger.info("Entering registration details of new customer......");
		RegisterPage registerPageObj = new RegisterPage(driver);
		registerPageObj.setFirstName(randomString().toUpperCase());
		registerPageObj.setLastName(randomString().toUpperCase());
		registerPageObj.setEmail(randomString()+"@gmail.com");
		registerPageObj.setTelephone(randomNumber());
		
		String password = randomAlphaNumeric();
		registerPageObj.setPassword(password);
		registerPageObj.setConfirmPassword(password);
		
		registerPageObj.confirmPrivacy();
		registerPageObj.clickContinue();
		logger.info("Validating registration success message");
		String actualMessage = registerPageObj.checkSuccessMessage();
		if(actualMessage.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else Assert.assertTrue(false);
		Assert.assertEquals(actualMessage, "Your Account Has Been Created!");
		}
		catch(Exception e){
			Assert.fail();
		}
		
		logger.info("Test execution completed successfully....");
	}
	
}
