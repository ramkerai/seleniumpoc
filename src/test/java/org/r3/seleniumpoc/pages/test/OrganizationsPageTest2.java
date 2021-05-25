package org.r3.seleniumpoc.pages.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import javax.print.attribute.standard.OrientationRequested;

import org.openqa.selenium.By;
import org.r3.seleniumpoc.pages.ApplicationsPage;
import org.r3.seleniumpoc.pages.LoginPage;
import org.r3.seleniumpoc.pages.MyOrganisationPage;
import org.r3.seleniumpoc.pages.OrganizationsPage;
import org.r3.seleniumpoc.seleniumpoc.browser.utility.BrowserName;
import org.r3.seleniumpoc.utility.TestUtility;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OrganizationsPageTest2 {
	
	
	LoginPage loginPage;
	OrganizationsPage organizationPage;
	MyOrganisationPage myOrg;
	ApplicationsPage applicationsPage;
	String orgName;
	
	@BeforeClass
	public void preTest() throws InterruptedException {
		loginPage = new LoginPage();
		loginPage.launchBrowser(BrowserName.CHROME);
		loginPage.goToPage(TestUtility.readProperty("config.properties", "URL"));
		String username= TestUtility.readProperty("config.properties", "USERNAME");
		String password= TestUtility.readProperty("config.properties", "PASSWORD");
	    organizationPage = loginPage.submitLogin(username, password);
	    int uniqueId  = (int)(Math.random() * ( 1000 - 465 + 1) + 465);
	    orgName = "Auto Test Org " + uniqueId;
		
	}

	@Test(priority = 0,testName = "AddNewOrganization", description =  "verify if new organization has been added")
	public void addNewOrganization() throws InterruptedException {
		
		organizationPage.CreateNewOrganization(orgName, orgName);
		assertTrue(organizationPage.isOrganizationAdded(orgName));
		
		
	}
	
	
	@Test(priority = 1  , testName = "Go to Organization", description = "Check we are able to navigate to specific organization" )
	public void goToSpecifiedOrganizations() {
		myOrg = organizationPage.goToOrganisation(orgName);
		assertEquals(myOrg.getOrganizationNameTopRightCorner(), orgName);
		
	}
	
	@Test( enabled=false,testName = "Go to Organization", description = "Check we are able to navigate to specific organization")
	public void addNewCordappForOrganization() {
		//myOrg = organizationPage.goToOrganisation(orgName);
		myOrg.goToOrganizationManagementTab();  //just a sample test to check we can click tabs
		
		applicationsPage = myOrg.goToApplicationsMenu();
		assertEquals(applicationsPage.getApplicationPageHeader(), "Applications");
		
		String path  = System.getProperty("user.dir") + "\\resources\\" + "tokens-workflows-1.0.jar";
		System.out.println("Path: " + path);
		String cordaapName = "Auto Test cordapp 1";
		String config = "x=y";
		
		applicationsPage.addNewCordapp(cordaapName, config, path);
		//verify cordapp added - will add later on


	}
	
	
	@Test(enabled=false, testName = "Go to Organization", description = "Check we are able to navigate to specific organization")
	public void AssignCordAppToIdentity() {
		//Assume identity is created
		organizationPage.goToOrganisation("Ramji org 3");
		By NODES_MENU_LOCATOR = By.xpath("//a[@href=\"/nodes\"]");	
		organizationPage.clickOnButton(NODES_MENU_LOCATOR);
		By NODE_TITLE_LOCATOR = By.xpath("//div[text() = 'Nodes']");
		//organizationPage.isElementVisible(NODE_TITLE_LOCATOR);
		assertTrue( organizationPage.isElementVisible(NODE_TITLE_LOCATOR));
		
		String nodeName = "Test Node 2";
		//click on node to see we on node page:
		//By NODES_PAGE_LOCATOR = By.xpath("//h6[text() = 'Test Node']")	;
		 By NODES_PAGE_LOCATOR = By.xpath("//h6[text() = \'" +nodeName + "\']");
		organizationPage.clickOnButton(NODES_PAGE_LOCATOR);
		
		//search for Test Node. Could be parameterized
		//By NODES_HEADER_LOCATOR = By.xpath("//div[text() = 'Test Node']");
		By NODES_HEADER_LOCATOR = By.xpath("//div[text() = \'" +nodeName + "\']");
		assertTrue( organizationPage.isElementVisible(NODES_HEADER_LOCATOR));
		System.out.println("header " + organizationPage.getText(NODES_HEADER_LOCATOR));
		//check we on specific node page
		
		//###########Add new identity##################
		

		By CREATE_IDENTITY_BUTTON_LOCATOR = By.xpath("//div[@data-tooltip = 'Create Identity']/../..");
		By ORG_NAME_TEXT_BOX_LOCATOR = By.id("OrganizationName");
		By ORG_UNIT_TEXT_BOX_LOCATOR = By.id("organizationalUnit");
		By LOCALITY_TEXT_BOX_LOCATOR = By.id("locality");
		//value = 'GB'  visible = 'GB: United Kingdom'
		By COUNTRY_DROPDOWN_LOCATOR = By.xpath("//span[text() = 'Country']/../preceding-sibling::select");
		By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
		
		//value = 'GB'  visible = 'MS Network'
		By NETWORK_DROPDOWN_LOCATOR = By.xpath("//span[text() = 'Network']/../preceding-sibling::select");
		
		
		By CREATE_NEW_IDENTITY_BUTTON_LOCATOR = By.xpath("//span[text() = 'Create New Identity']/..");
		
		//enter identities#############
		organizationPage.clickOnButton(CREATE_IDENTITY_BUTTON_LOCATOR);
		organizationPage.enterText(ORG_NAME_TEXT_BOX_LOCATOR, "Test Org");
		organizationPage.enterText(ORG_UNIT_TEXT_BOX_LOCATOR, "Test Org");
		organizationPage.enterText(LOCALITY_TEXT_BOX_LOCATOR, "London");
		organizationPage.selectDropDownByValue(COUNTRY_DROPDOWN_LOCATOR, "GB");
		organizationPage.enterText(EMAIL_TEXT_BOX_LOCATOR, "test@gmail.com");
		organizationPage.selectDropDownByVisibleText(NETWORK_DROPDOWN_LOCATOR, "MS Network");
		
		assertTrue( organizationPage.isElementVisible(CREATE_NEW_IDENTITY_BUTTON_LOCATOR));
		
		
		//########
		
				
		//##########identity completed.####################
		
		//Add application. click it
		/*
		By ADD_APPLICATION_BUTTON_LOCATOR = By.xpath("//div[@data-tooltip = 'Add Applications']/../..");
		organizationPage.clickOnButton(ADD_APPLICATION_BUTTON_LOCATOR);
		
		//check we on Application Deployment for Test Node
		By APPLICATION_DEPLOYMENT_HEADER_LOCATOR = By.xpath("//span[contains(text(), 'Application Deployment')]");
		assertTrue( organizationPage.isElementVisible(APPLICATION_DEPLOYMENT_HEADER_LOCATOR));
		
		By CORDAPPS_TAB_LOCATOR = By.xpath("//div[text() = 'CorDapps']");
		organizationPage.clickOnButton(CORDAPPS_TAB_LOCATOR);
		//check cordapps present
		By VENDOR_NAME_LOCATOR = By.xpath("//span[@class = 'vendor-title']");
		System.out.println("Vendor :" + organizationPage.getText(VENDOR_NAME_LOCATOR));;
		assertEquals(organizationPage.getText(VENDOR_NAME_LOCATOR),"R3");
		
		//click Vendor R2 button:
		
		By VENDOR_R3_BUTTON_LOCATOR = By.xpath("//span[text() = 'R3']/..");
		organizationPage.clickOnButton(VENDOR_R3_BUTTON_LOCATOR);
		
		//CANT check list so select the sdk workflows app
		
		By WORKFLOWS_CORDAPP_LOCATOR = By.xpath("//span[text() = 'Token SDK Workflows']");
		organizationPage.clickOnButton(WORKFLOWS_CORDAPP_LOCATOR);
		
		//check we move to different page that shows Token SDK Workflows by checking 'back to app' comamnd is shown
		
		By BACK_TO_APP_CHECK_LOCATOR = By.xpath("//span[text() = 'Back to Apps']");
		
		By APPS_TABLE_TITLE_LOCATOR = By.xpath("//h5[@class = 'table-title']");
		assertEquals(organizationPage.getText(APPS_TABLE_TITLE_LOCATOR),"Token SDK Workflows");
		//check add button is disabled
		
		By ADD_BUTTON_LOCATOR = By.xpath("//span[text() = \"Add\"]/..");
		//isElementEnabled
		assertEquals(organizationPage.isElementEnabled(ADD_BUTTON_LOCATOR), false);
		
		
		//the select and check its selected and check add is enabled.NOT GOOD as no way to identify
		By WORKFLOW_APP_LOCATOR = By.xpath("//span[text()  = 'WORKFLOW']");
		organizationPage.clickOnButton(WORKFLOW_APP_LOCATOR);
		//check add button is now enabled
		assertEquals(organizationPage.isElementEnabled(ADD_BUTTON_LOCATOR), true);
		
		//click add
		
		organizationPage.clickOnButton(ADD_BUTTON_LOCATOR);
		
		//check left side is empry by checking left side with "No more CorDapps"
		
		By NO_MORE_CORDAPPS_CHECK_LOCATOR = By.xpath("//span[text()  = 'No more CorDapps']");
		
		assertTrue( organizationPage.isElementVisible(NO_MORE_CORDAPPS_CHECK_LOCATOR));
		
		//check RHS to see cordapp has been assigned
		
		By CORDAPPS_ASSIGNED_CHECK_LOCATOR = By.xpath("//h4[text() = 'CorDapps']");
		assertTrue( organizationPage.isElementVisible(CORDAPPS_ASSIGNED_CHECK_LOCATOR));
		
		By WORKFLOWS_CORDAPPS_ASSIGNED_CHECK_LOCATOR = By.xpath("//p[text() = 'Token SDK Workflows']");
		assertTrue( organizationPage.isElementVisible(WORKFLOWS_CORDAPPS_ASSIGNED_CHECK_LOCATOR));
		
		
		
		By REQUEST_DEPLOYMENT_LOCATOR = By.xpath("//span[text() = 'Request Deploy']/..");
		organizationPage.clickOnButton(REQUEST_DEPLOYMENT_LOCATOR);
		
		
		//confirm pop-up page
		By REQUEST_DEPLOYMENT_CONFIRM_LOCATOR = By.xpath("//div[text() = 'Send Request for Deployment']");
		assertTrue( organizationPage.isElementVisible(REQUEST_DEPLOYMENT_CONFIRM_LOCATOR));

		
		By CONFIRM_AND_SEND_BUTTON_LOCATOR = By.xpath("//span[text() = 'Confirm and Send']/..");
		
		organizationPage.clickOnButton(CONFIRM_AND_SEND_BUTTON_LOCATOR);
		
		//check submission
		
		By CONFIRM_SUBMISSION_LOCATOR = By.xpath("//div[text() = 'Submitted']");
		assertTrue( organizationPage.isElementVisible(CONFIRM_SUBMISSION_LOCATOR));
		
		
		
		By SHUTDOWN_SUBMISSION_MESSAGE_LOCATOR = By.xpath("//*[name()='svg' and @class ='h-5-1/2 text-blue cursor-pointer']");
		organizationPage.clickOnButton(SHUTDOWN_SUBMISSION_MESSAGE_LOCATOR);
		
		//check main page shows "Pendinf deployment"
		
		By PENDING_DEPLOYMENT_NODE_HEADER_LOCATOR = By.xpath("//span[text() = 'PENDING DEPLOYMENT']");
		assertTrue( organizationPage.isElementVisible(PENDING_DEPLOYMENT_NODE_HEADER_LOCATOR));
		
		*/
		
		//assigne cordapp
		//1. select coradpp
		//2. check add button is enabled
		//cick add
		//check its added in deployment section.
		//Then click deployment
		//
		
		
	}
}
