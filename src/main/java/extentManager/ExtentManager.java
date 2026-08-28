package extentManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;



import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.dockerjava.api.model.Device;


public class ExtentManager {

	   private static String reportBaseDirectory;
	   private static ExtentReports extent;
	   public static final String OUTPUT_FOLDER_SCREENSHOTS ="/Screenshots/";
	   
	   public static final String REPORT_FILE_PATH =System.getProperty("user.dir")+ "/Automation_Reports/";

	   public static ExtentReports getInstance() {
	       if (extent == null)
	          createInstance();
	    	
	       return extent;
	   }
	   //Create an extent report instance
	   public static void createInstance() {
	       ExtentManager.initDirectories();
	      
	      
	       setReportBaseDirectory(REPORT_FILE_PATH);
	       Calendar calendar = Calendar.getInstance();
	       SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyy_hh_mm_ss");
	      // ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(REPORT_FILE_PATH+"Test-Automaton-Report"+formater.format(calendar.getTime())+".html");
	     // ExtentSparkReporter htmlReporter = new ExtentSparkReporter(REPORT_FILE_PATH+"Test-Automation-Report"+formater.format(calendar.getTime())+".html");
	       
ExtentSparkReporter htmlReporter = new ExtentSparkReporter(REPORT_FILE_PATH+"Test-Automation-Report.html");
//ADD THIS CODE TO LOAD CONFIG:
try {
 File configFile = new File("src/main/java/resources/extent-config.xml");
 if (configFile.exists()) {
     htmlReporter.loadXMLConfig(configFile);
 }
} catch (Exception e) {
 System.out.println("Could not load extent config: " + e.getMessage());
}
	       htmlReporter.config().setTheme(Theme.STANDARD);
	       htmlReporter.config().setEncoding("utf-8");
	       htmlReporter.config().setReportName("Automation Test Results");
	       htmlReporter.config().setEncoding("utf-8");
	      // htmlReporter.config().setCss("$('.brand-logo').text('AutomationDemo');");
	       htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	       extent = new ExtentReports();
	       extent.attachReporter(htmlReporter);
	       extent.setSystemInfo("OS",  System.getProperty("os.name"));
	      // extent.setSystemInfo("Java",  System.getProperty("java.specification.version"));
	       extent.setSystemInfo("User",  System.getProperty("user.name"));
	       //extent.setSystemInfo("Browser", "Chrome");
	    
	       
	       extent.setSystemInfo("Application", "MPC Portal");
	      
	      // extent.setSystemInfo("Browser", System.getProperty("Browser.CHROME"));
	     //  extent.setSystemInfo("Browser", System.getProperty("Browser.EDGE"));
	      // extent.setSystemInfo("Browser", System.getProperty("org.openqa.selenium.devtools.v100.browser.Browser.class"));
	       
	   }

	   public synchronized static String getReportBaseDirectory() {
	      // return reportBaseDirectory;
		   
		   return reportBaseDirectory;
	   }
	   public synchronized static void setReportBaseDirectory(String reportBaseDirectory) {
	      ExtentManager.reportBaseDirectory = reportBaseDirectory;
	      
	   }

	   public static void initDirectories() {
	       try {
	          createFolder(REPORT_FILE_PATH + OUTPUT_FOLDER_SCREENSHOTS);
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	   }
	   public static void createFolder(String folderPath) {
	       File file = new File(folderPath);
	       if (!file.exists()) file.mkdirs();
	   }
}
