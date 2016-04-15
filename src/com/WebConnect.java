package com;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lesterjohn
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL; 
// To implement https uncomment and call function HttpsURLConnection
import javax.net.ssl.HttpsURLConnection;

public class WebConnect {
    
static int responseCode;

	// HTTP GET request
	public static String sendGet(String url) throws Exception {
 
                // Examples of Parameters
                // String url = "http://www.google.com/search";
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
 
		responseCode = con.getResponseCode();
		// System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);
                StringBuffer response;
                try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null)
					response.append(inputLine);
            }
 
		// print result
		// System.out.println(response.toString());
                
                return response.toString();
 
	}
        
        // HTTP POST request
	public static String sendPost(String url, String urlParameters) throws Exception {
 

        URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		// Send post request
		con.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
        	wr.writeBytes(urlParameters);
        	wr.flush();
            }
 
		responseCode = con.getResponseCode();
		// System.out.println("\nSending 'POST' request to URL : " + url);
		// System.out.println("Post parameters : " + urlParameters);
		// System.out.println("Response Code : " + responseCode);
            StringBuffer response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null)
					response.append(inputLine);
            }

            //print result
            System.out.println(response.toString());
            return response.toString();
	}
}