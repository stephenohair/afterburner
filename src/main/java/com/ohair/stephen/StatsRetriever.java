package com.ohair.stephen;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.bind.JAXBException;

import jssc.SerialPort;

import com.ohair.stephen.jaxb.HardwareMonitorType;

/**
 * Retrieves Statistics from Afterburner.
 * 
 * @author Stephen O'Hair
 * 
 */
public class StatsRetriever extends TimerTask {

	private static MyUnmarshaller um;
	private static Configuration config;
	private static SerialComms comms;

	@Override
	public void run() {
		System.out.println("Timer task started at:" + new Date());
		completeTask();
		System.out.println("Timer task finished at:" + new Date());
	}

	private void completeTask() {
		URL url;
		InputStream is;
		try {
			url = new URL(config.getWebServiceUrl());
			URLConnection uc = url.openConnection();

			String userpass = config.getUsername() + ":" + config.getPassword();
			String basicAuth = "Basic "
					+ javax.xml.bind.DatatypeConverter
							.printBase64Binary(userpass.getBytes());

			uc.setRequestProperty("Authorization", basicAuth);
			is = uc.getInputStream();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		HardwareMonitorType hmt = um.unmarshal(is);
		comms.sendToSerial(MessageWriter.getStatsInfo(hmt
				.getHardwareMonitorEntries().getHardwareMonitorEntry(), config));
		// comms.sendToSerial("FPS:0   GPU1:1  CPU:3   RAM:2802");
		Util.printHardwareMonitor(hmt);
	}

	public static void main(String[] args) throws JAXBException {
		config = new Configuration();
		um = new MyUnmarshaller();
		comms = new SerialComms(config);
		TimerTask timerTask = new StatsRetriever();
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0,
				config.getRefreshIntervalInMillis());
		System.out.println("TimerTask started");
		// cancel after sometime
		try {
			Thread.sleep(360000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();
		System.out.println("TimerTask cancelled");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
