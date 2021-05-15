package JavaFX;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

public class MenuController {

	private final ObservableList<Employe> donneesTableau = FXCollections.observableArrayList();
	private Boolean estRoot;
	private Employe admin;
	@FXML
	private TableView<Employe> table;
	@FXML
	private TableColumn<Employe, String> nomTable;
	@FXML
	private TableColumn<Employe, String> prenomTable;
	@FXML
	private TableColumn<Employe, LocalDate> dateDTable;
	@FXML
	private TableColumn<Employe, LocalDate> dateFTable;
	@FXML
	private TableColumn<Employe, String> mailTable;
	@FXML
	private TableColumn<Employe, Void> supprimerTable;
	@FXML
	private Button deconnexion;
	@FXML
	private Button add;
	@FXML
	private Button retour;
	@FXML
	private Label nomLigue;
	@FXML	
    private void initialiser() throws IOException {
		if (table.getItems().size() > 0) {
            table.getItems().clear(); // refresh
        }
		for(Employe unEmploye:GestionPersonnel.getGestionPersonnel().getLigue(admin).getEmployes()) {
			donneesTableau.add(unEmploye);
		}
		table.setItems(donneesTableau);
		nomTable.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomTable.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		dateDTable.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
		dateFTable.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
		mailTable.setCellValueFactory(new PropertyValueFactory<>("mail"));
		ajouterColonneSupprimerEmploye();
		table.autosize();
	}
	@FXML
    private void deconnect(ActionEvent event) throws IOException {
		Stage stage;
        Parent root;
		stage = (Stage) deconnexion.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageConnexion.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
			
		}
	@FXML	
    private void ajouter() throws IOException {
		Stage stage;
        Parent root;
		stage = (Stage) add.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageAjouter.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        AjouterController gestionLigue = loader.<AjouterController>getController();
        gestionLigue.whichLigue(admin.getLigue(), estRoot);
	}
	
	@FXML	
    private void retourRoot() throws IOException {
		Stage stage;
        Parent root;
		stage = (Stage) add.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageMenuRoot.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        MenuRootController gestionLigue = loader.<MenuRootController>getController();
        gestionLigue.afficheLigues();
	}
	
	private void ajouterColonneSupprimerEmploye() {
        Callback<TableColumn<Employe, Void>, TableCell<Employe, Void>> cellFactory = new Callback<TableColumn<Employe, Void>, TableCell<Employe, Void>>() {
            @Override
            public TableCell<Employe, Void> call(final TableColumn<Employe, Void> param) {
                final TableCell<Employe, Void> cell = new TableCell<Employe, Void>() {

                    private final Button btn = new Button("X");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                                try {
									getTableView().getItems().get(getIndex()).remove();
								} catch (SauvegardeImpossible e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
                            try {
                                initialiser();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        supprimerTable.setCellFactory(cellFactory);
    }
	
	void isItAdmin(Employe unEmploye, Boolean estRoot) {
		admin = unEmploye;
		nomLigue.setText(admin.getLigue().getNom());
		 this.estRoot = estRoot;
		 retour.setVisible(estRoot);
		try {
			initialiser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

