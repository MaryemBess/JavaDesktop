/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CONNECTION.Myconnection;
import Entity.Reclamation;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author maryem
 */
public class ServiceReclamation {
     Statement ste;
    Connection  conn;
    
     private PreparedStatement pst = null;
     private PreparedStatement pst2 = null;

    private String sql;
    ResultSet rs;
      public static User currentUser = new User();
        public ServiceReclamation() throws SQLException
    {
       conn  = Myconnection.getInstance().getCnx();   
    }
        
        public void ajoutReclam(Reclamation R) throws SQLException {
      
    PreparedStatement pre=conn.prepareStatement
("INSERT INTO `reclamation` (`id`, `sujetReclamation`, `statu`,`descriptionReclamation`, `dateReclamation` ,`id_Client`) VALUES ( NULL, ?, 'en attente', ?,?,5);");
      
    //pre.setInt(1, R.getId());
    pre.setString(1, R.getSujetReclamation());
    pre.setString(2, R.getDescriptionReclamation());
    pre.setString(3, R.getDateReclamation());
    pre.executeUpdate();
    
              JOptionPane.showMessageDialog(null, "Reclamation Ajouté!");

    }
        
         public void modifieReclamation (Reclamation R) throws SQLException {
   
         String req ="update reclamation set descriptionReclamation= ?  WHERE id=?";
         try {
            PreparedStatement pst = conn.prepareStatement(req);
  pst.setString(1, R.getDescriptionReclamation());
  
            System.out.println("Reclamation Modifiée");
              
              
              ste.executeUpdate(req);
          } catch (SQLException ex) {
              Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
     public void supreclam(int id) {
        
          try {
              String req2 =
                      "DELETE FROM reclamation where id=?";    
             
              PreparedStatement ps = 
                      conn.prepareStatement(req2);
              ps.setInt(1, id);
              ps.executeUpdate();
              System.out.println("Reclamation supprimée");
          } catch (SQLException ex) {
            System.out.println(ex.getMessage());
          }
    } 
     
     
     public List<Reclamation> readall() throws SQLException{
         List<Reclamation> rec = new ArrayList<>();
         ste =conn.createStatement();
         ResultSet rs = ste.executeQuery("select sujetReclamation, statu,descriptionReclamation, dateReclamation from reclamtion where id_client=?");
         while(rs.next()){
             String sujetReclamation=rs.getString(1);
             String statu=rs.getString(2);
             String descriptionReclamation=rs.getString(3);
             String dateReclamation=rs.getString(4);
             Reclamation r= new Reclamation(sujetReclamation, statu, descriptionReclamation, dateReclamation);
         rec.add(r);
         }
         return rec;
     }
}
