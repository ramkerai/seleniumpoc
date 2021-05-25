package org.r3.seleniumpoc.pages;


import org.openqa.selenium.By;
import org.r3.seleniumpoc.seleniumpoc.browser.utility.BrowserUtility;

public class MyOrganisationPage extends BrowserUtility {
	

	private static final By ORGANISATION_NAME_HEADER_LOCATOR = By.xpath("//div[@class = 'org-name']/descendant::p");
	
	private static final By NETWORKS_MENU_LOCATOR = By.xpath("//a[@href=\"/networks\"]");
	private static final By ORGANIZATION_HEADING_TAB_LOCATOR = By.xpath("//div[text()=\"Organization Management\"]");
	
	private static final By APPLICATIONS_MENU_LOCATOR = By.xpath("//a[@href=\"/applications\"]");	
	
	private static final By NODES_MENU_LOCATOR = By.xpath("//a[@href=\"/nodes\"]");
	
	

	public boolean verifyOrganisationVisible() {
		
		boolean isVisible = isElementVisible(ORGANISATION_NAME_HEADER_LOCATOR);
		return isVisible;
	}
	
	public String getOrganizationNameTopRightCorner() {
		return getText(ORGANISATION_NAME_HEADER_LOCATOR);
	}
	
	public void goToOrganizationManagementTab() {
		
		clickOnButton(ORGANIZATION_HEADING_TAB_LOCATOR);
	}
	
	public ApplicationsPage goToApplicationsMenu() {
		
		clickOnButton(APPLICATIONS_MENU_LOCATOR);
		
		return new ApplicationsPage();
	}
	
	public NodesPage goToNodesPage() {
		
		clickOnButton(NODES_MENU_LOCATOR);
		
		return new NodesPage();
	}


}
