/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Controllers.DynamicView;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MaryemBessrour
 */
public class ContentAreaController implements Initializable {

    @FXML
    private VBox sidebar;
    @FXML
    private Label idClients;
    @FXML
    private HBox idCoachs;
    @FXML
    private HBox idPlaylist;
    @FXML
    private HBox idPlanning;
    @FXML
    private HBox idbooks;
    @FXML
    private HBox idexercices;
    @FXML
    private HBox idalimentation;
    @FXML
    private JFXComboBox<String> combo;
    private ObservableList<String> ob = FXCollections.observableArrayList("en attente", "traitées");

    @FXML
    private VBox content_area;
    @FXML
    private BorderPane contents;
    @FXML
    private Label idPL;
    @FXML
    private Label idP;
    @FXML
    private Button logoutbutton;
    Stage stage,s;
    @FXML
    private AnchorPane scenePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                        combo.setItems(ob);
    }    

    @FXML
    private void Client(MouseEvent event) throws IOException{
         DynamicView.Change_content(contents,"UserInterface");
    }
    
    @FXML
    
    private void Coachs(MouseEvent event) {
    }

    @FXML
    private void Catégorie(MouseEvent event) {
    }

    @FXML
    private void Promo(MouseEvent event) {
    }

    @FXML
    private void AllOrdersManage(MouseEvent event) {
    }

           @FXML
    private void combo(ActionEvent event) throws IOException {
         ObservableList<String> ob = FXCollections.observableArrayList();

        if (combo.getValue().toString().equals("en attente")) {
                    DynamicView.Change_content(contents, "ReclamBack");

            
            }
         
            else if (combo.getValue().toString().equals("traitées")) {
                    DynamicView.Change_content(contents, "ReclamBack2"); 
    }
    
    
  
}
    
//
//    @FXML
//    private void logout(ActionEvent event) throws IOException {
//        
//        Alert alert = new Alert(AlertType.CONFIRMATION);
//        alert.setTitle("LOGOUT");
//        alert.setHeaderText("You're about to logout!");
//        alert.setContentText("Do you want to save before exiting");
//        if(alert.showAndWait().get() == ButtonType.OK){
//        
//        System.out.println("You successfully logged out ! ");
//        DynamicView.Change_content(contents,"Login");
//       
//        
//    }
//        
//    }
@FXML
  public void logout(ActionEvent t) throws SQLException {

        try {
           
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/GUI/Login.fxml")); //
            Parent root =Loader.load();
            LoginController r = Loader.getController();
            logoutbutton.getScene().setRoot(root);
        
           
        } catch (IOException ex) {
            Logger.getLogger(PageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
    

