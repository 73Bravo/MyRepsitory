import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import metra.imagestore.client.ImageStoreService;
import metra.imagestore.client.LinxService;

public class FileUpaloadTest {
	private static final QName SERVICE_NAME = new QName("http://util.imagestore.za.co.metra/", "ImageStoreService");
	
	public static int uploadDocToImagestore(byte[] file, String fileExt,String env) throws Exception
	{
		URL wsdlURL = null;
		try {
			if (env.equalsIgnoreCase("P")){
				wsdlURL = new URL("http://172.17.4.156:8180/LinxWebServiceJS/LinxService?WSDL");
			} else {
				wsdlURL = new URL("http://172.17.4.152:8180/LinxWebServiceDEV/LinxService?WSDL");
			}
		} catch (MalformedURLException e) {
			System.out.println(e);
		}

		ImageStoreService ss = new ImageStoreService(wsdlURL, SERVICE_NAME);
			
        LinxService port = ss.getImageStorePort();  
        
        Client proxy = ClientProxy.getClient( port );
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(360000);
        httpClientPolicy.setAllowChunking(false);
       
        HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
        conduit.setClient(httpClientPolicy);
        
        System.out.println("Invoking storeImage...");
        int _storeImage__return = port.storeFile(file, fileExt);
        System.out.println("storeImage.result=" + _storeImage__return);
        return _storeImage__return;
	}
	public static void main(String[] args) {
		String environment = "P";
	     Path path = Paths.get("C:/Werner/BRID/FNB_ PO_Bank_verification.MSG");
	      try {
			byte[] data = Files.readAllBytes(path);
			int imageref = uploadDocToImagestore(data,"MSG",environment);
			System.out.println(imageref);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

}
