package com.ohair.stephen;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	private static MainController controller;

	@Override
	public void start(Stage primaryStage) {
		try {

			// load main controller
			URL location = App.class.getClassLoader().getResource("Main.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(location);
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root = (Parent) fxmlLoader.load(location.openStream());

			controller = (MainController) fxmlLoader.getController();

			Scene scene = new Scene(root, 900, 724);
			scene.getStylesheets().add(
					App.class.getClassLoader().getResource("application.css")
							.toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Afterburner Client v1.0.0");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static MainController getController() {
		return controller;
	}

}
