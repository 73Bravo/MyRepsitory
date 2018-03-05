import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
	static PreparedStatement stmt;
	static Connection conn;
	static String folder;
	
    private static String removeSpecial(String folderName) {
    	folderName = folderName.replace(' ', '_');
    	folderName = folderName.replace('\'', '_');
    	folderName = folderName.replace('\\', '_');
    	folderName = folderName.replace('/', '_');
    	folderName = folderName.replace('?', '_');
    	folderName = folderName.replace(',', '/');
    	return folderName;
    }
 
	public static void main(String[] args) {
		File file = new File("c:/werner/BRID/Driver.csv");
        try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        conn = DriverManager.getConnection("jdbc:sqlserver://172.17.4.100:1433;databaseName=Imagestore", "linx", "linx@tpfa");
	        System.out.println("connected");
	        String sql = "SELECT Data FROM \"dbo\".\"File\" where DocumentID = ? "; //6377869
	        stmt = conn.prepareStatement(sql);		
        } catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] linePart = null;
				linePart = line.split(",");
				folder = "c:/werner/BRID/" + linePart[0] + "/" + linePart[1] + "/" + linePart[2];
				new File(folder).mkdirs();
				stmt.setInt(1, Integer.valueOf(linePart[4]));
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Files.copy(rs.getBlob(1).getBinaryStream(), 
							   Paths.get(folder + "/" + linePart[3]));
					break;
				}

				// process the line.
			}
		} catch (IOException | NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
        try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Done");

	}

}
