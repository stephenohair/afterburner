package com.ohair.stephen;

import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialComms {

	private SerialPort port;
	private int baudRate;
	private int stopBits;
	private int dataBits;
	private int parity;

	public SerialComms(Configuration config) {
		port = new SerialPort(config.getSerialPort());
		baudRate = config.getBaudRate();
		stopBits = config.getStopBits();
		dataBits = config.getDataBits();
		parity = config.getParity();

	}

	public void sendToSerial(String message) {
		try {
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