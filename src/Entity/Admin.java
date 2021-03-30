/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import CONNECTION.Myconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Maryem
 */
public class Admin extends User {
    public Admin (){};

    public Admin(String mail, String password) {
        super(mail, password);
    }

    public Admin(int idU, String name) {
        super(idU, name);
    }
     public Admin(int idU, String name,String role) {
        super(idU, name,role);
    }
     

    @Override
    public String toString() {
        return "Admin{" + '}';
    }
     public User Session(User si) throws SQLException {
       
      // DataBaseConnection dc = null;  
     PreparedStatement pst = null;
     Myconnection conn;
     String sql;
    ResultSet rs;
        conn= Myconnection.getInstance();
          pst = conn.getCnx().prepareStatement("SELECT * FROM user WHERE  email = ? and password = ? " );
            pst.setString(1, si.getEmail());
            pst.setString(2, si.getPassword());
           
         
            //   pst.setInt(3, si.getRole());
            rs = pst.executeQuery();
            while (rs.next()) {
            
            User admin = new User( rs.getInt("id"),rs.getString("username"),rs.getString("roles"));
            admin.setId(rs.getInt("idU"));
            admin.setEmail(rs.getString("name"));
            admin.setRoles(rs.getString("role"));
           
          
            return admin;
        }
         
         
         
         
         return null;
         
     }

}
