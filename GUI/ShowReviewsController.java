/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.coach;
import entities.review;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.controlsfx.control.Rating;
import services.reviewCRUD;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ShowReviewsController implements Initializable {

    private int idcoachRevs;
    ObservableList<review> listR;
    @FXML
    private TableView<review> tableR;
    @FXML
    private TableColumn<review, String> coletat;
    @FXML
    private TableColumn<review, Rating> colrating;
    @FXML
    private TableColumn<review, String> colcomment;
    @FXML
    private Button aff;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
    }

    public int getIdcoachRevs() {
        return idcoachRevs;
    }

    public void setIdcoachRevs(int idcoachRevs) {
        this.idcoachRevs = idcoachRevs;
    }

    public void updateTable() {
        listR = reviewCRUD.showReviews(idcoachRevs);
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colrating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        colcomment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        tableR.setItems(listR);
    }

    @FXML
    private void affiche(ActionEvent event) {
        updateTable();
    }
}
