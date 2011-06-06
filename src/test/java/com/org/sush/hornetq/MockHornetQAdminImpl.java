package com.org.sush.hornetq;

import java.util.Queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import com.mockrunner.mock.jms.MockConnection;

public class MockHornetQAdminImpl extends HornetQAdmin{

	@Override
	protected void init(ConnectionFactory cf) throws JMSException {
		DestinationManager destinationManager = new DestinationManager();
		
		destinationManager.createQueue("sush");
		conn = (Connection) new MockConnection(destinationManager, new ConfigurationManager());
		
		
	}

}
