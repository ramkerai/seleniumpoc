package org.r3.seleniumpoc.pages;

import org.openqa.selenium.By;
import org.r3.seleniumpoc.seleniumpoc.browser.utility.BrowserUtility;

public class ApplicationDeploymentPage extends BrowserUtility {
	
	
	
	private static final By APPLICATION_DEPLOYMENT_HEADER_LOCATOR = By.xpath("//span[contains(text(), 'Application Deployment')]");
	private static final By CORDAPPS_TAB_LOCATOR = By.xpath("//div[text() = 'CorDapps']");
	private static final By VENDOR_NAME_LOCATOR = By.xpath("//span[@class = 'vendor-title']");
	private static final By VENDOR_R3_BUTTON_LOCATOR = By.xpath("//span[text() = 'R3']/..");
	
	private static final By WORKFLOWS_CORDAPP_LOCATOR = By.xpath("//span[text() = 'Token SDK Workflows']");
	private static final By BACK_TO_APP_CHECK_LOCATOR = By.xpath("//span[text() = 'Back to Apps']");
	
	private static final By APPS_TABLE_TITLE_LOCATOR = By.xpath("//h5[@class = 'table-title']");
	
	private static final By ADD_BUTTON_LOCATOR = By.xpath("//span[text() = \"Add\"]/..");
	
	private static final By WORKFLOW_APP_LOCATOR = By.xpath("//span[text()  = 'WORKFLOW']");
	
	private static final By NO_MORE_CORDAPPS_CHECK_LOCATOR = By.xpath("//span[text()  = 'No more CorDapps']");
	
	private static final By CORDAPPS_ASSIGNED_CHECK_LOCATOR = By.xpath("//h4[text() = 'CorDapps']");
	
	private static final By WORKFLOWS_CORDAPPS_ASSIGNED_CHECK_LOCATOR = By.xpath("//p[text() = 'Token SDK Workflows']");
	private static final By REQUEST_DEPLOYMENT_LOCATOR = By.xpath("//span[text() = 'Request Deploy']/..");
	private static final By REQUEST_DEPLOYMENT_CONFIRM_LOCATOR = By.xpath("//div[text() = 'Send Request for Deployment']");
	private static final By CONFIRM_AND_SEND_BUTTON_LOCATOR = By.xpath("//span[text() = 'Confirm and Send']/..");
	
	private static final By CONFIRM_SUBMISSION_LOCATOR = By.xpath("//div[text() = 'Submitted']");
	private static final By SHUTDOWN_SUBMISSION_MESSAGE_LOCATOR = By.xpath("//*[name()='svg' and @class ='h-5-1/2 text-blue cursor-pointer']");
	
	private static final By PENDING_DEPLOYMENT_NODE_HEADER_LOCATOR = By.xpath("//span[text() = 'PENDING DEPLOYMENT']");

	public boolean isApplicationDeploymentHeaderVisible() {
		return isElementVisible(APPLICATION_DEPLOYMENT_HEADER_LOCATOR);
	}

	public boolean isCordappVisible() {
		System.out.println("Vendor :" + getText(VENDOR_NAME_LOCATOR));;
		if( getText(VENDOR_NAME_LOCATOR).equalsIgnoreCase("R3"))
				return true;
		return false;
	}

	public void goToCorDappsTab() {
		clickOnButton(CORDAPPS_TAB_LOCATOR);
		
	}

	public void clickR3VendorToCordappList() {
		clickOnButton(VENDOR_R3_BUTTON_LOCATOR);
		
	}

	public boolean isTokenSDKWorkFlowVisible() {
		return isElementVisible(WORKFLOWS_CORDAPP_LOCATOR);
	}

	public void clickTokenSdkWorkflows() {
		clickOnButton(WORKFLOWS_CORDAPP_LOCATOR);
		
	}

	public boolean isBackToAppCommandVisible() {
		return isElementVisible(BACK_TO_APP_CHECK_LOCATOR);
	}

	public String getAppsTitle() {
		return getText(APPS_TABLE_TITLE_LOCATOR);
	}

	public boolean isAddButtonEnabled() {
	
		return isElementEnabled(ADD_BUTTON_LOCATOR);
	}

	public void selectCordappToBeDeployed() {
		clickOnButton(WORKFLOW_APP_LOCATOR);
		
	}

	public void addCordappToBeDeployed() {
		clickOnButton(ADD_BUTTON_LOCATOR);
		
	}

	public boolean isLHSShowingNoMoreCordapps() {
		return isElementVisible(NO_MORE_CORDAPPS_CHECK_LOCATOR);
	}

	public boolean isRHSShowingCordapps() {
		return isElementVisible(CORDAPPS_ASSIGNED_CHECK_LOCATOR);
		
	}

	public boolean isRHSShowingCordappsAssigned() {
		return isElementVisible(WORKFLOWS_CORDAPPS_ASSIGNED_CHECK_LOCATOR);
	}

	public void clickRequestButton() {
		clickOnButton(REQUEST_DEPLOYMENT_LOCATOR);
		
	}

	public boolean isRequestPopupShown() {
		return isElementVisible(REQUEST_DEPLOYMENT_CONFIRM_LOCATOR);
	}

	public void clickConfirmAndSend() {
		clickOnButton(CONFIRM_AND_SEND_BUTTON_LOCATOR);
		
	}

	public boolean isSubmitted() {
		return isElementVisible(CONFIRM_SUBMISSION_LOCATOR);
	}

	public void shutdownSubmissionWindow() {
		clickOnButton(SHUTDOWN_SUBMISSION_MESSAGE_LOCATOR);
		
	}

	public boolean isPendingDeploymentMessageShown() {
		return isElementVisible(PENDING_DEPLOYMENT_NODE_HEADER_LOCATOR);
	}


}
