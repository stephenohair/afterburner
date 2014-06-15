package com.ohair.stephen;

import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialComms {

	private SerialPort port;

	public SerialComms(Configuration config) {
		port = new SerialPort(config.getSerialPort());
	}

	public void sendToSerial(String message) {
		if (message.length() > 16) {
			try {
				System.out.println("Port opened: " + port.openPort());
				System.out.println("Params setted: "
						+ port.setParams(9600, 8, 1, 0));
				System.out.println("\"" + message
						+ "\" successfully writen to port: "
						+ port.writeBytes(message.getBytes()));
				System.out.println("Port closed: " + port.closePort());
			} catch (SerialPortException ex) {
				System.out.println(ex);
			}
		} else
			throw new RuntimeException("message length too large");
	}
}