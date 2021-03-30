/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Coaach;
import Entity.review;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import services.reviewCRUD;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ReviewFXMLController implements Initializable {

    @FXML
    private TextArea tfcomment;
    @FXML
    private RadioButton radSatis;
    @FXML
    private ToggleGroup etat;
    @FXML
    private RadioButton radNor;
    @FXML
    private RadioButton radMed;
    @FXML
    private Rating rat;
    @FXML
    private Button addRev;
    private Coaach c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addRev(ActionEvent event) {
         String etat;
        if (radSatis.isSelected()) {
            etat = radSatis.getText();
        } else if (radNor.isSelected()) {
            etat = radNor.getText();
        } else {
            etat = radMed.getText();
        }
        double rating = rat.getRating();
        String comment = tfcomment.getText();
        System.out.println(etat);

        review r = new review(c.getId(), rating, etat, comment);
        reviewCRUD R = new reviewCRUD();
        R.addReview(r);
        R.updateCoachRating(c.getId());
        
//        SendMessage.sms();


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("review ajouté");
        /*alert.setContentText("You didn't select a file!");*/
        alert.showAndWait();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public Coaach getC() {
        return c;
    }

    public void setC(Coaach c) {
        this.c = c;
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
        
      
        
    }
    

