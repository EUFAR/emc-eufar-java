
package com.eufar.emc.server;

import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@SuppressWarnings("hiding")
public class UploadFunction<FileItem> extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		@SuppressWarnings("unused")
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		  
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			while(iter.hasNext()){
				Object obj = iter.next();
				if(obj == null) continue;
		        org.apache.commons.fileupload.FileItem item = (org.apache.commons.fileupload.FileItem)obj;
		        if(item.isFormField()){
		        	String name = item.getName();
		        	String value = "";
		            if(name.compareTo("textBoxFormElement")==0){
		                value = item.getString();		                
		            } 
		            else {
		                value = item.getString();		                
		            }
		            response.getWriter().write(name + "=" + value + "\n");
		         } 
		         else {
	                byte[] fileContents = item.get();
	                String message = new String(fileContents);
	                response.setCharacterEncoding("UTF-8");
	                response.setContentType("text/html");
	        		response.getWriter().write(message);	        		
		         }
			}		   
		} catch (Exception ex) {			
			response.getWriter().write("ERROR:" + ex.getMessage());
		}
	}	
}