/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Kenza
 */
public class myconnexion {
     Connection cnx;
        String url="jdbc:mysql://localhost:3306/meliora";
        String login="root";
        String pwd="";
        static myconnexion ds;

    public myconnexion() {
            try {
                cnx= DriverManager.getConnection(url,login, pwd);
                System.out.println("Connection avec succes");
            } catch (SQLException ex) {
                System.out.println("Erreur de connection");
                System.out.println(ex.getMessage());
            }
    }
    public static myconnexion getInstance(){
        if(ds==null)
        {
            ds= new myconnexion();
        }
        
        return ds;
    }

    public Connection getCnx() {
        return cnx;
    }
    
     }

