/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Music;
import entities.video;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.MusicCRUD;
import services.videoCRUD;

/**
 * FXML Controller class
 *
 * @author SAFA
 */
public class MusicFXMLController implements Initializable {

    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfgenre;
    @FXML
    private TextField tfartiste;
    @FXML
    private TableView<Music> tabM;
    @FXML
    private TableColumn<Music, String> coltitre;
    @FXML
    private TableColumn<Music, String> colgenre;
    @FXML
    private TableColumn<Music, String> colartiste;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button Supprimer;
    @FXML
    private TextField tfrech;
    @FXML
    private Button Ajouter;
    @FXML
    private TextField tftitreV;
    @FXML
    private TextField tfgenreV;
    @FXML
    private TextField tfNbl;
    @FXML
    private TextField tfNbd;
    @FXML
    private TableView<video> tabV;
    @FXML
    private TableColumn<video, String> coltitrev;
    @FXML
    private TableColumn<video, String> colgenrev;
    @FXML
    private TableColumn<video, Integer> nbl;
    @FXML
    private TableColumn<video, Integer> nbd;
    @FXML
    private TableColumn<video, String> playv;
    @FXML
    private TextField tfrechV;
    @FXML
    private Button ajouterV;
    @FXML
    private Button ModifierV;
    @FXML
    private Button supprimerV;

    ObservableList<Music> Playlist;
    ObservableList<video> listv;
    @FXML
    private TableColumn<Music, String> play;
    @FXML
    private Button btnplay;

    private String MusicString;

    private String VideoString;

    @FXML
    private Button btnbrowse;

