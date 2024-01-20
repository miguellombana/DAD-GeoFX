package dad.geo.main;

import dad.geo.vista.geoController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GeoAPP extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		geoController mainController = new geoController();

		primaryStage.setTitle("GeoFX");
		primaryStage.setScene(new Scene(mainController.getView(), 800, 600));
		primaryStage.show();

	}

}
