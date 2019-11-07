package com.kkwrite.demo.springcloud2.consumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
public class ConsumerConfig {

	@Value("${server.port}")
	private int serverPort;
	
	@Value("${spring.application.Name}")
	private String applicationName;

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	
}
