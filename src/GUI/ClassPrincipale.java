/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import mailing.mail;
import entities.video;
import services.videoCRUD;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.MusicCRUD;
import tools.MyConnection;

/**
 *
 * @author asus
 */
public class ClassPrincipale extends Application {

    @Override
    public void start(Stage primaryStage) {
        // send mail and change mailSent

        Thread th = new Thread(() -> {
            ObservableList<video> VideoList = videoCRUD.showvideo();
            for (video v : VideoList) {
                if (v.getNb_dislikes() > v.getNb_likes() && v.getMailSent() == 0) {
                    try {
                        mail.SendMail("safa97kaabi@gmail.com", v.getTitre(), v.getNb_dislikes());
                        videoCRUD.modMailSentChange1(v.getId());
                    } catch (Exception ex) {
                        Logger.getLogger(ClassPrincipale.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (v.getNb_dislikes() < v.getNb_likes()) {
                    videoCRUD.modMailSentChange0(v.getId());
                }
            }
        });
        th.setDaemon(true);
        th.start();

        // interface
        try {
            Parent root
                    = FXMLLoader.load(getClass().getResource("MusicFXML.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/css/Style.css");
            primaryStage.setTitle("Home window");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
