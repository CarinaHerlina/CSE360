package application.start;

import java.io.IOException;

import application.App;
import javafx.fxml.FXML;

public class StartController {

	private App app;

	@FXML
	private void upload() throws IOException {
		app.goMain();
	}
}
