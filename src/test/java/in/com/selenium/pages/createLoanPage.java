package in.com.selenium.pages;

import org.testng.annotations.Test;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import in.com.selenium.utiliy.CoreBrowser;

public class createLoanPage extends CoreBrowser{

	
	@Test (priority = 1,enabled=true)
	public void CreateLoan(){
		
		CoreBrowser.browser();
		ExtentTest logger = extent.createTest("Create Loan");
		logger.log(Status.PASS, "Browser Launched");
		
		CoreBrowser.setUrl();
		logger.log(Status.PASS, "URL Launched");
	}
	
	@Test (priority = 2,enabled=true)
	public void trackLoan(){
		
		CoreBrowser.browser();
		ExtentTest logger = extent.createTest("Track Loan");
		logger.log(Status.PASS, "Browser Launched");
		
		CoreBrowser.setUrl();
		logger.log(Status.PASS, "URL Launched");
	}

	@Test (priority = 3,enabled=true)
	public void updateBrokerDetails(){
		
		CoreBrowser.browser();
		ExtentTest logger = extent.createTest("Update Broker Details");
		logger.log(Status.PASS, "Browser Launched");
		
		CoreBrowser.setUrl();
		logger.log(Status.PASS, "URL Launched");
	}
}
