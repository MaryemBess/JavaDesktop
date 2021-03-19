/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CONNECTION.DataSource;
import Entity.Admin;

import Entity.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;



/**
 *
 * @author Maryem
 */
public class Service_Admin implements CRUD<Admin>{
    private Statement ste;
    private PreparedStatement pst = null;
    private DataSource conn;
    private String sql;
    ResultSet rs;
        public static User currentUser = new User();
    
    public Service_Admin() throws SQLException{
        conn = DataSource.getInstance();
    }
    @Override
    public int insert(Admin i) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
    
  
    public int SingIn(Admin si) {
        try {
            
            pst = conn.getCnx().prepareStatement("SELECT * FROM user WHERE  email = ? and password = ? ");
            pst.setString(1, si.getEmail());
            pst.setString(2, si.getPassword());
         
            //pst.setInt(3, 1);
            rs = pst.executeQuery();
     if (rs != null) {
                while (rs.next()) {
                    System.out.println(rs.getInt(6));
                    System.out.println(rs.getString(1));
                    System.out.println(rs.getString(2));
                 currentUser.setPassword(rs.getString(8));
                    //currentUser.setIdU(rs.getInt(4));
                currentUser.setUsername(rs.getString(2));
                currentUser.setEmail(rs.getString(4));
                currentUser.setRoles(rs.getString(12));
                //currentUser.setDesactivate(rs.getInt(13));
                currentUser.setBirthDate(rs.getDate(14));
                //currentUser.setImage(rs.getString(10));
                
                  
                    TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
        
            tray.setAnimationType(type);
            tray.setTitle("LOGGED IN");
            tray.setMessage("WELCOME "+""+currentUser.getUsername());
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.dismiss();
            tray.showAndDismiss(Duration.millis(3000));
                 
                   
                    return 1;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Service_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

      return 0;
    }

    @Override
    public int VerifierCompte(Admin verif, String code) {
          try {
            String requete = "SELECT * FROM user Where password =? and roles= ? and email = ?  ";
            pst = conn.getCnx().prepareStatement(requete);
            pst.setString(1, verif.getPassword());
            pst.setInt(2, 1);
            pst.setString(3, verif.getEmail());
          

            rs = pst.executeQuery();

            boolean v = rs.next();

            if (v == true) {
                if ((rs.getString(10).equals(code)) == false) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Account not verified "
                    + "\n code is not correct");
            alert.showAndWait();
                    return 0;
                } else {
                    int idU = rs.getInt("id");
                    System.out.println(idU);
                    String req = "UPDATE user set enabled=? where id=? ";
                    try {
                        pst = conn.getCnx().prepareStatement(req);
                        pst.setInt(1, 1);
                        pst.setInt(2, idU);
                        pst.executeUpdate();
                          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("Account has been verified");
            alert.showAndWait();
                        return 1;
                    } catch (SQLException ex) {
                        Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
          return 0;
    
    }

    
    

  

       

   
    @Override
    public String getMd5(String input) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }

   
   
   

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Service_Admin.currentUser = currentUser;
    }

     
    
}
