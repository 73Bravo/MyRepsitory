  

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExtractImage {

	static String folder;
	static String driver;
    public int createDirectories() {
    	try {
    		 Path myDir = Paths.get(folder);
    		 Path readFile = myDir.resolve(driver);
    	      BufferedReader br = Files.newBufferedReader(readFile,Charset.forName("UTF-8"));
    	      String thisLine;
     	           boolean ft = true;
     	           String cb = "";
//     	           System.out.println(br.readLine());
    	            while ((thisLine = br.readLine()) != null) {
    	            	System.out.println("thisline="+thisLine);
    	            	thisLine = getDirectory(thisLine);
    	            	thisLine = removeSpecial(thisLine);
     	            	if (ft) {
    	            		cb = thisLine;
    	            		ft =false;
    	            		new File(folder + thisLine).mkdirs();
    	            	}
     	            	if (!cb.equals(thisLine)) {
     	            		new File(folder + thisLine).mkdirs();
     	            		cb = thisLine;
     	            	}
    	                //System.out.println(thisLine);
    	  
    	            }
    	            System.out.println(thisLine);
    	            br.close();
    	            return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
    }

    private String removeSpecial(String thisLine) {
    	thisLine = thisLine.replace(' ', '_');
    	thisLine = thisLine.replace('\'', '_');
    	thisLine = thisLine.replace('\\', '_');
//    	thisLine = thisLine.replace('/', '_');
    	thisLine = thisLine.replace('?', '_');
    	return thisLine;
    }
    
    private String getDirectory(String line) {
    	return line.substring(0,line.indexOf(","));
    }
    
	public void extract() {
        try {
			
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://172.17.4.100:1433;databaseName=Imagestore", "linx", "linx@tpfa");
            System.out.println("connected");
            String sql = "SELECT Data FROM \"dbo\".\"File\" where DocumentID = ? "; //6377869
          
            PreparedStatement stmt = conn.prepareStatement(sql);
            //stmt.setInt(1, 6377869);
            
			Path myDir = Paths.get(folder);
			Path readFile = myDir.resolve(driver);
			BufferedReader br = Files.newBufferedReader(readFile,Charset.forName("UTF-8"));
			String thisLine;
  		
			String[] csv = null;
			int b = 0, counter = 0;
			while ((thisLine = br.readLine()) != null) {
				thisLine = removeSpecial(thisLine);
				System.out.println(thisLine);
				csv = thisLine.split(",");
				stmt.setInt(1, Integer.valueOf(csv[2]));
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Files.copy(rs.getBlob(1).getBinaryStream(), Paths.get(folder + csv[0] + "/" + csv[1]));
					break;
				}
				if (b == 1000) {
					System.out.println(" done: " + counter);
					b = 0;
				}
				b++;
				counter++;
			}
			System.out.println("Extracted :" + counter);
			conn.close();
            
		} catch (Exception e) {
			e.printStackTrace();
			
		}		
	}

	public static void main(String[] args) {
		long start = new java.util.Date().getTime();
		System.out.println("Start" );
	    folder = "c:/werner/BRID/";
	    driver = "Driver.csv";		
//		folder = args[0];
//		driver = args[1];
		ExtractImage e = new ExtractImage();
		if (e.createDirectories() == 1) return;
		e.extract();
		long end = new java.util.Date().getTime();
		System.out.println("Done took: " + (end-start) + " miliseconds" + " "+ (end-start)/1000 + " seconds" + " "+((end-start)/1000)/60 + " min");
	}

}
//12529494