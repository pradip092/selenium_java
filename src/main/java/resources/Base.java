package resources;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.MediaEntityBuilder;
import DB_PPT_Common_PageObjects.MyAccount_PgaeObject;
import Harmony1_Integration_PageObjects.Harmony_Login_PageObject;
import extentManager.ExtentManager;
import extentManager.ExtentTestManager;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;




public class Base {

	public WebDriver driver;
	
	public static Properties properties;
	static String base64Screenshots = null;
	static String base64Screenshot = null;
	static String device;
	static String BrowserDetails;
	static String deviceEdge;
	static String browserName;
	public String v;
	public static String URLS;
	public static String NAME;
	public static String PPT;
	public static String browsertype;
	private String clientUrl;
	public static String encodedString;
	public static String clientName;
	public String browser1="edge";
	public String browser2="chrome";
	private static ExtentTestManager Listeners = new ExtentTestManager();
	
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

  
	
	@Parameters({ "browser","URLS"})
	@BeforeTest(groups = { "All" })
	// Parallel execution method
	public synchronized void initDriver(String browser,String url) throws IOException {		
	
		URLS=url;
		browsertype=browser;
	
		properties = new Properties();
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties";
		FileInputStream fis = new FileInputStream(filePath);
		properties.load(fis);
	//	
	
	if (browser.equals("chrome")) 
	{		
	  System.out.println("chrome launch");
	 WebDriverManager.chromedriver().setup();
	 WebDriverManager.chromedriver().clearDriverCache().setup();
     WebDriverManager.chromedriver().clearResolutionCache().setup();
      ChromeOptions options = new ChromeOptions();
    
		 // 🚀 MAIN FIX FOR YOUR PIPELINE ISSUE
	    options.addArguments("--disable-features=LocalNetworkAccess");
	    options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");

     options.addArguments("--incognito");
     options.addArguments("--headless");
	        

			HashMap<String, Object> prefs = new HashMap<>();
			prefs.put("plugins.always_open_pdf_externally", true);
            String path = System.getProperty("user.dir") + "\\File_Download_directory";
           prefs.put("download.default_directory", path);
           options.setExperimentalOption("prefs", prefs);
           options.addArguments("--disable-cache");
           options.addArguments("--disable-extensions");
           options.addArguments("--disable-dev-shm-usage");
           options.addArguments("--disable-infobars");
           options.addArguments("--remote-allow-origins=*");
           
           DesiredCapabilities capabilities = new DesiredCapabilities();
           capabilities.setCapability(ChromeOptions.CAPABILITY, options);
          options.merge(capabilities);
		   tlDriver.set(new ChromeDriver(options));
	
		   getDriver().manage().window().setSize(new Dimension(1440, 900));

		} 
			
			
			
	else if (browser.equals("firefox"))
	{
		    System.out.println("firefox launch headless");
			WebDriverManager.firefoxdriver().setup();
		 FirefoxOptions options = new FirefoxOptions();
		 options.addArguments("--private"); // Enable private browsing mode
		 options.addArguments("--headless"); // Enable headless mode
		// Prevent internal network restrictions (ZPA/SIPA-friendly)
					options.addPreference("network.captive-portal-service.enabled", false);
					options.addPreference("security.fileuri.strict_origin_policy", false);

					// Allow user:pass in URLs (if needed)
					options.addPreference("network.http.phishy-userpass-length", 255);

					// Optional stability settings for pipeline
					options.addPreference("browser.tabs.remote.autostart", false);
					options.addPreference("browser.tabs.remote.autostart.2", false);

			 DesiredCapabilities capabilities = new DesiredCapabilities();
	          capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
	          options.merge(capabilities);
			 
			tlDriver.set(new FirefoxDriver(options));
           
            getDriver().manage().window().setSize(new Dimension(1440, 900));
    }
		
	else if (browser.equals("edge")) 
	{
			
			WebDriverManager.edgedriver().setup();
			System.out.println("webdriver setup");
			
			EdgeOptions edgeOptions = new EdgeOptions();
			System.out.println("options");
			edgeOptions.addArguments("--disable-features=LocalNetworkAccess");
			edgeOptions.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");

		   edgeOptions.addArguments("--inprivate");
		    edgeOptions.addArguments("--disable-cache");
		   // edgeOptions.addArguments("enable-features=NetworkServiceInProcess");
		   // edgeOptions.addArguments("--disable-gpu");
		   // WebDriverManager.edgedriver().forceDownload().setup();

		  edgeOptions.addArguments("--headless");
		
		    tlDriver.set(new EdgeDriver(edgeOptions));
			System.out.println("edge launch");
		
			  getDriver().manage().window().setSize(new Dimension(1440, 900));

	} 


		else if (browser.equals("Safari")) 
		{
			System.out.println("safari launch");
			final String AUTOMATE_USERNAME = "pradeep;
		    final String AUTOMATE_ACCESS_KEY = "ycHhNprty";
		    final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";		    
		    MutableCapabilities capabilities = new MutableCapabilities();
		    capabilities.setCapability("browserName", "Safari");
		    HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
		    browserstackOptions.put("os", "OS X");
		    browserstackOptions.put("osVersion", "Catalina");
		    browserstackOptions.put("browserVersion", "13.1");
		    browserstackOptions.put("local", "false");
		    browserstackOptions.put("seleniumVersion", "3.14.0");
		    capabilities.setCapability("bstack:options", browserstackOptions);


         tlDriver.set((WebDriver) new RemoteWebDriver(new URL(URL), capabilities));

         getDriver().manage().window().maximize();
		}
		else 
		{
			System.out.println("Please pass the correct browset vlaue: " + browser);
		}
			
			
        
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(240));
     	
