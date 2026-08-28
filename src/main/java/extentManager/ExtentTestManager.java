package extentManager;
import java.util.HashMap;
import java.util.Map;

//import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

//import resources.bases;

//import AbstractComponents.BaseActions;

public  class ExtentTestManager {

	   static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	   
	   ExtentTest test;
	   static ExtentReports extent = ExtentManager.getInstance();
	   private static final ThreadLocal<String> categoryName = new ThreadLocal<>();

	   public static synchronized ExtentTest getTest() {
	       return extentTestMap.get((int) Thread.currentThread().getId());
	   }
	   
	 public static synchronized ExtentTest getTest1() {
	    //  return extentTestMap.get((int) Thread.currentThread().getId());
		// return extentTestMap
		 
		return  extent.createTest("Test").assignDevice("chrome").pass("details");
		   
	   } 

	   public static synchronized void endTest() {
	       extent.flush();
	   }

	   public synchronized static void createTest(String testName, String description) {
	       extentTestMap.put((int) Thread.currentThread().getId(), 
	    		   
	    		   extent.createTest(testName, description));
	   }

	  public static ThreadLocal<String> getCategoryName() {
	       return categoryName;
	   }
	

	   public static void setCategoryName(String categoryName) {
	       getCategoryName().set(categoryName);
	   } 

	/*   public synchronized stati void reporterLog(String log, String methodName) {
	           if (ExtentTestManager.getTest() != null) {
	               ExtentTestManager.getTest().log(Status.PASS, log);
	               base.takeScreenshot(methodName);
	               Reporter.log(log + "<br/>");
	       
	           }
	   }  */
	           public synchronized  void reportlog(String log1) {
		          
		               ExtentTestManager.getTest().info(log1);
		               
		               //Reporter.log(log + "<br/>");
		       
		           }
	           

	           public synchronized static void warninglog(String logs) {
		          
		               ExtentTestManager.getTest().warning(logs);
		               
		               //Reporter.log(log + "<br/>");
		           }
	   }

