package org.r3.seleniumpoc.pages;

import org.openqa.selenium.By;
import org.r3.seleniumpoc.seleniumpoc.browser.utility.BrowserUtility;

public class ApplicationsPage extends BrowserUtility {
	
	private static final By ORGANIZATION_HEADING_LOCATOR = By.xpath("//div[text()=\"Organization Management\"]");
	private static final By NETWORKS_MENU_LOCATOR = By.xpath("//a[@href=\"/networks\"]");
	private static final By APPLICATIONS_MENU_LOCATOR = By.xpath("//a[@href=\"/applications\"]");
	private static final By ADD_CORDAPP_LOCATOR = By.xpath("//span[text() = \"Add New Cordapp\"]/../..");
	private static final By DESCRIPTION_NAME_ADD_CORDAPP = By.id("description");
	private static final By CONFIG_ADD_CORDAPP = By.id("corDappConfig");
	private static final By APPLICATIONS_HEADER_PAGE_LOCATOR = By.xpath("//span[@class = 'cta-header']");
	
	private static final By FILE_TEXT_LOCATOR = By.xpath("//input[@type = 'file']");  
	private static final By FILE_TEXT_SELECTOR_LOCATOR = By.xpath("//span[text() = 'Select File']");
	private static final By FILE_UPLOAD_BUTTON_LOCATOR = By.xpath("//span[text() = 'Upload']/..");
	private static final By CONFIRMATION_TEXT_LOCATOR = By.xpath("//div[text() = 'Upload Confirmation']");
	private static final By CLOSE_BUTTON_LOCATOR = By.xpath("//span[text()='Close']/..");
	private static final By WORKFLOWS_SDK_NAME_LOCATOR = By.xpath("//li[text() = 'Token SDK Workflows']");

	
	public String getApplicationPageHeader() {
		return getText(APPLICATIONS_HEADER_PAGE_LOCATOR);
	}
	
	public void addNewCordapp(String description, String config, String path) {
		
		//click coradapp button
		clickOnButton(ADD_CORDAPP_LOCATOR);
		enterText(DESCRIPTION_NAME_ADD_CORDAPP,description);
		enterText(CONFIG_ADD_CORDAPP,config);
				
		enterTextNoWait(FILE_TEXT_LOCATOR, path);
				
		clickOnButton(FILE_UPLOAD_BUTTON_LOCATOR);
		//confirm its been added by checking for Upload confirmation
		isElementVisible(CONFIRMATION_TEXT_LOCATOR);
		//click button close window
		clickOnButton(CLOSE_BUTTON_LOCATOR);
	}
	
	public boolean isCordappVisible() {
		return isElementVisible(WORKFLOWS_SDK_NAME_LOCATOR);
	}

}
