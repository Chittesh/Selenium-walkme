package com.salesforce.apiCalls;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class ApiAuthorizationToken {
	   private static final String clientId = "3MVG9eQyYZ1h89HeO90UV6o6amO3SSg971FrziKWgzvSPxTRHFm.z3VGHIa3VHMxp_o3TwjCwuO310lUmGabl";
	   private static final String clientSecret = "5022ECFBB84540901A2435AA7A6B15A2D1450AEEC25CDC79F78E719BB14696B4";
	   private static final String API_URL = "https://test.salesforce.com";
	   private static final String route = "/services/oauth2/token";
	   private static final String USERNAME = "apiautouser@allegisgroup.com.load";
	   private static final String PASSWORD = "API_Aut0user";
	      
	   public String getToken() {
	      try {
	         return authenticate();
	      } catch (Exception e) {
	         System.out.println("Error: " + e.getMessage());
	      }
	      return null;
	   }
	   
	   private String authenticate() throws Exception {
	      HttpURLConnection connection = createConnection(API_URL + route);
	      writeRequestBody(connection);
	      String responseBody = readResponseBody(connection);
	      return extractToken(responseBody);
	   }
	   
	   private HttpURLConnection createConnection(String apiUrl) throws Exception {
	      URL url = new URL(apiUrl);
	      HttpURLConnection connection = (HttpURLConnection) url.openConnection();	      
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	      connection.setDoOutput(true);
	      return connection;
	   }
	   
	   private void setAuthorizationHeader(HttpURLConnection connection) throws Exception {
	      String credentials = USERNAME + ":" + PASSWORD;
	      String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
	      connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
	   }
	   
	   private void writeRequestBody(HttpURLConnection connection) throws Exception {
		  String requestBody = "grant_type=password&client_secret=" + clientSecret + "&client_id=" + clientId + "&username=" + USERNAME + "&password=" + PASSWORD;		   
	      DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
	      outputStream.writeBytes(requestBody);
	      outputStream.flush();
	      outputStream.close();
	   }
	   
	   private String readResponseBody(HttpURLConnection connection) throws Exception {
	      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	      String line;
	      StringBuffer response = new StringBuffer();
	      while ((line = reader.readLine()) != null) {
	         response.append(line);
	      }
	      reader.close();
	      return response.toString();
	   }
	   
	   private String extractToken(String responseBody) {
	      // Parse the response to extract the authorization token
	      return responseBody;
	   }
}
