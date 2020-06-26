package application;
/**
 * CSE360 Group Project
 * An application to process data from a user's
 * inputed .txt file.
 * @author Herlina A. Widodo
 */

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage stage;
	private static VBox root;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception{
		this.stage = stage;
		this.stage.setTitle("Group 8 - Data Analyzer");
		display();
	}
	
	private void display() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("MainView.fxml"));
		root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}