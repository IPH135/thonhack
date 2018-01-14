package com.in.Utility;

import java.util.Map;

import org.testng.annotations.BeforeClass;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;




public class RestTestBase {
	
	public RequestSpecBuilder reqSpecBuilder;
	public RequestSpecBuilder builder;
	public RequestSpecification reqSpec;
	
	
	 @BeforeClass(groups = { "Smoke"},enabled=false)
	  public  RequestSpecification PostSetRestAssuredConfig(String Postauth,String Posturl) {
	    logger.info("------- setRestAssuredConfig---------");
	    logger.info("----Building Request Specification for Post Call -----");
	    Map<String, String> PostReqHeaders = Utility.getHeaders("contentType:application/json,Authorization:"+getConfigManager().getConfig(Postauth));
	    requestSpecBuilder=new RequestSpecBuilder();
	    requestSpecBuilder = requestSpecBuilder.setBaseUri(getConfigManager().getConfig(Posturl));
	    requestSpecBuilder = requestSpecBuilder.addHeaders(PostReqHeaders);
	   return requestSpec = requestSpecBuilder.build();
	   }

}
