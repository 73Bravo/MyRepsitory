package za.co.Werner;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPClient {

	public static void main(String[] args)  throws Exception {
/*		  System.out.println("START");
		  String sentence;
		  String modifiedSentence;
		  Socket clientSocket = new Socket("172.17.4.21", 9001);
		  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		  sentence = "aaaaaa";
		  outToServer.writeBytes(sentence + '\n');
		  modifiedSentence = inFromServer.readLine();
		  System.out.println("FROM SERVER: " + modifiedSentence);
		  clientSocket.close();
*/
		String toDate = "1234.56.78";
		toDate = toDate.substring(0, 4).concat("-").concat(toDate.substring(5, 7)).concat("-").concat(toDate.substring(8, 10));		
		System.out.println(toDate);
		
//		DateFormatUtils.format(yourDate, "yyyy-MM-dd HH:mm:SS");
	}

}