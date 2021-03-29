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
import javafx.stage.Stage;
import services.AlimentCRUD;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DetailAlimentController implements Initializable {

    @FXML
    private TextField tf_libelle;
    @FXML
    private TextField tf_carbs;
    @FXML
    private TextField tf_gras;
    @FXML
    private TextField tf_calories;
    @FXML
    private TextArea tf_recette;
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    
     public  int Id_a;
     public int id_s;
    @FXML
    private Button btnListerAliment;
    @FXML
    private Button btnListerRegime;
    @FXML
    private Button btnhome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Acceuil(ActionEvent event) {
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
            Stage l = (Stage) btnModifier.getScene().getWindow();
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acceuil Alimentation");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(DetailAlimentController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }

    @FXML
    private void ModifierAliment(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("oui biensur ");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous modifier ce aliment");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
        try {
            
            String lib = tf_libelle.getText();
            String rec =tf_recette.getText();
            
            String c = tf_calories.getText();
            String g = tf_gras.getText();
            String ca = tf_carbs.getText();
            
            float cal = Float.parseFloat(c);
            float gra = Float.parseFloat(g);
            float car = Float.parseFloat(ca);
            
            Aliment a = new Aliment(Id_a,lib,rec,cal,gra,car);
            AlimentCRUD pcd = new AlimentCRUD();
            pcd.ModifierAliment(a);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerAliment.fxml"));
            Parent root =loader.load();
            ListerAlimentController pdc = loader.getController();
            Stage l = (Stage) btnModifier.getScene().getWindow();
            
                 l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("window modifier");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(DetailAlimentController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }

    @FXML
    private void SupprimerAliment(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("oui biensur ");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous ajouter ce aliment");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
        try {
            String lib = tf_libelle.getText();
            String rec =tf_recette.getText();
            
            String c = tf_calories.getText();
            String g = tf_gras.getText();
            String ca = tf_carbs.getText();
            
            float cal = Float.parseFloat(c);
            float gra = Float.parseFloat(g);
            float car = Float.parseFloat(ca);
            Aliment a = new Aliment(Id_a,lib,rec,cal,gra,car);
            AlimentCRUD pcd = new AlimentCRUD();
            pcd.supprimerAliment(a);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerAliment.fxml"));
            Parent root =loader.load();
            ListerAlimentController pdc = loader.getController();
            Stage l = (Stage) btnSupprimer.getScene().getWindow();
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("window modifier");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(DetailAlimentController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }
 
    public void AfficherAliment(Aliment al,int id){
        id_s=id;
       Id_a=al.getId_aliment();
        String lib = al.getLibelle();
       String rec =al.getRecette();
       
       String cal=String.valueOf(al.getCalorie());
       String gra=String.valueOf(al.getGras());
       String car=String.valueOf(al.getCarbs());
       
       tf_libelle.setText(lib);
       tf_recette.setText(rec);
       tf_calories.setText(cal);
       tf_gras.setText(gra);
       tf_carbs.setText(car);
        System.out.println("this is id user in details "+id_s);
    
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
        }}
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
        }
    }}
}
