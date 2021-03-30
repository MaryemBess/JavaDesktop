/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;



import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class meliora extends Application  {
    double X,Y = 0 ;
@Override    
    public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/gui/EspaceAdmin.fxml"));
    primaryStage.initStyle(StageStyle.UNDECORATED);

root.setOnMousePressed(event -> {
    X= event.getSceneX();
    Y= event.getSceneY();
});

root.setOnMouseDragged(event -> {
    primaryStage.setX(event.getScreenX() - X);
    primaryStage.setY(event.getScreenY() - Y);
});
primaryStage.setScene(new Scene(root));
primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}





