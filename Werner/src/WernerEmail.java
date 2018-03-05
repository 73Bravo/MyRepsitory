/**
 * 
 */


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Sean Nell (sean.nell@momentum.co.za)
 * 
 * @since 2016/11/07
 */
public class WernerEmail {

	private static final String FROM_ADDRESS 	= "werner.kruger@momentum.co.za";
	
	private static final String TO_ADDRESS 		= "Marius.Swanepoel@momentum.co.za";
	
	/**
	 * Constructor
	 */
	public WernerEmail() {
		
		Properties props = new Properties();
	    props.setProperty("mail.transport.protocol", "smtp");
	    props.setProperty("mail.host", "10.1.26.82");
	    props.put("mail.smtp.auth", "false");
	    props.put("mail.smtp.port", "25");
	    props.put("mail.smtp.SMTP_AUTH_USER", "");
	    props.put("mail.smtp.SMTP_AUTH_PWD", "");
	    props.put("mail.debug", "false");
	    props.setProperty("mail.smtp.quitwait", "false");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {
				
		        return new PasswordAuthentication("username", "password");
			}
			
		  });

		try {

			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(WernerEmail.FROM_ADDRESS));
			
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(WernerEmail.TO_ADDRESS));
			
			message.setSubject("Testing Email on SMTP Host : 172.17.4.233");
			
			message.setText("Hello World," + "\n\n Email sent successfully !");

			System.out.println("***** Sending mail to : " + WernerEmail.TO_ADDRESS + " - from : " + WernerEmail.FROM_ADDRESS);
			
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String [ ] arguments) {

		new WernerEmail();
	}

}
