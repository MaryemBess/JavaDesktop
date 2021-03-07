/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import entite.e_book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Kenza
 */
public class mycnxsql {
   
    Connection conn = null;
    
    public static Connection ConnectDb()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/meliora","root","");
            JOptionPane.showMessageDialog(null,"connection eetabliire");
            return conn ;          
        }catch(Exception e){ JOptionPane.showMessageDialog(null, e);
        return null;
             
        }
    
    
    }
    public static ObservableList<e_book> gete_book()
    {
    Connection conn= ConnectDb();
    ObservableList<e_book> list = FXCollections.observableArrayList();
        try{
        PreparedStatement ps= conn.prepareStatement("select  * from e_books");
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
            list.add(new e_book(Integer.parseInt(rs.getString("id")), rs.getString("auteur"), rs.getString("titre"), rs.getString("genre"), rs.getInt("evaluation"), rs.getInt("id_c"), rs.getInt("favoris")));
        }
        }catch(Exception e){ }
     return list;
    }
    
    
}

