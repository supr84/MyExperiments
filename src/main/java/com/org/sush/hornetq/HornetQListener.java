package com.org.sush.hornetq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class HornetQListener implements MessageListener, Runnable{
	private HornetQAdmin hornetQAdmin;
	public HornetQListener() {
		hornetQAdmin = new HornetQAdminImpl();
	}

	@Override
	public void onMessage(Message message) {
		System.out.println("recieved message from hornetQ");
		System.out.println("message::" + message.toString());
	}

	@Override
	public void run() {
		try {
			hornetQAdmin.setMessageListener("sush", this);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
