/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Entity.Planning;
import Entity.Planningcreer;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.PlanningCrud;

/**
 * FXML Controller class
 *
 * @author htc
 */
public class CreerpcController implements Initializable {

    @FXML
    private AnchorPane tvnp;
    @FXML
    private TextField tv_desc;
    @FXML
    private Button btn_c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redirectL(ActionEvent event) throws AWTException {
         if ((tv_desc.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez affecter une description a votre planning");
            Optional<ButtonType> action = alert.showAndWait();
        } else {
            String rDescription = tv_desc.getText();
           
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment AJOUTER ce planning de ton programme ?!");
            Optional<ButtonType> action = alert.showAndWait();
            int idc_c =1;
            if (action.get() == ButtonType.OK) {

                PlanningCrud pcd = new PlanningCrud();

                Planningcreer p = new Planningcreer(123, 0, 0, idc_c, rDescription);

              

                int x = pcd.addPlanningc(p,idc_c);
                if (SystemTray.isSupported()) {
                    PlanningCrud td = new PlanningCrud();
                    td.displayTray("votre planning a eté ajouter");
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
    