    @FXML
    private Button btnplayv;
    @FXML
    private Button btnbrowseV;
    @FXML
    private MediaView medview;
    @FXML
    private Button music;
    private String imageString;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTableM();
        updateTableV();
    }

    public void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void updateTableM() {
        Playlist = MusicCRUD.showmusic();
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colgenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colartiste.setCellValueFactory(new PropertyValueFactory<>("Artiste"));
        play.setCellValueFactory(new PropertyValueFactory<>("MusicPath"));

        tabM.setItems(Playlist);
    }

    public void initM() {
        tftitre.clear();
        tfgenre.clear();
        tfartiste.clear();
    }

    public void updateTableV() {
        listv = videoCRUD.showvideo();
        coltitrev.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        colgenrev.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        nbl.setCellValueFactory(new PropertyValueFactory<>("nb_likes"));
        nbd.setCellValueFactory(new PropertyValueFactory<>("nb_dislikes"));
        playv.setCellValueFactory(new PropertyValueFactory<>("Videopath"));
        tabV.setItems(listv);
    }

    public void initV() {
        tftitreV.clear();
        tfgenreV.clear();
        tfNbl.clear();
        tfNbd.clear();

    }

    @FXML
    private void selectedM(MouseEvent event) {
        tftitre.setText(tabM.getSelectionModel().getSelectedItem().getTitre());
        tfgenre.setText(tabM.getSelectionModel().getSelectedItem().getGenre());
        tfartiste.setText(tabM.getSelectionModel().getSelectedItem().getArtiste());
        MusicString = tabM.getSelectionModel().getSelectedItem().getMusicPath();
    }

    @FXML
    private void modifier_music(ActionEvent event) {
        String rtitre = tftitre.getText();
        String rgenre = tfgenre.getText();
        String rartiste = tfartiste.getText();

        Music m = new Music(tabM.getSelectionModel().getSelectedItem().getNombre(), rtitre, rgenre, rartiste, MusicString , imageString);
        MusicCRUD t = new MusicCRUD();
        t.modMusic(m);
        alert("Musique modifiée!");
        updateTableM();
        initM();
    }

    @FXML
    private void supprimer_music(ActionEvent event) {
        MusicCRUD M = new MusicCRUD();
        M.suppMusic(tabM.getSelectionModel().getSelectedItem().getNombre());

        updateTableM();
        initM();
        alert("Musique supprimée!");
    }

    @FXML
    private void search_Music(KeyEvent event) {
        if (tfrech.getText().equals("")) {
            Playlist = MusicCRUD.showmusic();
        } else {
            Playlist = MusicCRUD.rechMusics(tfrech.getText());
        }
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tabM.setItems(Playlist);
    }

    @FXML
    private void AjouterMusic(ActionEvent event) {
        String rTitre = tftitre.getText();
        String rGenre = tfgenre.getText();
        String rArtiste = tfartiste.getText();

        Music m = new Music(0, rTitre, rGenre, rArtiste, MusicString,imageString);
        MusicCRUD C = new MusicCRUD();
        C.addMusic(m);
        alert("Musique ajoutée!");
        updateTableM();
        initM();
    }

    @FXML
    private void selectedV(MouseEvent event) {
        tftitreV.setText(tabV.getSelectionModel().getSelectedItem().getTitre());
        tfgenreV.setText(tabV.getSelectionModel().getSelectedItem().getGenre());
        tfNbl.setText(String.valueOf(tabV.getSelectionModel().getSelectedItem().getNb_likes()));
        tfNbd.setText(String.valueOf(tabV.getSelectionModel().getSelectedItem().getNb_dislikes()));
        VideoString = tabV.getSelectionModel().getSelectedItem().getVideoPath();
    }

    @FXML
    private void PlayMusic(ActionEvent event) {
        String path = tabM.getSelectionModel().getSelectedItem().getMusicPath();
        System.out.println(path.substring(6));
        Media m = new Media(new File(path.substring(6)).toURI().toString());
        MediaPlayer M = new MediaPlayer(m);
        M.play();

    }

    @FXML
    private void BrowseMusic(ActionEvent event) throws Exception {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SS", "*.mp3"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            String musicpath = file.toURI().toString();
            System.out.println("file:" + musicpath);
            Media m = new Media(musicpath);
            MusicString = musicpath;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select a File");
            /*alert.setContentText("You didn't select a file!");*/
            alert.showAndWait();
        }
    }

    @FXML
    private void AjouterVideo(ActionEvent event) {
        String rTitre = tftitreV.getText();
        String rGenre = tfgenreV.getText();
        String rNb_like = tfNbl.getText();
        String rNb_dislike = tfNbd.getText();
        video v = new video(9999, rTitre, rGenre, VideoString, Integer.parseInt(rNb_like), Integer.parseInt(rNb_dislike), 0);
        videoCRUD C = new videoCRUD();
        C.addVideo(v);
        alert("Video ajoutée!");
        updateTableV();
        initV();
    }

    @FXML
    private void Modifiervideo(ActionEvent event) {
        String rtitre = tftitreV.getText();
        String rgenre = tfgenreV.getText();
        String rNb_like = tfNbl.getText();
        String rNb_dislike = tfNbd.getText();

        video v = new video(tabV.getSelectionModel().getSelectedItem().getId(), rtitre, rgenre, VideoString, Integer.parseInt(rNb_like), Integer.parseInt(rNb_dislike), tabV.getSelectionModel().getSelectedItem().getMailSent());
        videoCRUD V = new videoCRUD();
        V.modVideo(v);
        alert("Video modifiée!");
        updateTableV();
        initV();
    }

    @FXML
    private void supprimervideo(ActionEvent event) {
        videoCRUD V = new videoCRUD();
        V.suppVideo(tabV.getSelectionModel().getSelectedItem().getId());
        alert("Video supprimée!");
        updateTableV();
        initV();
    }

    @FXML
    private void search_Video(KeyEvent event) {
        if (tfrechV.getText().equals("")) {
            listv = videoCRUD.showvideo();
        } else {
            listv = videoCRUD.rechVideo(tfrechV.getText());
        }
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tabV.setItems(listv);
    }

    @FXML
    private void PlayVideo(ActionEvent event) {
        String path = tabV.getSelectionModel().getSelectedItem().getVideoPath().substring(6);
        System.out.println(path);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayList.fxml"));
            Parent root = loader.load();
            PlayListController plc = loader.getController();
            // plc.setVidpath(path);
            Stage stage = new Stage();

            stage.setTitle("Video Player");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Media v = new Media(new File(path.substring(6)).toURI().toString());
//        MediaPlayer V = new MediaPlayer(v);
//        medview.setMediaPlayer(V);
//        V.setAutoPlay(true);
//        V.play();

    }

    @FXML
    private void BrowseVideo(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Video", "*.mp4"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            String Videopath = file.toURI().toString();
            System.out.println("file:" + Videopath);
            Media V = new Media(Videopath);
            VideoString = Videopath;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select a File");
            /*alert.setContentText("You didn't select a file!");*/
            alert.showAndWait();
        }
    }

    @FXML
    private void GoToMusic(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MusicPlayList.fxml"));
            Parent root = loader.load();
            MusicPlayListController plc = loader.getController();
            // plc.setVidpath(path);
            Stage stage = new Stage();

            stage.setTitle("Video Player");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            MusicPlayListController.mediaplayer.pause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
