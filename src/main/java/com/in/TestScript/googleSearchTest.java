package com.in.TestScript;

import org.testng.annotations.Test;

import com.in.events.googleSearchEvent;

public class googleSearchTest extends googleSearchEvent{
	

	@Test (priority=0,enabled=true,groups={"smoke"})
	
	public void searchGoogle(){
		
      doOperation();		
	}

}
