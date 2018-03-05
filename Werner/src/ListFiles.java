

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class ListFiles {

	public void walk(String path,Collection<String> listf) {
	        File root = new File( path );
	        File[] list = root.listFiles();

	        if (list == null) return;

	        for ( File f : list ) {
	            if ( f.isDirectory() ) {
	                walk( f.getAbsolutePath(),listf );
//	                System.out.println( "Dir:" + f.getName() );
	            }
	            else {
	                System.out.println( "File:" + f.getAbsolutePath() );
	                String fname = f.getName();
//	                listf.add(f.getAbsolutePath() + "," + fname.substring(fname.lastIndexOf("_") + 1,fname.lastIndexOf(".")) + ",");
//	                listf.add("ren " + "C:\\Werner\\FormerMemberClaimForm20170227\\Images\\" + fname + " " + fname.substring(fname.indexOf("_PolNo_")+7, fname.length()));
	                listf.add(f.getName());
	            }
	        }

	    }

	    public static void main(String[] args) {
			Collection<String> listf = new ArrayList<String>();
			PrintWriter out = null;
			try {
				out = new PrintWriter("C:/Werner/CRF_FEI/201710List.txt");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    	ListFiles fw = new ListFiles();
	        fw.walk("C:/Werner/CRF_FEI/201710",listf);
	        for (String line : listf) {
	            out.println(line);
	        }
	        out.close();
	    }
}
