/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import CONNECTION.DataSource;
import Services.ServiceReclamation;
import Entity.Reclamation;
import java.io.IOException;
import java.net.URL;
import static Services.Service_Admin.currentUser;
import com.jfoenix.controls.JFXButton;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author maryem
 */
public class ReclamAjoutController implements Initializable {
    @FXML
    private Label id_client;
    @FXML
    private TextField sjt;
    @FXML
    private TextField desc;
    @FXML
    private Button ajout;
    @FXML
    private Label date; 
 private DataSource conn;
     private boolean Timer;
    private Integer tseconds;
    @FXML
    private JFXButton cancel_btn;

    /**
     * Initializes the controller class.
     */
    //supposee yaaref el reclamation mta3 enehou user bedhabt 
 private void userid(){
        Connection conn=DataSource.getInstance().getCnx();
        String username = id_client.getText();
          try {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select id from user");
       while(rs.next()){
      
            System.out.println(rs.getString("true"+currentUser));
           if(currentUser.getId()==rs.getInt("id")){
       
          System.out.println("true");
           }
       }
        }catch(Exception e){
        }
 }
 private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.millis(Calendar.getInstance().get(Calendar.MILLISECOND)), (ActionEvent event) -> {
            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            date.setText(String.format("   %02d-%02d-%02d ", year, month+1,day ));
         
        }), new KeyFrame(Duration.millis(Calendar.getInstance().get(Calendar.MILLISECOND))));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    
   @FXML
    private void ajoutrecl(ActionEvent event) throws IOException {
        try {
            String sujet = sjt.getText();
            String description = desc.getText();
            String time = date.getText();
           
            ServiceReclamation sp = new ServiceReclamation();
            Reclamation R = new Reclamation(sujet,description,time,currentUser.getId());
            
            sp.ajoutReclam(R);
  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                    CloseStageAutomaticly(event);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    System.out.println(("true"+currentUser));
                    id_client.setText(currentUser.getUsername());

         startClock();
    userid();
       
    }    
    
    private void CloseStageAutomaticly(Event e) throws IOException{
        final Node source = (Node) e.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    }

   
}
