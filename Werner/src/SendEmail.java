

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class SendEmail {

	public static void main(String[] args) throws EmailException {
		// TODO Auto-generated method stub
		HtmlEmail email = new HtmlEmail();
		email.setHostName("10.1.26.82");
		email.setSmtpPort(25);
		email.setDebug(true);
		email.addTo("marius.swanepoel@momentum.co.za");
		email.setFrom("wernerkruger@gmail.com");
		email.setSubject("bla");
		email.setHtmlMsg("Bodyyyyyyyyyyyy");
		email.send();
	}

}
