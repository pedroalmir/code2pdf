/**
 * 
 */
package com.pedroalmir.code2pdf.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.pedroalmir.code2pdf.model.Model;
import com.pedroalmir.code2pdf.service.JavaPDFSourceCodeConverter;
import com.pedroalmir.code2pdf.util.HttpRequestMultipart;


/**
 * @author Pedro Almir
 *
 */
@SuppressWarnings("serial")
public class ConverterServlet extends HttpServlet {
	
	/**
	 * Buffer size
	 */
	private static final int BUFSIZE = 4096;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		try {
			/* Check that we have a file upload request */
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart){
				Model model = HttpRequestMultipart.processRequest(request);
				System.out.println(model);
				
				ByteArrayOutputStream outputStream = JavaPDFSourceCodeConverter.execute(model);
				/**/
				response.setContentType("application/pdf");
				response.setContentLength(outputStream.size());
				/* sets HTTP header */
		        //response.setHeader("Content-Disposition", "attachment; filename=\"result.pdf\"");
		        
		        int length   = 0;
		        byte[] data = outputStream.toByteArray();
		        byte[] byteBuffer = new byte[BUFSIZE];
		        
		        DataInputStream in = new DataInputStream(new ByteArrayInputStream(data));
				ServletOutputStream outStream = response.getOutputStream();
		        
		        /* reads the file's bytes and writes them to the response stream */
		        while ((in != null) && ((length = in.read(byteBuffer)) != -1)){
		            outStream.write(byteBuffer, 0, length);
		        }
		        
		        in.close();
		        outStream.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}
}