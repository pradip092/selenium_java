package resources;

public class Client_Names_And_Environments_URL  extends Base{
	public static String clientUrl;
    //@Parameters({"clientUrl"})

 

  public void client_Env_Url(String PPT) 

    {
//String parameter = context.getCurrentXmlTest().getParameter("PPT");    

//System.out.println("parameter"+parameter);

 

    clientUrl =System.getProperty("clientUrl");

    if(clientUrl.equalsIgnoreCase("IMRFDIT"))
    {

    if(PPT.equalsIgnoreCase("memberportal")) {
     getDriver().get(properties.getProperty("urlIMRFDIT"));    
    }

    else if(PPT.equalsIgnoreCase("employerportal")) {

    System.out.println("HIIIIIII");
     getDriver().get(properties.getProperty("IMRFQA"));    

    }


    }
    else if(clientUrl.equalsIgnoreCase("HOOPPMEMBERDIT"))
    {

        if(PPT.equalsIgnoreCase("memberportal")) {
             getDriver().get(properties.getProperty("urlIMRFDIT"));    
            }
            else if(PPT.equalsIgnoreCase("employer")) {

             getDriver().get(properties.getProperty("urlIMRFDIT"));    

            }
        // getDriver().get(properties.getProperty("HOOPPMEMBERDIT"));    
    }

    else if(clientUrl.equalsIgnoreCase("HoopUAT"))
    {

         getDriver().get(properties.getProperty("HoopUAT"));    

    }

    else if(clientUrl.equalsIgnoreCase("IMRFQA"))
    {

         getDriver().get(properties.getProperty("IMRFQA"));
    }

}
}
