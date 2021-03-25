/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.coach;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.CoachCRUD;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLController implements Initializable {

    @FXML
    private ImageView imgwinn2;
    @FXML
    private ImageView imgwinn1;
    @FXML
    private ImageView imgwinn3;
    @FXML
    private Label winner2;
    @FXML
    private Label winner1;
    @FXML
    private Label winner3;
    ObservableList<coach> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = CoachCRUD.showTopCoachs();
        list.forEach(e -> System.out.println(e));
        Image img = new Image(list.get(0).getImage());
        imgwinn1.setImage(img);
        winner1.setText(list.get(0).getNom()+" "+list.get(0).getPrenom());
        img = new Image(list.get(1).getImage());
        imgwinn2.setImage(img);
        winner2.setText(list.get(1).getNom()+" "+list.get(1).getPrenom());
        img = new Image(list.get(2).getImage());
        imgwinn3.setImage(img);
        winner3.setText(list.get(2).getNom()+" "+list.get(2).getPrenom());
    }

}
