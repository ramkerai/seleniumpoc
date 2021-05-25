package org.r3.seleniumpoc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Runner {
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		driver.get("https://portal.v1-0-1.dev.test.r3ms.io/");
		LoginPage loginPage = new LoginPage();
		OrganizationsPage landingPage = loginPage.submitLogin("ramji_admin", "Password1!");
		
		
	}

}
