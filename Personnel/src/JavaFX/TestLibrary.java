package JavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TestLibrary extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		BorderPane root = new BorderPane();
        FXMLLoader loader = new FXMLLoader(TestLibrary.class.getResource("PageConnexion.fxml"));
        root.setCenter(loader.load());
        Scene scene = new Scene(root, 700, 400);
        arg0.setTitle("Connexion");
        arg0.setScene(scene);
        arg0.show();
        arg0.centerOnScreen();
        arg0.setResizable(false);
	}

}
