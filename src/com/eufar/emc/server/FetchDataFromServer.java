 
package com.eufar.emc.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FetchDataFromServer extends HttpServlet {
	private static final long serialVersionUID = 7942567940558056865L;	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("FetchDataFromServer - the function started");
		BufferedReader reader = null;
		HashMap<String, String> urlMap = new HashMap<String, String>();
		urlMap.put("projects", "http://xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		urlMap.put("aircraft", "http://xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		urlMap.put("operators", "http://xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		urlMap.put("instruments", "http://xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		
	    try {
	    	String flux = req.getParameter("flux");
	    	System.out.println("FetchDataFromServer - flux: " + flux);
	    	URL url = new URL(urlMap.get(flux));
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 
	        resp.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF8"), true);
	        out.println(buffer.toString());
	        out.flush();
	        
	    } finally {
	        if (reader != null) {
	        	reader.close();
	        }
	    }
	}
}