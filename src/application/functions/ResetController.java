package application.functions;

import application.App;
import application.DataAnalyzer;
import application.main.MainController;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public class ResetController {

	FileChooser fc = new FileChooser();

	@FXML private Button browseButton;
	@FXML private Label fileErrorLabel;

	@FXML
	private void reset() {
		Window window = browseButton.getScene().getWindow();
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));
		File file = fc.showOpenDialog(window);

		//Only .txt files are accepted
		if(file != null && file.getPath().endsWith(".txt")){
			DataAnalyzer.resetDataArray();
			MainController.resetDataTableDefaults();

			DataAnalyzer.addFileDataToArray(file);
			Stage stage = (Stage) browseButton.getScene().getWindow();
			stage.close();
		} else {
			fileErrorLabel.setMinHeight(20);
			fileErrorLabel.setVisible(true);
		}
	}
}
