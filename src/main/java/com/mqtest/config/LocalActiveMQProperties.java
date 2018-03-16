package com.mqtest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="spring.activemq")
public class LocalActiveMQProperties {
	private String brokerUrl;
    private boolean inMemory;
    private String user;
    private String password;
    private boolean poolEnable;
	public String getBrokerUrl() {
		return brokerUrl;
	}
	public void setBrokerUrl(String brokerUrl) {
		this.brokerUrl = brokerUrl;
	}
	public boolean isInMemory() {
		return inMemory;
	}
	public void setInMemory(boolean inMemory) {
		this.inMemory = inMemory;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isPoolEnable() {
		return poolEnable;
	}
	public void setPoolEnable(boolean poolEnable) {
		this.poolEnable = poolEnable;
	}
}