     	getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	
		 getDriver().manage().deleteAllCookies();

	}
	
@AfterTest(groups = { "All" })	
public  synchronized void teardown() throws InterruptedException {
	//Thread.sleep(5000);
	
getDriver().quit();
cleanupLocalThread();

	}


@org.testng.annotations.AfterSuite(groups = { "All" })
public  void AfterSuite() {
	
	SendEmail se = new SendEmail();
	 try {
		se.emailSending()  ;
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


	//Full page screenshot using String Argument to get the method name
	/*@SuppressWarnings("resource")
	public static synchronized String takeScreenshot(String methodName) {
	
		DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_ss_SSS");
		Date date = new Date();
		String dateName = dateFormat.format(date);
		String OUTPUT_FOLDER_SCREENSHOTS = "/Screenshots/";
		String filePathExtent = OUTPUT_FOLDER_SCREENSHOTS + "Web" + methodName + "_" + dateName + ".png";
		String filePath = ExtentManager.getReportBaseDirectory() + filePathExtent;
		String encodedBase64 = null;
		// String base64Screenshot= null;
		String base64Screenshot = null;

		try {
			
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportRetina(500, 0, 0, 2))
					.takeScreenshot(getDriver());

			// Convert the screenshot to a Base64 encoded string
			BufferedImage image = screenshot.getImage();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			baos.flush();
			byte[] imageInByteArray = baos.toByteArray();
			baos.close();
			
			base64Screenshot = Base64.encodeBase64String(imageInByteArray);
			System.out.println(base64Screenshot);
		} catch (IOException e) {
			e.getStackTrace();
			Reporter.log("Failed To Take screenshot " + e, true);
		}
		
		return base64Screenshot;
	}*/
	// Ai Method
	public static synchronized void takeScreenshots() throws IOException {
	    String base64Screenshots = null;
	    try {
	        Screenshot screenshot = new AShot()
	            .shootingStrategy(ShootingStrategies.viewportRetina(500, 0, 0, 2))
	            .takeScreenshot(getDriver());

	        BufferedImage image = screenshot.getImage();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(image, "png", baos);
	        baos.flush();
	        byte[] imageInByteArray = baos.toByteArray();
	        baos.close();
	        
	        base64Screenshots = Base64.encodeBase64String(imageInByteArray);
	        
	        // Better formatted screenshot in report
	        ExtentTestManager.getTest().info("Screenshot",
	            MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshots)
	                .build());
	                
	    } catch (IOException e) {
	        ExtentTestManager.getTest().warning(" Screenshot capture failed: " + e.getMessage());
	    }
	}
	/*Full page screenshot using void method 
	@SuppressWarnings("resource")
	public static synchronized void takeScreenshots() throws IOException {
	
		String base64Screenshots = null;

		try {
			
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportRetina(500, 0, 0, 2))
					.takeScreenshot(getDriver());

			
			
			BufferedImage image = screenshot.getImage();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			baos.flush();
			byte[] imageInByteArray = baos.toByteArray();
			baos.close();
			
			base64Screenshots = Base64.encodeBase64String(imageInByteArray);
			
		} catch (IOException e) {
			e.getStackTrace();
			Reporter.log("Failed To Take screenshot " + e, true);
		}
		ExtentTestManager.getTest().info("<br><font color= green>" + "Screenshot of Web" + "</font></b>",
				MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshots).build());
		
	}*/

//For Taking screenshot for any Specific WebElement
	@SuppressWarnings("resource")
	public static synchronized  String takeScreenshotforSpecificElement(WebElement t) throws IOException {

	
		String encodedBase64 = null;
		try {

			File screenshotFile = t.getScreenshotAs(OutputType.FILE);
			FileInputStream fileInputStreamReader;
			fileInputStreamReader = new FileInputStream(screenshotFile);
			byte[] bytes = new byte[(int) screenshotFile.length()];

			fileInputStreamReader.read(bytes);

			encodedBase64 = Base64.encodeBase64String(bytes);
		} catch (IOException e) {
			e.getStackTrace();
			Reporter.log("Failed To Take screenshot " + e, true);
		}
		ExtentTestManager.getTest().info("", MediaEntityBuilder
				.createScreenCaptureFromBase64String(encodedBase64).build());
		
		return encodedBase64;
	}

	//For Taking screenshot for any Specific WebElement
		@SuppressWarnings("resource")
		public static synchronized String takeScreenshotforSpecificElement(By locator) {

			WebElement t = getDriver().findElement(locator);
			String encodedBase64 = null;
			try {

				File screenshotFile = t.getScreenshotAs(OutputType.FILE);
				FileInputStream fileInputStreamReader;
				fileInputStreamReader = new FileInputStream(screenshotFile);
				byte[] bytes = new byte[(int) screenshotFile.length()];

				fileInputStreamReader.read(bytes);

				encodedBase64 = Base64.encodeBase64String(bytes);
			} catch (IOException e) {
				e.getStackTrace();
				Reporter.log("Failed To Take screenshot " + e, true);
			}
			return encodedBase64;
		}
	
	
	
	@SuppressWarnings("resource")
	public static synchronized String TakeScreenshotforSpecificElement(WebDriver driver,WebElement t) {

		
		String encodedBase64 = null;
		try {

			File screenshotFile = t.getScreenshotAs(OutputType.FILE);
			FileInputStream fileInputStreamReader;
			fileInputStreamReader = new FileInputStream(screenshotFile);
			byte[] bytes = new byte[(int) screenshotFile.length()];

			fileInputStreamReader.read(bytes);

			encodedBase64 = Base64.encodeBase64String(bytes);
		} catch (IOException e) {
			e.getStackTrace();
			Reporter.log("Failed To Take screenshot " + e, true);
		}
		return encodedBase64;
	}
	
//To print browser Console log
	public void getBrowserLog() {
		Logs log = getDriver().manage().logs();
		LogEntries logEntries = log.get(LogType.BROWSER);
		log("======== Browser log - starts ========");
		for (LogEntry logEntry : logEntries) {
			log(logEntry.getMessage() + "\n");
		}
		log("======== Browser log - ends ========");
	}

	public void log(String message) {
		System.out.println(message);
		Listeners.reportlog(message);
	}
	public static void BrowserName() throws InterruptedException {
	    Capabilities cap = ((RemoteWebDriver) getDriver()).getCapabilities();
	    String browser = cap.getBrowserName();
	    
	    // Use "Browser-" prefix to force to absolute top of list
	    if (browser.equalsIgnoreCase("chrome")) {
	        ExtentTestManager.getTest().assignCategory("Chrome");
	    } else if (browser.equalsIgnoreCase("msedge") || browser.equalsIgnoreCase("edge")) {
	        ExtentTestManager.getTest().assignCategory("Edge");
	    } else if (browser.equalsIgnoreCase("firefox")) {
	        ExtentTestManager.getTest().assignCategory("Firefox");
	    } else if (browser.equalsIgnoreCase("Safari")) {
	        ExtentTestManager.getTest().assignCategory("Safari");
	    }
	}
	/*
	public static void BrowserName() throws InterruptedException {

		int x = 10;

		Capabilities cap = ((RemoteWebDriver) getDriver()).getCapabilities();

		String s = cap.getBrowserName();
		if (x == 10) {

			if (s.equals("chrome")) {
				// System.out.println("String chrome = \"chrome\";");
				ExtentTestManager.getTest().assignCategory(s);

			}
		}
		int y = 11;

		if (y == 11) {

			if (s.equals("msedge")) {
				// System.out.println("String edge = \"edge\";");
				ExtentTestManager.getTest().assignCategory(s);
			}

		}
		
		int z = 12;

		if (z == 12) {

			if (s.equals("Safari")) {
				// System.out.println("String edge = \"edge\";");
				ExtentTestManager.getTest().assignCategory(s);
			}

		}

		else {
			System.out.println("Please pass the correct browset vlaue: ");
		}
	}
		    public  String getVersion() {
		    Capabilities cap = ((RemoteWebDriver) getDriver()).getCapabilities();
		    v = cap.getBrowserVersion().toString();
		    return v;
		    }
	*/
	public synchronized void desired_Environment_Portal_login(String validUserID, String validPassword) throws InterruptedException, IOException
	{
		
		MyAccount_PgaeObject MyAp1= new MyAccount_PgaeObject(getDriver());
		 JavascriptExecutor js1=(JavascriptExecutor)getDriver();
		 Harmony_Login_PageObject hlp= new Harmony_Login_PageObject(getDriver());
			// clientUrl =System.getProperty("URLS");

			clientUrl=URLS;
		   clientName=NAME;
		// if(clientUrl.equals("MBCTRACFGURL")&&clientName.equals("MBCTRA"))
			 if(clientUrl.equals("MBCTRACFGURL"))
	    	{
	        getDriver().get(properties.getProperty("MBCTRACFGURL"));
	       
	  	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(110));
	  	    customWait( MyAp1.username,20);
	        wait.until(ExpectedConditions.visibilityOf( MyAp1.username()));
	        Listeners.reportlog("launch MPC Successfully  ");
	        Thread.sleep(3000);
	  	    

	        Base.takeScreenshots();
	        Listeners.reportlog("Screen Shot taken of Login Page");
	        MyAp1.username().clear();
	        Listeners.reportlog("clear User Name Field ");
	        MyAp1.username().sendKeys(validUserID);
	        Listeners.reportlog("Entered valid UserID:"+validUserID);
	        
	        
	  	    MyAp1.password().clear();
	  	    Listeners.reportlog("clear Password Field ");
	  	    
	  	    //Encrypt 
	  	     String EncryptredPassword=  encodedStringString(validPassword);
	  	    
	  	    MyAp1.password().sendKeys(validPassword);
	  	    Listeners.reportlog("Entered valid Password:"+EncryptredPassword);
	  	    Base.takeScreenshots();  
	  	   MyAp1.loginButton();
	       js1.executeScript("arguments[0].click();", MyAp1.loginButton());
	       Listeners.reportlog("clicked on SignIn Button");
	     
	     	
	    	}
		 
	        else if(clientUrl.equals("MBCTRAPRODURL"))
	    	{
	        getDriver().get(properties.getProperty("MBCTRAPRODURL"));
	        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(110));
	        customWait(  MyAp1.username,20);
	        wait.until(ExpectedConditions.visibilityOf( MyAp1.username()));
	        Listeners.reportlog("launch MPC Successfully  ");
	        Thread.sleep(3000);
	  	    

	        Base.takeScreenshots();
	        Listeners.reportlog("Screen Shot taken of Login Page");
	        MyAp1.username().clear();
	        Listeners.reportlog("clear User Name Field ");
	        MyAp1.username().sendKeys(validUserID);
	        Listeners.reportlog("Entered valid UserID:"+validUserID);
	        
	        
	  	    MyAp1.password().clear();
	  	    Listeners.reportlog("clear Password Field ");
	  	    
	  	  //Encrypt 
	  	     String EncryptredPassword=  encodedStringString(validPassword);
	  	    
	  	    MyAp1.password().sendKeys(validPassword);
	  	    Listeners.reportlog("Entered valid Password:"+EncryptredPassword);
	    	  
	  	   MyAp1.loginButton();
	       js1.executeScript("arguments[0].click();", MyAp1.loginButton());
	       Listeners.reportlog("clicked on SignIn Button");
	     	
	    }
	       // getDriver().manage().window().maximize();
	        else if(clientUrl.equals("CAT"))
	    	{ 
	        	 getDriver().get(properties.getProperty("CATURL"));
	             MyAp1.username().clear();
	             MyAp1.username().sendKeys(validUserID);
	             
	             
	       	    MyAp1.password().clear();
	       	  //Encrypt 
		  	     String EncryptredPassword=  encodedStringString(validPassword);
		  	    
		  	    MyAp1.password().sendKeys(validPassword);
		  	    Listeners.reportlog("Entered valid Password:"+EncryptredPassword);
		    	  
	         	  
	       	   MyAp1.loginButton();
	            js1.executeScript("arguments[0].click();", MyAp1.loginButton());
	            Listeners.reportlog("Entered valid UserID and Password and clicked on SignIn Button");
	          	
	    	}
	        else if(clientUrl.equals("MBCTRA_HarmonyCFGURL"))
	    	{ 
	        	 getDriver().get(properties.getProperty("HarmonyCFGURL"));
	        	 Thread.sleep(2000);
	        	customWait( hlp.AgentID,20);
	       boolean AgentId= hlp.AgentID().isDisplayed();
	       boolean Password	= hlp.Password().isDisplayed();
	       boolean 	SignIn=  hlp.SignIn().isDisplayed();
	       if(AgentId|Password|SignIn==true)
	       {
	    	    Listeners.reportlog("Harmony Application launched Succesfully");
	        	 Base.takeScreenshots();
	        	 Thread.sleep(2000);
	        	 hlp.AgentID().clear();
	        	 hlp.AgentID().sendKeys(validUserID);
	        	 Listeners.reportlog("Entered valid UserID:"+validUserID);
	             
	             
	       	    hlp.Password().clear();
	       	  //Encrypt 
		  	     String EncryptredPassword=  encodedStringString(validPassword);
		  	    
		  	    MyAp1.password().sendKeys(validPassword);
		  	    Listeners.reportlog("Entered valid Password:"+EncryptredPassword);
		    	  
	       	  
	            js1.executeScript("arguments[0].click();", hlp.SignIn());
	            Listeners.reportlog("clicked on SignIn Button");
	       }
	       else
	       {
	    	   Listeners.reportlog("unable to launch Harmony Application");
	    	   Assert.assertTrue(false);
	       }
	          	
	    	}
	        else if(clientUrl.equals("DIT"))
	    	{ 
	            getDriver().get(properties.getProperty("DITURL"));
	            System.out.println("launch diturl");
	        	int Dashboard_Header_TablistSize=MyAp1.Dashboard_Header_TablistSize().size();
	        	 System.out.println("sizeof heade"+Dashboard_Header_TablistSize);
	        	if(Dashboard_Header_TablistSize>0)
	        	{
	        		System.out.println("Login page disable");
	        		Listeners.reportlog("Login page is disable");
	        	}
	        	else
	        	{
	        		System.out.println("iam Login page here");
	        		 MyAp1.username().clear();
	                 MyAp1.username().sendKeys(validUserID);
	                 
	                 
	           	    MyAp1.password().clear();
	           	    MyAp1.password().sendKeys(validPassword);
	             	  
	           	    MyAp1.loginButton();
	                js1.executeScript("arguments[0].click();", MyAp1.loginButton());
	                Listeners.reportlog("Entered valid UserID and Password and clicked on SignIn Button");
	              	
	        	}
	        	
	        	
	    	}     
	        else if(clientUrl.equals("DOC_CFGURL"))
	    	{ 
	        	 getDriver().get(properties.getProperty("DOC_CFGURL"));
	        	 Thread.sleep(10000);	          	
	    	}
	}
		
 public void clicked_on_Desired_MyAccount_Dropdown(String MyAcntDropdowntextName) throws InterruptedException
 {
	 JavascriptExecutor js2=(JavascriptExecutor)getDriver();
	//MyAccount_PgaeObject MyAp2= new MyAccount_PgaeObject(getDriver());
	 MyAccount_PgaeObject MyAp2 = new MyAccount_PgaeObject(getDriver());
	 js2.executeScript("arguments[0].click();",  MyAp2.MyAccount());
	 //  MyAp2.MyAccount().click();
	  Listeners.reportlog("click on My Account tab");
	
	 WebDriverWait wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(110));
	// wait2.until(ExpectedConditions.visibilityOfAllElements(MyAp2.MyAccountDrpdwnSize()));
	  Thread.sleep(3000);   
	 int size=MyAp2.MyAccountDrpdwnSize().size();
	 int loopSize=0;
	 
	 boolean dropdownFind =false;
	 System.out.println("Sizeof_DropDown"+size);
	  if(size>0)
	  {
	 List<WebElement> list= MyAp2.MyAccountDrpdwnSize();
	 Iterator<WebElement> it=list.iterator();
	 while(it.hasNext())
	 {
	   System.out.println("Enter While Loop");
	   WebElement MyAccount_Optn_Availabel_drpDown=it.next();
	   String MyAccount_Optn_Availabel_drpDown_text= MyAccount_Optn_Availabel_drpDown.getText();
	   System.out.println("MyAccount_Optn_Availabel_drpDown_text"+MyAccount_Optn_Availabel_drpDown_text);
	   loopSize++;
	    if(MyAccount_Optn_Availabel_drpDown_text.equalsIgnoreCase(MyAcntDropdowntextName))
	     {
	   	  // wait2.until(ExpectedConditions.visibilityOf(MyAccount_Optn_Availabel_drpDown));
	    	  dropdownFind=true;

       		 js2.executeScript("arguments[0].click();", MyAccount_Optn_Availabel_drpDown);
	         Listeners.reportlog("click on Desired dropdown.."+MyAcntDropdowntextName);
	         System.out.println("click on Desired dropdown.."+MyAcntDropdowntextName);
    	      break;

	      }
	    if(dropdownFind==false & loopSize==size)
	    {
	    	Listeners.reportlog("My Account desired options :"+MyAcntDropdowntextName+":is not Availabel");
            Assert.assertTrue(false);
	    	
	    }
	      
	   }
	  }
	  else
	  {
		  Listeners.reportlog("MyAccount Dropdown There is no options Availabel");
		  Assert.assertTrue(false);
	  }
	
 }
 
 
 public static void customWait(By Locator, int waitTimeInSeconds) {
     WebElement element = null;
     int retries = 1;
     int maxRetries = waitTimeInSeconds; // Maximum number of retries (each retry after 500 milliseconds)
     while (element == null && retries <= maxRetries) {
         	 
    	 try {
        
             element = getDriver().findElement(Locator);
             //takeScreenshotforSpecificElement(element);
             Listeners.reportlog("Element Found in How many Tries:"+retries);
            System.out.println("Element Found in How many Tries:"+retries);          
             break;
         } catch (Exception e) {
       String m=	e.getMessage();
         System.out.println("m--:"+m);
         Listeners.reportlog("Element not Found in How many Tries:"+retries);
       Listeners.reportlog("Giving Exceptions:"+m);
       
         }
         retries++;
        
     }
     if(element == null && maxRetries==waitTimeInSeconds)
     {
     	Listeners.reportlog("Element Not Found Wait for Maximum tries:"+maxRetries);
     	Assert.assertTrue(false);
     }
  
 }
 /*
 public static void customWait(By Locator, int waitTimeInSeconds) {
     WebElement element = null;
     int retries = 1;
     int maxRetries = waitTimeInSeconds; // Maximum number of retries (each retry after 500 milliseconds)
     while (element == null && retries <= maxRetries) {
         	 
    	 try {
        
             element = getDriver().findElement(Locator);
             //takeScreenshotforSpecificElement(element);
             Listeners.reportlog("Element Found in How many Tries:"+retries);
            System.out.println("Element Found in How many Tries:"+retries);          
             break;
         } catch (Exception e) {
       String m=	e.getMessage();
         System.out.println("m--:"+m);
         Listeners.reportlog("Element not Found in How many Tries:"+retries);
       Listeners.reportlog("Giving Exceptions:"+m);
       
         }
         retries++;
        
     }
     if(element == null && maxRetries==waitTimeInSeconds)
     {
     	Listeners.reportlog("Element Not Found Wait for Maximum tries:"+maxRetries);
     	Assert.assertTrue(false);
     }
  
 }

*/
 public void customWait_Invisiblity_Element(By Locator, int waitTimeInSeconds) {
	     WebElement element = getDriver().findElement(Locator);
	     int retries = 1;
	     int maxRetries = waitTimeInSeconds; // Maximum number of retries (each retry after 500 milliseconds)
	     while (element!=null && retries <= maxRetries) {
	         	 
	    	 try {
	        
	             element = getDriver().findElement(Locator);
	             takeScreenshotforSpecificElement(element);
	             Listeners.reportlog("Element Still visible:"+retries);
	            System.out.println("Element Still visible:"+retries);          
	 
	         } catch (Exception e) {
	          String m=	e.getMessage();
	         System.out.println("m--:"+m);
	         Listeners.reportlog("Element is not visible in How many Tries:"+retries);
	         Listeners.reportlog("Giving Exceptions:"+m);
	         break;
	       
	       
	         }
	         retries++;
	        
	     }
	     if(element != null && maxRetries==waitTimeInSeconds)
	     {
	     	Listeners.reportlog("Element is Still visible"+maxRetries);
	     	Assert.assertTrue(false);
	     }
    
 }
 
 
 public static String encodedStringString(String password)
 {
	 byte[] EncodedString=Base64.encodeBase64(password.getBytes());
		
	    encodedString=new String(EncodedString);
		System.out.println("encodedString:"+encodedString);
		return encodedString ;
 }
 
 public boolean isInvisible(By by, int timeout) throws InterruptedException {
     long endTime = System.currentTimeMillis() + timeout * 1000;
     
     while (System.currentTimeMillis() < endTime) {
         List<WebElement> elements = driver.findElements(by);
         if (elements.size() == 0) {
             return true; // Element is invisible
         }
         //Thread.sleep(500); // Sleep for a short interval before checking again
     }
     
     return false; // Element is not invisible after the timeout
 }
