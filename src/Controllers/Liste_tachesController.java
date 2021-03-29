/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.itextpdf.text.DocumentException;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Entity.Tache;
import services.PlanningCrud;
import services.TacheCrud;
import CONNECTION.Myconnection;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author htc
 */
public class Liste_tachesController implements Initializable {

    private int x;
    private int id_p;

    @FXML
    private TableView<Tache> tvtache;
    @FXML
    private TableColumn<Tache, Integer> tv_idt;
    @FXML
    private TableColumn<Tache, String> tv_nom;
    @FXML
    private TableColumn<Tache, String> tv_t;
    @FXML
    private TableColumn<Tache, Integer> tv_l;
    @FXML
    private TableColumn<Tache, Integer> tv_d;
    @FXML
    private Button btn_addtache;
    @FXML
    private Button btn_suptache;
    @FXML
    private TextField btn_idplanning;
    @FXML
    private TableView<Tache> tv_tacheajouté;
    @FXML
    private TableColumn<Tache, String> nom_t;
    @FXML
    private TableColumn<Tache, String> type_t;
    @FXML
    private TableColumn<Tache, Integer> date_t;
    @FXML
    private DatePicker btn_date;
    @FXML
    private Button btn_mail;
    @FXML
    private ComboBox<String> time;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tv_idt.setCellValueFactory(new PropertyValueFactory<>("idnonnull"));
        tv_nom.setCellValueFactory(new PropertyValueFactory<>("nom_tache"));
        tv_t.setCellValueFactory(new PropertyValueFactory<>("type_tache"));
        tv_l.setCellValueFactory(new PropertyValueFactory<>("like"));
        tv_d.setCellValueFactory(new PropertyValueFactory<>("dislike"));
        System.out.println("sss");
        TacheCrud pcd = new TacheCrud();
        ObservableList<Tache> list1 = pcd.lister();
        tvtache.setItems(list1);
        ObservableList<String> newtime = FXCollections.observableArrayList();
        newtime.add("00:00:00");
        newtime.add("01:00:00");
        newtime.add("02:00:00");
        newtime.add("03:00:00");
        newtime.add("04:00:00");
        newtime.add("05:00:00");
        newtime.add("06:00:00");
        newtime.add("07:00:00");
        newtime.add("08:00:00");
        newtime.add("09:00:00");
        newtime.add("10:00:00");
        newtime.add("11:00:00");
        newtime.add("12:00:00");
        newtime.add("13:00:00");
        newtime.add("14:00:00");
        newtime.add("15:00:00");
        newtime.add("16:00:00");
        newtime.add("17:00:00");
        newtime.add("18:00:00");
        newtime.add("19:00:00");
        newtime.add("20:00:00");
        newtime.add("21:00:00");
        newtime.add("22:00:00");
        newtime.add("23:00:00");
        time.setItems(newtime);
        System.out.println(time.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void send_mail() throws DocumentException, SQLException, IOException {

        String x = btn_idplanning.getText();
        id_p = Integer.parseInt(x);
        PlanningCrud sendmail = new PlanningCrud();
        String Resultmail = sendmail.getmail(id_p);

        LocalDate data = btn_date.getValue();

        Date testdate = Date.valueOf(data);

        LocalTime timePart = LocalTime.parse("04:05:06");

        LocalDateTime dt = LocalDateTime.of(data, timePart);
        System.out.println(data);
        System.out.println(timePart);
        System.out.println(dt);

    }

    @FXML
    public void complete_liste() {

        nom_t.setCellValueFactory(new PropertyValueFactory<>("nom_tache"));
        type_t.setCellValueFactory(new PropertyValueFactory<>("type_tache"));
        date_t.setCellValueFactory(new PropertyValueFactory<>("date"));
        String s = btn_idplanning.getText();
        System.out.println(s);
        System.out.println("yooo");
        x = Integer.parseInt(s);
        TacheCrud pcd1 = new TacheCrud();
        ObservableList<Tache> list2 = pcd1.lister_t_a_mon_programe(x);
        tv_tacheajouté.setItems(list2);
    }

    public void xxx(String x) {
        System.out.println("aa");
    }

    public void setId(String id) {
        this.btn_idplanning.setText(id);
    }

    @FXML
    public void add_tache(ActionEvent event) {

        LocalDate data = btn_date.getValue();
        String datetos = String.valueOf(data);
        System.out.println(data);
        System.out.println(datetos);

        String timex = time.getSelectionModel().getSelectedItem();
        String finaltime = datetos + " " + timex;
        System.out.println(finaltime);
        Timestamp timeins = Timestamp.valueOf(finaltime);

        // date = new Date(btn_date.getValue().toEpochDay());
        Tache t = tvtache.getSelectionModel().getSelectedItem();
        t.setDate(timeins);
        String s = btn_idplanning.getText();
        x = Integer.parseInt(s);
        TacheCrud tc = new TacheCrud();
        tc.add_tache(t, x);

        complete_liste();
    }

    @FXML
    public void supprimerp(ActionEvent event) {
        int c;
        try {
            Tache t = tv_tacheajouté.getSelectionModel().getSelectedItem();
            System.out.println(t.getId_p());
            String rech = "SELECT id FROM liste_taches  where id_p='" + t.getId_p() + "' AND nom_tache = '" + t.getNom() + "' AND date='" + t.getDate() + "' AND type_tache ='" + t.getType_tache() + "'";
            PreparedStatement pst1 = new Myconnection().cnx.prepareStatement(rech);
            ResultSet rs = pst1.executeQuery();
            rs.next();
            c = rs.getInt(1);

            System.out.println(c + "/" + t.getDate() + "/" + t.getNom_tache() + "/" + t.getType_tache());
            String query = "DELETE FROM liste_taches  where id = '" + c + "'";
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(query);
            pst.execute();

            System.out.println("gggggggggg");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        String s = btn_idplanning.getText();
        x = Integer.parseInt(s);
        TacheCrud pcd1 = new TacheCrud();
        ObservableList<Tache> list2 = pcd1.lister_t_a_mon_programe(x);
        tv_tacheajouté.setItems(list2);

    }

    @FXML
    private void getselected_t(MouseEvent event) {
        int test = 0;
        test = tvtache.getSelectionModel().getSelectedIndex();

        if (test != -1) {

            btn_addtache.setDisable(false);
        }
    }

    @FXML
    private void getselected_t2(MouseEvent event) {
        int test = 0;
        test = tv_tacheajouté.getSelectionModel().getSelectedIndex();

        if (test != -1) {

            btn_suptache.setDisable(false);
        }
    }

    /*@FXML
    private void refresh(MouseEvent event) {
        int  test = 0;
            test=tv_tacheajouté.getSelectionModel().getSelectedIndex();
      
           if (test != -1) {
          
           btn_mail.setDisable(false);
           }
    }*/
}
