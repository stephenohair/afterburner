package com.ohair.stephen;

import jssc.SerialPort;
import jssc.SerialPortException;

import org.apache.commons.lang3.StringUtils;

public class SerialComms {

	private final SerialPort port;
	private final int baudRate;
	private final int stopBits;
	private final int dataBits;
	private final int parity;

	public SerialComms(Configuration config) {
		port = new SerialPort(config.getSerialPort());
		baudRate = config.getBaudRate();
		stopBits = config.getStopBits();
		dataBits = config.getDataBits();
		parity = config.getParity();

	}

	public void sendToSerial(String message) {
		try {
			// pad message to 32 chars if it isn't already
			StringUtils.rightPad(message, 32);

			App.getController().appendTxtAreaLogOutput(
					"Port opened: " + port.openPort());
			App.getController().appendTxtAreaLogOutput(
					"Params set: "
							+ port.setParams(baudRate, dataBits, stopBits,
									parity));
			App.getController().appendTxtAreaLogOutput(
					"\"" + message + "\" successfully wrote to port: "
							+ port.writeBytes(message.getBytes()));
			App.getController().appendTxtAreaLogOutput(
					"Port closed: " + port.closePort());
		} catch (SerialPortException ex) {
			App.getController().appendTxtAreaLogOutput(ex.getMessage());
		}
	}
}