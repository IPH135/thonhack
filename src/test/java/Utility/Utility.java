package Utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

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
	
	public void takescreenshot() throws IOException{
		File ss=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(ss, new File(config.testdata+"ss"+".png"));
	}
}