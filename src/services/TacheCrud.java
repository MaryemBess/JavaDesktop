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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Entity.Tache;
import CONNECTION.Myconnection;
import java.sql.Timestamp;

/**
 *
 * @author htc
 */
public class TacheCrud {
    
public ObservableList<Tache> lister(){
     ObservableList<Tache> list1 =FXCollections.observableArrayList();
    String requete = "SELECT * FROM tache ";
    try { 
        PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            list1.add(new Tache(rs.getInt("id"),rs.getInt("id_m"),rs.getInt("id_v"),rs.getInt("id_c"),rs.getInt("id_e"),rs.getInt("like"),rs.getInt("dislike"),rs.getString("type_tache"),rs.getString("nom_tache"),rs.getInt("idnonnull")));
        }
    } catch (SQLException ex) {
        System.out.println("ereur lister taches "); 
        System.out.println(ex.getMessage());
    }
     /* for( Tache a : list1)
System.out.println(a.getId_t()+"/"+a.getType()+"/"+a.getId_v()+"/"+a.getId_b()+"/"+"/"+a.getId_c()+"/"+a.getId_m()+"/"+a.getIdnonnull());*/
        
     return list1;
}
public ObservableList<Tache> listerDetails(Integer x){
     ObservableList<Tache> list1 =FXCollections.observableArrayList();
    String requete = "SELECT * FROM liste_taches where id_p="+x;
    try { 
        PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            list1.add(new Tache(rs.getInt("id"),rs.getInt("id_t"),rs.getString("type_tache"),rs.getString("nom_tache"),rs.getTimestamp("date"),rs.getInt("etat_du_tache")));
        }
    } catch (SQLException ex) {
        System.out.println("ereur listerDtails"); 
        System.out.println(ex.getMessage());
    }
    /*  for( Tache a : list1)
System.out.println(a.getId()+"/"+a.getType()+"/"+a.getId_v()+"/"+a.getId_b()+"/"+"/"+a.getId_c()+"/"+a.getId_m()+"/"+a.getIdnonnull());
        */
     return list1;
}
public ObservableList<Tache> lister_t_a_mon_programe(Integer x){
     ObservableList<Tache> list1 =FXCollections.observableArrayList();
    String requete = "SELECT id,id_p,id_t,nom_tache,type_tache,date FROM liste_taches where id_p="+x;
    try { 
        PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            list1.add(new Tache(rs.getInt("id"),rs.getInt("id_p"),rs.getInt("id_t"),rs.getString("type_tache"),rs.getString("nom_tache"),rs.getTimestamp("date")));
        }
        
    } catch (SQLException ex) {
        System.out.println("ereur listerles taches a ajouter"); 
        System.out.println(ex.getMessage());
    }
      for( Tache a : list1)
System.out.println(a.getDate().getTime());
        
     return list1;
}
public void tache_termine (Tache t){
  String req="";
    try {
        
        req="UPDATE liste_taches SET etat_du_tache = '1' WHERE id ='"+t.getId()+"'";
        PreparedStatement pst =new Myconnection().cnx.prepareStatement(req);
                
                pst.executeUpdate();
        System.out.println(t.getId());
System.out.println("update fini"); 
    } catch (SQLException ex) {
        System.out.println("erreur update tache"); 
        System.out.println(ex.getMessage());}


} 
public void add_tache (Tache t,int x ){
    try {
        String  requete;
        requete = "INSERT INTO liste_taches (id_p,id_t,date,nom_tache,type_tache) VALUES ("+x+",'"+t.getId()+"',?,'"+t.getNom_tache()+"','"+t.getType_tache()+"')";
        PreparedStatement pst =
                new Myconnection().cnx.prepareStatement(requete);
        pst.setTimestamp(1, t.getDate());
        pst.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("errreur ajour tache");  
        System.out.println(ex.getMessage());
    }
    
    
    
    
}
}