/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Coaach;
import Entity.MyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.CoaachCrud;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class CoachFrontController implements Initializable {

    @FXML
    private AnchorPane scenePane;
    @FXML
    private GridPane gridpane;

    private MyListener myListener;
    List<Coaach> Coachs = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Show();
    }

    public void hez_ghadi(Coaach c) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ProfilCoach.fxml"));
            Parent root = loader.load();
            ProfilCoachController pcc = loader.getController();
            pcc.setDatttta(c);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle(c.getNom()+" "+c.getPrenom()+"'s profile");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            Show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Show() {
        gridpane.getChildren().clear();
        Coachs = CoaachCrud.showcoachEnd();
        if (Coachs.size() > 0) {
            myListener = new MyListener() {
                @Override
                public void onClickListener(Coaach ex) {
                    hez_ghadi(ex);
                }
            };
        }
        int column = 0;
        int row = 0;

        try {
            for (Coaach ex : Coachs) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/itemCoach.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemCoachController itemCoachController = fxmlLoader.getController();
                itemCoachController.setData(ex, myListener);

                if (column == 4) {
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

                GridPane.setMargin(anchorPane, new Insets(30));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
