package UtilitiesMethod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UtilitiesMethods {
	
	public static String fetchCurrentDate()
	{
		 String filePath = "C://Users//next_indian_date.txt";
		 String CurrentDate;
	      
	     // Get the last 7 stored next Indian dates or create a new list if the file is empty
	        List<LocalDate> nextIndianDates = new ArrayList<>();
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader(filePath));
	            String storedDate;
	            while ((storedDate = reader.readLine()) != null) {
	                nextIndianDates.add(LocalDate.parse(storedDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")));
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Get the current Indian date
	        LocalDate currentDate = LocalDate.now();

	        // Print the current Indian date
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	         CurrentDate=  currentDate.format(formatter);
	        System.out.println("Current Indian Date: " + currentDate.format(formatter));

	        // Add the current date to the list
	        nextIndianDates.add(currentDate);

	        // If there are more than 7 dates stored, remove the oldest date
	        if (nextIndianDates.size() > 7) {
	            nextIndianDates.remove(0);
	        }

	        // Store the last 7 next Indian dates in the file for future use
	        try {
	            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
	            for (LocalDate date : nextIndianDates) {
	                writer.write(date.format(formatter));
	                writer.newLine();
	            }
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return CurrentDate;
	    }
	
	

}
