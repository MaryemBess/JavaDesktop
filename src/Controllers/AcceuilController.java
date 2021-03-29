/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author Kenza
 */
public class AcceuilController implements Initializable {
Timeline rotate= new Timeline();
    @FXML
    private ImageView logoview;
    @FXML
    private BorderPane View_MeditationMaryem;
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void rotateImage(){
    DoubleProperty r = logoview.rotateProperty();
    rotate.getKeyFrames().addAll(
                        new KeyFrame(new Duration(0),new KeyValue(r,0)),
                        new KeyFrame(new Duration(2300),new KeyValue(r,-360))
                                );
    rotate.play();
    }
    public void loadUI(String ui){
        Parent root=null;
        try{
            root=FXMLLoader.load(getClass().getResource(ui));
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
         View_MeditationMaryem.setCenter(root);
    }
    
    public void getPane(String ch){
        System.out.println(ch);
        loadUI(ch);
    }
    
     @FXML
    void Alimentation(ActionEvent event) {
        loadUI("/gui/HomeAlimentationClient.fxml");
    }

    @FXML
    void Citation(ActionEvent event) {

    }

    @FXML
    void Coach(ActionEvent event) {

    }

    @FXML
    void E_book(ActionEvent event) {

    }

    @FXML
    void Music(ActionEvent event) {

    }

    @FXML
    void Reclamation(ActionEvent event) {
    loadUI("/gui/PageClient.fxml");
    }

    void Statistique(ActionEvent event) {
   
    }

    void User(ActionEvent event) {

    }

    @FXML
    void Video(ActionEvent event) {

    }



    @FXML
    private void windowsMin(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void windowMax(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setFullScreen(true);
        
    }

    @FXML
    private void Windowsclose(javafx.scene.input.MouseEvent event) {
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

   
}
