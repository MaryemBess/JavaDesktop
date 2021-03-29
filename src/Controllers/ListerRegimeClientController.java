/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Regime;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.RegimeCRUD;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListerRegimeClientController implements Initializable {

    @FXML
    private VBox View_MeditationMaryem;
    @FXML
    private Button btnhome;
    @FXML
    private Button btnListerAliment;
    @FXML
    private Button btnListerRegime;
     @FXML
    private TableView<Regime> tableregime;
    @FXML
    private TableColumn<Regime, Integer> ID;
    @FXML
    private TableColumn<Regime, String> DESCRIPTION;
    @FXML
    private TableColumn<Regime, Integer> DURATION;
    @FXML
    private Button btnModifier;
    @FXML
    private Button supppreg;
    @FXML
    private Button btnAjouterRegime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        RegimeCRUD rg = new RegimeCRUD();
        ObservableList<Regime> data = rg.listerAll();
        ID.setCellValueFactory(new PropertyValueFactory<Regime,Integer>("id_regime"));
        DESCRIPTION.setCellValueFactory(new PropertyValueFactory<Regime,String>("description"));
        DURATION.setCellValueFactory(new PropertyValueFactory<Regime,Integer>("duration"));
      
       
        tableregime.setItems(data);
    }    

    @FXML
    private void ModifierRegimeClient(ActionEvent event) {
         try {
            Regime rg = tableregime.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ModifierRegimeClient.fxml"));
            Parent root =loader.load();
            ModifierRegimeClientController pdc = loader.getController();
            pdc.Showinformation(rg);
             System.out.println("before close");
            Stage l = (Stage) btnModifier.getScene().getWindow();
             System.out.println("after close");
            l.close();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifier Regime");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(ModifierRegimeClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerRegime(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("oui biensur ");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous supprimer ce regime");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
         Regime rg = tableregime.getSelectionModel().getSelectedItem();
        RegimeCRUD rgc = new RegimeCRUD();
        rgc.Supprimerreg(rg.getId_regime());
        System.out.println("regime supprimer");
        Stage l = (Stage) supppreg.getScene().getWindow();
        l.close();
    }}

    @FXML
    private void AjouterRegime(ActionEvent event) {
        try {
            Regime rg = tableregime.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/AjouterRegime.fxml"));
            Parent root =loader.load();
            AjouterRegimeController pdc = loader.getController();
            
            
            Stage l = (Stage) btnModifier.getScene().getWindow();
            
            l.close();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ajouter Regime");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(AjouterRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @FXML
    private void redirListerAlimentClient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerAlimentClient.fxml"));
            Parent root =loader.load();
            ListerAlimentClientController pdc = loader.getController();
            Stage l = (Stage) btnListerAliment.getScene().getWindow();
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acceuil Alimentation");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(ListerAlimentClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirListerRegime(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerRegimeClient.fxml"));
            Parent root =loader.load();
            ListerRegimeClientController pdc = loader.getController();
            Stage l = (Stage) btnModifier.getScene().getWindow();
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Lister Regime Client");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(ListerRegimeClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void redirHome(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Acceuil.fxml"));
            Parent root =loader.load();
            AcceuilController pdc = loader.getController();
            Stage l = (Stage) btnListerAliment.getScene().getWindow();
            pdc.loadUI("/gui/HomeAlimentationClient.fxml");
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Espace Admin");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

