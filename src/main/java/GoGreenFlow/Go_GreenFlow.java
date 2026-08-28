package GoGreenFlow;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;  
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractComponent;

public class Go_GreenFlow  extends AbstractComponent{

		public WebDriver driver;
		
		public Go_GreenFlow(WebDriver driver) {
			 super(driver);
			this.driver=driver;
			
		}
		
		//#<<<<<<<<<<-----------My Account----------------------------------->>>
		By MyAccount=By.xpath("//span[@class='item-name' and contains(text(),'My Account')]");
		By MyAccountDrpdwn=By.xpath(" //div[@menu-item='mainMenu.myAccount']/div/div/div/div/ul/li/div/span/a/span");
	    By Profile_drpdwn=By.xpath("//span[@class='ng-binding ng-scope' and contains(text(),'Profile')]");
	//#<-------------------
	    By MyAccountPageHead_text=By.xpath("//p[@data-bo-bind='staticContent.Terms.MyAccount']");
	    By MyAccount_Headers_Tabs=By.xpath("//*[@class='track-scrollable-wrapper js-nav-sup-wrapper'] //ul[@class='tabs track-scrollable-content js-nav-sup']/li/a");
	//#<------------Personal information--------------------------------->    
		By PersonalInformation_head_text=By.xpath("//h1[@class='page-head-title ng-binding']");
		By Profile_PersonalInformation_tab=By.xpath("//*[@role='tab' and contains(text(),'Personal Information')]");
	   
		By Profile_PersonalInforamPage_PersonName=By.xpath("//*[@class='section-title size-large ng-binding']");
		
		By Personal_information_Page_Panel_tiles=By.cssSelector("[class*='panel-title']");
	    By Profile_Data=By.xpath("//*[@content-source='profileData']");
		//<----##Personal Info Sections--->
		By contactInformationHeader=By.xpath("//*[@data-ng-bind='content.ContactInformation']");
		By PrimaryAddressHeader=By.xpath("//*[@data-bo-bind='content.PrimaryAddress']");
		By Primyadd_PersonalEmail_text=By.xpath("//*[@data-profile-email='PartProvidedEmail']/span");
		
		By Alternatinmailingaddresheader=By.xpath("//*[@data-bo-bind='content.SecondaryAddress']");
		By EditBtton=By.xpath("//*[@data-bo-bind='static.Terms.Edit']");
		
	//##########################################################################################
		
	//<--------------------------------------------Edit page -------------------------------------------------------------------------->
		By EditPagetext=By.xpath("//*[@data-ng-bind='static.Terms.AllFieldsRequired']");
		By Panel_titles=By.cssSelector("[class*='panel-title']");
		//<------Your Name-------->
		By Edit_firstName=By.xpath("//*[@aria-label='First Name']");
		
		By Edit_MidleNAme=By.xpath("//*[@aria-label='Middle Initial']");
		//By Edit_MidleNAme=By.cssSelector("[aria-label*='Middle Initial']");
		By Edit_Last_Name=By.xpath("//*[@aria-label='Last Name']");
		//<----Personal information--->
		By Edit_Dob=By.xpath("//*[@aria-label='Date of Birth (MM/DD/YYYY)']");
		By Edit_EmployeeNumber=By.xpath("//*[@aria-label='Employee Number']");
		By Edit_MedicalBeneficiaryItem=By.xpath("//*[@aria-label='Medicare Beneficiary Identifier']");
		By Edit_PleaseEnter_Valid_value_text=By.xpath("//*[contains(text(),'Please Provide A Valid Value')]");
		By Edit_you_have_Exceed_Maximum_Length=By.xpath("//*[contains(text(),'Please Provide A Valid Value')]/preceding-sibling::div[1]");
		By Edit_Martial_Status_Select=By.xpath("//*[@data-select2-id='1']");
		By Edit_MartialStatusArrowbutton=By.xpath("//*[@data-ng-if='profileModel.MaritalStatus.displayed'] //*[@class='select2-selection__arrow']");
		By Edit_MartialStatustextbox=By.xpath("//*[@data-ng-if='profileModel.MaritalStatus.displayed'] //*[@role='textbox']");
		By Edit_MartialStatusDropDownList=By.xpath("//*[@role='listbox']/li");
		By Edit_Gender_Select=By.xpath("//*[@data-select2-id='4']");
		By Edit_GenderoptionArrowButton=By.xpath("//*[@data-ng-if='profileModel.Gender.displayed'] //*[@class='select2-selection__arrow']");
		By Edit_GerndedropdownList=By.xpath("//*[@role='listbox']/li");
		By Edit_GenderOptiontextbox=By.xpath("//*[@data-ng-if='profileModel.Gender.displayed'] //*[@role='textbox']");
		
