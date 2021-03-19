/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CONNECTION.DataSource;
import Entity.Client;

import Entity.User;
import static Services.Service_Admin.currentUser;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;



/**
 *
 * @author Maryem
 */
public class Service_Client implements CRUD<Client>{
    
    private PreparedStatement pst = null;
    private PreparedStatement pst2 = null;
    private final DataSource conn;
    private String sql;
    ResultSet rs;
      public static User currentUser = new User();
    public Service_Client() throws SQLException{
        conn = DataSource.getInstance();
    }
    @Override
    public int insert(Client i) {
          String md5 = getMd5(i.getPassword());
       
        String requete = "insert into user (id,username,username_canonical,email,email_canonical,password,roles,dateNai,adresse,tel,confirmation_token) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            pst =conn.getCnx().prepareStatement(requete);
            pst.setString(1, i.getUsername());
            pst.setString(2, i.getEmail());
            pst.setString(3, md5);
            pst.setInt(4, i.getId());
            pst.setString(5, i.getTel());
             pst.setDate(6, (java.sql.Date) i.getBirthDate());
            
            pst.setString(7, i.getAdresse());
            
            pst.setInt(8, 1);
            pst.setInt(9, 0);
            pst.setFloat(10, 0);
 
            pst.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }


    @Override
    public int SingIn(Client si1) {
               try {
            pst = conn.getCnx().prepareStatement("SELECT * FROM user WHERE  email = ? and password = ? and roles=? ");
            pst.setString(1, si1.getEmail());
            pst.setString(2, si1.getPassword());
           pst.setInt(3, 2);
            rs = pst.executeQuery();
     if (rs != null) {
                while (rs.next()) {
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getString(3));
                    System.out.println(rs.getString(2));
                 
                    currentUser.setId(1);
                    currentUser.setUsername(rs.getString(2));
                currentUser.setEmail(rs.getString(4));
                currentUser.setRoles(rs.getString(12));
                
                    TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
        
            tray.setAnimationType(type);
            tray.setTitle("LOGGED IN");
            tray.setMessage("WELCOME Mr "+""+currentUser.getUsername());
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.dismiss();
            tray.showAndDismiss(Duration.millis(3000));
                 
                   
                    return 1;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
      return 0;
    }

    @Override
    public int VerifierCompte(Client verif, String code) {
          try {
            String requete = "SELECT * FROM user Where password =? and roles= ? and email = ?  ";
            pst = conn.getCnx().prepareStatement(requete);
            pst.setString(1, verif.getPassword());
            pst.setInt(2, 2);
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
                        pst2 = conn.getCnx().prepareStatement(req);
                        pst2.setInt(1, 1);
                        pst2.setInt(2, idU);
                        pst2.executeUpdate();
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
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    /**
     *
     * @return
     */
   
    public ArrayList<User> getshow() {
             Statement ste = null;  
        ArrayList<User> A = new ArrayList<>();
        String req = "SELECT * FROM `user`where role=2";
        System.out.println(req);
        try {
            rs = ste.executeQuery(req);
            System.out.println(rs);
            if (rs != null) {
                while (rs.next()) {
                    User f = new User();
                    f.setUsername(rs.getString(1));
                    f.setEmail(rs.getString(2));
                    f.setRoles(rs.getString(12));
                    f.setTel(rs.getString(5));

                    
                    A.add(f);

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        return A;
    }
      public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Service_Client.currentUser = currentUser;
    }
    
}
