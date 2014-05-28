package com.pedroalmir.code2pdf.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FilenameUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pedroalmir.code2pdf.model.Model;

/**
 * @author Pedro Almir
 */
public class JavaPDFSourceCodeConverter {

	private final static int pdfMargin = 25;
	private final static Font fontTitle = FontFactory.getFont(FontFactory.COURIER, 16f, Font.BOLD, BaseColor.BLACK);
	private final static Font fontNormal = FontFactory.getFont(FontFactory.COURIER, 8f, Font.NORMAL, BaseColor.BLACK);
	@SuppressWarnings("unused")
	private final static Font fontCommentsGreen = FontFactory.getFont(FontFactory.COURIER, 8f, Font.NORMAL, new BaseColor(0, 128, 0));
	@SuppressWarnings("unused")
	private final static Font fontCommentsBlue = FontFactory.getFont(FontFactory.COURIER, 8f, Font.NORMAL, BaseColor.BLUE);
	private final static DecimalFormat df = new DecimalFormat("00000");

	/**
	 * @param model
	 * @return
	 */
	public static ByteArrayOutputStream execute(Model model) {
		try {
			return JavaPDFSourceCodeConverter.convertZipFile(model.getProjectName(), model.getCodeFileName(), model.getCodeFile(), model.getTypes());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param fileName
	 * @param byteArray
	 * @return
	 * @throws IOException
	 * @throws DocumentException 
	 */
	private static ByteArrayOutputStream convertZipFile(String fileTitle, String fileName, byte[] byteArray, ArrayList<String> extensions) 
			throws IOException, DocumentException {
		File tempFile = File.createTempFile(fileName, ".zip");
		FileOutputStream fos = new FileOutputStream(tempFile);
		fos.write(byteArray);
		fos.close();
		
		/* Result */
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		ZipFile zipFile = new ZipFile(tempFile);
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		
		Document document = new Document(PageSize.A4.rotate(), pdfMargin, pdfMargin, pdfMargin, pdfMargin);
		document.addTitle(fileTitle);
		document.addCreator("Code2PDF");
		document.addAuthor("Code2PDF");
		document.addCreationDate();
		
		try {
			PdfWriter.getInstance(document, outputStream);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.open();
		
	    while(entries.hasMoreElements()){
	        ZipEntry entry = entries.nextElement();
	        if(!entry.isDirectory() && extensions.contains(FilenameUtils.getExtension(entry.getName()))){
	        	InputStream stream = zipFile.getInputStream(entry);
	        	
	        	String[] parts = entry.getName().split("/");
	        	String title = parts[parts.length - 1]; 
	        	
	        	Paragraph p = new Paragraph();
	        	p.add(new Chunk(title, fontTitle));
	        	p.setAlignment(Element.ALIGN_CENTER);
	        	document.add(p);
	        	p = new Paragraph();
	        	p.add(new Chunk("", fontTitle));
	        	document.add(p);
	        		        	
	        	JavaPDFSourceCodeConverter.convertFile(document, stream);
	        	document.add(Chunk.NEXTPAGE);
	        }
	    }
	    
	    document.close();
	    return outputStream;
	}

	/**
	 * Convert file
	 * @param file
	 * @param outputStream
	 */
	private static void convertFile(Document document, InputStream inputStream) {
		long fileLines = 0;

//		boolean comments = false;
//		Font fntComments = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			String str;
			while ((str = in.readLine()) != null) {
				Paragraph p = new Paragraph();
				/* adiciona o numero da linha em preto */
				p.add(new Chunk(df.format(fileLines++ + 1) + ": ", fontNormal));

				if (str.contains("\t")) {
					str = str.replaceAll("\t", "    ");
				}

//				if (str.trim().equals("")) {
//					p.add(new Chunk(" "));
//				}else if (str.contains("/**")) {
//					comments = true;
//					fntComments = fontCommentsBlue;
//					p.add(new Chunk(str, fntComments));
//				}else if (str.contains("/*")) {
//					comments = true;
//					fntComments = fontCommentsGreen;
//					p.add(new Chunk(str, fntComments));
//				}else if (str.contains("*/")) {
//					comments = false;
//					p.add(new Chunk(str, fntComments));
//				}else{
//					if (str.contains("//")) {
//						String aux[] = str.split("//");
//						if (aux.length > 0) {
//							if (str.split("//")[0].trim().length() > 0) {
//								p.add(new Chunk(str.split("//")[0], fontNormal));
//								p.add(new Chunk("//" + str.split("//")[1], fontCommentsGreen));
//							} else {
//								p.add(new Chunk(str, fontCommentsGreen));
//							}
//						} else {
//							p.add(new Chunk("//", fontCommentsGreen));
//						}
//					} else if (comments) {
//						p.add(new Chunk(str, fntComments));
//					} else {
//						
//					}
//				}
				
				if (str.trim().equals("")) {
					p.add(new Chunk(" "));
				}else{
					p.add(new Chunk(str, fontNormal));
				}
				
				try {
					document.add(p);
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
