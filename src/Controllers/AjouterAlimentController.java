/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Aliment;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.AlimentCRUD;
import services.mailaddregime;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterAlimentController implements Initializable {

    @FXML
    private VBox View_MeditationMaryem;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btn_ajouterAliment;
    @FXML
    private TextField tf_libelle;
    @FXML
    private TextArea tf_recette;
    @FXML
    private TextField tf_carbs;
    @FXML
    private TextField tf_gras;
    @FXML
    private TextField tf_calories;
    @FXML
    private Button btnhome;
    @FXML
    private Button btnListerAliment;
    @FXML
    private Button btnListerRegime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void annuler(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("oui biensur ");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous quitter cette page");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerAliment.fxml"));
            Parent root =loader.load();
            ListerAlimentController pdc = loader.getController();
            Stage l = (Stage) btnCancel.getScene().getWindow();
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acceuil Alimentation");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(ListerAlimentController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }

    @FXML
    private void AjouterAliment(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("oui biensur ");
                alert.setHeaderText(null);
                alert.setContentText("vous etes sur de votre choix");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
         String lib = tf_libelle.getText();
    String rec =tf_recette.getText();
    
    String c = tf_calories.getText();
    String g = tf_gras.getText();
   String ca = tf_carbs.getText();
    
    float cal = Float.parseFloat(c);
    float gra = Float.parseFloat(g);
    float car = Float.parseFloat(ca);
    
    Aliment a = new Aliment(lib,rec,cal,gra,car);
    AlimentCRUD pcd = new AlimentCRUD();
    pcd.addAliment(a);
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerAliment.fxml"));
            Parent root =loader.load();
            ListerAlimentController pdc = loader.getController();
            Stage l = (Stage) btnCancel.getScene().getWindow();
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acceuil Alimentation");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(ListerAlimentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    try {
        mailaddregime.sendMail("bouchedakh.firas@gmail.com",a);
    } catch (Exception ex) {
        Logger.getLogger(AjouterAlimentController.class.getName()).log(Level.SEVERE, null, ex);
    }
                }
    }

    @FXML
    private void redirHome(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("oui biensur ");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous quitter cette page");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/EspaceAdmin.fxml"));
            Parent root =loader.load();
            EspaceAdminController pdc = loader.getController();
            Stage l = (Stage) btnListerAliment.getScene().getWindow();
            pdc.loadUI("/gui/HomeAlimentation.fxml");
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Espace Admin");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(EspaceAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }

     @FXML
    private void redirListerAliment(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("oui biensur ");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous quitter cette page");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerAliment.fxml"));
            Parent root =loader.load();
            ListerAlimentController pdc = loader.getController();
            Stage l = (Stage) btnListerAliment.getScene().getWindow();
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acceuil Alimentation");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(ListerAlimentController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }

    @FXML
    private void redirListerRegime(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("oui biensur ");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous quitter cette page");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerRegime.fxml"));
            Parent root =loader.load();
            ListerRegimeController pdc = loader.getController();
            Stage l = (Stage) btnListerAliment.getScene().getWindow();
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acceuil Alimentation");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(ListerRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}
    
}
