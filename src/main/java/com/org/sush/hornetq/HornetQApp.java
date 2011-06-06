package com.org.sush.hornetq;

import javax.jms.JMSException;


public class HornetQApp {
	public static void main(String[] args){
		
		HornetQListener hornetQListener = new HornetQListener();
		HornetQAdmin hornetQAdmin = new HornetQAdminImpl();
		
		try {
//			hornetQAdmin.setMessageListener("sush", hornetQListener);
			hornetQAdmin.sendMessage("sush", "this is a test message");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		Thread tlistener = new Thread(hornetQListener);
		tlistener.start();
	}
}
