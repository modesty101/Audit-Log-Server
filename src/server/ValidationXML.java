package server;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ValidationXML {
	private String path = "extractXml.xml";
	
	public boolean isValidXml() throws Exception {
		boolean flag = false;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		if(db.parse(new File(path)) != null) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
}
