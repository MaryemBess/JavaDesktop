/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kenza
 */
public class Gerer_citation_ebookController implements Initializable {

    @FXML
    private Button btnE_book;
    @FXML
    private Button btnCitation;

    /**
//     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void fenetreE_book(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("e_book.fxml"));

        try {
            Parent root = loader.load();
            btnE_book.getScene().setRoot(root);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("newCascadeStyleSheet.css").toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("Interface E_BOOK");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println("ERREUR  INTERFACE BOOK");
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void fenetreCitation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("citation.fxml"));

        try {
            Parent root = loader.load();
            btnE_book.getScene().setRoot(root);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("newCascadeStyleSheet.css").toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("Interface CITATION");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println("ERREUR MAIN INTERFACE");
            System.out.println(e.getMessage());
        }
    }

}
