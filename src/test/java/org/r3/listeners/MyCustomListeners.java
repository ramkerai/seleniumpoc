package org.r3.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.r3.seleniumpoc.seleniumpoc.browser.utility.BrowserUtility;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class MyCustomListeners implements ITestListener {
	
	private ExtentHtmlReporter extentHtmlReporter;
	private ExtentReports extentReport;
	private ExtentTest extentTest;

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest("Test Name " + result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.pass("Test has passed " + result.getMethod().getDescription());
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.fail("Test has failed " + result.getMethod().getDescription());
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		BrowserUtility.takeScreenShotForPage(result.getMethod().getMethodName()+ timeStamp);
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.skip("Test has skipped " + result.getMethod().getDescription());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		Date date = new Date();
		SimpleDateFormat myDateFormat = new SimpleDateFormat("dd-MM-YYYY-HH-mm-SS");
		String stringDate = myDateFormat.format(date);
		String reportName = "TestReport-"+stringDate+".html";
		extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "//reports//"+ reportName);
		extentReport = new ExtentReports();
		extentReport.attachReporter(extentHtmlReporter);
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();  //dumps data
		
	}

}
