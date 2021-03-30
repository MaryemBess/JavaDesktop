/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.Myconnection;
import Entity.Planning;
import Entity.Planningcreer;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.PlanningCrud;
import services.PlanningcreerCons;

/**
 * FXML Controller class
 *
 * @author htc
 */
public class PlanningcoachController implements Initializable {

    private Button btndetails;
    private Button btnDelete;
    @FXML
    private Button btna;
    @FXML
    private TableView<Planningcreer> tvplanningcreer;
    @FXML
    private TableColumn<?,Integer> tvid_p;
    @FXML
    private TableColumn<?, String> tv_desc;
    @FXML
    private TableColumn<?, Integer> tvlike;
    @FXML
    private TableColumn<?, Integer> tvdislike;
    @FXML
    private TableColumn<?, Date> tv_crea;
    private Button btn_mod;
    private Button hhh;
    @FXML
    private Button sup;
    @FXML
    private Button mod;
    @FXML
    private Button det;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       tvid_p.setCellValueFactory(new PropertyValueFactory<>("id"));
   tv_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
   tvlike.setCellValueFactory(new PropertyValueFactory<>("liker")); 
   tvdislike.setCellValueFactory(new PropertyValueFactory<>("disliker")); 
   tv_crea.setCellValueFactory(new PropertyValueFactory<>("nom_p")); 
 int x = 1;
        PlanningcreerCons pcd1 = new PlanningcreerCons();
         ObservableList<Planningcreer> list2 = pcd1.listercoach(x);
        tvplanningcreer.setItems(list2);
    }    

    @FXML
    private int RedirectDetails(ActionEvent event) {
        Planningcreer t = tvplanningcreer.getSelectionModel().getSelectedItem();
        int x = t.getId();
        String id=String.valueOf(x);
         try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/gui/Detailsplanning.fxml"));
           Parent root = loader.load();
           DetailsplanningController dpr = loader.getController();
            dpr.setId(id);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
         return x;
    }

    @FXML
    private void supprimerp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
                alert.setHeaderText(null);
                alert.setContentText("Vous voulez vraiment SUPPRIMER ce planning de votre liste ?!");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
        try {
            Planningcreer p = tvplanningcreer.getSelectionModel().getSelectedItem();
            //rech du l id
            /*String rech ="select id from planning_user where nom_planning='"+p.getNom_planning()+"' AND nb_tache ='"+p.getNb_taches()+"'";
            PreparedStatement pst1 = new Myconnection().cnx.prepareStatement(rech);
         
            ResultSet rs=pst1.executeQuery();
            rs.next();
            int x = rs.getInt(1);*/
            String query  = "DELETE FROM planning where id ='"+p.getId()+"'";
            System.out.println(p.getId());
            System.out.println("aaaaaaaaaaaaaaaaa");
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(query);
            pst.execute();
            
            System.out.println("gggggggggg");
                   } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
        }
        PlanningcreerCons pcd = new PlanningcreerCons();
             int x = 1 ;
       ObservableList<Planningcreer> list1 = pcd.listercoach(x);
       
        tvplanningcreer.setItems(list1);


    }

    @FXML
    private void redirect(ActionEvent event) {
        System.out.println("aa");    
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/gui/creerpc.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            
            System.out.println(ex.getMessage());        }
    }

    @FXML
    private void getselected(MouseEvent event) {
            int test = 0;
        test = tvplanningcreer.getSelectionModel().getSelectedIndex();

        if (test != -1) {

            sup.setDisable(false);
             mod.setDisable(false);
              det.setDisable(false);
        
        }

    }

    @FXML
    private void redirectMod(ActionEvent event) {
        try {

              FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/gui/Modifierplanning.fxml"));
               Parent root = loader.load();
        ModifierplanningController hhh = loader.getController();
        Planningcreer r =tvplanningcreer.getSelectionModel().getSelectedItem();
        int rid=r.getId();
        String rrstring=String.valueOf(rid);
            hhh.setId(rrstring);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("erreur set du id");
            System.out.println(ex.getMessage());        }

        
    }

    
    
}
