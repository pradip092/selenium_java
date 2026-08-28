package resources;

import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import resources.Base;


public class DataProviders extends Base  {
	
	public String parameter;
	
	
	@DataProvider
	public Object[][] MPC_PPT_Data(ITestContext context)
	{
		
	    parameter = context.getCurrentXmlTest().getParameter("PPT");
	//    parameter="DB_Active_Retirement_Single_Plan_CFG_PPT_TestData";
        System.out.println("uio:-"+parameter);
		System.out.println("MPC PPT value"+parameter);
	   	
		 Object[][] data;
		 data = new Object[1][2];
		
		if(parameter.equalsIgnoreCase("DB_Test"))
		{
	            data[0][0] = "msaini@gmail.com";
		        data[0][1] = "MBCTRA@001";
        }
	

		return data;
	}
	
}