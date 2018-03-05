import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileInspect {

	public static void main(String[] args) {
        try {
        	String backupLine = " ";

            File inFile  = new File("C:/Werner/Trust2/32001_loadpolicy.09.log");
            File outFile = new File("C:/Werner/Trust2/32001_loadpolicy.09.log.txt");

            System.out.println("Reading files using Apache IO:");

            List<String> lines = FileUtils.readLines(inFile, "UTF-8");

            for (String line : lines) {
				try {
					String xx = line.substring(0, 3);
//					System.out.println(xx);
					if (xx.equalsIgnoreCase("ERR")){
					   System.out.println(line + ";"+ backupLine);
					   FileUtils.writeStringToFile(outFile, line + ";"+ backupLine +"\n","UTF-8",true);   
					} else {backupLine = line;}
				} catch (Exception e) {
				}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
