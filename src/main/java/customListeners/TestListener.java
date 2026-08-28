package customListeners;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import resources.Base;
import resources.SendEmail;
import resources.WriteTestResults;
import extentManager.ExtentTestManager;

public class TestListener extends Base implements ITestListener {

	private static final String String = null;
	private static long endTimes;
	ExtentTestManager Listeners = new ExtentTestManager();
	Base b = new Base();

	private static void setStartTime(long startTime) {
	}

	private static void setEndTime(long endTime) {
		TestListener.endTimes = endTime;
	}

	@Override
	public synchronized void onStart(ITestContext context) {
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println("on Finish method");
		
		
		setStartTime(context.getStartDate().getTime());
		setEndTime(context.getEndDate().getTime());
		
	}
	@Override
	public synchronized void onTestStart(ITestResult result) {
	    System.out.println("i am on Listeners class");
	    System.out.println("--------- Executing :- " + getSimpleMethodName(result) + " ---------");
	    
	    // Step 1: Create the test
	    ExtentTestManager.createTest(result.getName(), result.getMethod().getDescription());
	    
	    // Step 2: Assign BROWSER category FIRST (so it appears at TOP of list)
	    try {
	        Base.BrowserName(); // This assigns "chrome", "edge", etc.
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    
	    // Step 3: Assign TEST CLASS category SECOND (appears after browser)
	    ExtentTestManager.setCategoryName(getSimpleClassName(result));
	}
	/*
	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println("i am on Listeners class");
		System.out.println("--------- Executing :- " + getSimpleMethodName(result) + " ---------");
		ExtentTestManager.createTest(result.getName(), result.getMethod().getDescription());

		ExtentTestManager.setCategoryName(getSimpleClassName(result));
	try {
			Base.BrowserName();
	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().assignCategory(getSimpleClassName(result));
   		String description = result.getMethod().getDescription();
		String workItemId=description;
		String project="Ariel EAS";
		String environment="";
		String status="Pass";
		String configName="";
		String planId="";
		String SuitId="";
		String responsibleTeam="Ariel";
		
		String personalAccessToken="noNyuFfeK8JlfsIfrs0DBrChfBFhSSwLCeGLNxtksUa0PrmTPYJQJQ";	
		String ADOAreaPath="https://dev.azure.com/Ariel%20EAS/_workitems/edit/";
		System.out.println("onTest success browser:"+browsertype);
		System.out.println("onTest success URL:"+URLS);
		String ADOLink = " <HTML>  <HEAD> <p style=\"text-decoration:underline;\"> <a href=\"https://dev.azure.com/Ariel%20EAS/_workitems/edit/" + workItemId + "\">ADO Test Case Link</a> </p> </HEAD> </HTML>";	
if((workItemId!=null) && (URLS.equalsIgnoreCase("MBCTRACFGURL") || URLS.equalsIgnoreCase("MBCTRAPRODURL")||URLS.equalsIgnoreCase("MBCTRA_HarmonyCFGURL")))
{
	
	if(browsertype.equalsIgnoreCase("chrome"))
	{
		configName="Chrome Windows";
	} 
	if(browsertype.equalsIgnoreCase("edge"))
	{
		configName="MSEDGE Windows";
	} 
	
	if(browsertype.equalsIgnoreCase("firefox"))
	{
		configName="Firefox Windows";
	} 
	
	if(browsertype.equalsIgnoreCase("Safari"))
	{
		configName="Mac Safari";
	} 
	if(URLS.equalsIgnoreCase("MBCTRACFGURL"))
	{
		environment="QA";
				
	} 
		 
    if(URLS.equalsIgnoreCase("MBCTRAPRODURL"))
	{
    	environment="QA";	
    }
    System.out.println("workItemId: " + description);
    System.out.println("project:" + project);
    System.out.println("environment:" +environment);
    System.out.println("status:" + status);
    System.out.println("configName:" +configName);
    System.out.println("responsibleTeam:" +responsibleTeam);
    System.out.println("personalAccessToken:" +personalAccessToken);
    try{
    	     WriteTestResults.WriteTestResult2(workItemId,project,environment,status, configName,responsibleTeam,personalAccessToken);
       		 System.out.println("called Api");
       		
    		   } 
          catch (Exception e) 
           {
    			// TODO Auto-generated catch block
    			 System.out.println("in case of catch Block");
    			e.printStackTrace();
    		}	
   
}

		     
if(workItemId!=null)
{
	ExtentTestManager.getTest().log(Status.PASS, result.getName() + "- Test Passed Successfully"+"  "+"ADOTestCaseId:-"+workItemId+":"+ADOLink);
	 
}
else
{
	ExtentTestManager.getTest().log(Status.PASS, result.getName() + " Test is Passed");
}

try {		
	//takeScreenshots();
	TakeScreenshots();

} catch (IOException e) {
	e.printStackTrace();
}
addExtentLabelToTest(result);
ExtentTestManager.endTest();
	}

	public synchronized void onTestFailure(ITestResult result) {

		ExtentTestManager.getTest().assignCategory(getSimpleClassName(result));
   		String description = result.getMethod().getDescription();
		String workItemId=description;
		String project="Ariel EAS";
		String environment="";
		String status="Fail";
		String configName="";
		String planId="";
		String SuitId="";
		String responsibleTeam="Ariel Shared";
		String personalAccessToken="noNyuFfeK8JlfsIfrs0DBrChfBFhSSwLCeGLNxtksUa0PrmTPYJQJQQJ99CCACAAAAAqrm15A";	
		String ADOAreaPath="https://dev.azure.com/Ariel%20EAS/_workitems/edit/";
		System.out.println("onTest success browser:"+browsertype);
		System.out.println("onTest success URL:"+URLS);
		String ADOLink = " <HTML>  <HEAD> <p style=\"text-decoration:underline;\"> <a href=\"https://dev.azure.com/Ariel%20EAS/_workitems/edit/" + workItemId + "\">ADO Test Case Link</a> </p> </HEAD> </HTML>";	
if((workItemId!=null) && (URLS.equalsIgnoreCase("MBCTRACFGURL") || URLS.equalsIgnoreCase("MBCTRAPRODURL")||URLS.equalsIgnoreCase("MBCTRA_HarmonyCFGURL")))
{
	
	if(browsertype.equalsIgnoreCase("chrome"))
	{
		configName="Chrome Windows";
	} 
	if(browsertype.equalsIgnoreCase("edge"))
	{
		configName="MSEDGE Windows";
	} 
	
	if(browsertype.equalsIgnoreCase("firefox"))
	{
		configName="Firefox Windows";
	} 
	
	if(browsertype.equalsIgnoreCase("Safari"))
	{
		configName="Mac Safari";
	} 
	if(URLS.equalsIgnoreCase("MBCTRACFGURL"))
	{
		environment="QA";
				
	} 
		 
    if(URLS.equalsIgnoreCase("MBCTRAPRODURL"))
	{
    	environment="QA";	
    }
    System.out.println("workItemId: " + description);
    System.out.println("project:" + project);
    System.out.println("environment:" +environment);
    System.out.println("status:" + status);
    System.out.println("configName:" +configName);
    System.out.println("responsibleTeam:" +responsibleTeam);
    System.out.println("personalAccessToken:" +personalAccessToken);
    try{
    	   //  WriteTestResults.WriteTestResult2(workItemId,project,environment,status, configName,responsibleTeam,personalAccessToken);
       		 System.out.println("called Api");
       		
    		   } 
          catch (Exception e) 
           {
    			// TODO Auto-generated catch block
    			 System.out.println("in case of catch Block");
    			e.printStackTrace();
    		}	
   
}

		     
if(workItemId!=null)
{
	ExtentTestManager.getTest().log(Status.FAIL, result.getName() + " Test is failed:"+"  "+"ADOTestCaseId:-"+workItemId+":"+ADOLink);
}
else
{
	ExtentTestManager.getTest().log(Status.FAIL, result.getName() + " Test is Failed");
}

try {		
	//takeScreenshots();
	TakeScreenshots();

} catch (IOException e) {
	e.printStackTrace();
}
addExtentLabelToTest(result);
ExtentTestManager.endTest();
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		// Base.BrowserVersion();
		ExtentTestManager.getTest().assignCategory(getSimpleClassName(result));
		ExtentTestManager.getTest().log(Status.SKIP, result.getName() + " Test is Skipped" + result.getThrowable());

		try {

			/*
			 * ExtentTestManager.getTest().skip("<br><font color= red>" +
			 * "Screenshot of Web" + "</font></b>",
			 * MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(
			 * getSimpleMethodName(result))) .build());
			 */
			Thread.sleep(8000);
			//takeScreenshots();
			TakeScreenshots();
		} catch (IOException | InterruptedException e) {
			// catch (IOException e){
			e.printStackTrace();
		}

	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	private synchronized String getSimpleClassName(ITestResult result) {
		return result.getMethod().getRealClass().getSimpleName();
	}

	private synchronized String getSimpleMethodName(ITestResult result) {
		return result.getName();
	}

	private synchronized void addExtentLabelToTest(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS)
			ExtentTestManager.getTest().pass(MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
		else if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().fail(MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
		} else
			ExtentTestManager.getTest().skip(MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));
	}

	public synchronized static By TakeScreenshotOfWebElemenT(By locator) throws IOException {

		ExtentTestManager.getTest().info("", MediaEntityBuilder
				.createScreenCaptureFromBase64String(takeScreenshotforSpecificElement(locator)).build());
		return locator;
	}  
	
	
}
