package server;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class EchoServer {

	public static void main(String[] args) {

		try {
			// ./LogSender --host 10.0.1.113 --port 7777 --protocol TLS --keystore /etc/tomcat7/XDSServer.jks --truststore /etc/tomcat7/XDSServer.jks --type 1

			System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\genius\\Desktop\\key\\XDSServer.jks");
			
			System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

			System.setProperty("javax.net.debug", "ssl");

			System.out.println("***********keyStore : " + System.getProperty("javax.net.ssl.keyStore"));
			System.out.println("***********trustStore : " + System.getProperty("javax.net.ssl.trustStore"));

			
			SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory
					.getDefault();

			// SSL ������ ��Ʈ ��ȣ ����. 7777��
			SSLServerSocket sslserversocket = (SSLServerSocket) sslserversocketfactory.createServerSocket(7777);
			System.out.println("Wating Connection");

			
			while (true) {
				SSLSocket socket = (SSLSocket) sslserversocket.accept();
				// ������ �д� �κ��� ������� ó��.
				// �����͸� �о� xml ���Ϸ� ����.
				ThreadServer thread = new ThreadServer(socket);
				thread.start();
			}
			
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

}