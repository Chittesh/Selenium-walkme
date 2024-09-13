package com.salesforce.apiCalls;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiManager {
	private static final String baseURI = "https://allegisgroup--load.sandbox.my.salesforce.com";	
	public String  createGetRequest(String token, String query, String endPoint) {
		String apiResponse;
	    try {
	    	URL url = new URL(baseURI + endPoint);
	    	HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    	conn.setRequestMethod("GET");
	    	setRequestProperty(token, conn);    	
	    	WriteQueryToOutputStreem(conn, query);
	    	apiResponse = this.readResponseBody(conn);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    
	    return apiResponse;
	}	
	public String createPostRequest(String token, String query, String endPoint) {
		String apiResponse;
	    try {	
	    	URL url = new URL(baseURI + endPoint);
	    	HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    	conn.setRequestMethod("POST");
	    	setRequestProperty(token, conn);    	
	    	WriteQueryToOutputStreem(conn, query);
	    	apiResponse = this.readResponseBody(conn);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }	    
		return apiResponse;
	}
	
	public String createPutRequest(String token, String query, String endPoint) {
		String apiResponse;
	    try {	
	    	URL url = new URL(baseURI + endPoint);
	    	HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    	conn.setRequestMethod("PUT");
	    	setRequestProperty(token, conn);    	
	    	WriteQueryToOutputStreem(conn, query);
	    	apiResponse = this.readResponseBody(conn);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }	    
		return apiResponse;
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
	
	private void WriteQueryToOutputStreem(HttpURLConnection conn, String query) throws IOException {
		OutputStream os = conn.getOutputStream();
    	os.write(query.getBytes());
    	os.flush();	    	
    	os.close();
	}
		
	private void setRequestProperty(String token, HttpURLConnection conn) {
		conn.setRequestProperty("Authorization", "Bearer " + token);
    	conn.setRequestProperty("Accept", "application/json");
    	conn.setRequestProperty("Content-Type", "application/json");
    	conn.setDoOutput(true);
	}
	
	
}
