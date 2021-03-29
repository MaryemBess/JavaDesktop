/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Aliment;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.AlimentCRUD;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListerAlimentClientController implements Initializable {

    @FXML
    private Button btnhome;
    @FXML
    private Button btnListerAliment;
    @FXML
    private Button btnListerRegimeClient;
    @FXML
    private TableView<Aliment> TABLEALIMENT;
    @FXML
    private TableColumn<Aliment, String> LIBELLE;
    @FXML
    private TableColumn<Aliment, String> RECETTE;
    @FXML
    private TableColumn<Aliment, Float> COLORIES;
    @FXML
    private TableColumn<Aliment, Float> GRAS;
    @FXML
    private TableColumn<Aliment, Float> CARBS;
    
    public int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LIBELLE.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        RECETTE.setCellValueFactory(new PropertyValueFactory<>("recette"));
        COLORIES.setCellValueFactory(new PropertyValueFactory<>("calorie"));
        GRAS.setCellValueFactory(new PropertyValueFactory<>("gras"));
        CARBS.setCellValueFactory(new PropertyValueFactory<>("carbs"));
         System.out.println("1");
        AlimentCRUD pcd = new AlimentCRUD();
        ObservableList<Aliment> data = pcd.lister();
        TABLEALIMENT.setItems(data);
        System.out.println("2");
    }    


    @FXML
    private void AjouterAlimentClient(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/AjouterAlimentClient.fxml"));
            Parent root =loader.load();
            AjouterAlimentClientController pdc = loader.getController();
            
            
            
            Stage l = (Stage) TABLEALIMENT.getScene().getWindow();
            
            l.close();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Lister Aliment");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterAlimentClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void Details(ActionEvent event) {
        Aliment al = TABLEALIMENT.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/DetailAlimentClient.fxml"));
            Parent root =loader.load();
            DetailAlimentClientController pdc = loader.getController();
            pdc.AfficherAliment(al,id);
            
            
            
            
            
            
            Stage l = (Stage) TABLEALIMENT.getScene().getWindow();
            
            l.close();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Detail Aliment");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DetailAlimentClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void userid(int idd){
     id=idd;
        System.out.println("id user at Lister"+id);
    }

    @FXML
    private void redirListerAliment(ActionEvent event) {
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
        }
    }

    @FXML
    private void redirListerRegimeClient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerRegimeClientClient.fxml"));
            Parent root =loader.load();
            ListerRegimeClientController pdc = loader.getController();
            Stage l = (Stage) btnListerAliment.getScene().getWindow();
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acceuil Alimentation");
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
            EspaceAdminController pdc = loader.getController();
            Stage l = (Stage) btnListerAliment.getScene().getWindow();
            pdc.loadUI("/gui/HomeAlimentationClient.fxml");
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acceuil");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

