/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kenza
 */
public class DetailController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label titre;
    @FXML
    private Label labauteur;
    @FXML
    private Label labgenre;
    @FXML
    private Label labcitation;
    @FXML
    private Button btnback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setTitre(String titre) {
        this.titre.setText(titre);
    }
     public void setLabauteur(String labauteur) {
        this.labauteur.setText(labauteur);
        
    }public void setLabgenre(String labgenre) {
        this.labgenre.setText(labgenre);
        
    }
    public void setImage(String image) {
        File imageFile = new File(image);
        Image i=new Image(imageFile.toURI().toString());
        this.image.setImage(i);
        
    }

    @FXML
    private void backmain(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("e_book.fxml"));

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
