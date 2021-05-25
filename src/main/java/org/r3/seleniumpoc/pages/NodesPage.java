package org.r3.seleniumpoc.pages;

import org.openqa.selenium.By;
import org.r3.seleniumpoc.seleniumpoc.browser.utility.BrowserUtility;

public class NodesPage extends BrowserUtility {
	
	private static final By NODE_TITLE_LOCATOR = By.xpath("//div[text() = 'Nodes']");
	private static final By CREATE_NODE_BUTTON_LOCATOR = By.xpath("//span[text() = 'Create Node']/..");
	private static final By NODE_NAME_INPUT_BOX_LOCATOR = By.id("nodesName");
	private static final By NODE_NAME_DESC_INPUT_BOX_LOCATOR = By.id("nodeDescription");
	private static final By CREATE_NEW_NODE_BUTTON_LOCATOR =By.xpath("//button[contains(@class, 'create-new-node-btn')]");
	
	

	
	
	public boolean verifyNodePageTitleIsVisible() {
		return isElementVisible(NODE_TITLE_LOCATOR);
	}

	public void createNewNode(String nodeName, String nodeDesc) throws InterruptedException {
		clickOnButton(CREATE_NODE_BUTTON_LOCATOR);
		enterText(NODE_NAME_INPUT_BOX_LOCATOR, nodeName);
		enterText(NODE_NAME_DESC_INPUT_BOX_LOCATOR, nodeDesc);
		if( isElementVisible(CREATE_NEW_NODE_BUTTON_LOCATOR))
		{
			Thread.sleep(100);  //good to have this as its so quick so didnt see the pop-up. 
			//even without it it worked.
			clickOnButton(CREATE_NEW_NODE_BUTTON_LOCATOR);

		}
		
	}
	
	public boolean isNewNodeVisible(String nodeName) {
		By NODES_PAGE_LOCATOR = By.xpath("//h6[text() = \'" +nodeName + "\']");
		return isElementVisible(NODES_PAGE_LOCATOR);
	}
	
	public MyNodePage gotoMyNodePage(String nodeName) {
		By NODES_PAGE_LOCATOR = By.xpath("//h6[text() = \'" +nodeName + "\']");
		clickOnButton(NODES_PAGE_LOCATOR);
		
		return new MyNodePage();
	}

	public boolean isMyNodeVisible(String nodeName) {
		By NODES_HEADER_LOCATOR = By.xpath("//div[text() = \'" +nodeName + "\']");
		System.out.println("header " + getText(NODES_HEADER_LOCATOR));
		return isElementVisible(NODES_HEADER_LOCATOR);
	}
	
}
