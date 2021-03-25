/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.DataSource;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maryem
 */
public class PageClientController implements Initializable {

     @FXML
    private BorderPane contents;
     boolean flag = true;
    @FXML
    private VBox content_area;
    @FXML
    private JFXComboBox<String> combo;
        private ObservableList<String> ob = FXCollections.observableArrayList("en attente", "traitées");
    @FXML
    private Button logoutbutton;
    Stage stage;
    @FXML
    private AnchorPane scenePane;
   

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                combo.setItems(ob);

    }    
    
    
    public BorderPane getContents(){
        return contents ;
    }
    
     @FXML
    private void ProduitClient (MouseEvent event) throws IOException {
        DynamicView.Change_content(contents, "ProduitClientInterface");
    }
    
    private void PanierClient (MouseEvent event) throws IOException {
        DynamicView.Change_content(contents, "PanierClientInterface");
    }

    @FXML
    private void combo(ActionEvent event) throws IOException {
         ObservableList<String> ob = FXCollections.observableArrayList();

        if (combo.getValue().toString().equals("en attente")) {
                    DynamicView.Change_content(contents, "ReclamFront");

            
            }
         
            else if (combo.getValue().toString().equals("traitées")) {
                    DynamicView.Change_content(contents, "ReclamFront2");
           

    }
    
  
}


    @FXML
    private void logout(ActionEvent event) {
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

