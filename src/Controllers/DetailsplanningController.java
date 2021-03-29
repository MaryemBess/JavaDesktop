/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.Myconnection;
import java.net.URL;
import java.sql.Date;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Entity.Tache;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;
import services.TacheCrud;

/**
 * FXML Controller class
 *
 * @author htc
 */
public class DetailsplanningController implements Initializable {
  private  int x ;
  private int  c1,c2,c3,c4;
  
    
    
    
    
    @FXML
    private TableView<Tache> tvtache;
    @FXML
    private TableColumn<Tache, Integer> tv_idt;
    @FXML
    private TableColumn<Tache, String> tv_nom;
    @FXML
    private TableColumn<Tache, String> tv_t;
    @FXML
    private TextField tv_idplanning;
    @FXML
    private TableColumn<Tache, Date> tv_date;
    @FXML
    private Button btn_check;
    @FXML
    private TableColumn<?, String> tv_etat;
    @FXML
    private Button btn_terminé;
    @FXML
    private Button btn_like;
    @FXML
    private Button btn_dis;
    @FXML
    private Button btn_like1;
    @FXML
    private Button btn_dis1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       /* TacheCrud tc=new TacheCrud();
       ObservableList<Tache> list1 =tc.listerDetails(int1);
        tvtache.setItems(list1);*/
    }    
    public void setId(String id) {
        this.tv_idplanning.setText(id);
    }
    @FXML
     public void update_tache (ActionEvent event){
         Tache t=tvtache.getSelectionModel().getSelectedItem();
        TacheCrud c=new TacheCrud();
        c.tache_termine(t); 
        
         TacheCrud tc=new TacheCrud();
      String s = tv_idplanning.getText();
        System.out.println(s);
        x=Integer.parseInt(s);
       ObservableList<Tache> list1 =tc.listerDetails(x);
        tvtache.setItems(list1);
    }
    @FXML
     public void xx (ActionEvent event){
         tv_idt.setCellValueFactory(new PropertyValueFactory<>("id_t"));
     tv_nom.setCellValueFactory(new PropertyValueFactory<>("nom_tache")); 
            tv_t.setCellValueFactory(new PropertyValueFactory<>("type_tache")); 
    tv_date.setCellValueFactory(new PropertyValueFactory<>("date")); 
    tv_etat.setCellValueFactory(new PropertyValueFactory<>("etat")); 
         String s;
      s = tv_idplanning.getText();
        System.out.println(s);
        x=Integer.parseInt(s);
        TacheCrud tc=new TacheCrud();
       ObservableList<Tache> list1 =tc.listerDetails(x);
        tvtache.setItems(list1);
                 
    }

    @FXML
    private void getselected(MouseEvent event) {
        int test = 0;
        test = tvtache.getSelectionModel().getSelectedIndex();

        if (test != -1) {

            btn_terminé.setDisable(false);
             
        }
    }

    @FXML
    private void like(ActionEvent event) {
        Tache t=tvtache.getSelectionModel().getSelectedItem();
        
      try {
          String req="UPDATE `tache` SET `like`= `like`+1 WHERE `id` ='"+t.getId_t()+"'";
        PreparedStatement pst;
          pst = new Myconnection().cnx.prepareStatement(req);
          pst.executeUpdate();
          System.out.println(t.getId_t());
          System.out.println(t.getId());
          System.out.println("xx");
      } catch (SQLException ex) {System.out.println(ex.getMessage());
          System.out.println("erreur like tache");      }
                
                
    }

    @FXML
    private void dislike(ActionEvent event) {
         Tache t=tvtache.getSelectionModel().getSelectedItem();
        
      try {
          String req="UPDATE `tache` SET `dislike`= `dislike`-1 WHERE `id` ='"+t.getId_t()+"'";
        PreparedStatement pst;
          pst = new Myconnection().cnx.prepareStatement(req);
          pst.executeUpdate();
          System.out.println(t.getId_t());
          System.out.println(t.getId());
          System.out.println("xx");
      } catch (SQLException ex) {System.out.println(ex.getMessage());
          System.out.println("erreur like tache");      }
    }

    @FXML
    private void like_p(ActionEvent event) {
         String gg = tv_idplanning.getText();
       
         int h=Integer.parseInt(gg);
        try {
          String req="UPDATE `planning` SET `liker`= `liker`+1 WHERE `id` ='"+h+"'";
        PreparedStatement pst;
          pst = new Myconnection().cnx.prepareStatement(req);
          pst.executeUpdate();
          
          System.out.println("xx");
      } catch (SQLException ex) {System.out.println(ex.getMessage());
          System.out.println("erreur like tache");      }
    }

    @FXML
    private void dislike_p(ActionEvent event) {
        String gg = tv_idplanning.getText();
       
         int h=Integer.parseInt(gg);
        try {
          String req="UPDATE `planning` SET `disliker`= `disliker`-1 WHERE `id` ='"+h+"'";
        PreparedStatement pst;
          pst = new Myconnection().cnx.prepareStatement(req);
          pst.executeUpdate();
          
          System.out.println("xx");
      } catch (SQLException ex) {System.out.println(ex.getMessage());
          System.out.println("erreur like tache");      }
    }

    @FXML
    private void count1(MouseEvent event) {
        c1++;
        if (c1 >= 1 ) {
            btn_like1.setDisable(true);
        }
    }
    @FXML
    private void count2(MouseEvent event) {
        c2++;
        if (c2 >= 1 ) {
            btn_dis1.setDisable(true);
        }
    }
    @FXML
    private void count3(MouseEvent event) {
        c3++;
        if (c3 >= 1 ) {
            btn_like.setDisable(true);
        }
    }
    @FXML
    private void count4(MouseEvent event) {
        c4++;
        if (c4 >= 1 ) {
            btn_dis.setDisable(true);
        }
    }
    
    
}
