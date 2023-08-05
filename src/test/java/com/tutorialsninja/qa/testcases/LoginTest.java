package com.tutorialsninja.qa.testcases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.pages.AccountPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.tutorialsninja.qa.base.Base;
import com.utils.Utils;

public class LoginTest extends Base{
	LoginPage loginPage;
	
	public WebDriver driver;
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage= homePage.navigateToLOginPage();
			
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		AccountPage accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformation());
		
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utils.getTestDataFromExcel("Login");
		return data;
		
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		loginPage.login(Utils.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNoMatchWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expected warning message is not displayed");
		
	}
  
    @Test(priority=3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {
    	
    	loginPage.login(Utils.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
    	Assert.assertTrue(loginPage.retrieveEmailPasswordNoMatchWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expected warning message is not displayed");
		
    }
    @Test(priority=4)
    public void verifyLoginWithValidEmailAndInvalidPassword() {
    	
    	loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
    	Assert.assertTrue(loginPage.retrieveEmailPasswordNoMatchWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expected warning message is not displayed");

   }
   @Test(priority=5)
   public void verifyLoginWithoutProvidingCredentials() {
	   
	   loginPage.clickOnLoginButton();
	   Assert.assertTrue(loginPage.retrieveEmailPasswordNoMatchWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expected warning message is not displayed");
	   
		
  }

}
