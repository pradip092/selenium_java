package resources;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class WriteTestResults {
	
	public static void WriteTestResult2(String workItemId, String project, String environment, String status, String configName, String responsibleTeam, String personalAccessToken) {
	    try {
	        String endpoint = "http://localhost:8040/api/WorkItem/CreateTestPlan/";
	        String jsonPayload = "{\"workItemId\": \"" + workItemId + "\", \"project\": \"" + project + "\", \"environment\": \"" + environment + "\", \"status\": \"" + status + "\", \"configName\": \"" + configName + "\", \"ResponsibleTeam\": \"" + responsibleTeam + "\"}";

	        URL url = new URL(endpoint);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Authorization", "Basic " + personalAccessToken);
	        connection.setRequestProperty("Content-Type", "application/json");
	        connection.setDoOutput(true);

	        connection.getOutputStream().write(jsonPayload.getBytes());

	        int responseCode = connection.getResponseCode();
	        System.out.println("Response Code: " + responseCode);

	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();

	        System.out.println("Response: " + response.toString());

	        connection.disconnect();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
