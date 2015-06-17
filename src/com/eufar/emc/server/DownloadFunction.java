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


public class DownloadFunction extends HttpServlet {
	private static final long serialVersionUID = -4356636877078339046L;
	byte[] bbuf = new byte[1024];
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			String filename = "";
			ServletContext context = getServletConfig().getServletContext();
			request.setCharacterEncoding("UTF-8");
			String dir = "";
			if (context.getRealPath("tmp")==null) {dir = context.getRealPath("/tmp");}
			else {dir = context.getRealPath("tmp");}
			File fileDir = new File(dir);
			try {
				String[] attrArray = request.getParameterValues("filename");			
				if(attrArray != null) {filename = attrArray[0];}
				String xmltree = "";
				attrArray = request.getParameterValues("xmltree");
				if(attrArray != null) {xmltree = attrArray[0];}
				Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir + "/" + filename), "UTF-8"));
				out.append(xmltree);
				out.flush();
				out.close();
			} catch (IOException e) {}
			try {	    	
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
				while ((in != null) && ((length = in.read(bbuf)) != -1)) {out.write(bbuf, 0, length);}
				in.close();
				out.flush();
				out.close();
				FileUtils.cleanDirectory(fileDir);
			} catch (Exception e) {}
	}	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			doPost(request, response);
	}
}