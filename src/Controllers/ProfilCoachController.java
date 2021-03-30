/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Coaach;
import Entity.review;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;
import services.CoaachCrud;
import services.reviewCRUD;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ProfilCoachController implements Initializable {

    @FXML
    private Label Lnom;
    @FXML
    private Label Lprenom;
    @FXML
    private Label Lemail;
    @FXML
    private Label Lphone;
    @FXML
    private Label LnbRating;
    @FXML
    private Rating Rating;
    @FXML
    private ImageView img;

    private Coaach c;
    private Image coachimg;
    @FXML
    private TableView<review> tableR;
    @FXML
    private TableColumn<review, String> coletat;
    @FXML
    private TableColumn<review, Double> colrating;
    @FXML
    private TableColumn<review, String> colcomment;
    ObservableList<review> listR;
    @FXML
    private JFXButton btnreview;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setDatttta(Coaach c) {
        this.c = c;
        System.out.println(c.getNom());
        Lnom.setText(c.getNom());
        Lprenom.setText(c.getPrenom());
        Lemail.setText(c.getEmail());
        Lphone.setText(String.valueOf(c.getTel()));
        Rating.setRating(c.getRating());
        LnbRating.setText("("+String.valueOf(reviewCRUD.getNbReviews(c.getId()))+")");
        updateTable();

        try {
            coachimg = new Image(c.getImage());
            img.setImage(coachimg);
        } catch (Exception e) {
        }
    }

    public Coaach getC() {
        return c;
    }

    public void setC(Coaach c) {
        this.c = c;
    }

    public void updateTable() {
        listR = reviewCRUD.showReviews(c.getId());
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colrating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        colcomment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        tableR.setItems(listR);
    }

    @FXML
    private void GotoReview(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/reviewFXML.fxml"));
            Parent root = loader.load();
            ReviewFXMLController pdc = loader.getController();
            pdc.setC(c);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Review");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            c = CoaachCrud.getCoach(c.getId());
            setDatttta(c);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
