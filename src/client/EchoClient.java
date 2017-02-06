package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class EchoClient {

	public static void main(String[] args) {

		try {

			// ���� ���丮 ����
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

			// �������� server keyStore ������ ������ ������ ��ġ��Ű�� �Ʒ��� ���� �������ش�. (Ŭ���̾�Ʈ����
			// keyStore ������ �־�� ��)
			System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\genius\\Desktop\\key\\XDSServer.jks");

			// password�� keyStore�� ���� �� �Է��ߴ� ��.
			System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

			// ������� ���� �Ʒ��� ���� ����.
			System.setProperty("javax.net.debug", "ssl");

			System.out.println("&&&&&& keyStore : " + System.getProperty("javax.net.ssl.keyStore"));
			System.out.println("&&&&&& trustStore : " + System.getProperty("javax.net.ssl.trustStore"));

			// ���Ͽ� ������ IP�� ��Ʈ ��ȣ�� �Է����ش�.
			SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("127.0.0.1", 7777);

			// ������ ���� ������ ��ο� �̸�.
			String dir = "C:\\Users\\genius\\Desktop\\logsender-0.0.1-SNAPSHOT-bin";
			String fileName = "test.txt";

			InputStream inputstream = new FileInputStream(new File(dir, fileName));
			InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

			OutputStream outputstream = sslsocket.getOutputStream();
			OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
			BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);

			String string = null;

			while ((string = bufferedreader.readLine()) != null) {
				bufferedwriter.write(string + '\n');
				bufferedwriter.flush();
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

}