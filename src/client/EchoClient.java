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

			// 소켓 팩토리 생성
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

			// 만들어놨던 server keyStore 파일을 임의의 폴더에 위치시키고 아래와 같이 설정해준다. (클라이언트에도
			// keyStore 파일이 있어야 함)
			System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\genius\\Desktop\\key\\XDSServer.jks");

			// password는 keyStore를 만들 때 입력했던 것.
			System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

			// 디버깅을 위해 아래와 같이 설정.
			System.setProperty("javax.net.debug", "ssl");

			System.out.println("&&&&&& keyStore : " + System.getProperty("javax.net.ssl.keyStore"));
			System.out.println("&&&&&& trustStore : " + System.getProperty("javax.net.ssl.trustStore"));

			// 소켓에 서버의 IP와 포트 번호를 입력해준다.
			SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("127.0.0.1", 7777);

			// 서버로 보낼 파일의 경로와 이름.
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