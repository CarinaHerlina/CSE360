package application.functions;

import application.DataAnalyzer;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;

public class AddController {

	private FileChooser fc = new FileChooser();

	@FXML private Label floatErrorLabel;

	@FXML private TextField floatPointField;

	@FXML private Button browseButton;

	@FXML private Label fileErrorLabel;

	@FXML
	private void addFloat() {
		try { //Attempt to parse float and add to DataArray
			float num = Float.parseFloat(floatPointField.getText());
			DataAnalyzer.addFloatToDataArray(num);
			Stage stage = (Stage) floatPointField.getScene().getWindow();
			stage.close();

			//If error is caught, incorrect characters were entered.
		} catch (NumberFormatException e) {
			floatPointField.clear();
			floatErrorLabel.setMinHeight(17);
			floatErrorLabel.setVisible(true);
		}
	}

	@FXML
	public void openExplorer() {
		Window window = browseButton.getScene().getWindow();
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));
		File file = fc.showOpenDialog(window);

		if(file != null && file.getPath().endsWith(".txt")) {
			DataAnalyzer.addFileDataToArray(file);
			Stage stage = (Stage) floatPointField.getScene().getWindow();
			stage.close();
		} else {
			fileErrorLabel.setVisible(true);
			fileErrorLabel.setMinHeight(17);
		}
	}
}
