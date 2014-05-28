/**
 * 
 */
package com.pedroalmir.code2pdf.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Pedro Almir
 */
public class Model {
	/** Project Name */
	private String projectName;
	/** Source code file */
	private byte[] codeFile;
	private String codeFileName;
	private String codeMimeType;
	
	/** File Extensions */
	private ArrayList<String> types;
	
	private byte[] resultFile;
	private String resultFileName;
	private String resultFileMimeType;
	
	/**
	 * Default constructor
	 */
	public Model() {
		this.types = new ArrayList<String>();
	}
	
	
	/**
	 * @param codeFile
	 * @param codeFileName
	 * @param codeMimeType
	 * @param types
	 */
	public Model(String projectName, byte[] codeFile, String codeFileName, String codeMimeType, ArrayList<String> types) {
		super();
		this.projectName = projectName;
		this.codeFile = codeFile;
		this.codeFileName = codeFileName;
		this.codeMimeType = codeMimeType;
		if(types != null){
			this.types = types;
		}else{
			this.types = new ArrayList<String>();
		}
	}
	
	/**
	 * @param codeFile
	 * @param codeFileName
	 * @param codeMimeType
	 */
	public Model(String projectName, byte[] codeFile, String codeFileName, String codeMimeType) {
		super();
		this.projectName = projectName;
		this.codeFile = codeFile;
		this.codeFileName = codeFileName;
		this.codeMimeType = codeMimeType;
		this.types = (ArrayList<String>) Arrays.asList("java");
	}
	
	/**
	 * @param extension
	 */
	public void addFileType(String extension){
		this.types.add(extension);
	}

	/**
	 * @return the codeFile
	 */
	public byte[] getCodeFile() {
		return codeFile;
	}
	/**
	 * @param codeFile the codeFile to set
	 */
	public void setCodeFile(byte[] codeFile) {
		this.codeFile = codeFile;
	}
	/**
	 * @return the codeFileName
	 */
	public String getCodeFileName() {
		return codeFileName;
	}
	/**
	 * @param codeFileName the codeFileName to set
	 */
	public void setCodeFileName(String codeFileName) {
		this.codeFileName = codeFileName;
	}
	/**
	 * @return the codeMimeType
	 */
	public String getCodeMimeType() {
		return codeMimeType;
	}
	/**
	 * @param codeMimeType the codeMimeType to set
	 */
	public void setCodeMimeType(String codeMimeType) {
		this.codeMimeType = codeMimeType;
	}
	/**
	 * @return the types
	 */
	public ArrayList<String> getTypes() {
		return types;
	}
	/**
	 * @param types the types to set
	 */
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	/**
	 * @return the resultFile
	 */
	public byte[] getResultFile() {
		return resultFile;
	}
	/**
	 * @param resultFile the resultFile to set
	 */
	public void setResultFile(byte[] resultFile) {
		this.resultFile = resultFile;
	}
	/**
	 * @return the resultFileName
	 */
	public String getResultFileName() {
		return resultFileName;
	}
	/**
	 * @param resultFileName the resultFileName to set
	 */
	public void setResultFileName(String resultFileName) {
		this.resultFileName = resultFileName;
	}
	/**
	 * @return the resultFileMimeType
	 */
	public String getResultFileMimeType() {
		return resultFileMimeType;
	}
	/**
	 * @param resultFileMimeType the resultFileMimeType to set
	 */
	public void setResultFileMimeType(String resultFileMimeType) {
		this.resultFileMimeType = resultFileMimeType;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Model [codeFileName=" + codeFileName + ", codeMimeType=" + codeMimeType + ", types=" + types + "]";
	}


	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}


	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
