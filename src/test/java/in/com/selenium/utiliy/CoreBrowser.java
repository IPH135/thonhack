package in.com.selenium.utiliy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class CoreBrowser {
	
	protected static ExtentHtmlReporter htmlReporter;
	protected static ExtentReports extent;
	protected static ExtentTest logger;

	public static WebDriver driver;

	@BeforeTest
	public void startReport(){
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/src/test/java/in/com/selenium/Reports/AutomationRepot.html");
		System.out.println(System.getProperty("user.dir") +"/src/test/java/in/com/selenium/Reports/AutomationRepot.html");
		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("Environment", config.url);

		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("Automation Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	@AfterSuite
	public void getResult(){
		extent.flush();
	}

	@AfterTest
	public void Close() throws Exception{
		CoreBrowser.closeUrl();
	}

	public static void browser() {
		String path = System.getProperty("user.dir");
		String browser = config.browser;
		switch (browser) {
		case "chrome":

			System.setProperty("webdriver.chrome.driver", path + "\\src\\test\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "firefox":

			System.setProperty("webdriver.gecko.driver", path + "\\fox\\geckodriver");
			driver = new FirefoxDriver();
			break;
		}
	}
	
	public static void setUrl(){
		driver.navigate().to(config.url);
		
	}
	
	public static void closeUrl() throws Exception{
		Thread.sleep(1000);
		driver.close();
	}

}
