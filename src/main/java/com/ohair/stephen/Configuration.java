package com.ohair.stephen;

import java.io.IOException;
import java.util.Properties;

/**
 * Simple configuration loader.
 * 
 * @author Stephen O'Hair
 * 
 */
public class Configuration {

	private static final String WEB_SERVICE_URL = "web.service.url";
	private static final String REFRESH_INTERVAL_MS = "refresh.interval.ms";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String SERIAL_PORT = "serial.port";
	private static final String MAX_MSG_SIZE = "max.message.character.size";

	private String webServiceUrl;
	private String username;
	private String password;
	private Long refreshIntervalMs;
	private String serialPort;
	private String maxMessageSize;

	/**
	 * Constructor.
	 */
	public Configuration() {
		Properties properties = new Properties();
		try {
			properties.load(Configuration.class.getClassLoader()
					.getResourceAsStream("configuration.properties"));
		} catch (IOException e) {
			throw new RuntimeException("unable to read configuration file");
		}

		webServiceUrl = properties.getProperty(WEB_SERVICE_URL);
		username = properties.getProperty(USERNAME);
		password = properties.getProperty(PASSWORD);
		refreshIntervalMs = Long.parseLong(properties
				.getProperty(REFRESH_INTERVAL_MS));
		serialPort = properties.getProperty(SERIAL_PORT);
		maxMessageSize = properties.getProperty(MAX_MSG_SIZE);
	}

	public String getWebServiceUrl() {
		return webServiceUrl;
	}

	public void setWebServiceUrl(String webServiceUrl) {
		this.webServiceUrl = webServiceUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRefreshIntervalInMillis() {
		return refreshIntervalMs;
	}

	public void setRefreshIntervalInMillis(Long refreshIntervalMs) {
		this.refreshIntervalMs = refreshIntervalMs;
	}

	public String getSerialPort() {
		return serialPort;
	}

	public void setSerialPort(String serialPort) {
		this.serialPort = serialPort;
	}

	public Integer getMaxMessageSize() {
		return Integer.parseInt(maxMessageSize);
	}

	public void setMaxMessageSize(int maxMessageSize) {
		this.maxMessageSize = maxMessageSize + "";
	}

}
