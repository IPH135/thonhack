package com.in.element;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.in.Utility.Util;
import com.in.Utility.driver;

public class googleSearchPage {
	
WebDriver d;

@FindBy(xpath="//*[@id='q']")
private WebElement searchBox;

public void openBrowser(){
	
WebElement we=searchBox;	
we.sendKeys(Keys.ENTER);
	
}
}
