/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CONNECTION.DataSource;
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

/**
 *
 * @author Maryem
 */
public class ServiceCoach  {
    
    DataSource cnx;
    private Connection con;
    private Statement ste;
 public ServiceCoach () {
        this.cnx = new DataSource();
        try {
            con = DataSource.getInstance().getCnx();
                        ste = con.createStatement();


        } catch (SQLException ex) {
            Logger.getLogger(ServiceCoach.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
   
    /**
     *
     * @param U
     * @throws SQLException
     */
}
  
    
    
 
