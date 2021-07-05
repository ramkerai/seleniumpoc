package org.r3.seleniumpoc.pages;

import org.openqa.selenium.By;
import org.r3.seleniumpoc.seleniumpoc.browser.utility.BrowserUtility;

public class MyNodePage extends BrowserUtility {
	
	
	private static final By CREATE_IDENTITY_BUTTON_LOCATOR = By.xpath("//div[@data-tooltip = 'Create Identity']/../..");
	private static final By ORG_NAME_TEXT_BOX_LOCATOR = By.id("OrganizationName");
	private static final By ORG_UNIT_TEXT_BOX_LOCATOR = By.id("organizationalUnit");
	private static final By LOCALITY_TEXT_BOX_LOCATOR = By.id("locality");
	//value = 'GB'  visible = 'GB: United Kingdom'
	private static final By COUNTRY_DROPDOWN_LOCATOR = By.xpath("//span[text() = 'Country']/../preceding-sibling::select");
	private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
	
	//value = 'GB'  visible = 'MS Network'
	private static final By NETWORK_DROPDOWN_LOCATOR = By.xpath("//span[text() = 'Network']/../preceding-sibling::select");
	
	
	private static final By CREATE_NEW_IDENTITY_BUTTON_LOCATOR = By.xpath("//span[text() = 'Create New Identity']/..");
	
	//private static final By ADD_APPLICATION_BUTTON_LOCATOR = By.xpath("//div[@data-tooltip = 'Create Identity to add Applications']/../..");
	private static final By ADD_APPLICATION_BUTTON_LOCATOR = By.id("addNodeApps");

	public void createNewIdentity(String orgName, String orgUnit, String locality, String country, String email,
			String network) throws InterruptedException {
		clickOnButton(CREATE_IDENTITY_BUTTON_LOCATOR);
		enterText(ORG_NAME_TEXT_BOX_LOCATOR, orgName);
		enterText(ORG_UNIT_TEXT_BOX_LOCATOR, orgUnit);
		enterText(LOCALITY_TEXT_BOX_LOCATOR, locality);
		selectDropDownByValue(COUNTRY_DROPDOWN_LOCATOR, country);
		enterText(EMAIL_TEXT_BOX_LOCATOR, email);
		selectDropDownByVisibleText(NETWORK_DROPDOWN_LOCATOR, network);
		
		Thread.sleep(5000);
		if( isElementEnabled(CREATE_NEW_IDENTITY_BUTTON_LOCATOR))
			clickOnButton(CREATE_NEW_IDENTITY_BUTTON_LOCATOR);
		
	}


	//used to verofy if identity is addeed
	public boolean isCreateIdentityButtonNotVisible() {
		boolean result = isElementVisible(CREATE_NEW_IDENTITY_BUTTON_LOCATOR);
		return !result;
	}


	public ApplicationDeploymentPage addApplications() {
		clickOnButton(ADD_APPLICATION_BUTTON_LOCATOR);
		return new ApplicationDeploymentPage();
	}
}
