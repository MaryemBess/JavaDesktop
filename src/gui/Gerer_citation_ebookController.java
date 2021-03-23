/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import static javafx.collections.FXCollections.rotate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Kenza
 */
public class Gerer_citation_ebookController implements Initializable {
Timeline rotate= new Timeline();

    @FXML
    private Button btnE_book;
    @FXML
    private Button btnCitation;
    @FXML
    private ImageView logoview;

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
@FXML
    public void rotateImage(){
    DoubleProperty r = logoview.rotateProperty();
    rotate.getKeyFrames().addAll(
                        new KeyFrame(new Duration(0),new KeyValue(r,0)),
                        new KeyFrame(new Duration(2300),new KeyValue(r,-360))
                                );
    rotate.play();
    }

}
