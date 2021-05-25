package org.r3.seleniumpoc.seleniumpoc.browser.utility;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.r3.seleniumpoc.utility.TestUtility;

import com.aventstack.extentreports.utils.FileUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtility {
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	
	public void launchBrowser(BrowserName name) {
		if (name == BrowserName.CHROME) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			long TIMEOUT = Long.parseLong(TestUtility.readProperty("config.properties", "TIMEOUT_SECONDS"));
			wait = new WebDriverWait(driver, TIMEOUT); // Instantiated
														// the
														// WebDriverWait
			// WebDriverWait w1= new WebDriverWait(driver, 40, 100);

		} else if (name == BrowserName.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			wait = new WebDriverWait(driver, 40); // Instantiated the WebDriverWait

		} else if (name == BrowserName.IE) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			wait = new WebDriverWait(driver, 40); // Instantiated the WebDriverWait

		}

		else {
			System.err.print("Invalid Browser Option can Support CHROME or FIREFOX or IE ");
		}

	}

	public void goToPage(String url) {
		try {
			driver.get(url);
			driver.manage().window().maximize();
		} catch (NullPointerException e) {
			System.err.print("Cannot go to " + url + " (Launch Browser Method needs to be the first method! ");
		}
	}
	
	
	public void closeBrowser() {
		driver.close();
	}
	
	public void enterText(By elementLocator, String textToEnter) {
		try {
			// WebElement emailElement = driver.findElement(elementLocator);

			WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
			emailElement.sendKeys(textToEnter);
		}

		catch (NullPointerException e) {
			System.err.print(" (Launch Browser Method needs to be the first method! ");
		}

		catch (NoSuchElementException e) {
			System.err.print("Check your locator Element Not found");

		}
	}

	
	public void enterTextNoWait(By elementLocator, String textToEnter) {
		WebElement emailElement = driver.findElement(elementLocator);
		emailElement.sendKeys(textToEnter);

	}
	
	public void clickOnButton(By elementLocator) {
		try {
			//WebElement element = driver.findElement(elementLocator);
			WebDriverWait wait2 = new WebDriverWait(driver, 30);
			WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(elementLocator));
			element.click();
		}

		catch (NullPointerException e) {
			System.err.print(" (Launch Browser Method needs to be the first method! ");
		}

		catch (NoSuchElementException e) {
			System.err.print("Check your locator Element Not found");

		}
	}

	
	public boolean isElementVisible(By elementLocator) {
		
		if(wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator))!= null)

			return true;

		else
			return false;
	}
	
	public boolean isElementEnabled( By elementLocator) {
		WebElement element = driver.findElement(elementLocator);
		boolean isEnabled = element.isEnabled();
		
		return isEnabled;
	}
	
	public String getText( By elementLocator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		String value = element.getText();
		return value;
	}

	public String getTextFromElement(By elementLocator) {
		WebElement element = driver.findElement(elementLocator);
		String text = element.getText();
		return text;
	}
	
	
	public List<WebElement> getAllElements(By elementLocator) {
		
		//List<WebElement> val = elementLocator.findElements(driver);
		List<WebElement> elementList = driver.findElements(elementLocator);
		System.out.println("number of element" + elementList.size());
		
		return elementList;
	}
	
	
	public void selectDropDownByValue( By dropdownLocator, String value) {
		
		WebElement selectLocatorElement = driver.findElement(dropdownLocator);
		Select select = new Select(selectLocatorElement);
		select.selectByValue(value);
	}
	
	public void selectDropDownByVisibleText( By dropdownLocator, String value) {
		
		WebElement selectLocatorElement = driver.findElement(dropdownLocator);
		Select select = new Select(selectLocatorElement);
		select.selectByVisibleText(value);
	}
	
	public String getAttributeValue(By locator, String attributeName) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		String result = element.getAttribute(attributeName);
		return result;
	}
	
	
	public static void takeScreenShotForPage( String testName) {
		TakesScreenshot screenshot= (TakesScreenshot) driver;
		File f = screenshot.getScreenshotAs(OutputType.FILE);
		File myFile = new File( System.getProperty("user.dir") + "//screenshot//" + testName + ".png");
		
		try {
			FileUtils.copyFile(f, myFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}
	

}
