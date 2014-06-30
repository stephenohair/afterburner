package com.ohair.stephen;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

	@FXML
	private Button btnStart;
	@FXML
	private Button btnStop;
	@FXML
	private Button btnSend;
	@FXML
	private TextArea txtAreaLogOutput;
	@FXML
	private TextField txtFieldMessage;

	private Configuration config;
	private StatsRetriever timerTask;
	private Timer timer;
	private final SimpleDateFormat sdf = new SimpleDateFormat(
			"FF:MM:yyyy-hh:mm:ss:SSS");
	private SerialComms comms;

	public MainController() {
		config = new Configuration();
		comms = new SerialComms(config);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void start(ActionEvent event) {
		setStartEnabled(false);
		timerTask = new StatsRetriever();
		timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0,
				config.getRefreshIntervalInMillis());
	}

	public void stop(ActionEvent event) {
		timer.cancel();
		setStartEnabled(true);
	}

	public void sendTestMessage(ActionEvent event) {
		String msg = txtFieldMessage.getText();
		appendTxtAreaLogOutput("sending msg [" + config.getSerialConfig()
				+ ", msg=\"" + msg + "\"]");
		comms.sendToSerial(msg);
	}

	public void setStartEnabled(boolean b) {
		if (b) {
			btnStart.setDisable(false);
			btnStop.setDisable(true);
		} else {
			btnStart.setDisable(true);
			btnStop.setDisable(false);
		}
	}

	public void appendTxtAreaLogOutput(String s) {
		txtAreaLogOutput.appendText("\n" + sdf.format(new Date()) + " - " + s);
	}
}