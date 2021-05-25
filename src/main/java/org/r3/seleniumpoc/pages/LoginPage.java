package org.r3.seleniumpoc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.r3.seleniumpoc.seleniumpoc.browser.utility.BrowserUtility;

public class LoginPage extends BrowserUtility {
	
	private static final By USERNAME_LOGIN_LOCATOR = By.id("username");
	private static final By PASSWORD_LOGIN_LOCATOR =  By.xpath("//input[@type = \"password\"]");
	private static final By SUBMIT_LOGIN_LOCATOR = By.xpath("//span[text() = \"Submit\"]/../..");
	//private WebDriver wb;
	
	
	public OrganizationsPage submitLogin(String username, String pwd) throws InterruptedException {
		
		enterText(USERNAME_LOGIN_LOCATOR, username);
		enterText(PASSWORD_LOGIN_LOCATOR, pwd);	
		clickOnButton(SUBMIT_LOGIN_LOCATOR);
		//Thread.sleep(1000);   //needs double click due a bug
		//clickOnButton(SUBMIT_LOGIN_LOCATOR);
		
		return new OrganizationsPage();
	}
	

}
