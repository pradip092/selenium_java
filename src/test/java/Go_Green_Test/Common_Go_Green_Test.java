package Go_Green_Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import DB_PPT_Common_PageObjects.MyAccount_PgaeObject;
import GoGreenFlow.Go_GreenFlow;
import customListeners.TestListener;
import extentManager.ExtentTestManager;

import pageObjects.ROLProcess.Rol_process_Step1;
import resources.Base;
import resources.DataProviders;
import resources.Retry;

public class Common_Go_Green_Test extends Base  {

	
	
	ExtentTestManager Listeners = new ExtentTestManager();

	TestListener t = new TestListener();
	
	
	
	@Test(priority = 0, dataProvider = "MPC_PPT_Data", dataProviderClass = DataProviders.class, retryAnalyzer = Retry.class,groups = { "Go_Green_Test"},description="5113732")

	public void VerifyLogin(String validUserID, String validPassword) throws IOException, InterruptedException

	{
		Rol_process_Step1 f = new Rol_process_Step1(getDriver());
	    MyAccount_PgaeObject MyAp= new MyAccount_PgaeObject(getDriver());   
		
	    Go_GreenFlow g= new Go_GreenFlow(getDriver());
		desired_Environment_Portal_login( validUserID, validPassword);
	      Listeners.reportlog("Login Success..");
	  	  customWait(g.Go_Green_Btn, 20);
		g.Wait_until_Click_On_Go_Green();
	}
	
	
	@Test( priority=1,dataProvider = "MPC_PPT_Data", dataProviderClass = DataProviders.class,retryAnalyzer = Retry.class,groups = { "Go_Green_Test"},dependsOnMethods = {
	"VerifyLogin"},description="5113733")
	public void to_verify_Click_On_Go_greenBtn(String validUserID, String validPassword) throws InterruptedException, IOException
	{
		Go_GreenFlow f= new Go_GreenFlow(getDriver());
		
		Base.takeScreenshots();
		f.Click_On_Go_Green();
		Thread.sleep(2000);		
		Listeners.reportlog("Clicked on go green Btn");		
		Base.takeScreenshots();
	  
	}
	
	@Test( priority=2,dataProvider = "MPC_PPT_Data", dataProviderClass = DataProviders.class,retryAnalyzer = Retry.class,groups = { "Go_Green_Test"},dependsOnMethods = {
	"VerifyLogin"},description="5113734")
	public void to_verify_Click_On_PartmailID_RadioBtn(String validUserID, String validPassword) throws InterruptedException, IOException
	{
		Go_GreenFlow f= new Go_GreenFlow(getDriver());
		
		f.Wait_until_PartitionEmailId_radio();
		f.PartEmailId_radio();
				
		Base.takeScreenshots();
	    
	    Listeners.reportlog("Clicked on Part EmailId Radio Btn");
	  
	}
	
	@Test( priority=3,dataProvider = "MPC_PPT_Data", dataProviderClass = DataProviders.class,retryAnalyzer = Retry.class,groups = { "Go_Green_Test"},dependsOnMethods = {
	"VerifyLogin"},description="5113735")
	public void to_verify_Click_On_WorkmailID_RadioBtn(String validUserID, String validPassword) throws InterruptedException, IOException
	{
		Go_GreenFlow f= new Go_GreenFlow(getDriver());
		
		f.Work_EmailId_radio();
		
		Thread.sleep(2000);
		
		Base.takeScreenshots();
	    
		   Listeners.reportlog("Clicked on Work Mail ID Radio Btn");
	  
	}
	
	
	@Test( priority=4,dataProvider = "MPC_PPT_Data", dataProviderClass = DataProviders.class,retryAnalyzer = Retry.class,groups = { "Go_Green_Test"},dependsOnMethods = {
	"VerifyLogin"},description="5113738")
	public void to_verify_Click_On_WorkmailID_InputBoxBtn(String validUserID, String validPassword) throws InterruptedException, IOException
	{
		Go_GreenFlow f= new Go_GreenFlow(getDriver());
		
		f.WorkMail_Input();
				
		Base.takeScreenshots();
	    
	   Listeners.reportlog("Clicked on Work Mail ID Input Box..");
	  
	}
	
	@Test( priority=5,dataProvider = "MPC_PPT_Data", dataProviderClass = DataProviders.class,retryAnalyzer = Retry.class,groups = { "Go_Green_Test"},dependsOnMethods = {
	"VerifyLogin"},description="5113739")
	public void to_verify_Click_On_PartmailID_InputBox(String validUserID, String validPassword) throws InterruptedException, IOException
	{
		Go_GreenFlow f= new Go_GreenFlow(getDriver());
		
		f.Personal_Mail_Input();
				
		Base.takeScreenshots();
	    
	    Listeners.reportlog("Clicked on Part EmailId Input Box..");
	  
	}
	
	@Test( priority=6,dataProvider = "MPC_PPT_Data", dataProviderClass = DataProviders.class,retryAnalyzer = Retry.class,groups = { "Go_Green_Test"},dependsOnMethods = {
	"VerifyLogin"},description="5113740")
	public void to_verify_Click_On_OutOf_Mail_RadioBox(String validUserID, String validPassword) throws InterruptedException, IOException
	{
		Go_GreenFlow f= new Go_GreenFlow(getDriver());
		
		f.Out_Of_Mail();
				
		Base.takeScreenshots();
	    
	    Listeners.reportlog("Clicked on  Out Of Mail Radio Box..");
	  
	}
	
	@Test( priority=7,dataProvider = "MPC_PPT_Data", dataProviderClass = DataProviders.class,retryAnalyzer = Retry.class,groups = { "Go_Green_Test"},dependsOnMethods = {
	"VerifyLogin"},description="5113741")
	public void to_verify_TextBox_Phone(String validUserID, String validPassword) throws InterruptedException, IOException
	{
		Go_GreenFlow f= new Go_GreenFlow(getDriver());
		
		f.Text_Box_Phone();
		
		Thread.sleep(2000);
		
		Base.takeScreenshots();
	    
	    Listeners.reportlog("Clicked on  Phone Text Box..");
	  
	}
	
	@Test( priority=8,dataProvider = "MPC_PPT_Data", dataProviderClass = DataProviders.class,retryAnalyzer = Retry.class,groups = { "Go_Green_Test"},dependsOnMethods = {
	"VerifyLogin"},description="5113742")
	public void to_verify_Radio_Btn_OUTOF_Phone(String validUserID, String validPassword) throws InterruptedException, IOException
	{
		Go_GreenFlow f= new Go_GreenFlow(getDriver());
		
		f.Radio_outofphone();
			
		Base.takeScreenshots();
	    
	    Listeners.reportlog("Clicked on OutOf Phone Radio Btn..");
	  
	}
	
	@Test( priority=9,dataProvider = "MPC_PPT_Data", dataProviderClass = DataProviders.class,retryAnalyzer = Retry.class,groups = { "Go_Green_Test"},dependsOnMethods = {
	"VerifyLogin"},description="5113743")
	public void to_verify_Click_On_Skip_Btn(String validUserID, String validPassword) throws InterruptedException, IOException
	{
		Go_GreenFlow f= new Go_GreenFlow(getDriver());
		
		f. Skip_Text();
				
		Base.takeScreenshots();
		
		f.Popup_Next_Btn();
			    
	    Listeners.reportlog("Clicked on Skip Btn..");
	  
	}
	    
}
