package server;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ValidationXML {
	private static final String path = "TLS_Cert.xml";
	
	/**
	 * 정상적인 XML 파일인지 판단합니다.
	 * 
	 * @return flag 
	 * @throws Exception
	 */
	public static boolean isValidXml() throws Exception {
		boolean flag = false;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		if (db.parse(new File(path)) != null) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;
	}
	
	public static void main() throws Exception {
		boolean flag = isValidXml();
		System.out.println(flag);
	}
}
