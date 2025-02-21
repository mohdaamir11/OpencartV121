package testCases;
import javax.swing.text.Utilities;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.MainMenuPage;
import utilities.DataProviders;

public class TC001_AccountRegistrationTest extends BaseClass {



	@Test (groups = {"Sanity","Regression","Master"})
	public void verifyAccountRegistration() throws InterruptedException {

		logger.info("************ starting TC001_AccountRegistrationTest **********");

		try {


		HomePage hp = new HomePage(driver);

		hp.myAccountClick();
		logger.info("************ clicked my Account label **********");


		AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);

		String randomlyGenEmail = randomString()+"@yopmail.com";

		accountRegistrationPage.setEmail_Register(randomlyGenEmail);
		logger.info("************ entered email id " + randomlyGenEmail + " for registration **********");

		accountRegistrationPage.setPassword_Register(prop.getProperty("password"));
		logger.info("************ entered password id for registration **********");

		accountRegistrationPage.clickRegisterButton();
		logger.info("************ clicked Register button **********");

		accountRegistrationPage.clickSignOut();
		logger.info("************ clicked Sign Out button **********");


		accountRegistrationPage.setEmail_Login(randomlyGenEmail);
		logger.info("************ entered email id for registration **********");

		accountRegistrationPage.setPassword_Login(prop.getProperty("password"));
		logger.info("************ entered password id for registration **********");

		accountRegistrationPage.clickLoginButton();
		logger.info("************ clicked Login button **********");


		} catch (Exception e) {

			logger.error("Test Failed...");

			logger.debug("Debug logs...");

			System.out.println(e);

		}

	}

	
	
	
	


}
