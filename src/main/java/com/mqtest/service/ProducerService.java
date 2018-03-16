package com.mqtest.service;

import javax.jms.Destination;

public interface ProducerService {
	void sendQueue(String destination, final String message);
	void sendTopic(String destination, final String message);
}
