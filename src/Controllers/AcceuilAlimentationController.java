/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AcceuilAlimentationController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    public int id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
       
        
    }    
    
     public void loadUI(String ui){
        Parent root=null;
        try{
            root=FXMLLoader.load(getClass().getResource(ui));
            
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
         borderpane.setCenter(root);
    }

    @FXML
    private void ListerAliment(ActionEvent event) {
    }

    @FXML
    private void ListerRegime(ActionEvent event) {
    }

    @FXML
    private void Statistique(ActionEvent event) {
    }
    
    public void userid(int idd){
    id=idd;
        System.out.println("id user at Acceuil list");
    }
}
