/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Coaach;
import Entity.MyListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;
import services.reviewCRUD;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ItemCoachController implements Initializable {

    @FXML
    private ImageView imgCoach;
    @FXML
    private Label NomPrenom;
    
    private MyListener MyListener;
    private Coaach c;
    @FXML
    private Rating Rating;
    @FXML
    private Label nbRev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent event) {
        MyListener.onClickListener(c);
    }
    
    public void setData(Coaach c, MyListener MyListener){
        this.c=c;
        this.MyListener=MyListener;
        int nb = 99 ;
        NomPrenom.setText(c.getNom()+" "+c.getPrenom());
        Rating.setRating(c.getRating());
        System.out.println(nb);
        nbRev.setText("("+String.valueOf(reviewCRUD.getNbReviews(c.getId()))+")");
        try {
            Image image = new Image(c.getImage());
            imgCoach.setImage(image);
        } catch (Exception e) {
            System.out.println("tehshé");
        }
        
    }
    
}


