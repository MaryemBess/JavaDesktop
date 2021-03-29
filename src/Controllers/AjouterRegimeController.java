/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import ApiAliment.FoodDataCollector;
import Entity.Aliment;
import Entity.Regime;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.AlimentCRUD;
import services.RegimeCRUD;
import services.mailaddregime;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterRegimeController implements Initializable {

   @FXML
    private TableView<Aliment> TABLEALIMENT;
    @FXML
    private TableColumn<Aliment, Integer> ID;
    @FXML
    private TableColumn<Aliment, String> LIBELLE;
    @FXML
    private TableColumn<Aliment, String> RECETTE;
    @FXML
    private TableColumn<Aliment, Float> CALORIES;
    @FXML
    private TableColumn<Aliment, Float> GRAS;
    @FXML
    private TableColumn<Aliment, Float> CARBS;
    @FXML
    private TextArea DESCRIPTION;
    @FXML
    private TextField DURATION;
    @FXML
    private Button btnAjouter;
    @FXML
    private TableView<Aliment> Selectedtable;
    @FXML
    private TableColumn<Aliment, Integer> selectedid;
    @FXML
    private TableColumn<Aliment, String> selectedLibelle;
    @FXML
    private TableColumn<Aliment, String> selectedrecette;
    @FXML
    private TableColumn<Aliment, Float> selectedcalories;
    @FXML
    private TableColumn<Aliment, Float> selectedgras;
    @FXML
    private TableColumn<Aliment, Float> selectedcarbs;
    @FXML
    private Button btnSupprimer;
    @FXML
    private VBox View_MeditationMaryem;
    @FXML
    private Button btnListerAliment;
    @FXML
    private Button btnListerRegime;
    @FXML
    private Button btnAjouterRegime;
    @FXML
    private TextField tf_search;
    @FXML
    private Button btn_recherche;
    @FXML
    private Button btn_clear;
    @FXML
    private Label tf_alert;
    ObservableList<Aliment> listA = FXCollections.observableArrayList();
    @FXML
    private Button btnhome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tf_search.setStyle("-fx-text_fil: black;");
        // TODO
        selectedid.setCellValueFactory(new PropertyValueFactory<Aliment,Integer>("id_aliment"));
        selectedLibelle.setCellValueFactory(new PropertyValueFactory<Aliment,String>("libelle"));
        selectedrecette.setCellValueFactory(new PropertyValueFactory<Aliment,String>("recette"));
        
        
        selectedcalories.setCellValueFactory(new PropertyValueFactory<Aliment,Float>("calorie"));
        selectedgras.setCellValueFactory(new PropertyValueFactory<Aliment,Float>("gras"));
        selectedcarbs.setCellValueFactory(new PropertyValueFactory<Aliment,Float>("carbs"));
        ///////////////////////////////////////////////////////////////////////////////////
        
        ID.setCellValueFactory(new PropertyValueFactory<Aliment,Integer>("id_aliment"));
        LIBELLE.setCellValueFactory(new PropertyValueFactory<Aliment,String>("libelle"));
        RECETTE.setCellValueFactory(new PropertyValueFactory<Aliment,String>("recette"));
        CALORIES.setCellValueFactory(new PropertyValueFactory<Aliment,Float>("calorie"));
        GRAS.setCellValueFactory(new PropertyValueFactory<Aliment,Float>("gras"));
        CARBS.setCellValueFactory(new PropertyValueFactory<Aliment,Float>("carbs"));
        System.out.println("After table cell init");
        
        AlimentCRUD pcd = new AlimentCRUD();
        ObservableList<Aliment> data = pcd.lister();
         int count = data.size();
         
         System.err.println(count);
        listA=data;
          TABLEALIMENT.setItems(data);
          System.err.println("after data");
    }    

    @FXML
    private void AjouterAliment(ActionEvent event) {
        Aliment selection = TABLEALIMENT.getSelectionModel().getSelectedItem();
        if(selection !=null)
        {
        Selectedtable.getItems().add(new Aliment(selection.getId_aliment(), selection.getLibelle(), selection.getRecette(), selection.getCalorie(), selection.getGras(), selection.getCarbs()));
         }
        TABLEALIMENT.getItems().remove(selection);
    }

    @FXML
    private void AjouterRegime(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("oui biensur ");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous ajouter ce regime");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
         String desc  = DESCRIPTION.getText();
    String dure =DURATION.getText();
    int dur = Integer.parseInt(dure);
    
    
    Regime reg = new Regime(desc,dur);
    RegimeCRUD pcd = new RegimeCRUD();
    ObservableList<Aliment> add = Selectedtable.getItems();
    pcd.addregime(reg, add, 1);
    
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerRegime.fxml"));
            Parent root =loader.load();
            ListerRegimeController pdc = loader.getController();
            Stage l = (Stage) btnAjouter.getScene().getWindow();
            mailaddregime.sendMailRegime("bouchedakh.firas@gmail.com",reg,add);
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acceuil Alimentation");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(ListerRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
           Logger.getLogger(AjouterRegimeController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }}

    @FXML
    private void SupprimerAliment(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("oui biensur ");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous supprimer ce regime");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
         Aliment selection = Selectedtable.getSelectionModel().getSelectedItem();
        if(selection !=null)
        {
        TABLEALIMENT.getItems().add(new Aliment(selection.getId_aliment(), selection.getLibelle(), selection.getRecette(), selection.getCalorie(), selection.getGras(), selection.getCarbs()));
         }
        Selectedtable.getItems().remove(selection);}
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
    private void rechercherAliment(ActionEvent event) {
        System.out.println("thsi is search key word "+tf_search.getText());
        if(tf_search.getText()==null){
            tf_alert.setText("saisir l'aliment a rechercher d'abord");
            tf_alert.setStyle("-fx-text-fil:red");
            
        
        }
        else{
            FoodDataCollector fc = new FoodDataCollector();
        
        ObservableList<Aliment> recipes = fc.collectRecipeData(tf_search.getText(), 2);
        if(recipes.isEmpty()){
        tf_alert.setText("aucune resultat trouvez");
            tf_alert.setStyle("-fx-text-fil:red;-fx-background-color: white;");}
            else{
                    TABLEALIMENT.setItems(recipes);
                    }
        }
    }

    @FXML
    private void clearRecherche(ActionEvent event) {
        TABLEALIMENT.setItems(listA);
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
