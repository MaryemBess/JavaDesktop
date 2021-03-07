/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.citation;
import java.net.URL;
import entite.e_book;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javax.swing.JOptionPane;
import service.DataValidation;
import service.e_bookCRUD;
import utils.myconnexion;

/**
 * FXML Controller class
 *
 * @author Kenza
 */
public class e_bookController implements Initializable {

    ObservableList<e_book> obs;
    Connection cx = myconnexion.getInstance().getCnx();

    e_book Aem;

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfauteur;
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfgenre;
    @FXML
    private ChoiceBox<citation> tfcitation;
    @FXML
    private TableView<e_book> tableView;
    @FXML
    private TableColumn<e_book, Integer> colid;
    @FXML
    private TableColumn<e_book, String> colauteur;
    @FXML
    private TableColumn<e_book, String> coltitre;
    @FXML
    private TableColumn<e_book, String> colgenre;
    @FXML
    private TableColumn<e_book, String> colcitation;
    @FXML
    private TableColumn<e_book, Integer> colevaluation;
    @FXML
    private TableColumn<e_book, Integer> col_nb_fav;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;

    ObservableList<e_book> oc = FXCollections.observableArrayList();
    @FXML
    private TextField search;
    @FXML
    private AnchorPane rootPane;
    private TextField tfcitation1;
    @FXML
    private Label tflabauteur;
    @FXML
    private Label tflabtitre;
    @FXML
    private Label tflabgenre;
    @FXML
    private Button btimprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnmodifier.setDisable(true);
        btnsupprimer.setDisable(true);
        btnajouter.setDisable(true);
        e_bookCRUD book = new e_bookCRUD();
        oc = book.afficherBook();
        colid.setCellValueFactory(new PropertyValueFactory<e_book, Integer>("id"));
        colauteur.setCellValueFactory(new PropertyValueFactory<e_book, String>("auteur"));
        coltitre.setCellValueFactory(new PropertyValueFactory<e_book, String>("titre"));
        colgenre.setCellValueFactory(new PropertyValueFactory<e_book, String>("genre"));
        colcitation.setCellValueFactory(new PropertyValueFactory<e_book, String>("id_c"));
        colevaluation.setCellValueFactory(new PropertyValueFactory<e_book, Integer>("evaluation"));
        col_nb_fav.setCellValueFactory(new PropertyValueFactory<e_book, Integer>("fav"));

