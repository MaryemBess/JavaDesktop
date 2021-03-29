/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Coaach;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import services.CoaachCrud;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class SignUpCoachController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField filename;
    @FXML
    private Label passwordStrength;
    @FXML
    private ImageView aaa;
    @FXML
    private JFXButton btnRegister;
    @FXML
    private JFXButton btnback;
    @FXML
    private Label ImagePath;
    @FXML
    private JFXTextField tfidU;
    @FXML
    private DatePicker birthDate;
    @FXML
    private JFXTextField addressTf;
    @FXML
    private JFXTextField phoneNumber;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField tfname;
    @FXML
    private JFXTextField tflastname;
    private String coachimgpath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void chooseProfilePicture(ActionEvent event) throws MalformedURLException {

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
            aaa.setImage(image);
            coachimgpath = imagepath;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select a File");

            alert.showAndWait();
        }
    }

    @FXML
    private void CreateAccount(ActionEvent event) {
        String name = tfname.getText();
        String lastname = tflastname.getText();
        String mail = email.getText();
        String pswd = password.getText();
        pswd = BCrypt.hashpw(pswd, BCrypt.gensalt(13));
        pswd = pswd.replaceFirst("a", "y");
        

        int number = Integer.valueOf(phoneNumber.getText());

        String address = addressTf.getText();
        Date DateB = Date.valueOf(birthDate.getValue());
        Coaach c = new Coaach(99, name, lastname, mail, pswd, number, address, coachimgpath, 0.0,DateB);
        System.out.println(c);
        CoaachCrud C = new CoaachCrud();
        C.addCoach(c);
    }

    @FXML
    private void back(ActionEvent event) {
    }

}
