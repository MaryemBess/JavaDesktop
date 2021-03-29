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
public class PlanningadminController implements Initializable {

    @FXML
    private TableView<Planningcreer> tvplanningcreer;
    @FXML
    private TableColumn<?, ?> tvid_p;
    @FXML
    private TableColumn<?, ?> tv_desc;
    @FXML
    private TableColumn<?, ?> tvlike;
    @FXML
    private TableColumn<?, ?> tvdislike;
    @FXML
    private TableColumn<?, ?> tv_crea;
    @FXML
    private Button btn_supp;
    @FXML
    private Button btn_details;

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
 
        PlanningcreerCons pcd1 = new PlanningcreerCons();
         ObservableList<Planningcreer> list2 = pcd1.lister();
        tvplanningcreer.setItems(list2);
    }    

    @FXML
    private void getselected(MouseEvent event) {
                int test = 0;
        test = tvplanningcreer.getSelectionModel().getSelectedIndex();

        if (test != -1) {

        
             btn_details.setDisable(false);
             btn_supp.setDisable(false);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
                alert.setHeaderText(null);
                alert.setContentText("Vous voulez vraiment SUPPRIMER ce planning de ton programme ?!");
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
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(query);
            pst.execute();
            
            System.out.println("gggggggggg");
                   } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
        }
        PlanningcreerCons pcd = new PlanningcreerCons() ;
             
       ObservableList<Planningcreer> list1;
        list1 = pcd.lister();
       
        tvplanningcreer.setItems(list1);
        
    }

    @FXML
    private int details(ActionEvent event) {
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
    
}
