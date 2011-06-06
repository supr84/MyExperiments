package com.org.sush.hornetq;

import static org.junit.Assert.fail;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;

import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import com.mockrunner.mock.jms.MockConnection;

public class HornetQAdminTest {
	
	

	@Test
	public void testSendMessage() {
		String message = "this is a text message";
		HornetQAdmin hornetQAdmin = new MockHornetQAdminImpl();
		try {
			hornetQAdmin.sendMessage("sush", message);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
private void test(){
	String message2send = "this is test message";
	DestinationManager destinationManager = new DestinationManager();
	Connection conn = (Connection) new MockConnection(new DestinationManager(), new ConfigurationManager());
	try {
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = (Queue) destinationManager.createQueue("sush");
		TextMessage message = session.createTextMessage(message2send);
		MessageProducer producer = session.createProducer(queue);
		producer.send(message);
		
	} catch (JMSException e) {
		
		fail("Exception occured");
	}
	
}
}
