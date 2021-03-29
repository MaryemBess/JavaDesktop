/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Entity.Stat;
import java.net.*;
import java.io.*;
import Entity.Coaach;
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
import services.CoaachCrud;
import CONNECTION.Myconnection;
import java.sql.Date;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Add_coachController implements Initializable {

    private TextField tfNom;
    @FXML
    private Button Ajouter;
    @FXML
    private TableView<Coaach> t;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    ObservableList<Coaach> listM;
    @FXML
    private javafx.scene.control.TextField tfrech;
    @FXML
    private Button btnreview;
    @FXML
    private Button choose;
   
    @FXML
    private ImageView img;
    private String imageString;
    @FXML
    private Button btnShowRevs;
    private TableColumn<Coaach, Double> colrating;
    @FXML
    private Button stat;
    @FXML
    private Button graphbtn;
    @FXML
    private TableColumn<Coaach, String> email;
    @FXML
    private TableColumn<Coaach, String> password;
    @FXML
    private TableColumn<Coaach, Integer> tel;
    @FXML
    private TableColumn<Coaach, String> adresse;
    @FXML
    private TableColumn<Coaach, String> image;
    @FXML
    private TableColumn<Coaach, Date> Dateb;
    @FXML
    private javafx.scene.control.TextField tfTel;
    @FXML
    private javafx.scene.control.TextField tfAdresse;
    @FXML
    private javafx.scene.control.TextField tfEmail;
    @FXML
    private javafx.scene.control.TextField tfPassword;
    @FXML
    private TableColumn<Coaach, Integer> id;
    @FXML
    private TableColumn<Coaach, String> nom;
    @FXML
    private TableColumn<Coaach, String> prenom;
    @FXML
    private javafx.scene.control.TextField tfNon;
    @FXML
    private javafx.scene.control.TextField tfPrenom;
    @FXML
    private TableColumn<Coaach, Double> rating;
    @FXML
    private Text rNom;
    @FXML
    private Text rPrenom;
    @FXML
    private Text rEmail;
    @FXML
    private Text rPassword;
    @FXML
    private Label rTel;
    @FXML
    private Label rAdresse;
    @FXML
    private Label rDate;
    @FXML
    private DatePicker tfDate;
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
    }

    public void updateTable() {
        listM = CoaachCrud.showcoachEnd();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        Dateb.setCellValueFactory(new PropertyValueFactory<>("date"));

        t.setItems(listM);
    }

    @FXML
    private int hez_lghadi(MouseEvent event) {
        tfNon.setText(t.getSelectionModel().getSelectedItem().getNom());
        tfPrenom.setText(t.getSelectionModel().getSelectedItem().getPrenom());
        tfEmail.setText(t.getSelectionModel().getSelectedItem().getEmail());
        tfPassword.setText(t.getSelectionModel().getSelectedItem().getPassword());
        tfTel.setText(String.valueOf(t.getSelectionModel().getSelectedItem().getTel()));
        tfAdresse.setText(t.getSelectionModel().getSelectedItem().getAdresse());
        img.setImage(generateImg());
        imageString = t.getSelectionModel().getSelectedItem().getImage();
        return t.getSelectionModel().getSelectedItem().getId();
    }

    

    @FXML
    private void ModifierCoach(ActionEvent event) {
        String rNom = tfNon.getText();
        String rPrenom = tfPrenom.getText();
        String rEmail = tfEmail.getText();
        String rPassword = tfPassword.getText();
        String rTel = tfTel.getText();
        String rAdresse = tfAdresse.getText();
        Date rDate= Date.valueOf(tfDate.getValue());

        Coaach c = new Coaach(t.getSelectionModel().getSelectedItem().getId(), rNom, rPrenom,rEmail, rPassword,Integer.parseInt(rTel),rAdresse,imageString,0.0,rDate);
        CoaachCrud C = new CoaachCrud();
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
        tfEmail.clear();
        tfPassword.clear();
        tfTel.clear();
        tfAdresse.clear();
        tfDate.setValue(null);
        img.setImage(null);

    }

    @FXML
    private void SupprimerCoach(ActionEvent event) {
        CoaachCrud C = new CoaachCrud();
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
                listM = CoaachCrud.showcoachEnd();
            } else {
                listM = CoaachCrud.rechCoach(tfrech.getText());
            }
            
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        Dateb.setCellValueFactory(new PropertyValueFactory<>("date"));

            t.setItems(listM);
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
    private void GotoGraph(ActionEvent event) {
        Stat s = new Stat();
        s.statistics();
    }

       


}

        
    


