import javax.jms.DeliveryMode;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import com.ibm.mq.jms.JMSC;
import com.ibm.mq.jms.MQQueueConnectionFactory;


public class MQTest {
	private static MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
	private static QueueConnection queueConnection = null;
	private static QueueSession    queueSession = null;
	private static QueueSender     queueSender = null;
	
	public static void main(String[] args) {
		try {

			String XMLMessage = "MariusMRA.TEST.MAYBE.DELETED";

			mqQueueConnectionFactory.setHostName("imhotep.momentum.co.za");
			mqQueueConnectionFactory.setChannel("TCP.CLT.CHL");
			mqQueueConnectionFactory.setPort(1415);
			mqQueueConnectionFactory.setQueueManager("MOMTEST.QUE.MGR");
			mqQueueConnectionFactory.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);

			queueConnection = mqQueueConnectionFactory.createQueueConnection();
			queueConnection.start();

			queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			queueSender = queueSession.createSender(queueSession.createQueue("SS_EFM_EVENT_MRA_QUEUE"));

			TextMessage textMessage = queueSession.createTextMessage(XMLMessage);
			textMessage.setJMSType("mcd://xmlns");
			textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
			queueSender.send(textMessage);

			String jmsCorrelationID = "JMSCorrelationID = '"
					+ textMessage.getJMSMessageID().substring(3, textMessage.getJMSMessageID().length()) + "'";
			System.out.println("textMessage=" + textMessage.toString());
			XMLMessage = jmsCorrelationID;
			String responseMsg = XMLMessage.replace("'", "\"");
			System.out.println("responseMsg=" + responseMsg);

			queueSender.close();
			queueSession.close();
			queueConnection.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
