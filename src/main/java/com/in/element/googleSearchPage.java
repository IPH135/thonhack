package com.in.element;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.in.Utility.Util;
import com.in.Utility.driver;

public class googleSearchPage {
	
WebDriver d;

public void openBrowser(){
	
 WebElement we=d.findElement(By.xpath("//*[@id='q']"));
 we.sendKeys(Keys.ENTER);
	
}
}
