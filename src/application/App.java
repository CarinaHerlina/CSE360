package application;
/*
 * CSE360 Group Project
 * An application to process data from a user's
 * inputed .txt file.
 * @author Herlina A. Widodo
 */

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {
	private static Stage stage;
	private static Stage startStage;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception{
		App.startStage = stage;
		stage.setTitle("Group 8 - Data Analyzer");
		Parent root = FXMLLoader.load(getClass().getResource("start/StartView.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
	}

	public static void loadMain() throws IOException {
		startStage.close();
		Stage mainStage = new Stage();
		mainStage.setTitle("Group 8 - Data Analyzer");
		Parent root = FXMLLoader.load(App.class.getResource("main/MainView.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(App.class.getResource("css/styles.css").toExternalForm());
		mainStage.setScene(scene);
		mainStage.show();
	}

	public static void error() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("error/ErrorMessage.fxml"));
		BorderPane e1 = loader.load();

		Stage e1Stage = new Stage();
		e1Stage.setTitle("Error");
		e1Stage.initModality(Modality.WINDOW_MODAL);
		e1Stage.initOwner(stage);
		Scene scene = new Scene(e1);
		e1Stage.setScene(scene);
		e1Stage.showAndWait();
	}
	
	public static void error2() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("error/ErrorMessage2.fxml"));
		BorderPane e2 = loader.load();

		Stage e2Stage = new Stage();
		e2Stage.setTitle("Error");
		e2Stage.initModality(Modality.WINDOW_MODAL);
		e2Stage.initOwner(stage);
		Scene scene = new Scene(e2);
		e2Stage.setScene(scene);
		e2Stage.showAndWait();
	}
}