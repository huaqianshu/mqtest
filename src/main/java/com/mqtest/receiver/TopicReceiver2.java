package com.mqtest.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
@Component
public class TopicReceiver2 implements MessageListener {

	@Override
	@JmsListener(destination = "TopicReceiver1")
	public void onMessage(Message message) {
		   try {
	            System.out.println("TopicReceiver2接收到消息:"+((TextMessage)message).getText());
	        } catch (JMSException e) {
	            e.printStackTrace();
	        }

	}

}
