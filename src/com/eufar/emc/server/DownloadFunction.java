/***LICENSE START
 * Copyright 2011 EUROPEAN UNION
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * 
 * Date: 03 January 2011
 * Authors: Marzia Grasso, Angelo Quaglia, Massimo Craglia
LICENSE END***/

package com.eufar.emc.server;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;


public class DownloadFunction extends HttpServlet {
	private static final long serialVersionUID = 4356636853168339046L;
	byte[] bbuf = new byte[1024];
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DownloadFunction - the function started");
		ServletContext context = getServletConfig().getServletContext();
		request.setCharacterEncoding("UTF-8");
		String dir = context.getRealPath("/tmp");;
		String filename = "";
		File fileDir = new File(dir);
		try {
			System.out.println("DownloadFunction - create the file on server");
			filename = request.getParameterValues("filename")[0];
			String xmltree =request.getParameterValues("xmltree")[0];
				
			// format xml code to pretty xml code
			Document doc = DocumentHelper.parseText(xmltree);  
	        StringWriter sw = new StringWriter();  
	        OutputFormat format = OutputFormat.createPrettyPrint();  
	        format.setIndent(true);
	        format.setIndentSize(4); 
	        XMLWriter xw = new XMLWriter(sw, format);  
	        xw.write(doc); 
	        String xmlString = sw.toString();
	        xmlString = xmlString.replace("\n\n", "\n");
	        
	        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir + "/" + filename), "UTF-8"));
	        out.append(xmlString);
			out.flush();
			out.close();
		} catch (Exception ex) {
			System.out.println("ERROR during rendering: " + ex);
		}
		try {
			System.out.println("DownloadFunction - send file to user");
			ServletOutputStream out = response.getOutputStream();
			File file = new File(dir + "/" + filename);
			String mimetype = context.getMimeType(filename);
			response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
			response.setContentLength((int) file.length());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");			
			response.setHeader("Pragma", "private");
			response.setHeader("Cache-Control", "private, must-revalidate");
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int length;
			while ((in != null) && ((length = in.read(bbuf)) != -1)) {
				out.write(bbuf, 0, length);
			}
			in.close();
			out.flush();
			out.close();
			FileUtils.cleanDirectory(fileDir);
		} catch (Exception ex) {
			System.out.println("ERROR during downloading: " + ex);
		}
		System.out.println("DownloadFunction - file ready to be donwloaded");
	}	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doPost(request, response);
	}
}