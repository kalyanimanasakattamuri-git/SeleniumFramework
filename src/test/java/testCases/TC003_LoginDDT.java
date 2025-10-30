package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups={"dataDriven", "master"}) //getting data provider from differnt class
	public void loginTest(String email, String pwd, String expected) {
		logger.info("***** Execution of 'TC003_Login_DataDriven_Test' started ");

		//HomePage
		logger.info("clicked on login.........");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login page
		logger.info("Entering login details..........");
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
			
		//MyAccount page
		logger.info("Checking MyAccount page is displayed");
		MyAccountPage myacc = new MyAccountPage(driver);
		boolean target = myacc.isMyAccountPageExists();
		
/* Data is valid - login success - test pass - logout
 				 - login failed - test fail
   Data is invalid - login success - test fail - logout
 				   - login failed - test pass
 */
		
		if(expected.equalsIgnoreCase("valid")) {
			if(target == true) {
				myacc.clickLogout();
				logger.info("when login data is valid and logout successful.........");
				Assert.assertTrue(true);
			}else {
				logger.info("when login data is valid but login failed.........");
				Assert.assertTrue(false);
			}
		}
		else if (expected.equalsIgnoreCase("invalid")) {
			if(target == true) {
				myacc.clickLogout();
				logger.info("when login data is invalid and logout successful.........");
				Assert.assertTrue(false);
			}else {
				logger.info("when login data is invalid but login failed.........");
				Assert.assertTrue(true);
			}
		}
		
	}
}
