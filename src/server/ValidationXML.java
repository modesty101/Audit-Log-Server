package server;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * XML ���� ���� 
 * - DOM ��ü�� ������ �־�, �Ľ��� �õ���. 
 * - ����� ���� XML�������� �ƴ��� Ȯ����.
 * 
 * @author �赿��
 * @since 2017
 */
public class ValidationXML {
	private static final String path = "TLS_Cert.xml";

	/**
	 * �������� XML �������� �Ǵ��մϴ�.
	 * 
	 * @return flag , true(XML FILE) false(opposite of true)
	 * @throws Exception
	 */
	public static boolean isValidXml() throws Exception {
		boolean flag = false;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		// �ش� ������ �Ľ��� �õ��ϸ� XML �������� �ƴ��� Ȯ���� �� ����.
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
