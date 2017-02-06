package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.SSLSocket;

public class ThreadServer extends Thread {

	private SSLSocket socket;
	private InputStream input;
	private InputStreamReader reader;
	private BufferedReader br;
	private BufferedWriter bw;

	public ThreadServer(SSLSocket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			String bytes = null;
			String fromClient = null;
			input = socket.getInputStream();
			reader = new InputStreamReader(input);
			br = new BufferedReader(reader);

			while ((fromClient = br.readLine()) != null) {
				// System.out.println(fromClient);
				System.out.flush();
				bytes = fromClient;
			}

			System.out.println("----I want to this :D----");
			System.out.println(bytes);
			System.out.println("Data length : " + bytes.length());

			bw = new BufferedWriter(new FileWriter("TLS_Cert.xml"));
			bw.write(bytes.substring(69, 1759));
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
