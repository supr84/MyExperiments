package com.org.sush.hornetq;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

public class HornetQAdminImpl extends HornetQAdmin{

	@Override
	protected void init(ConnectionFactory cf) throws JMSException {
//		conn = cf.createConnection();
	}
	
}
