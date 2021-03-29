/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.video;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import services.MusicCRUD;
import services.videoCRUD;

/**
 * FXML Controller class
 *
 * @author SAFA
 */
public class PlayListController implements Initializable {

    @FXML
    private ImageView Startbtn;
    @FXML
    private ImageView volumedown;
    @FXML
    private ImageView speaker;
    @FXML
    private ImageView volumeup;
    @FXML
    private MediaView VideoView;

    private int paused = 0;
    private int mute = 0;
    ObservableList<video> listv;
    
    private String vidpath;// = "C:/Users/SAFA/Downloads/EgyBest.Peaky.Blinders.S05E06.WEB-DL.720p.x264.mp4";
    private final Image start = new Image("images/play_50px.png");
    private final Image pause = new Image("images/pause_48px.png");
    private final Image sound_on = new Image("images/speaker_52px.png");
    private final Image sound_off = new Image("images/mute_100px.png");
    private Media v ;
    private MediaPlayer V ;
    private double initVolume = 0.5;
    @FXML
    private ProgressBar VolumeBar;
    
//    private File Dir;
//    private File[] files;
//    private ArrayList<File> videos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
             init();
             v = new Media(new File(vidpath).toURI().toString());
             V = new MediaPlayer(v);
//        videos = new ArrayList<>();
//        Dir = new File("video");
//        files = Dir.listFiles();
//        if (files != null) {
//            for (File f : files) {
//                videos.add(f);
//                System.out.println(f);
//            }
//        }
        try {
            VolumeBar.setProgress(initVolume);
            System.out.println(vidpath);
            VideoView.setMediaPlayer(V);
            V.setAutoPlay(true);
            V.setVolume(initVolume);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getVidpath() {
        return vidpath;
    }

    public void setVidpath(String vidpath) {
        this.vidpath = vidpath;
    }

    public void init() {
        try {
            Startbtn.setImage(pause);
            speaker.setImage(sound_on);
        } catch (Exception e) {
        }
    }

    @FXML
    private void start_pause(MouseEvent event) {
        if (paused == 0) {
            V.pause();
            Startbtn.setImage(start);
            paused = 1;
        } else {
            V.play();
            Startbtn.setImage(pause);
            paused = 0;
        }
    }

    @FXML
    private void VolumeDown(MouseEvent event) {
        initVolume -= 0.1;
        V.setVolume(initVolume);
        VolumeBar.setProgress(initVolume);
        if (V.getVolume() < 0) {
            initVolume = 0;
            speaker.setImage(sound_off);
        }
    }

    @FXML
    private void VolumeUp(MouseEvent event) {
        initVolume += 0.1;
        V.setVolume(initVolume);
        VolumeBar.setProgress(initVolume);
        if (V.getVolume() > 0) {
            speaker.setImage(sound_on);
        }
        if (V.getVolume() > 1) {
            initVolume = 1;
        }
    }

    @FXML
    private void mute(MouseEvent event) {
        if (mute == 0) {
            speaker.setImage(sound_off);
            V.setVolume(0);
            VolumeBar.setProgress(0);
            mute = 1;
        } else {
            speaker.setImage(sound_on);
            V.setVolume(initVolume);
            VolumeBar.setProgress(initVolume);
            mute = 0;
        }
    }

//    @FXML
//    private void search_video(KeyEvent event) {
//        
//                if (rechV.getText().equals("")) {
//            listv = videoCRUD.showvideo();
//        } else {
//            listv = videoCRUD.rechVideo(rechV.getText());
//        }
//        
        
      
//}
}

