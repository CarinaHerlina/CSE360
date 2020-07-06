package application.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import application.App;
import application.DataAnalyzer;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class MainController {

	@FXML private static AnchorPane dataTablePane;

	@FXML public void initialize() {
		buildDataTable(10, GridSort.HORIZONTAL, NumericalSort.DESCENDING);
	}

	enum GridSort {
		HORIZONTAL,
		VERTICAL
	}

	enum NumericalSort {
		ASCENDING,
		DESCENDING
	}

	public static void buildDataTable(int numColumns, GridSort gridSort, NumericalSort numericalSort) {

		float[] dataArray;
		int dataArrayIndex = 0;
		if(numericalSort == NumericalSort.ASCENDING) dataArray = DataAnalyzer.getSortedDataArray();
		else dataArray = DataAnalyzer.getReversedDataArray();

		// Remainder used to determine how many numbers get placed in the bottom rom of the table
		int remainder = dataArray.length % numColumns;
		int numRows = (remainder != 0) ? (dataArray.length/numColumns + 1) : (dataArray.length/numColumns);


		dataTablePane.getChildren().clear();
		GridPane dataTable = new GridPane();


		if(gridSort == GridSort.HORIZONTAL){
			for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
				for (int columnIndex = 0; columnIndex < numColumns; columnIndex++) {
					Label label = new Label();
					label.setText(String.valueOf(dataArray[dataArrayIndex++]));
					label.setPrefHeight(30);
					label.setPrefWidth(100);
					label.setAlignment(Pos.CENTER);
					label.setTextAlignment(TextAlignment.CENTER);
					label.getStyleClass().add("dataTableLabel");
					dataTable.add(label, columnIndex, rowIndex);

					//Break out of loop if there are no more data points to add
					if(dataArrayIndex >= dataArray.length) break;
				}
			}
		} else { // if gridSort == GridSort.VERTICAL
			for (int columnIndex = 0; columnIndex < numColumns; columnIndex++) {
				for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
					// When sorting vertically, on the bottom row, only fill columns until
					// the value of remainder is reached.
					if(rowIndex >= numRows - 1 && columnIndex >= remainder) break;

					Label label = new Label();
					label.setText(String.valueOf(dataArray[dataArrayIndex++]));
					label.setPrefHeight(30);
					label.setPrefWidth(100);
					label.setAlignment(Pos.CENTER);
					label.setTextAlignment(TextAlignment.CENTER);
					label.getStyleClass().add("dataTableLabel");
					dataTable.add(label, columnIndex, rowIndex);
				}
			}
		}
		dataTablePane.getChildren().add(dataTable);
	}


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
	private void save() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Data");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));

		Window window = dataTablePane.getScene().getWindow();
		File file = fileChooser.showSaveDialog(window);
		if(file != null) {
			try{
				FileWriter fw = new FileWriter(file);
				float[] array = DataAnalyzer.getSortedDataArray();

				for(float i: array){
					fw.write(i + "\n");
				}
				fw.close();
			} catch (IOException e){
				//DO nothing
			}
		}
	}
	
	@FXML
	private void startNew() {
		
	}
}
