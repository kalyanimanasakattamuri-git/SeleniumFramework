package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups={"regression", "master"})
	public void loginTest() {
		logger.info("***** Execution of 'TC002_LoginTest' started ");
		
		try {
			logger.info("clicked on login.........");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
		
			logger.info("Entering login details..........");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(props.getProperty("email"));
			lp.setPassword(props.getProperty("password"));
			lp.clickLogin();
			
			logger.info("Checking MyAccount page is displayed");
			MyAccountPage myacc = new MyAccountPage(driver);
			boolean target = myacc.isMyAccountPageExists();
			Assert.assertTrue(target);
			
		} catch(Exception e) {
			Assert.fail();
		}
		
	}
}
