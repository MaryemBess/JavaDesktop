/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Music;
import Entity.MyMusicListener;
import Entity.favoris;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import services.FavorisCRUD;
import services.MusicCRUD;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MusicPlayerController implements Initializable {

    @FXML
    private AnchorPane scenePane;
    @FXML
    private GridPane gridpane;
    @FXML
    private TextField search;
    @FXML
    private ImageView Startbtn;
    @FXML
    private ImageView next;
    @FXML
    private ImageView speaker;
    @FXML
    private ImageView previous;
    @FXML
    private Slider VolumeSlider;
    @FXML
    private Label MusicName;
    @FXML
    private ImageView AlbumImg;

    private Image albumImage;
    private final Image Defaultdisk = new Image("images/disk.jpg");
    private final Image start = new Image("images/play_50px.png");
    private final Image pause = new Image("images/pause_48px.png");
    private final Image sound_on = new Image("images/speaker_52px.png");
    private final Image sound_off = new Image("images/mute_100px.png");
    private final Image liked = new Image("images/mute_100px.png");
    private final Image disliked = new Image("images/speaker_52px.png");

    private double initVolume = 0.7;
    private int paused = 0;
    private int mute = 0;

    private ObservableList<Music> songs;
    private ObservableList<Music> FavSongs;

    private Media media;
    public static MediaPlayer mediaplayer;

    private int songNumber;
    private MyMusicListener MyMusicListener;
    @FXML
    private ImageView Favorite;

    private final int id_user = 1;
    ObservableList<favoris> listIdFav;
    @FXML
    private JFXComboBox<String> cbmusic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
        listIdFav = FavorisCRUD.filterFavoris(id_user);
        for (favoris f : listIdFav) {
            System.out.println(f);
        }
        songs = MusicCRUD.showmusicSorted();
        Show(songs);
        media = new Media(new File(songs.get(songNumber).getMusicPath().substring(6)).toURI().toString());
        mediaplayer = new MediaPlayer(media);
        SetAlbumImage(songNumber);
        checkFav();
        MusicName.setText(songs.get(songNumber).getTitre() + "  -  " + songs.get(songNumber).getArtiste());
        mediaplayer.play();

        VolumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaplayer.setVolume(VolumeSlider.getValue() * 0.01);
                initVolume = VolumeSlider.getValue() * 0.01;
            }
        });
    }

    @FXML
    private void SearchSong(KeyEvent event) {
        gridpane.getChildren().clear();
        if (search.getText().equals("")) {
            songs = MusicCRUD.showmusicSorted();
            Show(songs);
        } else {
            songs = MusicCRUD.rechMusics(search.getText());
            if (songs.size() > 0) {
                MyMusicListener = new MyMusicListener() {
                    @Override
                    public void onClickListener(Music mu) {
                        hez_ghadi(mu);
                    }
                };
            }

            int column = 0;
            int row = 0;

            try {
                for (Music m : songs) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/itemMusic.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemMusicController itemMusicController = fxmlLoader.getController();
//                    if (songs.get(songNumber).getNombre() == m.getNombre()) {
//                        itemController.getAnchor().setStyle("-fx-border-color: #e82300;-fx-border-radius: 50;");
//                    } else {
//                        itemController.getAnchor().setStyle("-fx-border-color: #9e4be7;-fx-border-radius: 50;");
//                    }
                    itemMusicController.setData(m, MyMusicListener);

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
    private void nextSong(MouseEvent event) {
        if (songNumber < songs.size() - 1) {
            songNumber++;
            changeSong(songNumber);
        } else {
            songNumber = 0;
            changeSong(songNumber);
        }
    }

    @FXML
    private void mute(MouseEvent event) {
        if (mute == 0) {
            speaker.setImage(sound_off);
            mediaplayer.setVolume(0);
            //VolumeSlider.setValue(0);
            mute = 1;
        } else {
            speaker.setImage(sound_on);
            mediaplayer.setVolume(initVolume);
            //VolumeSlider.setValue(initVolume * 100);
            mute = 0;
        }
    }

    @FXML
    private void previousSong(MouseEvent event) {
        if (songNumber > 0) {
            songNumber--;
            changeSong(songNumber);
        } else {
            songNumber = songs.size() - 1;
            changeSong(songNumber);
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

    public void changeSong(int Number) {
        mediaplayer.stop();
        media = new Media(new File(songs.get(Number).getMusicPath().substring(6)).toURI().toString());
        mediaplayer = new MediaPlayer(media);
        mediaplayer.setVolume(initVolume);
        SetAlbumImage(Number);
        if (listIdFav != null) {
            checkFav();
        }
        MusicName.setText(songs.get(songNumber).getTitre() + "  -  " + songs.get(songNumber).getArtiste());
        Show(songs);
        mediaplayer.play();
    }

    public void SetAlbumImage(int Number) {
        if (songs.get(Number).getImage() == null) {
            AlbumImg.setImage(Defaultdisk);
        } else {
            albumImage = new Image(songs.get(Number).getImage());
            AlbumImg.setImage(albumImage);
        }
    }

    public int checkFav() {
        //if(listIdFav != null)
        //listIdFav = FavorisCRUD.filterFavoris(id_user);
        int found = 0;
        int favId = -1;
        for (favoris m : listIdFav) {
            if (m.getId_music() == songs.get(songNumber).getNombre()) {
                found = 1;
                favId = m.getId();
            }
        }
        if (found == 1) {
            Favorite.setImage(liked);
            System.out.println(songs.get(songNumber).getTitre() + " : belong to favorite");
        } else {
            Favorite.setImage(disliked);
            System.out.println(songs.get(songNumber).getTitre() + " : doesn't belong to favorite");
        }
        return favId;
    }

    public void hez_ghadi(Music mu) {
        songNumber = getSelectedSongNb(mu);
        if (songNumber >= 0) {
            changeSong(songNumber);
        }
    }

    public int getSelectedSongNb(Music m) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getNombre() == m.getNombre()) {
                return i;
            }
        }
        return -1;
    }

    public void Show(ObservableList<Music> songs) {
        gridpane.getChildren().clear();
        if (songs.size() > 0) {
            MyMusicListener = new MyMusicListener() {
                @Override
                public void onClickListener(Music mu) {
                    hez_ghadi(mu);
                }
            };
        }

        int column = 0;
        int row = 0;

        try {
            for (Music m : songs) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/itemMusic.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemMusicController itemMusicController = fxmlLoader.getController();
                if (songs.get(songNumber).getNombre() == m.getNombre()) {
                    itemMusicController.getAnchor().setStyle("-fx-border-color: #ffffff;-fx-border-radius: 50;");
                } else {
                    itemMusicController.getAnchor().setStyle("-fx-border-color: #065978;-fx-border-radius: 50;");
                }
                itemMusicController.setData(m, MyMusicListener);

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
    private void FavUnfav(MouseEvent event) {
        int x = checkFav();
        if (x == -1) {
            System.out.println("not found!");
        } else if (x == 0) {
            FavorisCRUD F = new FavorisCRUD();
            F.suppFavoris(x);
            checkFav();
        } else {
            favoris f = new favoris(99, id_user, songs.get(songNumber).getNombre());
            FavorisCRUD F = new FavorisCRUD();
            F.addFavoris(f);
            checkFav();
        }

    }
}
