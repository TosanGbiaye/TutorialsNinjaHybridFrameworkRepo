package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	WebDriver driver;
	
	//Object
	@FindBy(linkText="Edit your account information")
	private WebElement editYourAccountInformation;
	
	
	
	//constructor
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//actions or methods
	public boolean getDisplayStatusOfEditYourAccountInformation() {
		boolean displayStatus = editYourAccountInformation.isDisplayed();
		return displayStatus;
	}
	
	

}
