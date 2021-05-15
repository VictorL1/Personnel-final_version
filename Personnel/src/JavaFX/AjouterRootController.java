package JavaFX;

import static commandLineMenus.rendering.examples.util.InOut.getString;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import personnel.Employe;
import personnel.ErreurDateDepart;
import personnel.ErreurDateFin;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

public class AjouterRootController {
	private Ligue ligue;
	@FXML
    private TextField addNom;
	@FXML
	private Button valider;
	@FXML
	private Button retour;
	@FXML
    private void back(ActionEvent event) throws IOException {
		Stage stage;
        Parent root;
		stage = (Stage) retour.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageMenuRoot.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        MenuRootController gestionLigue = loader.<MenuRootController>getController();
        gestionLigue.afficheLigues();
		}
	@FXML
	private void validation(ActionEvent event) throws IOException, ErreurDateDepart, ErreurDateFin, SauvegardeImpossible, SQLException {
		Stage stage;
        Parent root;
        if (addNom.getText().length() > 0 ) {
        
        	GestionPersonnel.getGestionPersonnel().addLigue(addNom.getText());
			
        	stage = (Stage) valider.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageMenuRoot.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            MenuRootController gestionLigue = loader.<MenuRootController>getController();
            gestionLigue.afficheLigues();
        }
			
	}
	
	
	
}
