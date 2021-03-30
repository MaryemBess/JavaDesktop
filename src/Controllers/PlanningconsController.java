/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static java.time.temporal.TemporalAdjusters.next;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Entity.Planning;
//import Entity.Planningcreer;
//import Entity.Tache;
import services.PlanningCrud;
//import services.PlanningcreerCons;
import CONNECTION.Myconnection;
import Entity.Planningcreer;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.sql.ResultSet;
import java.util.Optional;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import services.PlanningcreerCons;

/**
 * FXML Controller class
 *
 * @author htc
 */
public class PlanningconsController implements Initializable {

    @FXML
    private TableView<Planning> tvplanning;
    @FXML
    private TableColumn<Planning, Integer> tvnum_p;
    @FXML
    private TableColumn<Planning, String> tvnom_p;
    @FXML
    private TableColumn<Planning, Date> tv_datea;
    @FXML
    private TableColumn<Planning, Integer> tv_nbr;
    @FXML
    private Button btndetails;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btna;
    @FXML
    private TableColumn <?,Integer> tvid_p;
    @FXML
    private TableColumn<?, String> tv_desc;
    @FXML
    private TableColumn<?, Integer> tvlike;
    @FXML
    private TableColumn<?, Integer> tvdislike;
    @FXML
    private TableColumn<?, Date> tv_crea;
    @FXML
    private TableView<Planningcreer> tvplanningcreer;
    @FXML
    private Button btn_addp;
    @FXML
    private TextField tv_addn;
    @FXML
    private Button btn_mod;
    @FXML
    private TextField rech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         btn_addp.setDisable(true);
       
    tvnum_p.setCellValueFactory(new PropertyValueFactory<>("id_p"));
 
            tv_datea.setCellValueFactory(new PropertyValueFactory<>("date_ajout")); 
    tvnom_p.setCellValueFactory(new PropertyValueFactory<>("nom_planning")); 
    tv_nbr.setCellValueFactory(new PropertyValueFactory<>("nb_tache")); 


      PlanningCrud pcd = new PlanningCrud() ;
       ObservableList<Planning> list1 = pcd.lister(1);
 tvplanning.setItems(list1);
        
   tvid_p.setCellValueFactory(new PropertyValueFactory<>("id"));
   tv_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
   tvlike.setCellValueFactory(new PropertyValueFactory<>("liker")); 
   tvdislike.setCellValueFactory(new PropertyValueFactory<>("disliker")); 
   tv_crea.setCellValueFactory(new PropertyValueFactory<>("nom_p")); 
 
        PlanningcreerCons pcd1 = new PlanningcreerCons();
         ObservableList<Planningcreer> list2 = pcd1.lister();
        tvplanningcreer.setItems(list2);
        
}@FXML
    public void redirectMod(ActionEvent event) throws AWTException
    { 
         //1- SAVE PERSON IN DATABASE
       
        

        try {

              FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/gui/Modifierplanning.fxml"));
               Parent root = loader.load();
        ModifierplanningController hhh = loader.getController();
        Planning r =tvplanning.getSelectionModel().getSelectedItem();
        int rid=r.getId_p();
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
    @FXML
   
    
    public void supprimerp(ActionEvent event)
    {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
                alert.setHeaderText(null);
                alert.setContentText("Vous voulez vraiment SUPPRIMER ce planning de ton programme ?!");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                

                    
        try {
            Planning p = tvplanning.getSelectionModel().getSelectedItem();
            //rech du l id
            /*String rech ="select id from planning_user where nom_planning='"+p.getNom_planning()+"' AND nb_tache ='"+p.getNb_taches()+"'";
            PreparedStatement pst1 = new Myconnection().cnx.prepareStatement(rech);
         
            ResultSet rs=pst1.executeQuery();
            rs.next();
            int x = rs.getInt(1);*/
            String query  = "DELETE FROM planning_user where id_p ='"+p.getId_p()+"'";
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(query);
            pst.execute();
            
            System.out.println("gggggggggg");
                   } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
        }
        PlanningCrud pcd = new PlanningCrud() ;
             
       ObservableList<Planning> list1 = pcd.lister(1);
       
        tvplanning.setItems(list1);


    }
    
   
    @FXML
      public void Add_monplanning(ActionEvent event) throws AWTException
    { int x=0;
    if ( !(tv_addn.getText().isEmpty()) ){
     Planningcreer p = tvplanningcreer.getSelectionModel().getSelectedItem();
     x=p.getId();
//     int test = tvplanningcreer.getSelectionModel().getSelectedIndex();
//     if (x != -1){
//         btn_addp.setDisable(false);
//     }
        String rNomplanning=tv_addn.getText();
    
       PlanningCrud pcd = new PlanningCrud() ;
      
          Planning p1 = new Planning(x,1,rNomplanning);
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
                alert.setHeaderText(null);
                alert.setContentText("Vous voulez vraiment AJOUTER ce planning a ton programme ?!");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    
pcd.addPlanning1(x,p1);
  if (SystemTray.isSupported()) {
                    PlanningCrud td = new PlanningCrud();
                    td.displayTray("votre planning a a eté creer");
                } else {
                    System.err.println("System tray not supported!");
                }
                    }
                
       

       
             
       ObservableList<Planning> list1 = pcd.lister(1);
       
        tvplanning.setItems(list1);
    tv_addn.setText("");
    }
    else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmation ");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez affecter un nom a votre planning");
                Optional<ButtonType> action = alert.showAndWait();
         
    }}
    @FXML
    public void redirect(ActionEvent event)
    {
        System.out.println("aaasssssdff");    
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ajouter_np.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            
            System.out.println(ex.getMessage());        }
            
            


    }

      @FXML
    private void getselected_t3(MouseEvent event) {
        int test = 0;
        test = tvplanning.getSelectionModel().getSelectedIndex();

        if (test != -1) {

            btn_mod.setDisable(false);
             btndetails.setDisable(false);
             btnDelete.setDisable(false);
        }
    }
    
    @FXML
    public int RedirectDetails (ActionEvent event){
        Planning t = tvplanning.getSelectionModel().getSelectedItem();
        int x = t.getId_p();
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
    private void search(ActionEvent event) {
       PlanningcreerCons cs = new PlanningcreerCons();
       ObservableList<Planningcreer> oc = cs.lister();
      
        tvplanningcreer.setItems(oc);
        FilteredList<Planningcreer> filteredData = new FilteredList<>(oc, b -> true);
        rech.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(a -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (a.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter descrition
                
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Planningcreer> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvplanningcreer.comparatorProperty());
        tvplanningcreer.setItems(sortedData);
    }
   
    
  /*  public void ajoutPlan(ActionEvent event)
    {
        try {
            Planningcreer p = tvplanningcreer.getSelectionModel().getSelectedItem();
            String query  = "INSERT INTO planning_user (id_p,id_u,date_ajout,?,nb_tache)"+
                    "VALUES ("+p.getId()+",1"+p.getDate_cr()++")";
            PreparedStatement pst = new Myconnection().cn.prepareStatement(query);
            pst.execute();
            
            System.out.println(" supppp");
                   } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
        
        PlanningCrud pcd = new PlanningCrud() ;
             
       ObservableList<Planning> list1 = pcd.lister();
       
        tvplanning.setItems(list1);


    }*/

    @FXML
    private void getselected(MouseEvent event) {
    
     int  test = 0;
            test=tvplanningcreer.getSelectionModel().getSelectedIndex();
      
           if (test != -1) {
          
           btn_addp.setDisable(false);
           }
           }

    
            
             
   
 
    }
    
    
   