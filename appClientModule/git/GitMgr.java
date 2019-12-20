package git;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;

public class GitMgr {

	public static String gitURLPrj = "https://gitlab.pucrs.br/api/v4/projects";
	public static String gitURLPages = "/?per_page=100&page=1&private_token=";

	public static String token = "YaSyiqN6FZyy1Y6gzKsi";

	public static void main(String[] args) {
	    String result = null;
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
				
		try {
			HttpGet request = new HttpGet(gitURLPrj+gitURLPages+token);
			request.setHeader(HttpHeaders.ACCEPT, "application/json");
			response = client.execute(request);         
			System.out.println("Lista: "+response.getStatusLine());
			HttpEntity entity = response.getEntity();
		    if (entity != null) {
	
	            // A Simple JSON Response Read
	            InputStream instream = entity.getContent();
	            result = convertStreamToString(instream);
	            // now you have the string representation of the HTML request
	            instream.close();
	            /*if (response.getStatusLine().getStatusCode() == 200) {
            		netState.setLogginDone(true);
        		}*/
	            
	            // Headers
	            if(false) {
	            	System.out.println("RESPONSE: " + result);
		            org.apache.http.Header[] headers = response.getAllHeaders();
	            	for (int x = 0; x < headers.length; x++) {
	            		System.out.println(headers[x]);
	            	}
	            }
	            
            	JSONArray arr = new JSONArray(result);
	            for (int i = 0; i < arr.length(); i++) {
	            	int id = arr.getJSONObject(i).getInt("id");
	            	String http_url_to_repo = arr.getJSONObject(i).getString("http_url_to_repo");
	            	
	            	////// Get file
	            	
	            	//String urlRaw = gitURLPrj+ "/"+id+"/repository/files/README.md/raw?ref=master";
	            	//String urlRaw = gitURLPrj+ "/"+id+"/repository/files/README.md/raw?ref=master";
	            	String urlRaw = gitURLPrj+ "/"+id+"/repository/files/Jenkinsfile/raw?ref=develop";
	            	
	            	System.out.println(http_url_to_repo);
	            	/*
	            	System.out.println(id);
	            	System.out.println(urlRaw);
	            	*/
	            	
	            	String result2 = getFile(urlRaw);
	        		 // System.out.println(result2);
	            }
	          
		    }  
	   } catch (ClientProtocolException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    } catch (IOException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
		
	}

	private static String getFile(String urlRaw) {
		String result2 = "";
		CloseableHttpResponse response2 = null;
    	CloseableHttpClient client2 = HttpClients.createDefault();
		try {
			HttpGet request2 = new HttpGet(urlRaw);
			request2.setHeader(HttpHeaders.ACCEPT, "application/json");
			request2.setHeader("PRIVATE-TOKEN", token);
			response2 = client2.execute(request2);         
			System.out.println(response2.getStatusLine());
			HttpEntity entity2 = response2.getEntity();
		    InputStream instream2 = entity2.getContent();
            result2 = convertStreamToString(instream2);
            // now you have the string representation of the HTML request

            instream2.close();
		} catch (ClientProtocolException e2) {
	        // TODO Auto-generated catch block
	        e2.printStackTrace();
	    } catch (IOException e2) {
	        // TODO Auto-generated catch block
	        e2.printStackTrace();
	    } 
		
		//System.out.println("RESPONSE2: " + result2);
        
		return result2;
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
