/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import application.meliora;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author dell
 */
public class FxmlLoader {
        private Pane view;
        public Pane getPage(String fileName){
        
        try{
            URL fileUrl = meliora.class.getResource("/gui/" + fileName + ".fxml");
            if(fileUrl == null){
                throw new java.io.FileNotFoundException("FXML file not found");
            }
           
            view = new FXMLLoader().load(fileUrl);
            
           }catch (Exception e){
               System.out.println("No page " + fileName + " please check FxmlLoader.");
           }
        return view;
    }
    
}
