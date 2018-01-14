package com.in.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.log4testng.Logger;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Util {
	
public static WebDriver d;
protected 	static ExtentReports report;
protected 	static ExtentTest    test;

static Response  resp;
static File file;
static ArrayList<String> headersList;
static ArrayList<String> valuesList;
static ArrayList<ArrayList<String>> testData;
static XSSFWorkbook excelsheet;
static XSSFSheet sheet;
static XSSFRow row;
static XSSFCell col;
static List<String> list;
static List<String> value;
private static String String;

final static Logger logger = Logger.getLogger(Util.class);

public static String path=System.getProperty("user.dir");






/**
* This method is used to select and open a browser
*/

public static  void openBrowser(){
	String browsre=Config.brws;
//	String path=System.getProperty("user.dir");
	
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


/**
 * this method is used to set the Url into the browser 
 */

public static void setBrowser(){
	
	d.navigate().to(Config.url);
}

/**
 * This method is used to take screenshot and store into local folder
 * @param d
 * @param screenname
 * @return
 * @throws Exception
 */

public static String screenshot(WebDriver d,String screenname) throws Exception{
	
String pathScreen=path+Config.ScreenshotPath+"\\error\\"+screenname+".png";
	
	File ss=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
	
	FileUtils.copyFile(ss, new File(pathScreen));
	return pathScreen;
}


/**
 * method is used to set the report path
 */

@BeforeTest

public static void setReportPath(){
	report=new ExtentReports(path+Config.ReportPath, true);
}

/**
 * method is used to only set the failed Test case and screenshot
 * @param result
 * @throws Exception
 */

@AfterMethod

public static void getResult(ITestResult result) throws Exception{
	
	if(result.getStatus()==ITestResult.FAILURE){
		
		String screenPath=screenshot(d, result.getName());
		test.log(LogStatus.FAIL, result.getThrowable());
		test.log(LogStatus.FAIL, "Not Matching", test.addScreenCapture(screenPath));
	}
}


/**
 * method is run once for the test script and close the browser and flush the report 
 */

@AfterTest

public static void flushReport(){
	
	d.close();
    report.flush();
    report.close();
}



public static Map<String,String> getHeaders(String headers)
{
	 String headerArray[] = headers.split(",");
	 Map<String, String> mapHeaders = new HashMap<String, String>();
	 for(String header:headerArray)
	 {
		 mapHeaders.put(header.split(":")[0],header.split(":")[1]);

	 }
	 logger.info("Map Headers : " + mapHeaders);
	 return mapHeaders;
}


/**
 * @param reqParams
 * @return
 * This method is to create the request parameters with name:value pair
 */
public static Map<String,String> getRequestParameters(String reqParams)
{
	 String headerArray[] = reqParams.split(",");
	 Map<String, String> mapReqParams = new HashMap<String, String>();
	 for(String header:headerArray)
	 {
		 mapReqParams.put(header.split("=")[0],header.split("=")[1]);
	 }
	 logger.info("Request Parameters : " + mapReqParams);
	 return mapReqParams;
}


/**
 * @author sbisoi
 * @param pathname
 * @param filename
 * @param mainval
 * @param index
 * @param attribute
 * @param val
 * @throws Exception
 * Method  to update JSON file-where the Value inside the Array gets Updated
 */
public static void JsonUpdateValue(String pathname,String filename, String mainval, int index,String attribute, String val) throws Exception{
	String path=pathname+"\\"+filename;
	File JsonFile=new File(path);
	//Read JSON File Data and store in string	
	String jsonString=FileUtils.readFileToString(JsonFile); 
	JSONParser jp=new JSONParser();   //invoke Json Parser
	org.json.JSONObject obj=new org.json.JSONObject(jsonString);
	System.err.println("Before update:= "+obj.getJSONArray(mainval).getJSONObject(index).getString(attribute));

	obj.getJSONArray(mainval).getJSONObject(index).put(attribute, val);
	System.err.println("After update= "+obj.getJSONArray(mainval).getJSONObject(index).get(attribute));
 //    obj.remove(attribute);
  //   obj.put(attribute, val); //update the attribute value with new value
    FileUtils.writeStringToFile(JsonFile, obj.toString());
    FileWriter fl=new FileWriter(JsonFile);
    fl.write(obj.toString());
    fl.flush();
    fl.close();
    
 }


/**
 * @author sbisoi	
 * @param payload
 * @param elementname
 * @param tagname
 * @param num
 * @param runtime
 * @param payloadname
 * @return
 * @throws IOException
 * @throws Exception
 * Common  method to fetch data of XML Node ,Put runtime=False to get paylod at runtime else use True to use Payload from Locals
 */
	

	public static String GetXMLDataLocalRunTime(String payloadSchemaPath, String payloadname,String elementname,String tagname,int num,boolean runtime) throws IOException, Exception{
		String path = null ;
		if(runtime==false) 
		{
		path = payloadSchemaPath+"\\"+payloadname;
		System.err.println(path);
		String xml=readText(path);
		
		org.w3c.dom.Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new InputSource(new StringReader(xml)));
	 
		//Fetch tag by tagname
		org.w3c.dom.NodeList nodes=doc.getElementsByTagName(elementname);
		Element element=(Element) nodes.item(0);
		// Fetch the Node value for the tagname ,where the occurrence of the tag incase of multiple occurrence of same tag
		String value=element.getElementsByTagName(tagname).item(num).getTextContent();
		return value;
		}
		
		else{
			
		org.w3c.dom.Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(payloadname)));
		//Fetch tag by tagname
		org.w3c.dom.NodeList nodes=doc.getElementsByTagName(elementname);
		Element element=(Element) nodes.item(0);
	
		// Fetch the Node value for the tagname ,where the occurrence of the tag incase of multiple occurrence of same tag	
		String value=element.getElementsByTagName(tagname).item(num).getTextContent();
		return value;
			
		}
		
	}
	
	
	/**
	 * @author sbisoi
	 * @param requestSpec
	 * @param payloadSchemaPath
	 * @param filename
	 * @param endpointUrl
	 * @return
	 * @throws IOException
	 * Post call without XML Payload - any other format is accepted -text,json
	 */
	
	public static Response PostCall(RequestSpecification requestSpec, String payloadSchemaPath, String filename, String endpointUrl) throws IOException
	{
		String payload = readText(payloadSchemaPath+"/"+filename);
		System.err.println(payload);
		Response rep=(Response) given().spec(requestSpec).body(payload).when().post(endpointUrl).getBody();
        return rep;
	}
	
	
	
	/**
	 * @author sbisoi
	 * @param path
	 * @return
	 * @throws IOException
	 * Read Tesx Data from Text File
	 */
	
	public static String readText(String path) throws IOException{
		
		FileInputStream input=new FileInputStream(path);
		String text=org.apache.commons.io.IOUtils.toString(input);
		return text;
		
	}
	
	public static String readStringCellValue(int rw, int cl, String testPathname, int Sheetnum, String filename) throws IOException{
		String file=testPathname+"\\"+filename;
		
		FileInputStream fis=new FileInputStream(file);
		excelsheet=new XSSFWorkbook(fis);  //Fetch the workbook of excel
		
		sheet=excelsheet.getSheetAt(Sheetnum); //Get the Sheet
		
		row=sheet.getRow(rw);     //Fetch the row 
		
		col=row.getCell(cl);     // Fetch the Coloumn
		String getCellval=col.getStringCellValue(); //Fetch the Cell Value and Return it 
	
		return getCellval;
	}
	
	
	/**
	 * 
	 * @param testdata
	 * @param filename
	 * @param sheetIndex
	 * @param row
	 * @param col
	 * @param str
	 * @throws IOException
	 * This Method works to Write Test Data to TestFile  using above parameter input 
	 */
	
	public static void WriteToExcel(String FilePath,String Filename,int sheetIndex,int row, int col , String Testdata) throws IOException{
		
		 String file=FilePath+"\\"+Filename;
		 //Create a input for File to be written 
		 FileInputStream fis=new FileInputStream(file);
		 
		 //Provide the File input , row and col data to fetch the cell data to particular Cell 
		 excelsheet=new XSSFWorkbook(fis);
		 sheet=excelsheet.getSheetAt(sheetIndex);
		 System.err.println(sheetIndex);
		 
		 //Provide the Testdata value to the cell to be written over 
		 sheet.getRow(row).createCell(col).setCellValue(Testdata);
		 
		 //Place the file to in Output stream to Write Input data into it 
		 FileOutputStream fos=new FileOutputStream(file);
	     excelsheet.write(fos);
		 fos.close();
		 fos.flush();
		
	}

	
	public static String readJsonObject(String jsonformat,String key) throws Exception{
		
		JSONParser Parser=new JSONParser();
		Object obj=Parser.parse(jsonformat);
		JSONObject jobj=(JSONObject) obj;
		String token=(String) jobj.get(key);
		return token;
		
	}
	
	
	
	/**
	 * 
	 * @param reqSpec
	 * @param endpointUrl
	 * @param param
	 * @return
	 * @throws Exception
	 * Pass "format=simplexml,"+"CORP_ID"+"="+Corp_ID as parameter to the param
	 */
	
	public static Response doGetCall(RequestSpecification reqSpec, String endpointUrl,String param) throws Exception{
	
		HashMap<String, String> namespaces = new HashMap<String, String>();
        namespaces.put("wd", "urn:com");
        XmlConfig xmlConf = new XmlConfig();
 
		Response response = given().spec(reqSpec).params(getRequestParameters(param)).config(RestAssured.config().xmlConfig(xmlConf.declareNamespaces(namespaces))).
				when().get(endpointUrl);
		return response;
	}
	
	
	
	/**
	 * @author sbisoi
	 * @param reqSpec
	 * @param endpointUrl
	 * @return
	 * @throws IOException
	 * LMS get call without request body and without parameter
	 */
	
	public static Response GetCall(RequestSpecification reqSpec, String endpointUrl) throws IOException
	{
		HashMap<String, String> namespaces = new HashMap<String, String>();
        namespaces.put("wd", "urn:com");
        
        HashMap<String, String> namespace = new HashMap<String, String>();
        namespaces.put("@odata", "$metadata#Users/$entity");
         XmlConfig xmlConf = new XmlConfig();
      //   readExcelsheet(Constant.CorpID_Path, sheet);
		Response response = given().spec(reqSpec).config(RestAssured.config().xmlConfig(xmlConf.declareNamespaces(namespace))).
				when().get(endpointUrl);
   
return response;
	}


}
