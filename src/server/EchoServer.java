package server;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 * �α� ���� ���� (Audit Log Server)
 *  - TLS �������� ���� (using TLS Protocol)
 *  - 7777�� ��Ʈ ���� (port 7777 open)
 *  - Ŭ���̾�Ʈ�κ��� XML������ ���� (XML Documents received from clients)
 * 
 * @author �赿��
 * @since 2017
 */

public class EchoServer {

	public static void main(String[] args) {

		try {
			/* SSL ��ȣŰ ����(���, ��й�ȣ) */
			System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\genius\\Desktop\\key\\XDSServer.jks");
			System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
			System.setProperty("javax.net.debug", "ssl");

			/* Ű�� �ŷ� ������ ����. */
			System.out.println("***********keyStore : " + System.getProperty("javax.net.ssl.keyStore"));
			System.out.println("***********trustStore : " + System.getProperty("javax.net.ssl.trustStore"));

			/* SSL ���� �ʱ�ȭ */
			SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory
					.getDefault();

			/* SSL ������ ��Ʈ ��ȣ ����. 7777�� */
			SSLServerSocket sslserversocket = (SSLServerSocket) sslserversocketfactory.createServerSocket(7777);
			System.out.println("Wating Connection");

			/*
			 * Ŀ�ؼ��� ������ �ʰ�, ���(listen)��. �����带 ������� ������, �ѹ� �������� ������ �����.
			 */
			while (true) {
				SSLSocket socket = (SSLSocket) sslserversocket.accept();
				/*
				 * ������ ó�� ��� 1. ������ �д� �κ��� ������� ó��. 2. �����͸� �о� xml ���Ϸ� ����.
				 */
				ThreadServer thread = new ThreadServer(socket);
				thread.start();
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}