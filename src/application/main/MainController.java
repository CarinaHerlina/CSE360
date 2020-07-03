package application.main;

import java.io.IOException;

import application.App;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class MainController {
	
	@FXML
	private ComboBox percentiles;

	@FXML
	private void add() throws IOException {
		App.addData();
	}
	
	@FXML
	private void delete() throws IOException {
		App.deleteData();
	}
	
	@FXML
	private void reset() throws IOException {
		App.resetView();
	}
	
	@FXML
	private void print() throws IOException {
		
	}
	
	@FXML
	private void startNew() throws IOException {
		
	}
}
