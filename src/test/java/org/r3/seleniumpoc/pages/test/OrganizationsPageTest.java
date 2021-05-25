package org.r3.seleniumpoc.pages.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import javax.print.attribute.standard.OrientationRequested;

import org.openqa.selenium.By;
import org.r3.seleniumpoc.pages.ApplicationDeploymentPage;
import org.r3.seleniumpoc.pages.ApplicationsPage;
import org.r3.seleniumpoc.pages.LoginPage;
import org.r3.seleniumpoc.pages.MyNodePage;
import org.r3.seleniumpoc.pages.MyOrganisationPage;
import org.r3.seleniumpoc.pages.NodesPage;
import org.r3.seleniumpoc.pages.OrganizationsPage;
import org.r3.seleniumpoc.seleniumpoc.browser.utility.BrowserName;
import org.r3.seleniumpoc.utility.TestUtility;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OrganizationsPageTest {
	
	
	LoginPage loginPage;
	OrganizationsPage organizationPage;
	MyOrganisationPage myOrg;
	ApplicationsPage applicationsPage;
	NodesPage nodesPage;
	MyNodePage myNodePage;
	ApplicationDeploymentPage applicationDeploymentPage;
	String orgName;
	int uniqueId;
	
	@BeforeClass
	public void preTest() throws InterruptedException {
		loginPage = new LoginPage();
		loginPage.launchBrowser(BrowserName.CHROME);
		loginPage.goToPage(TestUtility.readProperty("config.properties", "URL"));
		String username= TestUtility.readProperty("config.properties", "USERNAME");
		String password= TestUtility.readProperty("config.properties", "PASSWORD");
	    organizationPage = loginPage.submitLogin(username, password);
	    uniqueId  = (int)(Math.random() * ( 1000 - 465 + 1) + 465);
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
	
	@Test( priority =2 ,enabled=true,testName = "Add new cordapp", description = "Check we are able to add new cordapp")
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
		 assertTrue(applicationsPage.isCordappVisible());


	}
	
	
	@Test(priority =3, enabled=true, testName = "Create New Node", description = "Check we are able to create new node and the go into that node")
	public void createNewNode() throws InterruptedException {
		
		//from myOrganisationOage go to nodes
		nodesPage = myOrg.goToNodesPage();
		assertTrue(nodesPage.verifyNodePageTitleIsVisible());
		//creat new node
		String nodeName = "Auto Test Node 1";
		String nodeDesc = "Auto Test Node Desc";
		nodesPage.createNewNode(nodeName, nodeDesc);
		//check node has been added
		
		assertTrue( nodesPage.isNewNodeVisible(nodeName));

		myNodePage = nodesPage.gotoMyNodePage(nodeName);
		assertTrue( nodesPage.isMyNodeVisible(nodeName));
	
		
	}
	
	@Test(priority =4, enabled=true, testName = "Create New Node", description = "Check we are able to create new node and the go into that node")
	public void createNewIdentityForNode() throws InterruptedException {
		
		//###########Add new identity##################
		
		
		String orgName = "Auto Test Identity";
		String orgUnit = "Auto Test Identity " + uniqueId;
		String locality = "London";
		String country = "GB";
		String email = "test@gmail.com";
		String network = "MS Network";
		myNodePage.createNewIdentity(orgName, orgUnit, locality, country, email, network);
		//enter identities#############
		
		//soon as idenity is added create identity button is not visible. Check to see if identity is added
		///THIS FAILS .should use another indicator
		assertTrue( myNodePage.isCreateIdentityButtonNotVisible());
		
		
	}
	
	@Test(priority =5, enabled=true, testName = "Create New Node", description = "Check we are able to create new node and the go into that node")
	public void deployNode() {
				
		//##########identity completed.####################
		
		applicationDeploymentPage = myNodePage.addApplications();
		//check we on Application Deployment for Test Node
		assertTrue(applicationDeploymentPage.isApplicationDeploymentHeaderVisible());
		applicationDeploymentPage.goToCorDappsTab();
		//check a cordapp is present
		assertTrue(applicationDeploymentPage.isCordappVisible());
		
		//click Vendor R2 button:
		applicationDeploymentPage.clickR3VendorToCordappList();
		//check for TOKEN SDK WORKFLOWS
		assertTrue(applicationDeploymentPage.isTokenSDKWorkFlowVisible());
		
		
		//CANT check list so select the sdk workflows app
		applicationDeploymentPage.clickTokenSdkWorkflows();
		
		//check we move to different page that shows Token SDK Workflows by checking 'back to app' comamnd is shown
		assertTrue(applicationDeploymentPage.isBackToAppCommandVisible());
		assertEquals(applicationDeploymentPage.getAppsTitle(), "Token SDK Workflows");
		
		//check add button is disabled as no cordapp is selected
		assertFalse(applicationDeploymentPage.isAddButtonEnabled());
		
		//then select and check its selected and check add is enabled.NOT GOOD as no way to identify
		applicationDeploymentPage.selectCordappToBeDeployed();
		//check add button is now enabled
		assertTrue(applicationDeploymentPage.isAddButtonEnabled());
		
		
		//click add
		applicationDeploymentPage.addCordappToBeDeployed();
		
		
		
		//check left side is empry by checking left side with "No more CorDapps"
		
		assertTrue(applicationDeploymentPage.isLHSShowingNoMoreCordapps());
		
		
		//check RHS to see cordapp has been assigned
		assertTrue(applicationDeploymentPage.isRHSShowingCordapps());
		//check RHS to see cordapp assigned is shown as 'Token SDK Workflows'
		assertTrue(applicationDeploymentPage.isRHSShowingCordappsAssigned());
		
		//Click RequestDeploy
		
		applicationDeploymentPage.clickRequestButton();
		//confirm pop-up page
		assertTrue(applicationDeploymentPage.isRequestPopupShown());
		
		//click confirm and send
		applicationDeploymentPage.clickConfirmAndSend();
		
		//check submission
		
		assertTrue(applicationDeploymentPage.isSubmitted());
		//shutdown submission page
		applicationDeploymentPage.shutdownSubmissionWindow();
	
		
		//check main page shows "Pendinf deployment"
		assertTrue(applicationDeploymentPage.isPendingDeploymentMessageShown());
	

		
		
		//assigne cordapp
		//1. select coradpp
		//2. check add button is enabled
		//cick add
		//check its added in deployment section.
		//Then click deployment
		//
		
		
	}
}
