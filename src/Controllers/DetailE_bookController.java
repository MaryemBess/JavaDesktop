/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.Myconnection;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Kenza
 */
public class DetailE_bookController implements Initializable {

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
    private Rating star;
    @FXML
    private ImageView QR;
    @FXML
    private Label labid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }

   public void setStar(int x) {
//        this.labid.setText(x);
    }
    public void setId(int x) {
        this.labid.setText(x+"");
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
        Connection cx = Myconnection.getInstance().getCnx();
        try {
            String req = " select * from citations where id=" + labcitation + "";
//            PreparedStatement pst = cx.prepareStatement(req);
            ResultSet rs = Myconnection.getInstance().getCnx().createStatement().executeQuery(req);
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
    public void setQR(int x) {
        File imageFile = new File("C:\\Users\\Kenza\\Desktop\\cours\\semestre2\\PIdEV\\Meliora-java\\meliora\\Document\\Ebook_n°"+x+".png");
        Image i = new Image(imageFile.toURI().toString());
        this.QR.setImage(i);

    }

    

    @FXML
    private void valider(MouseEvent event) {
        JOptionPane.showMessageDialog(null, "EVALUATION ADD");
        if (this.star.getRating()!= -1)
    {
        Connection cx = Myconnection.getInstance().getCnx();
        String valeurid = this.labid.getText();
            double nouvelvaleur=this.star.getRating();
        try {
            
            String req = "update e_books set evaluation=round((evaluation+"+nouvelvaleur+
                    ")/2 ,2) where id = ?";
            PreparedStatement pst = cx.prepareStatement(req);
//            pst.setInt(1, Integer.getInteger(""+(int)nouvelvaleur));
            pst.setInt(1, Integer.parseInt(valeurid));
            pst.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERREUR UPDATE EVALUATION");
            System.out.println(ex.getMessage());
        }
        
    }
    }
    
}
