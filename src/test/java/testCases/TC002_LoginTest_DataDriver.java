package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.MainMenuPage;
import utilities.DataProviders;

public class TC002_LoginTest_DataDriver extends BaseClass {
	
	
	@Test (dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups = {"Sanity","Regression","DataDriver","Master"})
	public void TC002_LoginTest_DataDriver(String email, String pwd, String exp) {
		
		logger.info("************ STARTED TC002_LoginTest_DataDriver - loginTest - data driven **********");

		try {


		HomePage hp = new HomePage(driver);

		hp.myAccountClick();
		logger.info("************ clicked my Account label **********");


		AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);

		accountRegistrationPage.setEmail_Login(email);
		logger.info("************ entered email id for registration **********");

		accountRegistrationPage.setPassword_Login(pwd);
		logger.info("************ entered password id for registration **********");

		accountRegistrationPage.clickLoginButton();
		logger.info("************ clicked Login button **********");

		MainMenuPage mp = new MainMenuPage(driver);
		
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(mp.isDashboardOptionPresent()) {
				
				accountRegistrationPage.clickSignOut();
				logger.info("************ clicked Sign Out button **********");
				Assert.assertTrue(true);
				
			}else {
				
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("invalid"))
		{
                 
            if(mp.isDashboardOptionPresent()) {
				
				accountRegistrationPage.clickSignOut();
				logger.info("************ clicked Sign Out button **********");
				Assert.assertTrue(false);
				
			}else {
				
				Assert.assertTrue(true);
			}
			
		}
		

		} catch (Exception e) {

		Assert.fail();

		}
		
		logger.info("************ ENDED TC002_LoginTest_DataDriver - loginTest - data driven **********");
		
	}
	

}
