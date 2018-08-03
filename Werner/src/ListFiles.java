

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
//	                System.out.println( "File:" + f.getAbsolutePath() );
	                String fname = f.getName();
//	                listf.add(f.getAbsolutePath() + "," + fname.substring(fname.lastIndexOf("_") + 1,fname.lastIndexOf(".")) + ",");
//	                listf.add("ren " + "C:\\Werner\\Tax_Cert\\2018\\TPF\\\"" + fname + "\" " + fname.substring(fname.indexOf("_")+1, fname.length()));
//	                String toFileName = fname.substring(fname.indexOf("_")+1, fname.indexOf(".")) + "_" + fname.substring(0, fname.indexOf("_")) + ".PDF";
//	                toFileName = toFileName.replace(' ', '_');
//	                System.out.println(fname);
/*	                long count = fname.chars().filter(ch -> ch =='.').count();
	                if(count>1){
	                	System.out.println(fname + "      " + count);
	                }*/
	                
//	                if (fname.indexOf(",")>0){
//	                	System.out.println(fname);
//	                }
//	                int qq = fname.lastIndexOf("PolicyEmpNo_");
//	                String pers = fname.substring(fname.lastIndexOf("PersonId_") + 9,fname.lastIndexOf("PolicyEmpNo_"));
//	                String pol = fname.substring(fname.lastIndexOf("PolicyEmpNo_") + 13,fname.lastIndexOf("pdf")-2);
	                String empNo = fname.substring(fname.lastIndexOf("_")+1,fname.lastIndexOf("."));
	                listf.add(empNo + "," + fname);	                
//	                String xx = fname.substring(0, fname.indexOf("."));
//	                System.out.println(xx + "," + fname);
//	                listf.add(xx + "," + fname);
//	                listf.add(fname);
	            }
	        }

	    }

	    public static void main(String[] args) {
			Collection<String> listf = new ArrayList<String>();
			PrintWriter out = null;
			try {
				out = new PrintWriter("C:/Werner/Tax_Cert/2018/TPF-Individual_Tax_certificates.txt");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    	ListFiles fw = new ListFiles();
	        fw.walk("C:/Werner/Tax_Cert/2018/TPF",listf);
	        for (String line : listf) {
	            out.println(line);
	        }
	        out.close();
	    }
}