		//<---Contact information----->
		
		By Edit_companyProvideEmail1=By.cssSelector("*[aria-label*='Company Provided Email']");
		By Please_Enter_valid_Email_ID=By.xpath("//*[contains(text(),'Please enter a valid Email.')]");
		By Edit_PersonalEmail=By.xpath("//*[contains(text(),'Personal Email')]");
		By Edit_workphone=By.cssSelector("*[aria-label*='Work Phone']");
		By Please_Enter_valid_Phn_no=By.cssSelector("//*[contains(text(),'Please enter a valid phone number.')]");
	    By Edit_mobilephone=By.cssSelector("*[aria-label*='Mobile Phone']");
	    By Edit_HomePhone=By.cssSelector("*[aria-label*='Home Phone']");
		
		//<---Primary Address------------->
		
		By Edit_CountryArrowButton=By.xpath("//*[@ng-if='address.Country.displayed'] //*[@class='select2-selection__arrow']");
		By Edit_CountryOptionList=By.xpath("//*[@role='listbox']/li");
		By Edit_CountrytextBox=By.xpath("//*[@ng-if='address.Country.displayed'] //*[@role='textbox']");
		By Edit_PrimaryBC_Permananent_Addres1=By.xpath("//*[@aria-label='Address 1']");
		 
		By Edit_Primary_Address2_Optional=By.xpath("//*[@aria-label='Address 2']");
	    By Edit_Primary_City=By.xpath("//*[@aria-label='City']");
	    By Edit_Primary_Zip_Postal_code=By.cssSelector("*[aria-label*='Zip/Postal Code']");
	    
	    //<----AleternateMailing Address-->
	    By Edit_Bc_AlternateMailAddes1=By.xpath("//*[@aria-label='Address 2']");
	    By Edit_Alternate_Address2_Optional=By.xpath("//*[@aria-label='Address 2']");
	    By Edit_Alternate_City=By.xpath("//*[@aria-label='City']");
	    By Edit_Alternate_Zip_Postal_code=By.xpath("//*[@aria-label='Zip/Postal Code']");
	    By Edit_CancelButton=By.xpath("//*[@data-bo-bind='static.Terms.Cancel']");
	    By Edit_SaveButton=By.xpath("//*[@data-bo-bind='static.Terms.Save']");
	    
	    
	    //--------------Go Green--------------------------------------------
	 public   By Go_Green_Btn=By.xpath("//*[text()='Go green']");
	    
	    By Work_EmailId_radio=By.xpath("//*[@id='workEmailId']");
	    
	    By PartEmailId_radio=By.xpath("//*[@id='partEmailId']");
	    
	    By WorkMail_Input=By.xpath("//input[@name='workEmail']");
	    By Personal_Mail_Input=By.xpath("//input[@name='personalEmail']");
	    
	    
	    By Out_Of_Mail=By.xpath("//input[@id='optOutEmailId']");
	    
	    By Radio_PHONE_Input=By.xpath("//input[@name='radioPhone']");
	    
	    By Text_Box_Phone=By.xpath("//input[@name='personalNumber']");
	    
	    By Radio_outofphone=By.xpath("//input[@id='optOutPhone']");
	    
	    By Skip_Text=By.xpath("//*[text()='Skip']");
	    
	    By Popup_Next_Btn=By.xpath("//*[text()='Next']");
	    
	    
	    
	    public void  Click_On_Go_Green()
	    {
	    	
	    	 WebElement element=driver.findElement(Go_Green_Btn);
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	    		js.executeScript("arguments[0].click();", element);  
	    	  //driver.findElement(Add_Bank_Account_button);
	    }
	    
	    
	    public void  Wait_until_Click_On_Go_Green()
	    {
	    	
	    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	
		    	wait.until(ExpectedConditions
		    			.visibilityOfElementLocated(Go_Green_Btn));
	    }
	    
	    public void  Work_EmailId_radio()
	    {
	    	
	    	 WebElement element=driver.findElement(Work_EmailId_radio);
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	    		js.executeScript("arguments[0].click();", element);  
	    	  //driver.findElement(Add_Bank_Account_button);
	    }
	    
