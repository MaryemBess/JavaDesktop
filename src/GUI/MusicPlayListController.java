/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Music;
import entities.MyListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import services.MusicCRUD;
import GUI.MusicFXMLController;

/**
 * FXML Controller class
 *
 * @author SAFA
 */
public class MusicPlayListController implements Initializable {

    @FXML
    private ImageView Startbtn;
    @FXML
    private ImageView next;
    @FXML
    private ListView<String> MusicListview;
    @FXML
    private ImageView speaker;
    @FXML
    private ImageView previous;

    private final Image Defaultdisk = new Image("images/disk.jpg");
    private final Image start = new Image("images/play_50px.png");
    private final Image pause = new Image("images/pause_48px.png");
    private final Image sound_on = new Image("images/speaker_52px.png");
    private final Image sound_off = new Image("images/mute_100px.png");

    private double initVolume = 0.5;
    private int paused = 0;
    private int mute = 0;
    @FXML
    private Slider VolumeSlider;

    private File Dir;
    private File[] files;
    private ArrayList<File> songs;

    private Media media;
    public static MediaPlayer mediaplayer;

    private int songNumber;
    @FXML
    private Label MusicName;
    @FXML
    private GridPane gridpane;
    List<Music> Mus = new ArrayList<>();
    private int chosennb;
    private MyListener myListener;
    @FXML
    private ImageView AlbumImg;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
        songs = new ArrayList<>();
        Dir = new File("music");
        files = Dir.listFiles();
        if (files != null) {
            for (File f : files) {
                songs.add(f);
                //MusicListview.getItems().add(f.getName());
                System.out.println(f);
            }
        }
        Show(songs);

        media = new Media(songs.get(songNumber).toURI().toString());
        mediaplayer = new MediaPlayer(media);

        MusicName.setText(songs.get(songNumber).getName());
        mediaplayer.play();

        VolumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaplayer.setVolume(VolumeSlider.getValue() * 0.01);
            }
        });
    }
        public void hez_ghadi(Music mu) {
        chosennb = mu.getNombre();
        MusicName.setText(mu.getTitre());
        Image image = new Image(mu.getImage());
        //imgchosen.setImage(image);
    }
        
       public void Show(List<File> Mus) {
        gridpane.getChildren().clear();
        //Mus = MusicCRUD.showmusic();
//        if (Mus.size() > 0) {
//            //hez_ghadi(Mus.stream().findFirst().get());
//            myListener = new MyListener() {
//                @Override
//                public void onClickListener(File mu) {
//                    hez_ghadi(mu);
//                }
//            };
//        } 
        int column = 0;
        int row = 0;

        try {
            for (File ex : Mus) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(ex, myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                gridpane.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                gridpane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridpane.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridpane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridpane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void start_pause(MouseEvent event) {
        if (paused == 0) {
            mediaplayer.pause();
            Startbtn.setImage(start);
            paused = 1;
        } else {
            mediaplayer.play();
            Startbtn.setImage(pause);
            paused = 0;
        }
    }

    @FXML
    private void mute(MouseEvent event) {
        if (mute == 0) {
            speaker.setImage(sound_off);
            mediaplayer.setVolume(0);
            mute = 1;
        } else {
            speaker.setImage(sound_on);

            mediaplayer.setVolume(initVolume);
            VolumeSlider.setValue(initVolume);
            mute = 0;
        }
    }

    public void init() {
        VolumeSlider.setValue(initVolume * 100);
        try {
            AlbumImg.setImage(Defaultdisk);
            Startbtn.setImage(pause);
            speaker.setImage(sound_on);
        } catch (Exception e) {
        }
    }

    @FXML
    private void nextSong(MouseEvent event) {
        if (songNumber < songs.size() - 1) {
            songNumber++;
            mediaplayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaplayer = new MediaPlayer(media);

            MusicName.setText(songs.get(songNumber).getName());
            mediaplayer.play();
        }else{
                        songNumber = 0;
            mediaplayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaplayer = new MediaPlayer(media);

            MusicName.setText(songs.get(songNumber).getName());
            mediaplayer.play();
        }
    }

    @FXML
    private void previousSong(MouseEvent event) {
                if (songNumber > 0) {
            songNumber--;
            mediaplayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaplayer = new MediaPlayer(media);

            MusicName.setText(songs.get(songNumber).getName());
            mediaplayer.play();
        }else{
                        songNumber = songs.size() - 1;
            mediaplayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaplayer = new MediaPlayer(media);

            MusicName.setText(songs.get(songNumber).getName());
            mediaplayer.play();
        }
    }

}
