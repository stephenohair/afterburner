package com.ohair.stephen;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

	@FXML
	private Button btnConnect;
	@FXML
	private Button btnDisconnect;
	@FXML
	private Button btnSend;
	@FXML
	private TextArea txtAreaLogOutput;
	@FXML
	private TextField txtFieldMessage;
	@FXML
	private Button btnClear;
	@FXML
	private CheckBox chkbxEnableLog;

	private final Configuration config;
	private StatsRetriever timerTask;
	private Timer timer;
	private final SimpleDateFormat sdf = new SimpleDateFormat(
			"FF:MM:yyyy-hh:mm:ss:SSS");
	private final SerialComms comms;

	public MainController() {
		config = new Configuration();
		comms = new SerialComms(config);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void connect(ActionEvent event) {
		setConnectionButtonsEnabled(false);
		timerTask = new StatsRetriever();
		timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0,
				config.getRefreshIntervalInMillis());
		// System.out.println("Timer task scheduled to repeat every "
		// + config.getRefreshIntervalInMillis() + "ms");
	}

	public void disconnect(ActionEvent event) {
		timer.cancel();
		// System.out.println("Timer task finished");
		setConnectionButtonsEnabled(true);
	}

	public void sendTestMessage(ActionEvent event) {

		String msg = txtFieldMessage.getText();
		appendTxtAreaLogOutput("sending msg [" + config.getSerialConfig()
				+ ", msg=\"" + msg + "\"]");
		comms.sendToSerial(msg);
	}

	public void setConnectionButtonsEnabled(boolean b) {
		if (b) {
			btnConnect.setDisable(false);
			btnDisconnect.setDisable(true);
		} else {
			btnConnect.setDisable(true);
			btnDisconnect.setDisable(false);
		}
	}

	public void clearLog() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				txtAreaLogOutput.clear();
			}
		});
	}

	public void appendTxtAreaLogOutput(final String s) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (chkbxEnableLog.isSelected())
					if (s != null && !s.isEmpty())
						txtAreaLogOutput.appendText("\n"
								+ sdf.format(new Date()) + " - " + s);
			}
		});

	}
}