//page screenshot using void method 
		@SuppressWarnings("resource")
		public static synchronized void TakeScreenshots() throws IOException {
			// public static synchronized String takeScreenshot(String base64Screenshot){

			/*
			 * DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_ss_SSS");
			 * Date date = new Date(); String dateName = dateFormat.format(date); String
			 * OUTPUT_FOLDER_SCREENSHOTS = "/Screenshots/";
			 */
			// String filePathExtent = OUTPUT_FOLDER_SCREENSHOTS + "Web" + methodName + "_"
			// + dateName + ".png";
			// String filePath = ExtentManager.getReportBaseDirectory() + filePathExtent;
			// String encodedBase64 = null;
			// String base64Screenshot= null;
			String base64Screenshots = null;

			try {
				 File screenshotFile = ((TakesScreenshot)
				getDriver()).getScreenshotAs(OutputType.FILE); FileInputStream
				fileInputStreamReader;
				 fileInputStreamReader = new FileInputStream(screenshotFile); byte[] bytes =
				 new byte[(int) screenshotFile.length()];
				 fileInputStreamReader.read(bytes);
				 base64Screenshots = Base64.encodeBase64String(bytes);
				// FileUtils.copyFile(screenshotFile, new File(filePath));


			} catch (IOException e) {
				e.getStackTrace();
				Reporter.log("Failed To Take screenshot " + e, true);
			}
			ExtentTestManager.getTest().info("<br><font color= green>" + "Screenshot of Web" + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshots).build());
			// return encodedBase64;
			// return base64Screenshots;
		}
		
		 // Method to quit WebDriver instance
	    public static  void cleanupLocalThread() {	        
	            tlDriver.remove();	       
	    }
}
 
