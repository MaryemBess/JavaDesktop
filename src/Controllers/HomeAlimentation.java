/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */

public class HomeAlimentation implements Initializable {

    @FXML
    private Button btnAliment;
    @FXML
    private Button btnRegime;
    @FXML
    private ImageView imageAliment;
    @FXML
    private ImageView imageRegime;
    @FXML
    private VBox View_MeditationMaryem;
    @FXML
    private AnchorPane anchorpane;
 public int id = 13;
    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("..\\..\\..\\..\\..\\téléchargement.jpg");
        Image image = new Image(file.toURI().toString());
        
       imageAliment.setImage(image);
       imageRegime.setImage(image);
       
    }    
     

    @FXML
    private void ListerAliment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerAliment.fxml"));
            Parent root =loader.load();
            ListerAlimentController pdc = loader.getController();
            pdc.userid(id);
            
            
            Stage l = (Stage) btnAliment.getScene().getWindow();
            
            l.close();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Lister Aliment");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(HomeAlimentation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void ListerRegime(ActionEvent event) {
    }
    
}
