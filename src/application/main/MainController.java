package application.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import application.App;
import application.DataAnalyzer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;


public class MainController {

	private static int numColumns = 10;
	private static GridSort gridSort = GridSort.HORIZONTAL;
	private static NumSort numSort = NumSort.DESCENDING;

	public enum GridSort {
		HORIZONTAL,
		VERTICAL
	}

	public enum NumSort {
		ASCENDING,
		DESCENDING
	}

	@FXML private AnchorPane dataTablePane;
	@FXML private Label meanLabel;
	@FXML private Label medianLabel;
	@FXML private Label topOccurence;
	@FXML private Label secondOccurrence;
	@FXML private Label thirdOccurrence;
	@FXML private ComboBox<Double> percentiles;
	@FXML private Label valuesAboveLabel;
	@FXML private Label percentileAvgLabel;
	@FXML private Label numPercentilesLabel;

	@FXML public void initialize() {
		ObservableList<Double> percentilesList = FXCollections.observableArrayList(0.0, 10.0, 20.0, 30.0, 40.0,
				50.0, 60.0, 70.0, 80.0, 90.0);
		percentiles.setItems(percentilesList);
		percentiles.setValue(90.0);
		updateMainView();
	}

	@FXML
	private void add() throws IOException {
		Parent add = FXMLLoader.load(App.class.getResource("functions/AddView.fxml"));

		Stage addStage = new Stage();
		addStage.setTitle("Add New Data");
		addStage.initModality(Modality.APPLICATION_MODAL);
		addStage.setScene(new Scene(add));
		addStage.showAndWait();
		updateMainView();
	}
	
	@FXML
	private void delete() throws IOException {
		Parent add = FXMLLoader.load(App.class.getResource("functions/DeleteView.fxml"));

		Stage delStage = new Stage();
		delStage.setTitle("Delete Data");
		delStage.initModality(Modality.APPLICATION_MODAL);
		delStage.setScene(new Scene(add));
		delStage.showAndWait();
		updateMainView();
	}
	
	@FXML
	private void viewSettings() throws IOException {
		Parent add = FXMLLoader.load(App.class.getResource("functions/ViewSettings.fxml"));

		Stage viewSettingsStage = new Stage();
		viewSettingsStage.setTitle("View Settings");
		viewSettingsStage.initModality(Modality.APPLICATION_MODAL);
		viewSettingsStage.setScene(new Scene(add));
		viewSettingsStage.showAndWait();
		updateMainView();
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
	private void resetData() throws IOException{
		Parent add = FXMLLoader.load(App.class.getResource("functions/ResetView.fxml"));

		Stage viewSettingsStage = new Stage();
		viewSettingsStage.setTitle("Reset Data");
		viewSettingsStage.initModality(Modality.APPLICATION_MODAL);
		viewSettingsStage.setScene(new Scene(add));
		viewSettingsStage.showAndWait();
		updateMainView();
	}

	public static void setGridSort(GridSort newSort) {
		gridSort = newSort;
	}

	public static GridSort getGridSort(){
		return gridSort;
	}

	public static void setNumSort(NumSort newNumSort) {
		numSort = newNumSort;
	}

	public static NumSort getNumSort(){
		return numSort;
	}

	public static void setNumColumns(int newNumColumns) {
		numColumns = newNumColumns;
	}

	public static int getNumColumns() {
		return numColumns;
	}

	public static void resetDataTableDefaults(){
		numColumns = 10;
		gridSort = GridSort.HORIZONTAL;
		numSort = NumSort.DESCENDING;
	}

	@FXML
	private void updateMainView(){
		buildDataTable(numColumns, gridSort, numSort);

		String meanLabelStr, medianLabelStr;
		if (DataAnalyzer.getDataArraySize() == 0) {
			meanLabelStr = "N/A";
			medianLabelStr = "N/A";
		} else {
			meanLabelStr = String.valueOf(DataAnalyzer.getMean());
			medianLabelStr = String.valueOf(DataAnalyzer.getMedian());
		}
		meanLabel.setText(meanLabelStr);
		medianLabel.setText(medianLabelStr);

		Object[] occurrences = DataAnalyzer.getTopThreeOccuring();
		topOccurence.setText("N/A");
		secondOccurrence.setText("N/A");
		thirdOccurrence.setText("N/A");

		if(occurrences[0] != null) {
			topOccurence.setText(occurrences[0].toString());
		}
		if(occurrences[1] != null) {
			secondOccurrence.setText(occurrences[1].toString());
		}
		if(occurrences[2] != null) {
			thirdOccurrence.setText(occurrences[2].toString());
		}

		float[] numAbovePercentiles = DataAnalyzer.getPercentile(percentiles.getValue());
		String numToString = "";
		for (int i = 0; i < numAbovePercentiles.length; i++) {
			numToString += numAbovePercentiles[i] + "   ";
		}
		valuesAboveLabel.setText(numToString);
		percentileAvgLabel.setText("" + DataAnalyzer.getAverageAbovePercentile(percentiles.getValue()));
		numPercentilesLabel.setText("" + numAbovePercentiles.length);

	}

	private void buildDataTable(int numColumns, GridSort gridSort, NumSort numSort) {

		float[] dataArray;
		int dataArrayIndex = 0;
		if(numSort == NumSort.ASCENDING) dataArray = DataAnalyzer.getSortedDataArray();
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
		} else if(gridSort == GridSort.VERTICAL && numColumns == 1) {
			for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
				Label label = new Label();
				label.setText(String.valueOf(dataArray[dataArrayIndex++]));
				label.setPrefHeight(30);
				label.setPrefWidth(100);
				label.setAlignment(Pos.CENTER);
				label.setTextAlignment(TextAlignment.CENTER);
				label.getStyleClass().add("dataTableLabel");
				dataTable.add(label, 0, rowIndex);
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
}
