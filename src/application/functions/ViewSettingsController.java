package application.functions;

import application.main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ViewSettingsController {

	@FXML private Label updateSettingsLabel;
	@FXML private TextField numColumns;
	@FXML private RadioButton horizontalRadio;
	@FXML private RadioButton verticalRadio;
	@FXML private RadioButton ascendingRadio;
	@FXML private RadioButton descendingRadio;

	@FXML
	public void initialize() {
		numColumns.setText(String.valueOf(MainController.getNumColumns()));

		if(MainController.getGridSort() == MainController.GridSort.HORIZONTAL) {
			horizontalRadio.setSelected(true);
			verticalRadio.setSelected(false);
		}
		else {
			horizontalRadio.setSelected(false);
			verticalRadio.setSelected(true);
		}

		if(MainController.getNumSort() == MainController.NumSort.DESCENDING) {
			descendingRadio.setSelected(true);
			ascendingRadio.setSelected(false);
		}
		else {
			ascendingRadio.setSelected(true);
			descendingRadio.setSelected(false);
		}
	}

	@FXML
	private void updateViewSettings() {
		try {
			int newNumColumns = Integer.parseInt(numColumns.getText());
			MainController.setNumColumns(newNumColumns);

			if(verticalRadio.isSelected()) MainController.setGridSort(MainController.GridSort.VERTICAL);
			else MainController.setGridSort(MainController.GridSort.HORIZONTAL);

			if(ascendingRadio.isSelected()) MainController.setNumSort(MainController.NumSort.ASCENDING);
			else MainController.setNumSort(MainController.NumSort.DESCENDING);

			updateSettingsLabel.setText("Settings Successfully Updated");
			updateSettingsLabel.setTextFill(Color.BLACK);
			updateSettingsLabel.setVisible(true);
		} catch (NumberFormatException e) {
			numColumns.setText(String.valueOf(MainController.getNumColumns()));

			updateSettingsLabel.setText("Invalid Characters entered.");
			updateSettingsLabel.setTextFill(Color.RED);
			updateSettingsLabel.setVisible(true);
		}
	}
	
}
