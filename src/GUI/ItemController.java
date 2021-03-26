/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import entities.Music;
import entities.MyListener;
import java.io.File;

/**
 * FXML Controller class
 *
 * @author SAFA
 */
public class ItemController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label conttitre;
    private String Titre, image;
    private MyListener myListener;
    private File music;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private void click(MouseEvent event) {
        myListener.onClickListener(music);
    }
    
    public void setData(File mu, MyListener myListener) {
        this.music = mu;
        this.myListener = myListener;
        conttitre.setText(mu.getName());
//        try {
//            Image image = new Image(mu.getImage());
//            img.setImage(image);
//        } catch (NullPointerException e) {
//            System.out.println("Image Link Invalid");
//        }
    }
    
}


