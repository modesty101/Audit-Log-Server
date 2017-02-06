package server;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class EchoServer {

	public static void main(String[] args) {

		try {
			
			System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\genius\\Desktop\\key\\XDSServer.jks");

			
			System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

			
			System.setProperty("javax.net.debug", "ssl");

			System.out.println("***********keyStore : " + System.getProperty("javax.net.ssl.keyStore"));
			System.out.println("***********trustStore : " + System.getProperty("javax.net.ssl.trustStore"));

			
			SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory
					.getDefault();

			
			SSLServerSocket sslserversocket = (SSLServerSocket) sslserversocketfactory.createServerSocket(7777);
			System.out.println("Wating Connection");

			
			while (true) {
				SSLSocket socket = (SSLSocket) sslserversocket.accept();
				// 데이터 읽는 부분은 쓰레드로 처리.
				// 데이터를 읽어 xml 파일로 저장.
				ThreadServer thread = new ThreadServer(socket);
				thread.start();
			}
			
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

}