	    public void  PartEmailId_radio()
	    {
	    	
	    	 WebElement element=driver.findElement(PartEmailId_radio);
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	    		js.executeScript("arguments[0].click();", element);  
	    	  //driver.findElement(Add_Bank_Account_button);
	    }
	    
	    
	    public void  Wait_until_PartitionEmailId_radio()
	    {
	    	
	    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		    	
		  	wait.until(ExpectedConditions
		    			.visibilityOfElementLocated(PartEmailId_radio)); 
		    	
	    }
	    
	    public void  WorkMail_Input() throws InterruptedException
	    {
	    	
	    	/* WebElement element=driver.findElement(PartEmailId_radio);
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	    		js.executeScript("arguments[0].click();", element);   */
			driver.findElement(WorkMail_Input).sendKeys(Keys.CONTROL,"a",Keys.DELETE);
	    	  driver.findElement(WorkMail_Input).sendKeys("pradip064@gmail.com");
	    	
	    	  Thread.sleep(2000);
	    	  
	    	  driver.findElement(WorkMail_Input).sendKeys(Keys.CONTROL,"a",Keys.DELETE);
	    	  
	    }
	    
	    
	    public void  Personal_Mail_Input() throws InterruptedException
	    {
	    	
	    	/* WebElement element=driver.findElement(PartEmailId_radio);
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	    		js.executeScript("arguments[0].click();", element);   */
	    	
	    	driver.findElement(Personal_Mail_Input).sendKeys(Keys.CONTROL,"a",Keys.DELETE);
	    	  driver.findElement(Personal_Mail_Input).sendKeys("pradip065@gmail.com");
	    	  
	    	  Thread.sleep(2000);
	    	  
	    		driver.findElement(Personal_Mail_Input).sendKeys(Keys.CONTROL,"a",Keys.DELETE);
	    }
	    
	    public void Out_Of_Mail()
	    {
	    	
	    	 WebElement element=driver.findElement(Out_Of_Mail);
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	    		js.executeScript("arguments[0].click();", element);  
	    	  //driver.findElement(Add_Bank_Account_button);
	    }
	 
	    public void  Text_Box_Phone() throws InterruptedException
	    {
	    	
	    	/* WebElement element=driver.findElement(PartEmailId_radio);
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	    		js.executeScript("arguments[0].click();", element);   */
	    	
	    	driver.findElement(Text_Box_Phone).sendKeys(Keys.CONTROL,"a",Keys.DELETE);
	    	  driver.findElement(Text_Box_Phone).sendKeys("pradip065@gmail.com");
	    	  
	    	  Thread.sleep(2000);
	    	  
	    		driver.findElement(Text_Box_Phone).sendKeys(Keys.CONTROL,"a",Keys.DELETE);
	    }
	    
	    public void Radio_outofphone()
	    {
	    	
	    	 WebElement element=driver.findElement(Radio_outofphone);
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	    		js.executeScript("arguments[0].click();", element);  
	    	  //driver.findElement(Add_Bank_Account_button);
	    }
	    
	    public void Skip_Text()
	    {
	    	
	    	 WebElement element=driver.findElement(Skip_Text);
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	    		js.executeScript("arguments[0].click();", element);  
	    	  //driver.findElement(Add_Bank_Account_button);
	    }
	    
	    public void  Popup_Next_Btn()
	    {
	    	
	    	 WebElement element=driver.findElement(Popup_Next_Btn);
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	    		js.executeScript("arguments[0].click();", element);  
	    	  //driver.findElement(Add_Bank_Account_button);
	    }
	    
	    
	    public List<WebElement> Edit_Primary_Zip_Postal_code()
	  	{
	  		return driver.findElements(Edit_Primary_Zip_Postal_code);
	  	}
	    
	    public WebElement Please_Enter_valid_Phn_no()
	  	{
	  		return driver.findElement(Please_Enter_valid_Phn_no);
	  	}
	    
	    public WebElement Please_Enter_valid_Email_ID()
	  	{
	  		return driver.findElement(Please_Enter_valid_Email_ID);
	  	}
	    public WebElement Edit_HomePhone()
	  	{
	  		return driver.findElement(Edit_HomePhone);
	  	}
	   
