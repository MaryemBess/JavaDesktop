/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONNECTION;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MaryemBessrour
 */
public class Myconnection {
    public Connection cnx;
        String url="jdbc:mysql://localhost:3306/meliora";
        String login="root";
        String pwd="";
        static Myconnection ds;

    public Myconnection() {
            try {
                cnx= DriverManager.getConnection(url,login, pwd);
                System.out.println("Connection avec succes");
            } catch (SQLException ex) {
                Logger.getLogger(Myconnection.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public static Myconnection getInstance(){
        if(ds==null)
        {
            ds= new Myconnection();
        }
        
        return ds;
    }

    public Connection getCnx() {
        return cnx;
    }

    public Connection getConnect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
