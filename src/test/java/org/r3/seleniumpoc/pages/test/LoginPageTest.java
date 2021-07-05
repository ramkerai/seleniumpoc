package org.r3.seleniumpoc.pages.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.r3.seleniumpoc.pages.OrganizationsPage;
import org.r3.seleniumpoc.pages.LoginPage;
import org.r3.seleniumpoc.seleniumpoc.browser.utility.BrowserName;
import org.r3.seleniumpoc.utility.TestUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.r3.listeners.MyCustomListeners.class)
public class LoginPageTest {

	private LoginPage loginPage;
	
	@BeforeMethod
	public void preTest() {
		loginPage = new LoginPage();
		loginPage.launchBrowser(BrowserName.CHROME);
		loginPage.goToPage(TestUtility.readProperty("config.properties", "URL"));
	}
	
	@Test(testName = "LoginTest", description =  "verify if login works")
	public void loginTest() throws InterruptedException{
		String username= TestUtility.readProperty("config.properties", "USERNAME");
		String password= TestUtility.readProperty("config.properties", "PASSWORD");
		OrganizationsPage orgPage = loginPage.submitLogin(username, password);
		//check if Organization text is visible at heading
		assertTrue( orgPage.verifyOrganisationVisible());
		//check can see portal username
		assertEquals( orgPage.getUsernameFromPage().toLowerCase(), username);
		
		
	}
	
	
	@AfterMethod( enabled=false)
	public void tearDown() {
		loginPage.closeBrowser();
	} 
}
