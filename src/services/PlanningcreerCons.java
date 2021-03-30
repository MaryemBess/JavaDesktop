/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author htc
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Entity.Planning;
import Entity.Planningcreer;
import CONNECTION.Myconnection;

/**
 *
 * @author htc
 */

public class PlanningcreerCons {
    public String getnomcreateur (int id_c){
        String x = null;
        String req ="SELECT CONCAT(`nom`,CONCAT( ' ',`prenom`)) FROM coach WHERE `id`='"+id_c+"'";
        try {
            
            
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            
            
           rs.next();
            x=rs.getString(1);
            
            
        } catch (SQLException ex) {
            System.out.println("erreur de getnomcreateur");  
            System.out.println(ex.getMessage());
        }
   
            return x;
                    
            }
    public ObservableList<Planningcreer>  listercoach (int x ){
     
          ObservableList<Planningcreer> list1 =FXCollections.observableArrayList();
          String requete="SELECT id,liker,disliker,id_c,date_creation,description FROM planning where id_c ='"+x+"'";
           try {
          PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete)  ;
          ResultSet rs = pst.executeQuery();
           while (rs.next()){
                     list1.add(new Planningcreer(rs.getInt("id"),rs.getString("description"),rs.getInt("liker"),rs.getInt("disliker"),rs.getInt("id_C"),rs.getDate("date_creation")));
                 
                     }
          
      } catch (SQLException ex) {
          System.out.println("errerur const planning totale ");
          System.out.println(ex.getMessage());
      }
            return list1;
 }


  public ObservableList<Planningcreer>  lister (){
     
          ObservableList<Planningcreer> list1 =FXCollections.observableArrayList();
          String requete="SELECT id,liker,disliker,id_c,date_creation,description FROM planning";
           try {
          PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete)  ;
          ResultSet rs = pst.executeQuery();
           while (rs.next()){
                     list1.add(new Planningcreer(rs.getInt("id"),rs.getString("description"),rs.getInt("liker"),rs.getInt("disliker"),rs.getInt("id_C"),rs.getDate("date_creation")));
                 
                     }
          
      } catch (SQLException ex) {
          System.out.println("errerur const planning totale ");
          System.out.println(ex.getMessage());
      }
          /*for( Planningcreer a : list1)
System.out.println(a.getId()+"/"+a.getDescription()+"/"+a.getLiker()+"/"+ a.getDisliker() +"/"+a.getId_c());
     */
      return list1;
  }
  
}
