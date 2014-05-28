/**
 * 
 */
package com.pedroalmir.code2pdf.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.pedroalmir.code2pdf.model.Model;

/**
 * Http Request Multipart manager
 * @author Pedro Almir
 */
public class HttpRequestMultipart {

	/**
	 * Process request and create user with image
	 * 
	 * @param request
	 * @return model
	 */
	public static Model processRequest(HttpServletRequest request){
		Model model = new Model();
		String value = "";
		ServletFileUpload upload = new ServletFileUpload();
		FileItemIterator iter;

		try {
			iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream fileItem = iter.next();
				if (fileItem.isFormField()) {

					InputStream stream = fileItem.openStream();
					byte[] str = new byte[stream.available()];
					stream.read(str);
					value = new String(str, "UTF8");
					
					if(fileItem.getFieldName().equals("extensions")){
						//System.out.println(value);
						if(!value.trim().isEmpty()){
							model.addFileType(value.trim());
						}
					}else if(fileItem.getFieldName().equals("projectName")){
						model.setProjectName(value);
					}
				} else {
					if(fileItem.getFieldName().equals("inputFile")){
						InputStream fileStream = fileItem.openStream();
						
						String fileName = fileItem.getName();
						String mime = fileItem.getContentType();
						byte[] byteArray = IOUtils.toByteArray(fileStream);
						
						model.setCodeFile(byteArray);
						model.setCodeFileName(fileName);
						model.setCodeMimeType(mime);
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}

}
