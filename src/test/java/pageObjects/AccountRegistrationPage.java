package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{


	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}




	@FindBy(xpath = "//div/h2[text()='Register']/..//input[@name='email']")
	WebElement emailField_Register;

	@FindBy(xpath = "//div/h2[text()='Register']/..//input[@name='password']")
	WebElement passwordField_Register;



	@FindBy(xpath = "//div/h2[text()='Login']/..//input[@name='username']")
	WebElement emailField_Login;

	@FindBy(xpath = "//div/h2[text()='Login']/..//input[@name='password']")
	WebElement passwordField_Login;



	@FindBy(xpath = "//input[@name='register']")
	WebElement registerButton;

	@FindBy (xpath = "//input[@name='login']")
	WebElement loginButton;

	@FindBy (xpath = "//a[text()='Sign out']")
	WebElement signOut;


	public void setEmail_Register(String val)
	{
		emailField_Register.sendKeys(val);
	}

	public void setPassword_Register(String val)
	{
		passwordField_Register.sendKeys(val);
	}

	public void setEmail_Login(String val)
	{
		emailField_Login.sendKeys(val);
	}

	public void setPassword_Login(String val)
	{
		passwordField_Login.sendKeys(val);
	}

	public void clickLoginButton() {

		waitForElementToBeClickable(driver, loginButton, 10);
		loginButton.click();
	}


    public void clickRegisterButton() {

		waitForElementToBeClickable(driver, registerButton, 10);
		registerButton.click();
	}


    public void clickSignOut() {

    	waitForElementToBeClickable(driver, signOut, 10);
    	signOut.click();
	}

}
