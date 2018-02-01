package Event;

import javax.xml.crypto.dsig.SignedInfo;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import Element.element;
import Utility.Utility;

public class event {

	
	element ele=new element();
	 static  WebDriver driver;

	@BeforeClass
	
	public void initElement(){
		Utility.openBrowser();
		Utility.setUrl();
		PageFactory.initElements(driver, ele);
	}
	
	
	@Test
	
	public void firstTest(){
		
	}
}
