package com.mqtest.config;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class ActiveMQConfiguration {
	/**
	 * 
	 * 定义点对点队列
	 * 
	 * @return
	 * 
	 */
	@Autowired
	private LocalActiveMQProperties properties;
    @Autowired
    private ActiveMQConnectionFactory jmsConnectionFactory;
//不加@Primary不会生效，但由于并未更改数据，可采用springboot 默认
	@Primary
	@Bean
	public ActiveMQProperties activeMQProperties() {
		ActiveMQProperties activeMQProperties = new ActiveMQProperties();
		activeMQProperties.setBrokerUrl(properties.getBrokerUrl());
		activeMQProperties.setInMemory(properties.isInMemory());
		if (!"".equals(properties.getUser())) {
			activeMQProperties.setUser(properties.getUser());
		}
		if (!"".equals(properties.getPassword())) {
			activeMQProperties.setUser(properties.getPassword());
		}
		ActiveMQProperties.Pool pool = new ActiveMQProperties.Pool();
		pool.setEnabled(properties.isPoolEnable());
		activeMQProperties.setPool(pool);
		return activeMQProperties;
	}
	 @Bean(name = "jmsTemplate")
	    public JmsTemplate jmsTemplate(){
	        JmsTemplate jt = new JmsTemplate();
	        SingleConnectionFactory scf = new SingleConnectionFactory();
	        scf.setTargetConnectionFactory(jmsConnectionFactory);
	        scf.setReconnectOnException(true);//连接断开后重试
	        jt.setConnectionFactory(scf);
	        return jt;
	    }
	   // 创建一个连接工厂，用于程序连接到activemq代理。
    @Bean(name = "jmsConnectionFactory")
    public ActiveMQConnectionFactory jmsConnectionFactory(){
        ActiveMQConnectionFactory amqf = new ActiveMQConnectionFactory(properties.getBrokerUrl());
        amqf.setCloseTimeout(10*1000);
        amqf.setSendTimeout(10*1000);
        return amqf;
    }
    // 创建服务端的jmsTemplate（一般情况下服务端和客户端的jmsTemplate可以设置相同，但为了效率因素，我们将其分开设置，服务端应尽量减少连接数量，所以使用singleConnectionFactory）。
   
}
