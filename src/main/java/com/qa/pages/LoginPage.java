package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	//Objects
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")
	private WebElement emailPasswordNoMatchWarningMessage;
	
	//constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
		
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);	
	}
	
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
		
	}
	
	public AccountPage login(String emailText,String passwordText) {
		emailAddressField.sendKeys(emailText);
		passwordField.sendKeys(passwordText);
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retrieveEmailPasswordNoMatchWarningMessageText() {
		String warningText = emailPasswordNoMatchWarningMessage.getText();
		return warningText;
		
	}

}
