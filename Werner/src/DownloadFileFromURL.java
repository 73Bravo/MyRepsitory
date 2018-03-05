import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadFileFromURL {
	
	static String folder;
	static String driver;

	public static void downloadFileFromURL(String urlString, File destination) throws Throwable {

	      URL website = new URL(urlString);
	      try(
	              ReadableByteChannel rbc = Channels.newChannel(website.openStream());
	              FileOutputStream fos = new FileOutputStream(destination);  
	              ){
	          fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
	      }

	  }
	
	public static void main(String[] args) throws Throwable {
		System.out.println("Start" );
	    folder = "C:/Werner/Temp/";
	    driver = "Driver.txt";	
		Path myDir = Paths.get(folder);
		Path readFile = myDir.resolve(driver);
	    BufferedReader br = Files.newBufferedReader(readFile,Charset.forName("UTF-8"));
	    String thisLine;
	    String[] csv = null;
	    while ((thisLine = br.readLine()) != null) {
	    	csv = thisLine.split(",");
	    	String url = csv[0];
	    	File file  = new File(csv[1]);
	    	downloadFileFromURL(url, file);
	    }
	    System.out.println("Stop" );
	}
}
