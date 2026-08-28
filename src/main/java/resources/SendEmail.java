package resources;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;

public class SendEmail {
	@Test(groups = {"sendmails"})
	public  void emailSending() throws UnsupportedEncodingException {
	
		String recipientEmail = "nandan.gupta@lifeworks.com";
		
		//String recipientEmail = "Gaurav.sikka@lifeworks.com";

        // Set mail properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", "tor-hub.msoit.com");
     //   props.put("mail.smtp.port", "25");

        // Create session without authentication
        Session session = Session.getInstance(props);

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("nandan.gupta@lifeworks.com"));
            message.setRecipients(Message.RecipientType.TO,
            	InternetAddress.parse("nandan.gupta@lifeworks.com"));  
          //  InternetAddress.parse("pradeep.nayak@lifeworks.com,nandan.gupta@lifeworks.com,ankita.goyal@lifeworks.com,askar.sachan@lifeworks.com,hemant.singh@lifeworks.com,kamil.khan@lifeworks.com,preeti.bhatia@lifeworks.com,priyanka.sethi@lifeworks.com,rohit.nagar@lifeworks.com"));
            message.setSubject("Automation Test Report");
            message.setText("FYI Test Report by Automation");

                              

            
        	// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText("FYI Test Report by Automation");

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is image attachment
			messageBodyPart = new MimeBodyPart();
			//String filename = System.getProperty("user.dir") + "\\Automation_Reports\\Test-Automaton-Report.html";
			String ReportFilePath=System.getProperty("user.dir");
			System.out.println("ReportFilePath--::="+ReportFilePath);
		String filename=System.getProperty("user.dir")+"//Automation_Reports//Test-Automation-Report.html";

		//String filename =System.getenv("build.artifactstagingdirectory") + "//Automation_Reports//Test-Automaton-Report.html";

        System.out.println("buildi" +filename);
			System.out.println();
			//String filename = "$(build.artifactstagingdirectory)/drop/Test-Automation-Report.html";

			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			// Trick is to add the content-id header here
			messageBodyPart.setHeader("Content-ID", "image_id");

			multipart.addBodyPart(messageBodyPart);
			// multipart.addBodyPart(messageBodyPart2);

			// third part for displaying image in the email body
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("<h1>Automation Test Result For EAS Arial</h1>", "text/html");
			multipart.addBodyPart(messageBodyPart);

			// Set the multipart message to the email message
			message.setContent(multipart);
            
            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }	
	
		
	
	//}
		
	//}


}
