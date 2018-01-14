package com.in.Utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Util {
	
public static WebDriver d;
protected 	static ExtentReports report;
protected 	static ExtentTest    test;

public static String path=System.getProperty("user.dir");
	
	
	public static  void openBrowser(){
		String browsre=Config.brws;
		String path=System.getProperty("user.dir");
		
		switch(browsre){
		
		case "chrome":
			
			System.setProperty("webdriver.chrome.driver", path+"\\src\\main\\resources\\chromedriver.exe");
			d=new ChromeDriver();
			break;
		case "firefox":
			
			System.setProperty("webdriver.gecko.driver", path+"\\src\\main\\resources\\geckodriver.exe");
			d=new ChromeDriver();
			break;
			
		}
	}
	
	
	public static void setBrowser(){
		
		d.navigate().to(Config.url);
	}
	
	
	public static String screenshot(WebDriver d,String screenname) throws Exception{
		
	String pathScreen=path+"\\src\\main\\java\\com\\in\\reports\\screenshot\\"+"\\error\\"+screenname+".png";
		
		File ss=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(ss, new File(pathScreen));
		return pathScreen;
	}
	
	
	
	@BeforeTest
	
	public static void setReportPath(){
		report=new ExtentReports(path+"\\src\\main\\java\\com\\in\\reports\\screenshot\\aUTOMATION.html", true);
	}
	
	@AfterMethod
	
	public static void getResult(ITestResult result) throws Exception{
		
		if(result.getStatus()==ITestResult.FAILURE){
			
			String screenPath=screenshot(d, "Failed");
			test.log(LogStatus.FAIL, result.getThrowable());
			test.log(LogStatus.FAIL, "Not Matching", test.addScreenCapture(screenPath));
		}
	}
	
	@AfterTest
	
	public static void flushReport(){
		
	//	d.close();
        report.flush();
        report.close();
	}

}
