package application.start;

import java.io.File;
import java.io.IOException;

import application.App;
import application.DataAnalyzer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class StartController {

	FileChooser fc = new FileChooser();

	@FXML
	private Button browseButton;

	@FXML
	private void upload() throws IOException {
		Window window = browseButton.getScene().getWindow();
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));
		File file = fc.showOpenDialog(window);

		//Only .txt files are accepted and program will not go forward without a file being uploaded.
		if(file != null && file.getPath().endsWith(".txt")){
			DataAnalyzer.addFileDataToArray(file);
			App.loadMain();
		}
		else{
			App.reloadStart();
		}
	}
}
