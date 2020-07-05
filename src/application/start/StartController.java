package application.start;

import java.io.File;
import java.io.IOException;

import application.App;
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
		File file = fc.showOpenDialog(window);
		if(file != null) {
			System.out.println("File Found");
		}

		App.loadMain();
	}
}
