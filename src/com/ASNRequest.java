package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class ASNRequest {
	
    static ArrayList<String> lines = new ArrayList<String>();
	private static String response;

	public static void main(String[] args) throws Exception {
		/* initial(); */
		/* Writer(); */
		Webpush();
		}
    
    
    public static void initial() throws Exception {
		FileReader indexReader = new FileReader("C:\\Users\\lester.john\\Desktop\\index.txt");
	    BufferedReader bufferedReader = new BufferedReader(indexReader);
	    String index=null;
	    
	    while ((index = bufferedReader.readLine()) != null) {
		ASNLoad(index);
	    /* System.out.println(index); */
	    }
	bufferedReader.close();
	}

    
	public static void ASNLoad(String pattern) throws IOException {
		FileReader fileReader = new FileReader("C:\\Users\\lester.john\\Desktop\\data-raw-table.txt");
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    /* ArrayList<String> lines = new ArrayList<String>(); */
	    String line = null;
	    
	    Pattern ASN = Pattern.compile(pattern);

	    while ((line = bufferedReader.readLine()) != null) {
	    	if(ASN.matcher(line).find() == true) {	
	        lines.add(line.replace(pattern, ""));
	        System.out.println(line.replace(pattern, ""));
	    	}
	    }
    bufferedReader.close();
	}
	
	
	public static void Writer() throws Exception {
	    Collections.sort(lines, Collator.getInstance());
	    
	    FileWriter writer = new FileWriter("C:\\Users\\lester.john\\Desktop\\asn.txt"); 
	    for(String str: lines) {
	    	writer.write(str + "\r\n");
	    	/* System.out.println(str); */
	    }
	writer.close();
	}
	
	
	public static void Webpush() throws Exception {

		String ccstr = "";
		String str = "";
		
		FileReader indexReader = new FileReader("C:\\Users\\lester.john\\Desktop\\asn.txt");
	    BufferedReader bufferedReader = new BufferedReader(indexReader);

	    while ((str = bufferedReader.readLine()) != null) {
	    ccstr = (ccstr+","+str);
	    }
	System.out.println(ccstr);
	WebConnect.sendPost("https://my.incapsula.com/api/prov/v1/sites/configure/acl", "api_id=19363&api_key=f63b9db9-6d41-48f8-b744-3a063025e4c5&site_id=1549083&rule_id=api.acl.whitelisted_ips&ips="+ccstr+"\r\n");
	bufferedReader.close();		
	}
}