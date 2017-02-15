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

	public void save(String bytes) throws Exception {
		System.out.println();
		System.out.println("----I want to this :D----");
		System.out.println(bytes);
		System.out.println("Data length : " + bytes.length());
		System.out.println();
		System.out.println("----And... split strange characters----");

		System.out.println("Where is <?xml ...> : " + bytes.indexOf("<?x"));
		int index = bytes.indexOf("<?x");
		System.out.println("where is End Sign > : " + bytes.lastIndexOf(">"));
		int lastindex = bytes.lastIndexOf(">");

		bw = new BufferedWriter(new FileWriter("TLS_Cert.xml"));
		/* 컴퓨터는 0부터 시작한다. 정상적인 값을 얻으려면 마지막에 +1을 해야된다. */
		bw.write(bytes.substring(index, lastindex + 1));
		bw.close();
		System.out.println("Done");
		System.out.println();
	}

	public void valid() throws Exception {
		if (true == ValidationXML.isValidXml()) {
			System.out.println("XML 문서가 정상적이므로 DB에 저장합니다.\n");
			PostgreSQLJDBC.insertTable();
		} else {
			System.out.println("XML 문서가 비정상적입니다..");
			Thread.sleep(1000);
			Thread.interrupted();
			System.exit(0);
		}
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

			save(bytes);

			valid();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
