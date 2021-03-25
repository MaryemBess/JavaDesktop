/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.citation;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayerBuilder;
import javafx.scene.media.MediaView;
import org.controlsfx.control.Rating;
import utils.myconnexion;

/**
 * FXML Controller class
 *
 * @author Kenza
 */
public class DetailController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label titre;
    @FXML
    private Label labauteur;
    @FXML
    private Label labgenre;
    @FXML
    private Label labcitation;
    @FXML
    private Button btnback;
    @FXML
    private Rating star;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField labid;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

   public void setStar(int x) {
//        this.titre.setText(x).toS;
    }

    public void setTitre(String titre) {
        this.titre.setText(titre);
    }

    public void setLabauteur(String labauteur) {
        this.labauteur.setText(labauteur);

    }

    public void setStar(double x) {
        this.star.setPartialRating(true);
        this.star.setRating(x);
//        
       }
    

    public void setLabgenre(String labgenre) {
        this.labgenre.setText(labgenre);

    }

    public void setlabcitation(int labcitation) {
        Connection cx = myconnexion.getInstance().getCnx();
        try {
            String req = " select * from citations where id=" + labcitation + "";
//            PreparedStatement pst = cx.prepareStatement(req);
            ResultSet rs = myconnexion.getInstance().getCnx().createStatement().executeQuery(req);
//            pst.setInt(1, labcitation);
            while (rs.next()) {
                this.labcitation.setText(rs.getString("auteur") + " : " + rs.getString("text"));
            }
        } catch (Exception ex) {
            System.out.println("ERREUR AFFICHAGE detail");
            System.out.println(ex.getMessage());
        }
//        this.labcitation.setText(""+labcitation);

    }

    public void setImage(String image) {

        File imageFile = new File(image);
        Image i = new Image(imageFile.toURI().toString());
        this.image.setImage(i);

    }

    @FXML
    private void backmain(ActionEvent event) {
        System.out.println(this.star.getRating());
         if (this.star.getRating()!= -1)
    {
        Connection cx = myconnexion.getInstance().getCnx();
        String valeurid = this.labid.getText();
            double nouvelvaleur=this.star.getRating();
        try {
            
            String req = "update e_books set evaluation=? where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
            pst.setInt(1, Integer.getInteger(""+nouvelvaleur));
            pst.setInt(2, Integer.parseInt(valeurid));
            pst.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERREUR UPDATE EVALUATION");
            System.out.println(ex.getMessage());
        }
        
    }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("e_book.fxml"));

        try {
            Parent root = loader.load();
            btnback.getScene().setRoot(root);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("newCascadeStyleSheet.css").toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("Interface E_BOOK");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println("ERREUR MAIN INTERFACE");
            System.out.println(e.getMessage());
        }
    }
}
