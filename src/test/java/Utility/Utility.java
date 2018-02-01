package Utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Utility {
	
	static WebDriver d;
	
	static ExtentReports report;
	static ExtentTest test;
	
	
	public void openBrowser(){
		
		//String default=System.setProperties("user.dir");
		
		String br=config.brws;
		
		switch (br){
		
		case "chrome":
			System.getProperty("webdriver.chrome.driver", "D:/Users/MyWorkspace/exe/src/test/resources/chrome/chromedriver.exe");
			d=new ChromeDriver();
			break;
		case "firefox":
			System.getProperty("webdriver.gecko.driver", "D:/Users/MyWorkspace/exe/src/test/resources/chrome/chromedriver.exe" );
			d=new FirefoxDriver();
			break;
			
	}}

	public void setUrl()
	    {
			d.get(config.url);
			
		}
		
	public void closebrws(){
			d.close();}
	
	public String takescreenshot(WebDriver dr,String screenname) throws IOException{
		String path=config.testdata+screenname+".png";
		File ss=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(ss, new File(path));
		return path;
	}
	
	@BeforeTest
	
	public static void generateReport(){
		report=new ExtentReports("D:/Users/MyWorkspace/exe/src/test/java/report");
	}

	@AfterMethod
	public void failedRepo(ITestResult result) throws Exception{
		
		if(result.getStatus()==ITestResult.FAILURE){
			
		String imagePath=takescreenshot( d,"failed");
		test.log(LogStatus.FAIL, result.getThrowable());
		test.log(LogStatus.FAIL, "failed here", test.addScreenCapture(imagePath));
	}}
}