/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import CONNECTION.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 *
 * @author Maryem
 */
public class Coach extends User {

    public Coach(String mail, String password) {
        super(mail, password);
    }

    public Coach(int idU, String name,String role) {
        super(idU, name,role);
    }



    @Override
    public String toString() {
        return "Chasseur {" + '}';

    }

    public Coach() {
    }

//    public Coach(int id, String mail, String password, String name, String tel,  String city, String address, int zipCode) {
//        super(id, mail, password, name, tel,  city, address, zipCode);
//    }
     public Coach(int idU, String mail,  String name, String password, String address, String tel) {
        super(idU, mail, password, name, address, tel);
    }

  
        public Coach Session(Coach si1) throws SQLException {
       
      // DataBaseConnection dc = null;  
     PreparedStatement pst = null;
     DataSource conn;
     String sql;
    ResultSet rs;
        conn= DataSource.getInstance();
          pst = conn.getCnx().prepareStatement("SELECT * FROM user WHERE  email = ? and password = ? and roles=?" );
            pst.setString(1, si1.getEmail());
            pst.setString(2, si1.getPassword());
           
         
               pst.setInt(3, 2);
            rs = pst.executeQuery();
            while (rs.next()) {
            
            Coach f = new Coach( rs.getInt("id"),rs.getString("username"),rs.getString("roles"));
            f.setId(rs.getInt("id"));
            f.setEmail(rs.getString("username"));
            f.setRoles(rs.getString("roles"));
           
          
            return f;
        }
         
         
         
         
         return null;
         
     }

    public Coach(int idU, String mail, String password, String name, String tel, Date birthDate, String city, String address, int zipCode, int status, String role, int desactivate, int code) {
        super(idU, mail, password, name, tel, birthDate, city, address, zipCode, status, role, desactivate, code);
    }
    

}
