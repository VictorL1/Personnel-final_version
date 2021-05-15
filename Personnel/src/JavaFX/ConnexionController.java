package JavaFX;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;

public class ConnexionController {
	
	@FXML
    private TextField mailText;
	@FXML
    private PasswordField mdpText;
	@FXML
	private Button seConnecter;
	@FXML
	private Label validate;	
	@FXML
    private void connexion(ActionEvent event) throws IOException {
		Stage stage;
        Parent root;
		for (Ligue uneLigue:GestionPersonnel.getGestionPersonnel().getLigues()) {
			
				if (mailText.getText().equals(uneLigue.getAdministrateur().getMail()) && mdpText.getText().equals(uneLigue.getAdministrateur().getPassword())) {
					stage = (Stage) seConnecter.getScene().getWindow();
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageMenu.fxml"));
		            root = loader.load();
		            Scene scene = new Scene(root);
		            stage.setScene(scene);
		            MenuController gestionLigue = loader.<MenuController>getController();
		            gestionLigue.isItAdmin(uneLigue.getAdministrateur(), false);
					
				}
				else {
					validate.setVisible(true);
				}
		}
		if (mailText.getText().equals("root") && mdpText.getText().equals("toor")) {
			stage = (Stage) seConnecter.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageMenuRoot.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene); 
            MenuRootController gestionLigue = loader.<MenuRootController>getController();
            gestionLigue.afficheLigues();
		}
		else {
			validate.setVisible(true);
		}
	}
}
