package com.org.sush.hornetq;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.integration.transports.netty.NettyConnectorFactory;
import org.hornetq.integration.transports.netty.TransportConstants;

public abstract class HornetQAdmin {
	protected Connection conn;
	protected Session session;
	private final Map<String,MessageConsumer> consumers = new HashMap<String, MessageConsumer>();
	private final Map<String,MessageProducer> producers = new HashMap<String, MessageProducer>();
	public HornetQAdmin() {
		Map<String, Object> connectionParams = new HashMap<String, Object>();
        connectionParams.put(TransportConstants.PORT_PROP_NAME, 5445);
        connectionParams.put(TransportConstants.HOST_PROP_NAME, "localhost");
        TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName(),
                                                                                   connectionParams);

        ConnectionFactory cf = HornetQJMSClient.createConnectionFactory(transportConfiguration);
        try {
			init(cf);
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected abstract void init(ConnectionFactory cf) throws JMSException ;
	public void sendMessage(String queueName, String sendMessage) throws JMSException{
		if(!producers.containsKey(queueName)){
			Queue queue = session.createQueue(queueName);
			MessageProducer producer = session.createProducer(queue);
			producers.put(queueName, producer);
			
		}
		TextMessage tm = session.createTextMessage(sendMessage);
		producers.get(queueName).send(tm);
	}
	public void setMessageListener(String queueName, MessageListener listener) throws JMSException{
		if(!consumers.containsKey(queueName)){
			Queue queue = session.createQueue(queueName);
			MessageConsumer consumer = session.createConsumer(queue);
			consumers.put(queueName, consumer);
		}
		consumers.get(queueName).setMessageListener(listener);
	}
	
}
