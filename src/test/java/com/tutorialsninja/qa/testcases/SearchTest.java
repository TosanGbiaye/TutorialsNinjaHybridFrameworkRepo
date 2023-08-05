package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.HomePage;
import com.qa.pages.SearchPage;
import com.tutorialsninja.qa.base.Base;

public class SearchTest extends Base{
	SearchPage searchPage;
	HomePage homePage;
	
	//Constructor
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfValidIphoneProduct(), "Valid iPhone product is not displayed");
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		searchPage = homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		Assert.assertEquals(searchPage.retrieveNoProductMatchMessage(), dataProp.getProperty("noProductMatchesSearchCriteriaaa"), "No product matches the searched item");
		
	}
	
	@Test(priority=3,dependsOnMethods = {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		
		searchPage= homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMatchMessage(), dataProp.getProperty("noProductMatchesSearchCriteria"), "No product matches the searched item");
		
	}

}