        tableView.setItems(oc);
//        options = book.fillcombobox();
//        tfcitation.setItems(options);
        try {
            String req = " select * from citations ";
            ResultSet rs = myconnexion.getInstance().getCnx().createStatement().executeQuery(req);
            ObservableList<citation> choices = FXCollections.observableArrayList();
            while (rs.next()) {
                choices.add(new citation(rs.getInt("id"), rs.getString("auteur"), rs.getString("text")));
            }
            tfcitation.setItems(choices);
            tfcitation.getSelectionModel().select(0);

        } catch (Exception ex) {
            System.out.println("ERREUR AFFICHAGE COMBOBOX");
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void SAVEbook(ActionEvent event) {
        boolean auteur = DataValidation.textFieldIsNull(tfauteur, tflabauteur, "Auteur is Required");
        boolean titre = DataValidation.textFieldIsNull(tftitre, tflabtitre, "Titre is Required");
        boolean genre = DataValidation.textFieldIsNull(tfgenre, tflabgenre, "Genre is Required");
        boolean alphabetauteur = DataValidation.textAlphabet(tfauteur, tflabauteur, "Please only enter letters from a - z");
        boolean alphabettitre = DataValidation.textAlphabet(tftitre, tflabtitre, "Please only enter letters from a - z");
        boolean alphabetgenre = DataValidation.textAlphabet(tfgenre, tflabgenre, "Please only enter letters from a - z");
        System.out.println(auteur);
        int id_c = tfcitation.getSelectionModel().getSelectedItem().getId();
        e_bookCRUD P = new e_bookCRUD();
        e_book a = new e_book(tfauteur.getText(), tftitre.getText(), tfgenre.getText(), 0, id_c, 0);
        
     P.ajouterBook(a);
        JOptionPane.showMessageDialog(null, "ADD DONE");
        e_bookCRUD cs = new e_bookCRUD();
        oc = cs.afficherBook();
        colid.setCellValueFactory(new PropertyValueFactory<e_book, Integer>("id"));
        colauteur.setCellValueFactory(new PropertyValueFactory<e_book, String>("auteur"));
        coltitre.setCellValueFactory(new PropertyValueFactory<e_book, String>("titre"));
        colgenre.setCellValueFactory(new PropertyValueFactory<e_book, String>("genre"));
        colcitation.setCellValueFactory(new PropertyValueFactory<e_book, String>("id_c"));
        colevaluation.setCellValueFactory(new PropertyValueFactory<e_book, Integer>("evaluation"));
        col_nb_fav.setCellValueFactory(new PropertyValueFactory<e_book, Integer>("fav"));
        tableView.setItems(oc);
        tfid.clear();
        tfauteur.clear();
        tftitre.clear();
        tfgenre.clear();
        tfcitation1.clear();
        chercher();

    }

    @FXML
    private void UPDATEbook(ActionEvent event) {
        boolean auteur = DataValidation.textFieldIsNull(tfauteur, tflabauteur, "Auteur is Required");
        boolean titre = DataValidation.textFieldIsNull(tftitre, tflabtitre, "Titre is Required");
        boolean genre = DataValidation.textFieldIsNull(tfgenre, tflabgenre, "Genre is Required");
        boolean alphabetauteur = DataValidation.textAlphabet(tfauteur, tflabauteur, "Please only enter letters from a - z");
        boolean alphabettitre = DataValidation.textAlphabet(tftitre, tflabtitre, "Please only enter letters from a - z");
        boolean alphabetgenre = DataValidation.textAlphabet(tfgenre, tflabgenre, "Please only enter letters from a - z");
       
        
        Aem = tableView.getSelectionModel().getSelectedItem();
         int id = Aem.getId();
         
        e_bookCRUD e = new e_bookCRUD();
        int id_c = tfcitation.getSelectionModel().getSelectedItem().getId();
        e_book a = new e_book(Integer.parseInt(tfid.getText()), tfauteur.getText(), tftitre.getText(), tfgenre.getText(), 0, id_c, 0);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment MODIFIER ce e_book ?!");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            if (id != 0) {
                 e.modifieBook(a);

            }
        }
        JOptionPane.showMessageDialog(null, "Update DONE");
        UpdateTableView();

    }

    @FXML
    private void DELETEbook(ActionEvent event) {
        Aem = tableView.getSelectionModel().getSelectedItem();
        e_bookCRUD SEnt = new e_bookCRUD();
        int id = Aem.getId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment SUPPRIMER ce e_book ?!");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            if (id != 0) {
                SEnt.supprimerBook(id);
            }
        }
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
            tfauteur.setText(colauteur.getCellData(Aem).toString());
            tfgenre.setText(colgenre.getCellData(Aem).toString());
        }
    }

    public void chercher() {
        FilteredList<e_book> filteredData = new FilteredList<>(oc, b -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aff -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (aff.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<e_book> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    @FXML
    void searchLabel() {
        colid.setCellValueFactory(new PropertyValueFactory<e_book, Integer>("id"));
        colauteur.setCellValueFactory(new PropertyValueFactory<e_book, String>("auteur"));
        coltitre.setCellValueFactory(new PropertyValueFactory<e_book, String>("titre"));
        colgenre.setCellValueFactory(new PropertyValueFactory<e_book, String>("genre"));
        colcitation.setCellValueFactory(new PropertyValueFactory<e_book, String>("id_c"));
        colevaluation.setCellValueFactory(new PropertyValueFactory<e_book, Integer>("evaluation"));
        col_nb_fav.setCellValueFactory(new PropertyValueFactory<e_book, Integer>("fav"));
        e_bookCRUD cs = new e_bookCRUD();
        oc = cs.afficherBook();
        tableView.setItems(oc);
        FilteredList<e_book> filteredData = new FilteredList<>(oc, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(a -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (a.getAuteur().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter Auteur
                } else if (a.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter titre
                } else if (a.getGenre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter genre
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<e_book> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    private void refresh(ActionEvent event) {
        try {
            Aem = tableView.getSelectionModel().getSelectedItem();
            int id = Aem.getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("e_book.fxml"));
            Parent root = loader.load();
            e_bookController e = loader.getController();
            //   e.setYarab(id);
            rootPane.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

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
            colid.setCellValueFactory(new PropertyValueFactory<e_book, Integer>("id"));
            colauteur.setCellValueFactory(new PropertyValueFactory<e_book, String>("auteur"));
            coltitre.setCellValueFactory(new PropertyValueFactory<e_book, String>("titre"));
            colgenre.setCellValueFactory(new PropertyValueFactory<e_book, String>("genre"));
            colcitation.setCellValueFactory(new PropertyValueFactory<e_book, String>("id_c"));
            colevaluation.setCellValueFactory(new PropertyValueFactory<e_book, Integer>("evaluation"));
            col_nb_fav.setCellValueFactory(new PropertyValueFactory<e_book, Integer>("fav"));
            tableView.setItems(oc);
            tfid.clear();
            tfauteur.clear();
            tftitre.clear();
            tfgenre.clear();
            tfcitation1.clear();
            chercher();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Imprimer(ActionEvent event) {
        try {
            printNode(tableView);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(e_bookController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(e_bookController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(e_bookController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(e_bookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout
                = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX
                = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY
                = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        node.getTransforms().add(scale);

        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();
            }
        }
        node.getTransforms().remove(scale);
    }
}
