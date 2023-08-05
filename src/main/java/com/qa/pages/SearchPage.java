package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	
	@FindBy(linkText="iPhone")
	private WebElement validIphoneProduct; 
	
	@FindBy(xpath="//p[text()='There is no product that matches the search criteria.']")
	private WebElement noProductMatchMessage;
	
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions/Methods
	public boolean displayStatusOfValidIphoneProduct() {
		boolean displayStatus = validIphoneProduct.isDisplayed();
		return displayStatus;
	}
	
	public String retrieveNoProductMatchMessage() {
		String noProductMatchText = noProductMatchMessage.getText();
		return noProductMatchText;
	}
	

}
