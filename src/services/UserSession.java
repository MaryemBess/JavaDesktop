/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CONNECTION.Myconnection;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Maryem
 */
public class UserSession {

    private LocalDate last_login;
    public static User currentUser = new User();
    Connection cnx;
    private Myconnection conn;
    Statement ste;
    ResultSet rs;
    PreparedStatement pst;

    public User login(String email){
        User user = new User();

        try {
            String requete = "select * from user where email='"+email+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            int count = 0;
            while(rs.next()){
                count ++;
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(4));
                user.setEmail(rs.getString(3));
                
                user.setRoles(rs.getString(5));
                user.setTel(rs.getString(9));
                user.setAdresse(rs.getString(8));
            
            
            
            }
            
            System.out.println(count);
            if(count == 0){
                 User a = new User();
                 a.setPassword("");
                return a;
            }else{
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserSession.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public UserSession() {
        this.cnx = Myconnection.getInstance().getCnx();
        try {
            ste = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LocalDate getLast_login() {
        return last_login;
    }

    public void setLast_login(LocalDate last_login) {
        this.last_login = last_login;
    }

    public UserSession UserSessionLogOut() {
        currentUser.setUsername("");
        currentUser.setEmail("");
        currentUser.setId(0);
        return null;
    }
//verifier login lors de la connexion
    public static boolean isLogedIn(User user) {
        if ((user.getEmail() == "" && user.getUsername() == "" && user.getRoles() == "") || (user.getBirthDate() == null  && user.getAdresse() == null)) {
            return false;
        } else {
            return true;
        }
    }
  
//user mawjoud fel base wala le
    public boolean isValidUser(String Email) {
        String req = "SELECT * FROM `user` WHERE email =\'" + Email + "\'";
        try {
            rs = ste.executeQuery(req);
            System.out.println(rs);
            if (rs != null) {
                while (rs.next()) {
                    System.out.println(rs.toString());
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }  
            
  
}