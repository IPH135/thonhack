package com.in.events;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.in.Utility.Util;
import com.relevantcodes.extentreports.LogStatus;

public class googleSearchEvent extends Util{
	

	public   void doOperation(){
		
		test=report.startTest("Google Opned");
		Util.openBrowser();
		Util.setBrowser();
		
		test.log(LogStatus.PASS, "Title displayed");
		
		String title=d.getTitle();
		
	System.out.println(title);
	
	
	
	assertEquals(title, "gogle");
	
	test.log(LogStatus.FAIL, "Title displayed");
	}

}
