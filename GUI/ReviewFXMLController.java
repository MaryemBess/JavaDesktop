/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.twilio.Twilio;
//import com.twilio.sdk.client.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.vonage.quickstart.sms.SendMessage;
import java.net.*;
import entities.review;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Rating;
import static org.shaded.bouncycastle.crypto.tls.ConnectionEnd.client;
import services.CoachCRUD;
import services.reviewCRUD;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ReviewFXMLController implements Initializable {

    @FXML
    private TextArea tfcomment;
    @FXML
    private RadioButton radSatis;
    @FXML
    private RadioButton radNor;
    @FXML
    private RadioButton radMed;

    ObservableList<review> listR;
    @FXML
    private ToggleGroup etat;
    @FXML
    private Rating rat;
    @FXML
    private Button addRev;

    private int idCoachRev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addRev(ActionEvent event) throws Exception {
        String etat;
        if (radSatis.isSelected()) {
            etat = radSatis.getText();
        } else if (radNor.isSelected()) {
            etat = radNor.getText();
        } else {
            etat = radMed.getText();
        }
        double rating = rat.getRating();
        String comment = tfcomment.getText();
        System.out.println(etat);

        review r = new review(idCoachRev, rating, etat, comment);
        reviewCRUD R = new reviewCRUD();
        R.addReview(r);
        R.updateCoachRating(idCoachRev);
        SendMessage.sms();
        
                
        

        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("review ajouté");
        /*alert.setContentText("You didn't select a file!");*/
        alert.showAndWait();
        SmsSender();

    }

    public int getIdCoachRev() {
        return idCoachRev;
    }

    public void setIdCoachRev(int idCoachRev) {
        this.idCoachRev = idCoachRev;
    }
// Install the Java helper library from twilio.com/docs/libraries/java

    public void SmsSender() {
//        // Find your Account Sid and Auth Token at twilio.com/console
//        final String ACCOUNT_SID
//                = "AC5d95e1f300ac9f46e93fd8e41408e19c";
//        final String AUTH_TOKEN
//                = "5ac37a17b01406da9b1ef2a0823767a0";
//
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message
//                .creator(new com.twilio.type.PhoneNumber("+21695378520"), // to
//                        new com.twilio.type.PhoneNumber("+21624669743"), // from
//                        "ahmed")
//                .create();
//
//        System.out.println(message.getSid());

    }
}
