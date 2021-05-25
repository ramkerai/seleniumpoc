package org.r3.seleniumpoc.pages.test;

import org.r3.seleniumpoc.pages.LoginPage;
import org.r3.seleniumpoc.pages.OrganizationsPage;
import org.r3.seleniumpoc.seleniumpoc.browser.utility.BrowserName;
import org.r3.seleniumpoc.seleniumpoc.browser.utility.BrowserUtility;
import org.r3.seleniumpoc.utility.TestUtility;
import org.testng.annotations.BeforeClass;

public class MyOrganizationPageTest  {
	
	private LoginPage loginPage;
	private OrganizationsPage organizationPage;
	
	
	@BeforeClass
	public void setup() throws InterruptedException {
		loginPage = new LoginPage();
		loginPage.launchBrowser(BrowserName.CHROME);
		loginPage.goToPage(TestUtility.readProperty("config.properties", "URL"));
		String username= TestUtility.readProperty("config.properties", "USERNAME");
		String password= TestUtility.readProperty("config.properties", "PASSWORD");
	    organizationPage = loginPage.submitLogin(username, password);
	    //organizationPage.goToOrganisation(password)
	}

	
}
