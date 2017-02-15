package server;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * XML 파일 검증 
 * - DOM 객체에 파일을 넣어, 파싱을 시도함. 
 * - 결과에 따라 XML파일인지 아닌지 확인함.
 * 
 * @author 김동규
 * @since 2017
 */
public class ValidationXML {
	private static final String path = "TLS_Cert.xml";

	/**
	 * 정상적인 XML 파일인지 판단합니다.
	 * 
	 * @return flag , true(XML FILE) false(opposite of true)
	 * @throws Exception
	 */
	public static boolean isValidXml() throws Exception {
		boolean flag = false;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		// 해당 문서에 파싱을 시도하면 XML 파일인지 아닌지 확인할 수 있음.
		if (db.parse(new File(path)) != null) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;
	}

	public static void main() throws Exception {
		isValidXml();
	}
}
