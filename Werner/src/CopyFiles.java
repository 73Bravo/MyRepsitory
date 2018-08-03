import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyFiles {
	static String driver;

	public static void main(String[] args)  throws IOException {
		driver  = "C:/Users/yj0007/Desktop/Benefit Statements/TRF/2017-10-10 to 2018-04-09/Delivery_Projection_Statement_List.csv";
		
		Path driverDir = Paths.get(driver);
		Path readFile = driverDir.resolve(driver);
		BufferedReader br = Files.newBufferedReader(readFile,Charset.forName("UTF-8"));
		String thisLine;
		String fromFolder = "C:/Users/yj0007/Desktop/Benefit Statements/TRF/2017-10-10 to 2018-04-09/Projection_Statements_All/";
		String toFolder = "C:/Users/yj0007/Desktop/Benefit Statements/TRF/2017-10-10 to 2018-04-09/Projection_Statements_Deliver/";
//	    String[] csv = null;
	    int b = 0,counter=0;
	    while ((thisLine = br.readLine()) != null) { 
/*	    	    csv = thisLine.split(",");
	          	csv[0] = csv[0].replace(' ', '_');
	          	csv[0] = csv[0].replace('\'', '_');
	          	csv[0] = csv[0].replace('\\', '_');
	          	csv[0] = csv[0].replace('/', '_');
	          	csv[0] = csv[0].replace('?', '_');*/
	           	
//	           	File fromFile = new File(csv[2] + csv[1] + "/" + csv[0]);
	    		File fromFile = new File(fromFolder + thisLine.trim());
//	    		File toDir    = new File(csv[3] + csv[1]);
//	           	toDir.mkdir();
	           	File toFile   = new File(toFolder + thisLine.trim());
	            CopyOption[] options = new CopyOption[]{
	            	      StandardCopyOption.REPLACE_EXISTING,
	            	      StandardCopyOption.COPY_ATTRIBUTES
	            	    }; 
	            System.out.println("File = " + thisLine);
	           	Files.copy(fromFile.toPath(), toFile.toPath(), options);
            	if (b==1000) { 
            		System.out.println(" done: "+counter);
            		b=0;
            	}
            	b++;
            	counter++;
            }
	        System.out.println("Extracteda :" + counter);    
		}
	}
