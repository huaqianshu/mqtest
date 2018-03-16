package com.mqtest.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
@Component
public class QueueReceiver2 implements MessageListener {
	//一条消息只能接收到一次，随后删除
	@Override
	@JmsListener(destination = "QueueReceiver1")
	public void onMessage(Message message) {
		   try {
	            System.out.println("QueueReceiver2接收到消息:"+((TextMessage)message).getText());
	        } catch (JMSException e) {
	        	 System.out.println("消息已删除");
	        }
	}

}
