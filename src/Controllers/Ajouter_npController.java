/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Entity.Planningcreer;
import services.PlanningCrud;
import services.PlanningcreerCons;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.collections.ObservableList;
import Entity.Planning;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author htc
 */
public class Ajouter_npController implements Initializable {

    @FXML
    private AnchorPane tvnp;
    @FXML
    private TextField tv_np;
    @FXML
    private TextField tv_desc;
    @FXML
    private Button btn_c;
    @FXML
    private Button btn_r;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*   if (tv_np.getText().length()<1)
        {btn_c.setDisable(true);
        }else btn_c.setDisable(false);*/
        // TODO
    }

    @FXML
    public void redirectL(ActionEvent event) throws AWTException {
        //1- SAVE Planning IN DATABASE
        if ((tv_desc.getText().isEmpty()) || (tv_np.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez affecter un nom et une description a votre planning");
            Optional<ButtonType> action = alert.showAndWait();
        } else {
            String rDescription = tv_desc.getText();
            String rNomplanning = tv_np.getText();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment AJOUTER ce planning de ton programme ?!");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {

                PlanningCrud pcd = new PlanningCrud();

                Planningcreer p = new Planningcreer(123, 0, 0, 3, rDescription);

                Planning p1 = new Planning(123, 1, rNomplanning);

                int x = pcd.addPlanning(p, p1);
                if (SystemTray.isSupported()) {
                    PlanningCrud td = new PlanningCrud();
                    td.displayTray("votre planning a eté creer");
                } else {
                    System.err.println("System tray not supported!");
                }
                String s = String.valueOf(x);

                System.out.println(s);

                System.out.println("ahna houni");
                int c = Integer.parseInt(s);
                System.out.println(c);
                System.out.println("oook");

//2- REDIRECTION
                try {

                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/gui/liste_taches.fxml"));
                    Parent root = loader.load();
                    Liste_tachesController hhh = loader.getController();
                    hhh.setId(s);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println("erreur set du id");
                    System.out.println(ex.getMessage());
                }

            }
        }
    }
}
