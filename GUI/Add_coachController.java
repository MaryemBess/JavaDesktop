/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.net.*;
import java.io.*;
import entities.coach;
import java.awt.TextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.CoachCRUD;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Add_coachController implements Initializable {

    private TextField tfNom;
    @FXML
    private javafx.scene.control.TextField tfPrenom;
    @FXML
    private javafx.scene.control.TextField tfNbl;
    @FXML
    private javafx.scene.control.TextField tfNbd;
    @FXML
    private Button Ajouter;
    @FXML
    private TableView<coach> t;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    @FXML
    private javafx.scene.control.TextField tfNon;
    @FXML
    private TableColumn<coach, Integer> id;
    @FXML
    private TableColumn<coach, String> nom;
    @FXML
    private TableColumn<coach, String> prenom;
    @FXML
    private TableColumn<coach, Integer> nbl;
    @FXML
    private TableColumn<coach, Integer> nbd;

    ObservableList<coach> listM;
    @FXML
    private javafx.scene.control.TextField tfrech;
    @FXML
    private Button btnreview;
    @FXML
    private Button choose;
    @FXML
    private TableColumn<coach, String> colimage;
    @FXML
    private ImageView img;
    private String imageString;
    @FXML
    private Button btnShowRevs;
    @FXML
    private TableColumn<coach, Double> colrating;
    @FXML
    private Button stat;
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
    }

    public void updateTable() {
        listM = CoachCRUD.showcoach();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nbl.setCellValueFactory(new PropertyValueFactory<>("nb_likes"));
        nbd.setCellValueFactory(new PropertyValueFactory<>("nb_dislikes"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colrating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        t.setItems(listM);
    }

    @FXML
    private int hez_lghadi(MouseEvent event) {
        tfNon.setText(t.getSelectionModel().getSelectedItem().getNom());
        tfPrenom.setText(t.getSelectionModel().getSelectedItem().getPrenom());
        tfNbl.setText(String.valueOf(t.getSelectionModel().getSelectedItem().getNb_likes()));
        tfNbd.setText(String.valueOf(t.getSelectionModel().getSelectedItem().getNb_dislikes()));
        img.setImage(generateImg());
        imageString = t.getSelectionModel().getSelectedItem().getImage();
        return t.getSelectionModel().getSelectedItem().getId();
    }

    @FXML
    private void AjouterCoach(ActionEvent event) {

        String rNom = tfNon.getText();
        String rPrenom = tfPrenom.getText();
        String rNb_like = tfNbl.getText();
        String rNb_dislike = tfNbd.getText();
              
        coach c = new coach(13, rNom, rPrenom, Integer.parseInt(rNb_like), Integer.parseInt(rNb_dislike),imageString,0);
        CoachCRUD C = new CoachCRUD();
            C.addCoach(c);
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Coach ajouté");
            /*alert.setContentText("You didn't select a file!");*/
            alert.showAndWait();
        updateTable();
        init();
    }

    @FXML
    private void ModifierCoach(ActionEvent event) {
        String rNom = tfNon.getText();
        String rPrenom = tfPrenom.getText();
        String rNb_like = tfNbl.getText();
        String rNb_dislike = tfNbd.getText();

        coach c = new coach(t.getSelectionModel().getSelectedItem().getId(), rNom, rPrenom, Integer.parseInt(rNb_like), Integer.parseInt(rNb_dislike),imageString,0);
        CoachCRUD C = new CoachCRUD();
        C.modCoach(c);
         
            
        updateTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Coach modifié");
            /*alert.setContentText("You didn't select a file!");*/
            alert.showAndWait();
        init();
    }

    public void init() {
        tfNon.clear();
        tfPrenom.clear();
        tfNbl.clear();
        tfNbd.clear();
        img.setImage(null);

    }

    @FXML
    private void SupprimerCoach(ActionEvent event) {
        CoachCRUD C = new CoachCRUD();
        C.suppCoach(t.getSelectionModel().getSelectedItem().getId());
        updateTable();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("cOACH SUPPRIME");
            /*alert.setContentText("You didn't select a file!");*/
            alert.showAndWait();
        init();
       
    }

    @FXML
    private void search_Coach(KeyEvent event) {
            if (tfrech.getText().equals("")) {
                listM = CoachCRUD.showcoach();
            } else {
                listM = CoachCRUD.rechCoach(tfrech.getText());
            }
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            nbl.setCellValueFactory(new PropertyValueFactory<>("nb_likes"));
            nbd.setCellValueFactory(new PropertyValueFactory<>("nb_dislikes"));
            t.setItems(listM);
    }

    @FXML
    private void GotoReview(ActionEvent event) {
        int selectedId = t.getSelectionModel().getSelectedItem().getId();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("reviewFXML.fxml"));
            Parent root = loader.load();
            ReviewFXMLController pdc = loader.getController();
            pdc.setIdCoachRev(selectedId);
            Stage stage = new Stage();
            
            stage.setTitle("Review");
            stage.setScene(new  Scene(root));
            stage.showAndWait();
            updateTable();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Image generateImg(){
        Image img = new Image(t.getSelectionModel().getSelectedItem().getImage());

        if (t.getSelectionModel().getSelectedItem().getImage().equals(""))
            img.cancel();
        return img;
    }

    @FXML
    private void AjouterPhoto(ActionEvent event) throws Exception
    {
        
         FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            String imagepath = file.toURI().toURL().toString();
            System.out.println("file:" + imagepath);
            Image image = new Image(imagepath);
            System.out.println("height:" + image.getHeight() + "\nWidth:" + image.getWidth());
            img.setImage(image);
            imageString=imagepath;
            
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select a File");
            /*alert.setContentText("You didn't select a file!");*/
            alert.showAndWait();
        }
    }

    @FXML
    private void showReviews(ActionEvent event) {
        int selectedId = t.getSelectionModel().getSelectedItem().getId();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowReviews.fxml"));
            Parent root = loader.load();
            ShowReviewsController pdc = loader.getController();
            pdc.setIdcoachRevs(selectedId);
            Stage stage = new Stage();
            
            stage.setTitle("Reviews");
            stage.setScene(new  Scene(root));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void GotoStat(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
            Parent root = loader.load();
            FXMLController pdc = loader.getController();
            Stage stage = new Stage();
            
            stage.setTitle("Top 3 reviews");
            stage.setScene(new  Scene(root));
            stage.showAndWait();
            updateTable();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
       


}

        
    


