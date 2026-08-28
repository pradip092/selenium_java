package AbstractComponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static resources.Base.properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import DB_PPT_Common_PageObjects.MyAccount_PgaeObject;
import extentManager.ExtentTestManager;
import resources.Base;

public class AbstractComponent {
	 SoftAssert softAssert = new SoftAssert();
	WebDriver driver;
	ExtentTestManager Listeners = new ExtentTestManager();
	By HomeTabButton = By.xpath("//span[contains(text(),'Home')]");

	By EditButtonInLeftPenal = By.xpath("//a[@*='sidebarEdit']");
	By privacypolicy = By.xpath("//span[contains(text(),'Privacy Policy') or contains(text(),'PRIVACY POLICY')]");
	
	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
	}

	public WebElement waitForElementToAppear(By FindBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		//WebDriverWait wait = new WebDriverWait(driver,150);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));

	}

	public Boolean waitForElementToDisAppear(By FindBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(FindBy));

	}

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);

	}

	public void waitTillAlertIsPresent(WebDriver driver, Duration timeOutInSeconds) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void click(WebDriver driver, WebElement ele) {

		Actions act = new Actions(driver);
		act.moveToElement(ele).doubleClick().build().perform();
	}

	public void WaitUnTillElementVisibl(WebElement w) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOf(w));
	}

	public void String_Any_Content_Matching_Generic_method(String s) {
		
		// Pattern pattern = Pattern.compile(".*[a-zA-Z.,].");
		 
		 Pattern pattern = Pattern.compile(".*[a-zA-Z0-9]+.*");
			
	     // Create a Matcher object
	     Matcher matcher = pattern.matcher(s);
		     Assert.assertTrue(matcher.find(),"pattern did not match");
	}
	public void WaitUntilHomeButtonVisible() {
		// return driver.findElement(SinDetailsTextBox);
		waitForElementToAppear(HomeTabButton);
	}
	 public void javascript_click_Element(WebDriver driver1,By locator) {
			
			WebElement  element1=driver1.findElement(locator);
	        JavascriptExecutor executor = (JavascriptExecutor) driver1;
	        executor.executeScript("arguments[0].click();", element1);
	    }
	
	public void ClickOnHomeButton(String browser) throws InterruptedException {
		//driver.navigate().refresh();
		WaitUntilHomeButtonVisible();
		Thread.sleep(2000);
		//driver.findElement(HomeTabButton).click();
		
		javascript_click_Element(driver,HomeTabButton);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		//WebDriverWait wait = new WebDriverWait(driver,90);

		if(browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("edge") ||browser.equalsIgnoreCase("firefox")) {
			
		 wait.until(ExpectedConditions.and(
					ExpectedConditions.visibilityOfElementLocated(EditButtonInLeftPenal),
					ExpectedConditions.visibilityOfElementLocated(
							privacypolicy)));
		}
		else {
			Base b=new Base();
			
		  b.customWait(EditButtonInLeftPenal,12);
			
		}
		
	}
	
	public void Click_On_MyAccount_DesiredDropDown(String DropdownTextName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		MyAccount_PgaeObject MyAp= new MyAccount_PgaeObject(driver);
		//	wait.until(ExpectedConditions.visibilityOf(MyAp. MyAccount()));
		
		MyAp.MyAccount_click();
		
		//wait.until(ExpectedConditions.visibilityOfAllElements(MyAp.MyAccountDrpdwnSize()));
		 Thread.sleep(3000);
		int size=MyAp.MyAccountDrpdwnSize().size();
		 System.out.println("Sizeof_DropDown"+size);
		  
		 List<WebElement> list= MyAp.MyAccountDrpdwnSize();
		 Iterator<WebElement> it=list.iterator();
		 while(it.hasNext())
		 {
		   System.out.println("Enter While Loop");
		   WebElement MyAccount_Optn_Availabel_drpDown=it.next();
		   String MyAccount_Optn_Availabel_drpDown_text= MyAccount_Optn_Availabel_drpDown.getText();
		   System.out.println("MyAccount_Optn_Availabel_drpDown_text"+MyAccount_Optn_Availabel_drpDown_text);
		
		    if(MyAccount_Optn_Availabel_drpDown_text.equalsIgnoreCase(DropdownTextName))
		     {
		   	   wait.until(ExpectedConditions.visibilityOf(MyAccount_Optn_Availabel_drpDown));
	    		
		    	 JavascriptExecutor js = (JavascriptExecutor) driver;
	     		 js.executeScript("arguments[0].click();", MyAccount_Optn_Availabel_drpDown);
		         Listeners.reportlog("click on Desired dropdown.."+DropdownTextName);
		         System.out.println("click on Desired dropdown.."+DropdownTextName);
	    	      break;
		      }
		      
		   }
		
	}

		
	public void Deletefile() {
		

		File f = new File(System.getProperty("user.dir") + "\\File_Download_directory");
	
	        File[] files = f.listFiles();
	        
	        // Iterate through the list of files
	        for (File file : files) {
	        	
	        	file.delete();
	        	
	        }
	}
	public void File_Downloaded_ORNOT_Verify() {
	
	
		File f = new File(System.getProperty("user.dir") + "\\File_Download_directory");
	
	        File[] files = f.listFiles();
	        
	        // Iterate through the list of files
	    
	        
	        for (File file : files) {  
	        	
	        int count=files.length;
	        
            String fileName = file.getName();

	        Listeners.reportlog("File downloaded count"+count+"File name:"+fileName);
	          // Check if the file is a regular file (not a directory)
	            if (count==1) {
	                // Get the file name
	              // String fileNamet = file.getName();
	        
	                Listeners.reportlog("File name downloaded is :"+fileName);
	                    // Delete the file
	                   
	                   file.delete();
	                    
	                   System.out.println("Deleted the file: "+fileName);
	                   Listeners.reportlog("Deleted the file: "+fileName);
	                }
	                else {
	                	
	                    Listeners.reportlog("Unable to delete the file As file is not present");
	                    softAssert.assertEquals(false, true,"Unable to delete the file As file is not present");
                     //   System.out.println("Unable to delete the file As file is not present: "+fileName);          
	            }  
	            
	            softAssert.assertAll();  
	        }

  }
	
	public void Login_Auth() throws InterruptedException, AWTException {
		
		String User = properties.getProperty("user");
		
		String Password = properties.getProperty("password");

		  //wait - increase this wait period if required
        Thread.sleep(5000);

        //create robot for keyboard operations
        Robot rb = new Robot();

        //Enter user name by ctrl-v
        StringSelection username1 = new StringSelection(User);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(username1, null);            
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);

        //tab to go password field
        rb.keyPress(KeyEvent.VK_TAB);
        rb.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(2000);

        //Enter password by ctrl-v
        StringSelection pwd = new StringSelection(Password);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);

        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
      
        Thread.sleep(5000);
	}
	
	 public void uploadFile(WebDriver driver, String filePath, By fileInputLocator) {
	        // Create a File object for the specified file path
		 
		 String Path = System.getProperty("user.dir");
		 
	        File file = new File(Path+filePath);

	        // Find the file input field and set the file detector
	        RemoteWebElement fileInput = (RemoteWebElement) driver.findElement(fileInputLocator);
	        fileInput.setFileDetector(new LocalFileDetector());

	        // Upload the file by sending its absolute path
	        fileInput.sendKeys(file.getAbsolutePath());
	    }
	
	public void If_A_Element_Is_Visible_Then_Click_On_theVisible_Element(WebElement This_Element_Must_be_Visible_In_Page) {
     System.out.print("HClick");
		try {
        
     //	WebElement buttonElement = This_Element_Must_be_Visible_In_Page;
     	
			This_Element_Must_be_Visible_In_Page.click();
         
         System.out.println("Button clicked.");
    
     } catch (org.openqa.selenium.NoSuchElementException e) {
         System.out.println("Element is not present in page");
     }
 }
	
	
}

	
	


