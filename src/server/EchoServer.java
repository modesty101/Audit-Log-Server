package server;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 * 로그 감사 서버 (Audit Log Server)
 *  - TLS 프로토콜 적용 (using TLS Protocol)
 *  - 7777번 포트 오픈 (port 7777 open)
 *  - 클라이언트로부터 XML문서를 받음 (XML Documents received from clients)
 * 
 * @author 김동규
 * @since 2017
 */

public class EchoServer {

	public static void main(String[] args) {

		try {
			/* SSL 암호키 설정(경로, 비밀번호) */
			System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\genius\\Desktop\\key\\XDSServer.jks");
			System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
			System.setProperty("javax.net.debug", "ssl");

			/* 키와 신뢰 정보를 얻어옴. */
			System.out.println("***********keyStore : " + System.getProperty("javax.net.ssl.keyStore"));
			System.out.println("***********trustStore : " + System.getProperty("javax.net.ssl.trustStore"));

			/* SSL 서버 초기화 */
			SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory
					.getDefault();

			/* SSL 서버의 포트 번호 지정. 7777번 */
			SSLServerSocket sslserversocket = (SSLServerSocket) sslserversocketfactory.createServerSocket(7777);
			System.out.println("Wating Connection");

			/*
			 * 커넥션이 끊기지 않게, 대기(listen)함. 스레드를 사용하지 않으면, 한번 실행으로 서버가 종료됨.
			 */
			while (true) {
				SSLSocket socket = (SSLSocket) sslserversocket.accept();
				/*
				 * 스레드 처리 목록 1. 데이터 읽는 부분은 쓰레드로 처리. 2. 데이터를 읽어 xml 파일로 저장.
				 */
				ThreadServer thread = new ThreadServer(socket);
				thread.start();
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}