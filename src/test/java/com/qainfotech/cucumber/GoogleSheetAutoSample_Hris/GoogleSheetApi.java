package com.qainfotech.cucumber.GoogleSheetAutoSample_Hris;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;


public class GoogleSheetApi {
	
	  private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	    private static final String CREDENTIALS_FOLDER = "credentia"; // Directory to store user credentials.
		
	    /**
	     * Global instance of the scopes required by this quickstart.
	     * If modifying these scopes, delete your previously saved credentials/ folder.
	     */
	    private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS);
	    private static final String CLIENT_SECRET_DIR = "client_secret.json";
	    public Sheets service;
	    List<List<Object>> values;
	    final String spreadsheetId = "1kxIM3TvjkCd5Gk5ILj89jqN-YOmuE_kkQSwW1gAFNSo";
	    
	    /**
	     * Creates an authorized Credential object.
	     * @param HTTP_TRANSPORT The network HTTP Transport.
	     * @return An authorized Credential object.
	     * @throws IOException If there is no client_secret.
	     */
	    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
	        // Load client secrets.
	        InputStream in = GoogleSheetApi.class.getResourceAsStream(CLIENT_SECRET_DIR);
	        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
            
	        // Build flow and trigger user authorization request.
	        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
	                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
	                .setAccessType("offline")
	                .build();
	        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	    }
	    
	    
	    private void GetData() throws Exception{
	    	 final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	         final String range = "Valid_Credentials!A1:F";
	        service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
	                 .setApplicationName(APPLICATION_NAME)
	                 .build();
	         ValueRange response = service.spreadsheets().values()
	                 .get(spreadsheetId, range)
	                 .execute();

	         values = response.getValues();
	      
	    }
	    
	    public String Update (String id,String status) throws Exception {
	    	List<List<Object>> Update_value = new ArrayList<List<Object>>();
	        List<Object> add; 
	        add = new ArrayList<Object>();
	        add.add(status);
	        Update_value.add(add);
	        
	        GetData();
	        String row = FindRow(id);
	        
	        ValueRange body = new ValueRange()
	      	      .setValues(Update_value);
	      	    UpdateValuesResponse result =service.spreadsheets().values()
	      	      .update(spreadsheetId, "Valid_Credentials!E"+row, body).setValueInputOption("RAW")
	      	      .execute();
	      	    return status;
	    }
	    
	    


		private String FindRow(String id) {
			int i=0;
			 for (List row : values) {
	               if(row.get(0).equals(id)) {
	            	   break;
	            	   
	               }
	               i++;
	            }
						return Integer.toString(i+1);
		}

}