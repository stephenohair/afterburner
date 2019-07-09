package com.ohair.stephen;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.TimerTask;

import com.ohair.stephen.jaxb.HardwareMonitorType;

/**
 * Retrieves Statistics from Afterburner.
 * 
 * @author Stephen O'Hair
 * 
 */
public class StatsRetriever extends TimerTask {

	private final MyUnmarshaller um;
	private final SerialComms comms;
	private final Configuration config;

	public StatsRetriever() {

		um = new MyUnmarshaller();
		config = new Configuration();
		comms = new SerialComms(config);
	}

	@Override
	public void run() {
		completeTask();
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
			HardwareMonitorType hmt = um.unmarshal(is);
			comms.sendToSerial(MessageWriter.getStatsInfo(hmt
					.getHardwareMonitorEntries().getHardwareMonitorEntry(),
					config));
			// comms.sendToSerial("FPS:0   GPU1:1  CPU:3   RAM:2802");
			Util.printHardwareMonitor(hmt);

		} catch (MalformedURLException e) {
			App.getController().appendTxtAreaLogOutput(
					"ERROR - " + e.getMessage());
		} catch (IOException e) {
			App.getController().appendTxtAreaLogOutput(
					"ERROR - " + e.getMessage());
		}
	}
}