	    public WebElement Edit_mobilephone()
	  	{
	  		return driver.findElement(Edit_mobilephone);
	  	}
	    
	    public WebElement Edit_workphone()
	  	{
	  		return driver.findElement(Edit_workphone);
	  	}
	    
	    public WebElement Edit_PersonalEmail()
	  	{
	  		return driver.findElement(Edit_PersonalEmail);
	  	}
	    
	    public WebElement Edit_companyProvideEmail1()
	  	{
	  		return driver.findElement(Edit_companyProvideEmail1);
	  	}
	    
	    public WebElement Edit_Gender_Select()
	  	{
	  		return driver.findElement(Edit_Gender_Select);
	  	}

	    public WebElement Edit_you_have_Exceed_Maximum_Length()
	  	{
	  		return driver.findElement(Edit_you_have_Exceed_Maximum_Length);
	  	}
	    
	    public WebElement Edit_PleaseEnter_Valid_value_text()
	  	{
	  		return driver.findElement(Edit_PleaseEnter_Valid_value_text);
	  	}
	    
	    public WebElement Edit_MedicalBeneficiaryItem()
	  	{
	  		return driver.findElement(Edit_MedicalBeneficiaryItem);
	  	}
	    
	    
	    public WebElement Edit_Martial_Status_Select()
	  	{
	  		return driver.findElement(Edit_Martial_Status_Select);
	  	}
	    
	    public WebElement Edit_EmployeeNumber()
	  	{
	  		return driver.findElement(Edit_EmployeeNumber);
	  	}
	    
	    public WebElement Edit_CancelButton()
	  	{
	  		return driver.findElement(Edit_CancelButton);
	  	}
	    
	    public WebElement Profile_Data()
	  	{
	  		return driver.findElement(Profile_Data);
	  	}
	    
	    public List<WebElement> Personal_information_Page_Panel_tiles()
	  	{
	  		return driver.findElements(Personal_information_Page_Panel_tiles);
	  	}
	    
	  
	    public List<WebElement> Panel_titles()
	  	{
	  		return driver.findElements(Panel_titles);
	  	}
	    
	    public WebElement Edit_Dob()
	  	{
	  		return driver.findElement(Edit_Dob);
	  	}
	    public WebElement Edit_Last_Name ()
	  	{
	  		return driver.findElement(Edit_Last_Name);
	  	}
	   
	    public WebElement Edit_firstName ()
	  	{
	  		return driver.findElement(Edit_firstName);
	  	}
	   
	    public WebElement Edit_MidleNAme()
	  	{
	  		return driver.findElement(Edit_MidleNAme);
	  	}
	  
	   public WebElement Edit_SaveButton ()
	  	{
	  		return driver.findElement(Edit_SaveButton);
	  	}
	   
	    
	    public WebElement Edit_PrimaryBC_Permananent_Addres1 ()
		{
			return driver.findElement(Edit_PrimaryBC_Permananent_Addres1);
		}
	    public WebElement EditPagetext ()
	  	{
	  		return driver.findElement(EditPagetext);
	  	}
	    
	    public WebElement Primyadd_PersonalEmail_text ()
	  	{
	  		return driver.findElement(Primyadd_PersonalEmail_text);
	  	}
	    
	    public WebElement PrimaryAddressHeader ()
	  	{
	  		return driver.findElement(PrimaryAddressHeader);
	  	}
	    public WebElement SaveButton ()
	  	{
	  		return driver.findElement(Edit_SaveButton);
	  	}
	    
	    public WebElement PersonalEmail()
	  	{
	  		return driver.findElement(Edit_PersonalEmail);
	  	}
	      
	    
	    public WebElement EditBtton()
		{
			return driver.findElement(EditBtton);
		}
	    
	    public WebElement Profile_PersonalInforamPage_PersonName()
		{
			return driver.findElement(Profile_PersonalInforamPage_PersonName);
		}

	    
	    public WebElement companyProvideEmail1()
		{
			return driver.findElement(Edit_companyProvideEmail1);
		}
		
		public WebElement Profile_drpdwn()
		{
			return driver.findElement(Profile_drpdwn);
		}
		
		public WebElement PersonalInformation_header_text()
		{
			return driver.findElement(PersonalInformation_head_text);
		}

		public WebElement Edit_CountryArrowButton() {
			// TODO Auto-generated method stub
			return driver.findElement(Edit_CountryArrowButton);
		}
		
}
