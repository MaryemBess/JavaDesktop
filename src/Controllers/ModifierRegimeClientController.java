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

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierRegimeClientController implements Initializable {

    @FXML
    private VBox View_MeditationMaryem;
    @FXML
    private Button btnhome;
    @FXML
    private Button btnListerAlimentClient;
    @FXML
    private Button btnListerRegimeClient;
    @FXML
    private TextArea DESCRIPTION;
    @FXML
    private TableView<Aliment> TABLEALIMENT;
    @FXML
    private TableColumn<Aliment, Integer> ID;
    @FXML
    private TableColumn<Aliment, String> LIBELLE;
    @FXML
    private TableColumn<Aliment,String> RECETTE;
    @FXML
    private TableColumn<Aliment,Float> CALORIES;
    @FXML
    private TableColumn<Aliment,Float> GRAS;
    @FXML
    private TableColumn<Aliment,Float> CARBS;
    @FXML
    private TextField DURATION;
    @FXML
    private Button SuppAliment;
    @FXML
    private Button ajouteralim;
    
    @FXML
    private TableView<Aliment> TABLEALIMENT1;
    @FXML
    private TableColumn<Aliment, Integer> ID1;
    @FXML
    private TableColumn<Aliment, String> LIBELLE1;
    @FXML
    private TableColumn<Aliment, String> RECETTE1;
    @FXML
    private TableColumn<Aliment, Float> CALORIES1;
    @FXML
    private TableColumn<Aliment, Float> GRAS1;
    @FXML
    private TableColumn<Aliment, Float> CARBS1;
    @FXML
    private TextField tf_search;
    @FXML
    private Button btn_recherche;
    @FXML
    private Button btn_clear;
    @FXML
    private Label tf_alert;
    public int idd;
    ObservableList<Aliment> deleteAliment = FXCollections.observableArrayList();
    ObservableList<Aliment> addedAliment = FXCollections.observableArrayList();
        ObservableList<Aliment> listA = FXCollections.observableArrayList();
            ObservableList<Aliment> listb = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ID1.setCellValueFactory(new PropertyValueFactory<>("id_aliment"));
        LIBELLE1.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        RECETTE1.setCellValueFactory(new PropertyValueFactory<>("recette"));
        CALORIES1.setCellValueFactory(new PropertyValueFactory<>("calorie"));
        GRAS1.setCellValueFactory(new PropertyValueFactory<>("gras"));
        CARBS1.setCellValueFactory(new PropertyValueFactory<>("carbs"));
         AlimentCRUD pcd = new AlimentCRUD();
        ObservableList<Aliment> dataAll=pcd.lister();
        for(Aliment add:dataAll)
        {System.out.println("aliment"+add.getLibelle());
        }
          TABLEALIMENT1.setItems(dataAll);
          
    }    

    @FXML
    private void redirListerAlimentClient(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("oui biensur ");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous quitter cette page");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerAlimentClient.fxml"));
            Parent root =loader.load();
            ListerAlimentClientController pdc = loader.getController();
            Stage l = (Stage) btnListerAlimentClient.getScene().getWindow();
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acceuil Alimentation");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(ListerAlimentClientController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }

    @FXML
    private void redirListerRegimeClient(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("oui biensur ");
                alert.setHeaderText(null);
                alert.setContentText("voulez vous quitter cette page");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerRegimeClient.fxml"));
            Parent root =loader.load();
            ListerRegimeClientController pdc = loader.getController();
            Stage l = (Stage) btnListerAlimentClient.getScene().getWindow();
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acceuil Alimentation");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(ListerRegimeClientController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }

    @FXML
    private void ModifierRegime(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
                alert.setHeaderText(null);
                alert.setContentText("Vous voulez vraiment AJOUTER ce planning a ton programme ?!");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    
         String desc = DESCRIPTION.getText();
        String dura = DURATION.getText();
        int dur = Integer.parseInt(dura); 
        
        Regime rgm = new Regime(idd, desc, dur);
        
        RegimeCRUD rgc = new RegimeCRUD();
        listb = rgc.ApiAliment(rgm, addedAliment);
    rgc.ModifierReg(rgm);
    rgc.SupprimerAlimentRegime(deleteAliment,rgm);
    rgc.ajouterAlimentRegime(listb, rgm);
       
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guiAliment/ListerRegimeClient.fxml"));
            Parent root =loader.load();
            ListerRegimeClientController pdc = loader.getController();
            Stage l = (Stage) TABLEALIMENT1.getScene().getWindow();
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acceuil Alimentation");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(ListerRegimeClientController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }

    @FXML
    private void supprimerAliment(ActionEvent event) {
        Aliment selection = TABLEALIMENT.getSelectionModel().getSelectedItem();
        if(selection !=null)
        {
            
        deleteAliment.add(selection);
            System.out.println(selection.getId_aliment()+" "+ selection.getLibelle());
         } 
        System.out.println("Selected Regime"+selection.getLibelle());
        TABLEALIMENT.getItems().remove(selection);
        RegimeCRUD pcd = new RegimeCRUD();
    }

    @FXML
    private void AjouterAliment(ActionEvent event) {
         Aliment selection = TABLEALIMENT1.getSelectionModel().getSelectedItem();
        if(selection !=null)
        {
            addedAliment.add(selection);
        TABLEALIMENT.getItems().add(new Aliment(selection.getId_aliment(), selection.getLibelle(), selection.getRecette(), selection.getCalorie(), selection.getGras(), selection.getCarbs()));
         }
        TABLEALIMENT1.getItems().remove(selection);
    }
     public void Showinformation(Regime rg) {
         System.out.println("id regime modifer a modifier"+idd);
       idd=rg.getId_regime();
       int dure = rg.getDuration();
       String dur = String.valueOf(dure);
        DESCRIPTION.setText(rg.getDescription());
        DURATION.setText(dur);
        
        ID.setCellValueFactory(new PropertyValueFactory<>("id_aliment"));
         LIBELLE.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        RECETTE.setCellValueFactory(new PropertyValueFactory<>("recette"));
        CALORIES.setCellValueFactory(new PropertyValueFactory<>("calorie"));
        GRAS.setCellValueFactory(new PropertyValueFactory<>("gras"));
        CARBS.setCellValueFactory(new PropertyValueFactory<>("carbs"));
        System.out.println("1");
        RegimeCRUD pcd = new RegimeCRUD();
            
        ObservableList<Aliment> data=pcd.lister(rg.getId_regime());
          TABLEALIMENT.setItems(data);
        System.out.println("2");
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
                    TABLEALIMENT1.setItems(recipes);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Acceuil.fxml"));
            Parent root =loader.load();
            AcceuilController pdc = loader.getController();
            Stage l = (Stage) btnListerAlimentClient.getScene().getWindow();
            pdc.loadUI("/gui/HomeAlimentationClient.fxml");
            
            l.close();
            
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Espace Admin");
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}

    

    

    
}
