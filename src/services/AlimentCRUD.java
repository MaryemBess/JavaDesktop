/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Aliment;
import CONNECTION.Myconnection;
import Entity.Regime;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class AlimentCRUD {
     public void addAliment(Aliment Al){
        String requete="INSERT INTO aliment (libelle,recette,calorie,gras,carbs)"+"VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
            pst.setString(1, Al.getLibelle());
            pst.setString(2, Al.getRecette());
            pst.setFloat(3, Al.getCalorie());
            pst.setFloat(4, Al.getCarbs());
            pst.setFloat(5, Al.getGras());
            
            pst.executeUpdate();
            System.out.println("Aliment ajoutéee!");
        } catch (SQLException ex) {
            Logger.getLogger(AlimentCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme au niveau de CRUD");
        }
        
    
}
     public void ModifierAliment(Aliment Al){
         try {
             int id_a = Al.getId_aliment();
             
             String requete="UPDATE Aliment SET "
                     + "Libelle = ?,"
                     + "recette=?,"
                     + "calorie = ?,"
                     + "gras = ?,"
                     + "carbs = ?"
                     + "Where id_aliment = ?";
             PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
             pst.setString(1,Al.getLibelle());
             pst.setString(2,Al.getRecette());
             pst.setFloat(3,Al.getCalorie());
             pst.setFloat(4,Al.getGras());
             pst.setFloat(5,Al.getCarbs());
             pst.setInt(6,id_a);
           
              pst.executeUpdate();
         } catch (SQLException ex) {
             System.out.println("--------------------------------------------");
             System.out.println("erreur au niveau de modification de aliment");
             Logger.getLogger(AlimentCRUD.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("--------------------------------------------");
         }
         System.out.println("modification avec succ");         
         
    }
    public ObservableList<Aliment> lister(){
        
         ObservableList<Aliment> data = FXCollections.observableArrayList();
            //String requete="SELECT id_aliment,libelle,recette,calories,gras,carbs FROM ALIMENT";
            String requete="SELECT * FROM ALIMENT";
            try {
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                data.add (new Aliment(rs.getInt("id_aliment"),rs.getString("libelle"),rs.getString("recette"),rs.getFloat("calorie"),rs.getFloat("gras"),rs.getFloat("carbs")));
                
            }
             System.out.println("getAll cart");
            
           
            System.out.println("Aliment lister");
            
        } catch (SQLException ex) {
            Logger.getLogger(AlimentCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme au niveau de CRUD");
            
        }
            return data;
    }
    public void supprimerAliment(Aliment Al){
         try {
             int id = Al.getId_aliment();
            
             String req="DELETE FROM aliment where id_aliment = ?";
             PreparedStatement pt = new Myconnection().cnx.prepareStatement(req);
             pt.setInt(1,id);
           
              pt.executeUpdate();
         } catch (SQLException ex) {
             System.out.println("--------------------------------------------");
             System.out.println("erreur au niveau de suppression de aliment");
             Logger.getLogger(AlimentCRUD.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("--------------------------------------------");
         }
         System.out.println("suppression avec succ");         
         
    }
    public int addAlimentID(Aliment Al){
       int generatedKey = 0;
        String requete="INSERT INTO aliment (libelle,recette,calorie,gras,carbs)"+"VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, Al.getLibelle());
            pst.setString(2, Al.getRecette());
            pst.setFloat(3, Al.getCalorie());
            pst.setFloat(4, Al.getCarbs());
            pst.setFloat(5, Al.getGras());
            
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            
            if (rs.next()) {
            generatedKey = rs.getInt(1);
                         }System.out.println("Generateed key -----------"+generatedKey);
                         
            return generatedKey;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AlimentCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme au niveau de CRUD");
        }
         return 0;
    }}
