package org.r3.seleniumpoc.seleniumpoc.browser.utility;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchBrowser {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		driver.get("https://portal.v1-0-1.dev.test.r3ms.io/");
		String username = "ramji_admin";

		// username locator
		By usernameLocator = By.id("username");
		WebElement usernameElement = driver.findElement(usernameLocator);
		usernameElement.sendKeys(username);

		// password locator
		By passwordLocator = By.xpath("//input[@type = \"password\"]");
		WebElement passwordElement = driver.findElement(passwordLocator);
		passwordElement.sendKeys("Password1!");

		// By submitLocator = By.xpath("//span[text() = \"Forgot Password?\"]/../.");
		// //this works for forgot password
		By submitLocator = By.xpath("//span[text() = \"Submit\"]/../..");
		WebElement submitElement = driver.findElement(submitLocator);
			Thread.sleep(1000);
		//wait1.until(ExpectedConditions.elementToBeClickable(submitLocator)).click();
		submitElement.click();
		Thread.sleep(1000);
		submitElement.click();
		
		By LoggedInSuccessfulLocator = By.xpath("//div[text() = \"Organizations\"]");
		WebElement LoggedInSuccessfulElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(LoggedInSuccessfulLocator));
		System.out.println(LoggedInSuccessfulElement.getText());
		//Assert(LoggedInSuccessfulElement.getText() == username);
		By LoggedInSuccessfulLocator2 = By.xpath("//span[@class = \"relative button-tertiary-text\"]");
		LoggedInSuccessfulElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(LoggedInSuccessfulLocator2));
		System.out.println(LoggedInSuccessfulElement.getText());

				

	}

}
