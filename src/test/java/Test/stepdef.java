package Test;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class stepdef {
	
	@Given("^test url as input$")

	public void passUrl(){
		System.out.println("Url passed");
	}
	
	@When("^logged in shows home page$")
	
	public void loggedIn(){
		System.out.println("logged in to Home page");
	}
	
	@And("^enter Id and Password$")
	
	public void authentication(){
		
		System.out.println("Pass Id password");
	}
	
	@Then("^Display logged in successfully$")
	
	public void closePage(){
		
		System.out.println("Close the Page.....");
	}
}
