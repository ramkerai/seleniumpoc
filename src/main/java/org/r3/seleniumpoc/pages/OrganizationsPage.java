package org.r3.seleniumpoc.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.r3.seleniumpoc.seleniumpoc.browser.utility.BrowserUtility;

public class OrganizationsPage extends BrowserUtility {
	
	private static final By MENU_TOOLTIP_LOCATOR = By.xpath("//div[@data-tooltip =\"Menu\"]");
	private static final By ORGANIZATION_TEXT_CHECK_LOCATOR = By.xpath("//div[text()=\"Organizations\"]");
	private static final By ADMIN_PORTAL_LOCATOR = By.xpath("//span[@class=\"relative button-tertiary-text\"]");
	private static final By CREATE_NEW_ORGANIZATION_LOCATOR = By.xpath("//span[text()=\"Create Organization\"]/..");

	//span[text()="Create Organization"]/..
	private static final By ORG_NAME_TEXT_LOCATOR =By.xpath("//input[@id = \"orgName\"]");
	private static final By ORG_DESC_TEXT_LOCATOR =By.xpath("//input[@id = \"orgDescription\"]");
	//private static final By CREATE_ORGANIZATION_LOCATOR = By.xpath("//span[text()=\"Create Organization\"]/..");
	private static final By CREATE_ORGANIZATION_LOCATOR = By.xpath("//button[@id = \"createOrgButton\"]");
	
	private static final By CONTAINER_LOCATOR = By.xpath("//div[@class = \"card-container\"]");
	
	private static final By ORGANIZATION_HEADING_LOCATOR = By.xpath("//h3");
	
	
	public void checkItemsOnSuccessfulLogin() {
		
	}
	
	public boolean verifyOrganisationVisible() {
		
		boolean isVisible = isElementVisible(ORGANIZATION_TEXT_CHECK_LOCATOR);
		return isVisible;
	}
	
	public String getUsernameFromPage() {
		String name = getText(ADMIN_PORTAL_LOCATOR);
		return name;
	}
	
	public MyOrganisationPage goToOrganisation( String org) {
		
		String xpathValue  = "//*[text()=\'"+org+"\']";
		By ORGANIZATION_HEADING_LOCATOR = By.xpath(xpathValue);
		clickOnButton(ORGANIZATION_HEADING_LOCATOR);
		
		return new MyOrganisationPage();
	}
	
	public void CreateNewOrganization(String orgname, String orgDesc) throws InterruptedException {
		clickOnButton(CREATE_NEW_ORGANIZATION_LOCATOR);
		enterText(ORG_NAME_TEXT_LOCATOR, orgname);
		enterText(ORG_DESC_TEXT_LOCATOR, orgDesc);
		if( isElementVisible(CREATE_ORGANIZATION_LOCATOR))
		{
			Thread.sleep(1000);  //good to have this as its so quick so didnt see the pop-up. 
			//even without it it worked.
			clickOnButton(CREATE_ORGANIZATION_LOCATOR);
			//Thread.sleep(1000); 
		}
	}

	public boolean doesHeadingExistInElements( String heading, By elementLocator) {
		
		List<WebElement> elements = getAllElements(elementLocator);
		String elementText;
		boolean isFound = false;
		for( int index = 0; index < elements.size(); index++) {
			elementText = elements.get(index).getText();
			if(elementText.equalsIgnoreCase(heading)) {
				isFound = true;
				break;
			}
		}
		
		return isFound;
	}
	
	public boolean isOrganizationAdded(String heading) {
		String xpathValue  = "//*[text()=\'"+heading+"\']";
		By ORGANIZATION_HEADING_LOCATOR = By.xpath(xpathValue);
		boolean isVisible = isElementVisible(ORGANIZATION_HEADING_LOCATOR);
		
		return isVisible;
		
	}
}
