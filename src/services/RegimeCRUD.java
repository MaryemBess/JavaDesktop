/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.mysql.jdbc.Statement;
import Entity.Aliment;
import Entity.Regime;
import CONNECTION.Myconnection;
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
public class RegimeCRUD {
    public void addregime(Regime Re, ObservableList<Aliment> als,int id){
                  ObservableList<Aliment> listA = FXCollections.observableArrayList();
            Regime rh = Re;
     String requete="INSERT INTO Regime (description,duration)"+"VALUES (?,?)";
        try {
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, Re.getDescription());
            pst.setInt(2, Re.getDuration());
            
            
            pst.executeUpdate();
            System.out.println("Regime ajoutéee!");
            ResultSet rs = pst.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
            generatedKey = rs.getInt(1);
            rh.setId_regime(generatedKey);
           
                            }
            listA= ApiAliment(rh, als);
            
            
            for( Aliment add:listA){
                System.out.println("contenu list A"+add.getLibelle()+add.getGras());
                int identidfiant = add.getId_aliment();
                String requete2="INSERT INTO regime_aliment (id_aliment,id_regime)"+"VALUES (?,?)";
                PreparedStatement pst2 = new Myconnection().cnx.prepareStatement(requete2);
                
                
                pst2.setInt(1, identidfiant);
                 pst2.setInt(2, generatedKey);
            
                 pst2.executeUpdate();
                
            
            }
 
            
        } catch (SQLException ex) {
            Logger.getLogger(AlimentCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme au niveau de CRUD Regime addRegime");
        }
        
        
    }
    
    public void Supprimerreg(int idre){
     String requete="DELETE from regime_aliment where id_regime = ?";
             
        try {
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
            
            pst.setInt(1, idre);
            
            
            pst.executeUpdate();
            System.out.println("aliment de regime supprimer!");
            
             String requete2="DELETE from regime where id_regime = ?";
             PreparedStatement pst2 = new Myconnection().cnx.prepareStatement(requete2);
             pst2.setInt(1, idre);
             pst2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlimentCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme au niveau de CRUD Regime addRegime");
        }
    }
    
        public ObservableList<Aliment> lister(int id){
        System.out.println("1");
        ObservableList<Aliment> data2 = FXCollections.observableArrayList();
         List<Integer> data = new ArrayList<>();
            //String requete="SELECT id_aliment,libelle,recette,calories,gras,carbs FROM ALIMENT";
            String requete="SELECT id_aliment FROM regime_aliment WHERE id_regime = ?";
            try {
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
            pst.setInt(1, id);
                System.out.println("2");
            ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                data.add(rs.getInt("id_aliment"));
                
            }
                System.out.println("3");
             System.out.println("aliment de regime recuperer");
            
            //String requete="SELECT id_aliment,libelle,recette,calories,gras,carbs FROM ALIMENT";
            String requete2="SELECT * FROM ALIMENT WHERE id_aliment = ? ";
                System.out.println("4");
                PreparedStatement pst2 = new Myconnection().cnx.prepareStatement(requete2);
            for(int i =0;i<data.size();i++) {
           
           pst2.setInt(1,data.get(i));
                System.out.println("-----------");
                System.out.println(data.get(i));
                ResultSet rs2 = pst2.executeQuery();
            while(rs2.next()){
                data2.add(new Aliment(rs2.getInt("id_aliment"),rs2.getString("libelle"),rs2.getString("recette"),rs2.getFloat("calorie"),rs2.getFloat("gras"),rs2.getFloat("carbs")));
                }}
           
            
        } catch (SQLException ex) {
            Logger.getLogger(AlimentCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme au niveau de CRUD Lister Regime");
            
        }
            return data2;
    }
    
    public Regime getRegime(int id){
       Regime r = new Regime();
        try {
            
            String requete="SELECT * FROM Regime where id_regime = ?";
            
            
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
            
            pst.setInt(1, id);
            
            
            ResultSet rs = pst.executeQuery();
                while(rs.next()){

                    
                    r.setDescription(rs.getString("description"));
                    r.setDuration(rs.getInt("duration"));
                }
            
            System.out.println("Regime ");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(RegimeCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
        
        
        return r; 
            
    }
    
    public ObservableList<Regime> listerAll(){
        
         ObservableList<Regime> data = FXCollections.observableArrayList();
            //String requete="SELECT id_aliment,libelle,recette,calories,gras,carbs FROM ALIMENT";
            String requete="SELECT * FROM REGIME";
            try {
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                data.add (new Regime(rs.getInt("id_regime"),rs.getString("description"),rs.getInt("duration")));
                
            }
             System.out.println("getAll cart");
            
           
            System.out.println("Aliment lister");
            
        } catch (SQLException ex) {
            Logger.getLogger(AlimentCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme au niveau de CRUD");
            
        }
            return data;
    }
    
    public void ModifierReg(Regime rg){
  
        try {
            String requete=" UPDATE regime SET "
                    + " description = ?,"
                    + " duration= ?"
                    + " Where id_regime = ?";
            System.out.println("1 crud");
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
            System.out.println("2 crud");
            pst.setString(1,rg.getDescription());
            System.out.println("3 crud");
            pst.setInt(2,rg.getDuration());
            System.out.println(" 4 crud");
            pst.setInt(3,rg.getId_regime());
            System.out.println("5 crud");
            pst.executeUpdate();
            System.out.println("6 crud");
            
            System.out.println("modification avec succ regime");    
        } catch (SQLException ex) {
            Logger.getLogger(RegimeCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
}


    public void SupprimerAlimentRegime(ObservableList<Aliment> als,Regime rg){
         try {
            String requete=" DELETE FROM regime_aliment "
                    + " Where id_regime = ?"
                    +" AND id_aliment = ? ";
            System.out.println("1 crud");
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
             for( Aliment add:als){
                
                pst.setInt(1,rg.getId_regime());
                pst.setInt(2,add.getId_aliment());
                 pst.executeUpdate();
                
            
            }
             
            
            System.out.println("modification avec succ regime");    
        } catch (SQLException ex) {
            Logger.getLogger(RegimeCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
     public void ajouterAlimentRegime(ObservableList<Aliment> als,Regime rg){
                   ObservableList<Aliment> listA = FXCollections.observableArrayList();
                   
         try {
            String requete="INSERT INTO regime_aliment (id_aliment,id_regime)"+"VALUES (?,?)";
            System.out.println("1 crud");
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
            listA = ApiAliment(rg, als);
             for( Aliment add:listA){
                
                 pst.setInt(1,add.getId_aliment());
                pst.setInt(2,rg.getId_regime());
                
                 pst.executeUpdate();
                
            
            }
             
            
            System.out.println("modification ajout aliment regime avec succ regime");    
        } catch (SQLException ex) {
            Logger.getLogger(RegimeCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
     
      public ObservableList ApiAliment(Regime Re, ObservableList<Aliment> als){
         int id_a;
          ObservableList<Aliment> listA = FXCollections.observableArrayList();
          ObservableList<Aliment> listb = FXCollections.observableArrayList();
  
          AlimentCRUD ac = new AlimentCRUD();
          listA = ac.lister();
          if(listA.containsAll(als)){
              System.out.println("aliment existe");
          }
          else{
                listb=als;
              for(Aliment a:listb){
                  if(listA.contains(a)){System.out.println("existe 2 aliment");}
                  else{
                  id_a=ac.addAlimentID(a);
                  a.setId_aliment(id_a);
                      System.out.println(a.getId_aliment()+"----++99");} 
                  System.out.println("***ii di= ddiid id id id ");
              
              }
              
          }
          return als;
        
        
    }

}