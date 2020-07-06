package application.functions;

import application.DataAnalyzer;
import application.main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;



public class DeleteController {

	@FXML private TextField floatTextField;

	@FXML private Label valueDeletedLabel;

	@FXML
	private void deleteData() {
		try {
			float numToDelete = Float.parseFloat(floatTextField.getText());
			if (DataAnalyzer.deleteFloatFromDataArray(numToDelete)) {
				valueDeletedLabel.setText(numToDelete + " was deleted.");
			} else {
				valueDeletedLabel.setText(numToDelete + " was not found.");
			}
			floatTextField.clear();
			valueDeletedLabel.setTextFill(Color.BLACK);
		} catch (NumberFormatException e) {
			floatTextField.clear();
			valueDeletedLabel.setText("Invalid characters entered.");
			valueDeletedLabel.setTextFill(Color.RED);
		}
		//MainController.buildDataTable(10, MainController.GridSort.HORIZONTAL, MainController.NumSort.DESCENDING);
	}
}
