package com.mqtest.service.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.mqtest.service.ProducerService;
@Service
public class ProducerServiceImpl implements ProducerService {
	
	@Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装  
	@Qualifier(value = "jmsTemplate")
    private JmsTemplate jmsTemplate;
   /**
     * 发送一条消息到指定的队列（目标）
     * @param queueName 队列名称
     * @param message 消息内容
     */
	@Override
	public void sendQueue(String destination, String message) {
		jmsTemplate.send(destination, new MessageCreator() {
			            @Override
			             public Message createMessage(Session session) throws JMSException {
			                 return session.createTextMessage(message);
			            }
			       });
	}
	/**
	 * 
	 * @description   Topic生产者发送消息到Topic
	 * 
	 */
	@Override
	public void sendTopic(String destination, String message) {
		  jmsTemplate.send(destination, new MessageCreator() {
	            @Override
	            public Message createMessage(Session session) throws JMSException {
	                return session.createTextMessage(message);
	            }
	        });
	        for(int i=0;i<10;i++) {
	            jmsTemplate.convertAndSend(new ActiveMQTopic(destination), message);
	        }
	}

}
