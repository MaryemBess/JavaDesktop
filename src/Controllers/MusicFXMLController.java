/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import Entity.Music;
import Entity.video;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private ImageView btnmodifier;
    @FXML
    private ImageView Supprimer;
    @FXML
    private TextField tfrech;
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
    private ImageView ModifierV;
    @FXML
    private ImageView supprimerV;

    ObservableList<Music> Playlist;
    ObservableList<video> listv;
    @FXML
    private TableColumn<Music, String> play;
    @FXML
    private ImageView btnplay;

    private String MusicString;
    private String imageString;
    private String VideoString;

    @FXML
    private ImageView btnplayv;
    @FXML
    private ImageView btnbrowseV;
    @FXML
    private Button btnbrowsemusic;
    @FXML
    private AnchorPane anchorBaba;

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

    @FXML
    private void Ajouter_Music(MouseEvent event) {
        String rTitre = tftitre.getText();
        String rGenre = tfgenre.getText();
        String rArtiste = tfartiste.getText();

        Music m = new Music(0, rTitre, rGenre, rArtiste, MusicString, imageString);
        MusicCRUD C = new MusicCRUD();
        C.addMusic(m);
        alert("Musique ajoutée!");
        updateTableM();
        initM();
    }

    @FXML
    private void modifier_music(MouseEvent event) {
        String rtitre = tftitre.getText();
        String rgenre = tfgenre.getText();
        String rartiste = tfartiste.getText();

        Music m = new Music(tabM.getSelectionModel().getSelectedItem().getNombre(), rtitre, rgenre, rartiste, MusicString, imageString);
        MusicCRUD t = new MusicCRUD();
        t.modMusic(m);
        alert("Musique modifiée!");
        updateTableM();
        initM();
    }

    @FXML
    private void supprimer_music(MouseEvent event) {
        MusicCRUD M = new MusicCRUD();
        M.suppMusic(tabM.getSelectionModel().getSelectedItem().getNombre());

        updateTableM();
        initM();
        alert("Musique supprimée!");
    }

    @FXML
    private void PlayMusic(MouseEvent event) {
        String path = tabM.getSelectionModel().getSelectedItem().getMusicPath();
        System.out.println(path.substring(6));
        Media m = new Media(new File(path.substring(6)).toURI().toString());
        MediaPlayer M = new MediaPlayer(m);
        M.play();
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
        imageString = null;
        MusicString = null;
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
    private void BrowseMusic(ActionEvent event) throws Exception {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        Stage stage = (Stage) anchorBaba.getScene().getWindow();
        chooser.setInitialDirectory(new File("C:\\Users\\asus\\Desktop\\projet\\music"));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SS", "*.mp3"));
        File file = chooser.showOpenDialog(stage);
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
    private void BrowseAlbumImg(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        Stage stage = (Stage) anchorBaba.getScene().getWindow();
        chooser.setInitialDirectory(new File("C:\\Users\\asus\\Desktop\\projet\\music\\AlbumImg"));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.png", "*.jpeg"));
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            String ImagePath = file.toURI().toString();
            System.out.println("file:" + ImagePath);
            //Media V = new Media(ImagePath);
            imageString = ImagePath;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select a File");
            alert.setContentText("You didn't select a file!");
            alert.showAndWait();
        }
    }

    @FXML
    private void AjouterVideo(MouseEvent event) {
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
    private void Modifiervideo(MouseEvent event) {
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
    private void supprimervideo(MouseEvent event) {
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
    private void selectedV(MouseEvent event) {
        tftitreV.setText(tabV.getSelectionModel().getSelectedItem().getTitre());
        tfgenreV.setText(tabV.getSelectionModel().getSelectedItem().getGenre());
        tfNbl.setText(String.valueOf(tabV.getSelectionModel().getSelectedItem().getNb_likes()));
        tfNbd.setText(String.valueOf(tabV.getSelectionModel().getSelectedItem().getNb_dislikes()));
        VideoString = tabV.getSelectionModel().getSelectedItem().getVideoPath();
    }

    @FXML
    private void BrowseVideo(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        Stage stage = (Stage) anchorBaba.getScene().getWindow();
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Video", "*.mp4"));
        File file = chooser.showOpenDialog(stage);
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
    private void PlayVideo(MouseEvent event) {
        String path = tabV.getSelectionModel().getSelectedItem().getVideoPath().substring(6);
        System.out.println(path);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayList.fxml"));
            Parent root = loader.load();
            PlayListController plc = loader.getController();
            plc.setVidpath(path);
            Stage stage = new Stage();

            stage.setTitle("Video Player");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
