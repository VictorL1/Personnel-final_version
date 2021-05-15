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
import personnel.Ligue;
import personnel.SauvegardeImpossible;

public class AjouterController {
	private Employe admin;
	private Boolean isRoot;
	private Ligue ligue;
	@FXML
    private TextField addNom;
	@FXML
    private TextField addPrenom;
	@FXML
    private TextField addMail;
	@FXML
    private PasswordField addMdp;
	@FXML
    private DatePicker addDDD;
	@FXML
    private DatePicker addDDF;
	@FXML
	private Button valider;
	@FXML
	private Button retour;
	@FXML
    private void back(ActionEvent event) throws IOException {
		Stage stage;
        Parent root;
		stage = (Stage) retour.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageMenu.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        MenuController gestionLigue = loader.<MenuController>getController();
        gestionLigue.isItAdmin(ligue.getAdministrateur(), isRoot);
		}
	@FXML
	private void validation(ActionEvent event) throws IOException, ErreurDateDepart, ErreurDateFin, SauvegardeImpossible, SQLException {
		Stage stage;
        Parent root;
        if (addNom.getText().length() > 0 && addPrenom.getText().length() > 0 && addMail.getText().length() > 0 && addMdp.getText().length() > 0 && addDDD.getValue().toString().length() > 0 && addDDF.getValue().toString().length() >0 && addDDD.getValue().isBefore(addDDF.getValue())) {
        
        	ligue.addEmploye(addNom.getText(),addPrenom.getText(),addMail.getText(),addDDD.getValue(),addDDF.getValue(),addMdp.getText());
			
        	stage = (Stage) valider.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageMenu.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            MenuController gestionLigue = loader.<MenuController>getController();
            gestionLigue.isItAdmin(ligue.getAdministrateur(), isRoot);
        }
			
	}
	
	void whichLigue(Ligue uneLigue, Boolean root) {
		ligue = uneLigue;
		isRoot = root;
	}
	
}
