package com.mqtest.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueReceiver1 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		   try {
	            System.out.println("QueueReceiver1接收到消息:"+((TextMessage)message).getText());
	        } catch (JMSException e) {
	            e.printStackTrace();
	        }
	}

}
