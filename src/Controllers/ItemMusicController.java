/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.MyMusicListener;
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
import Entity.Music;
import java.io.File;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author SAFA
 */
public class ItemMusicController implements Initializable {

    @FXML
    private Label conttitre;
    private String Titre, image;
    private MyMusicListener MyMusicListener;
    private Music music;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void click(MouseEvent event) {
        MyMusicListener.onClickListener(music);
    }

    public void setData(Music mu, MyMusicListener MyMusicListener) {
        this.music = mu;
        this.MyMusicListener = MyMusicListener;
        conttitre.setText(mu.getTitre() + "  -  " + mu.getArtiste());
//        try {
//            Image image = new Image(mu.getImage());
//            img.setImage(image);
//        } catch (NullPointerException e) {
//            System.out.println("Image Link Invalid");
//        }

    }

    public AnchorPane getAnchor() {
        return anchor;
    }

    public void setAnchor(AnchorPane anchor) {
        this.anchor = anchor;
    }
}
