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

public class MenuRootController {

	private final ObservableList<Ligue> donneesTableau = FXCollections.observableArrayList();
	private Employe admin;
	@FXML
	private TableView<Ligue> table;
	@FXML
	private TableColumn<Ligue, String> nomTable;
	@FXML
	private TableColumn<Ligue, Void> afficherLigue;
	@FXML
	private TableColumn<Ligue, Void> supprimerTable;
	@FXML
	private Button deconnexion;
	@FXML
	private Button add;
	@FXML
    private void initialiser() throws IOException {
		if (table.getItems().size() > 0) {
            table.getItems().clear(); // refresh
        }
		for(Ligue uneLigue:GestionPersonnel.getGestionPersonnel().getLigues()) {
			donneesTableau.add(uneLigue);
		}
		table.setItems(donneesTableau);
		nomTable.setCellValueFactory(new PropertyValueFactory<>("nom"));
		ajouterColonneVoirLigue();
		ajouterColonneSupprimerLigue();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageAjouterLigueRoot.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

	}
	
	private void ajouterColonneSupprimerLigue() {
        Callback<TableColumn<Ligue, Void>, TableCell<Ligue, Void>> cellFactory = new Callback<TableColumn<Ligue, Void>, TableCell<Ligue, Void>>() {
            @Override
            public TableCell<Ligue, Void> call(final TableColumn<Ligue, Void> param) {
                final TableCell<Ligue, Void> cell = new TableCell<Ligue, Void>() {

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
	
	private void ajouterColonneVoirLigue() {
        Callback<TableColumn<Ligue, Void>, TableCell<Ligue, Void>> cellFactory = new Callback<TableColumn<Ligue, Void>, TableCell<Ligue, Void>>() {
            @Override
            public TableCell<Ligue, Void> call(final TableColumn<Ligue, Void> param) {
                final TableCell<Ligue, Void> cell = new TableCell<Ligue, Void>() {

                    private final Button btn = new Button("Afficher");
                    {
                    	
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            
                            try {
                            	Stage stage;
                                Parent root;
                                stage = (Stage) btn.getScene().getWindow();
                            	FXMLLoader loader = new FXMLLoader(getClass().getResource("PageMenu.fxml"));
								root = loader.load();
								Scene scene = new Scene(root);
								stage.setScene(scene);
								MenuController gestionLigue = loader.<MenuController>getController();
					            gestionLigue.isItAdmin(getTableView().getItems().get(getIndex()).getAdministrateur(), true);
							} catch (IOException e) {
								// TODO Auto-generated catch block
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

        afficherLigue.setCellFactory(cellFactory);
    }
	
	void afficheLigues() {
		try {
			initialiser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

