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
 * @author maryem
 */
public class Client extends User {
      public Client(String mail, String password) {
        super(mail, password);
    }

    public Client(int id, String name,String role) {
        super(id, name,role);
    }

    @Override
    public String toString() {
        return "Client {" + '}';

    }

    public Client(int idU, String mail, String password, String name, String tel, Date birthDate, String city, String address, int zipCode, int status, String role, int desactivate, int code) {
        super(idU, mail, password, name, tel, birthDate, city, address, zipCode, status, role, desactivate, code);
    }
   

    public Client() {
    }

    public Client(int id, String mail, String password, String name, String tel, Date birthDate, String address) {
        super(id, mail, password, name, tel, birthDate, address);
    }

    public Client(int idU, String mail, String name, String password, String address, String tel) {
        super(idU, mail, name, password, address, tel);
    }
         public Client Session(Client si2) throws SQLException {
       
      // DataBaseConnection dc = null;  
     PreparedStatement pst = null;
     DataSource conn;
     String sql;
    ResultSet rs;
        conn= DataSource.getInstance();
          pst = conn.getCnx().prepareStatement("SELECT * FROM user WHERE  email = ? and password = ? and roles=?" );
            pst.setString(1, si2.getEmail());
            pst.setString(2, si2.getPassword());
            pst.setInt(3, 3);
            rs = pst.executeQuery();
            while (rs.next()) {
            
            Client c = new Client( rs.getInt("id"),rs.getString("username"),rs.getString("roles"));
            c.setId(rs.getInt("id"));
            c.setEmail(rs.getString("name"));
            c.setRoles(rs.getString("role"));
           
          
            return c;
        }
         
         
         
         
         return null;
         
     }
    
}
