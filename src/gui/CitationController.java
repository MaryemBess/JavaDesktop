/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.citation;
import entite.e_book;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.CitationCRUD;
import service.DataValidation;
import service.e_bookCRUD;
import utils.myconnexion;

/**
 * FXML Controller class
 *
 * @author Kenza
 */
public class CitationController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<citation> tableView;
    @FXML
    private TableColumn<citation, Integer> colid;
    @FXML
    private TableColumn<citation, String> colauter;
    @FXML
    private TableColumn<citation, String> coltitre;
    @FXML
    private TableColumn<citation, String> colgenre;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfauteur;
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfgenre;
    @FXML
    private TextField search;
    @FXML
    private Label tflabauteur;
    @FXML
    private Label tflabtitre;
    @FXML
    private Label tflabgenre;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnback;
    ObservableList<citation> obs;
    Connection cx = myconnexion.getInstance().getCnx();
    ObservableList<citation> oc = FXCollections.observableArrayList();
    citation Aem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnmodifier.setDisable(true);
        btnsupprimer.setDisable(true);
        tableView.setEditable(true);
        CitationCRUD book = new CitationCRUD();
        oc = book.afficherCitation();
        colid.setCellValueFactory(new PropertyValueFactory<citation, Integer>("id"));
        colauter.setCellValueFactory(new PropertyValueFactory<citation, String>("auteur"));
        colauter.setCellFactory(TextFieldTableCell.forTableColumn());
        colauter.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<citation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<citation, String> event) {
                citation e = event.getRowValue();
                CitationCRUD P = new CitationCRUD();
                P.modifiAuteur(colid.getCellData(Aem), event.getNewValue());
                UpdateTableView();
            }
        });

        coltitre.setCellValueFactory(new PropertyValueFactory<citation, String>("text"));
        coltitre.setCellFactory(TextFieldTableCell.forTableColumn());
        coltitre.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<citation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<citation, String> event) {
                citation e = event.getRowValue();
                CitationCRUD P = new CitationCRUD();
                P.modifiText(colid.getCellData(Aem), event.getNewValue());
                UpdateTableView();
            }
        });
        colgenre.setCellValueFactory(new PropertyValueFactory<citation, String>("genre"));
        colgenre.setCellFactory(TextFieldTableCell.forTableColumn());
        colgenre.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<citation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<citation, String> event) {
                citation e = event.getRowValue();
                CitationCRUD P = new CitationCRUD();
                P.modifiGenre(colid.getCellData(Aem), event.getNewValue());
                UpdateTableView();
            }
        });
        tableView.setItems(oc);
    }

    @FXML
    private void searchLabel(ActionEvent event) {
        colid.setCellValueFactory(new PropertyValueFactory<citation, Integer>("id"));
        colauter.setCellValueFactory(new PropertyValueFactory<citation, String>("auteur"));
        coltitre.setCellValueFactory(new PropertyValueFactory<citation, String>("text"));
        colgenre.setCellValueFactory(new PropertyValueFactory<citation, String>("genre"));

        CitationCRUD cs = new CitationCRUD();
        oc = cs.afficherCitation();
        tableView.setItems(oc);
        FilteredList<citation> filteredData = new FilteredList<>(oc, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(a -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (a.getAuteur().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter Auteur
                } else if (a.getText().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter text
                } else if (a.getGenre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter genre
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<citation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    @FXML
    private void SAVECiattion(ActionEvent event) {
        boolean auteur = DataValidation.textFieldIsNull(tfauteur, tflabauteur, "Auteur is Required");
        boolean titre = DataValidation.textFieldIsNull(tftitre, tflabtitre, "Titre is Required");
        boolean genre = DataValidation.textFieldIsNull(tfgenre, tflabgenre, "Genre is Required");
        boolean alphabetauteur = DataValidation.textAlphabet(tfauteur, tflabauteur, "Please only enter letters from a - z");
        boolean alphabettitre = DataValidation.textAlphabet(tftitre, tflabtitre, "Please only enter letters from a - z");
        boolean alphabetgenre = DataValidation.textAlphabet(tfgenre, tflabgenre, "Please only enter letters from a - z");
        CitationCRUD P = new CitationCRUD();
        citation a = new citation(tfauteur.getText(), tftitre.getText(), tfgenre.getText());
        P.ajouterCitation(a);
        JOptionPane.showMessageDialog(null, "ADD DONE");
        CitationCRUD cs = new CitationCRUD();
        oc = cs.afficherCitation();
        colid.setCellValueFactory(new PropertyValueFactory<citation, Integer>("id"));
        colauter.setCellValueFactory(new PropertyValueFactory<citation, String>("auteur"));
        coltitre.setCellValueFactory(new PropertyValueFactory<citation, String>("text"));
        colgenre.setCellValueFactory(new PropertyValueFactory<citation, String>("genre"));
        tableView.setItems(oc);
        tfid.clear();
        tfauteur.clear();
        tftitre.clear();
        tfgenre.clear();
        chercher();
    }

    @FXML
    private void DELETEcitation(ActionEvent event) {
        Aem = tableView.getSelectionModel().getSelectedItem();
        CitationCRUD SEnt = new CitationCRUD();
        int id = Aem.getId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment SUPPRIMER cette citation ?!");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            if (id != 0) {
                SEnt.supprimerCitation(id);
            }
        }
        UpdateTableView();
    }

    @FXML
    private void UPDATEcitation(ActionEvent event) {
        boolean auteur = DataValidation.textFieldIsNull(tfauteur, tflabauteur, "Auteur is Required");
        boolean titre = DataValidation.textFieldIsNull(tftitre, tflabtitre, "Text is Required");
        boolean genre = DataValidation.textFieldIsNull(tfgenre, tflabgenre, "Genre is Required");
        boolean alphabetauteur = DataValidation.textAlphabet(tfauteur, tflabauteur, "Please only enter letters from a - z");
        boolean alphabettitre = DataValidation.textAlphabet(tftitre, tflabtitre, "Please only enter letters from a - z");
        boolean alphabetgenre = DataValidation.textAlphabet(tfgenre, tflabgenre, "Please only enter letters from a - z");

        Aem = tableView.getSelectionModel().getSelectedItem();
        int id = Aem.getId();
        CitationCRUD e = new CitationCRUD();
        citation a = new citation(Integer.parseInt(tfid.getText()), tfauteur.getText(), tftitre.getText(), tfgenre.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment MODIFIER cette citation?!");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            if (id != 0) {
                e.modifieCitation(a);
            }
        }
        JOptionPane.showMessageDialog(null, "Update DONE");
        UpdateTableView();
    }

    @FXML
    private void getSelected(MouseEvent event) {
        Aem = tableView.getSelectionModel().getSelectedItem();

        if (Aem == null) {
            JOptionPane.showMessageDialog(null, "Nothing selected");
            btnmodifier.setDisable(true);
            btnsupprimer.setDisable(true);
        } else {
            btnmodifier.setDisable(false);
            btnsupprimer.setDisable(false);
            tfid.setText(colid.getCellData(Aem).toString());
            tftitre.setText(coltitre.getCellData(Aem).toString());
            tfauteur.setText(colauter.getCellData(Aem).toString());
            tfgenre.setText(colgenre.getCellData(Aem).toString());

        }
    }

    public void chercher() {
        FilteredList<citation> filteredData = new FilteredList<>(oc, b -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aff -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (aff.getAuteur().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<citation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    public void UpdateTableView() {
        try {
            Aem = tableView.getSelectionModel().getSelectedItem();
            int id = Aem.getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("e_book.fxml"));
            Parent root = loader.load();
            e_bookController e = loader.getController();
            //   e.setYarab(id);
            rootPane.getChildren().setAll(root);
            e_bookCRUD cs = new e_bookCRUD();
            oc = cs.afficherBook();
            colid.setCellValueFactory(new PropertyValueFactory<citation, Integer>("id"));
            colauter.setCellValueFactory(new PropertyValueFactory<citation, String>("auteur"));
            coltitre.setCellValueFactory(new PropertyValueFactory<citation, String>("text"));
            colgenre.setCellValueFactory(new PropertyValueFactory<citation, String>("genre"));

            tableView.setItems(oc);
            tfid.clear();
            tfauteur.clear();
            tftitre.clear();
            tfgenre.clear();

              chercher();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
 private void refresh(ActionEvent event) {
        try {
            Aem = tableView.getSelectionModel().getSelectedItem();
            int id = Aem.getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("citation.fxml"));
            Parent root = loader.load();
            e_bookController e = loader.getController();
            //   e.setYarab(id);
            rootPane.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @FXML
    private void backmain(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gerer_citation_ebook.fxml"));

        try {
            Parent root = loader.load();
            btnback.getScene().setRoot(root);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("newCascadeStyleSheet.css").toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("Interface E_BOOK");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println("ERREUR MAIN INTERFACE");
            System.out.println(e.getMessage());
        }
    }

}
