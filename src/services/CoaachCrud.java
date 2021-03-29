/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import CONNECTION.Myconnection;
import Entity.Coaach;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class CoaachCrud {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    public void addCoach(Coaach c) {
        try {
            String requete = "INSERT INTO coach (nom, prenom, email, password, tel, adresse, image, rating,date) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);

            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setString(3, c.getEmail());
            pst.setString(4, c.getPassword());
            pst.setInt(5, c.getTel());
            pst.setString(6, c.getAdresse());
            pst.setString(7, c.getImage());
            pst.setDouble(8, c.getRating());
            pst.setDate(9, c.getDateb());

            pst.executeUpdate();
            System.out.println("Coach ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(CoaachCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static int GetNbCoach(int id){
//        String requete = "select * from ";
//
//    }
    public static ObservableList<Coaach> showcoachEnd() {
        ObservableList<Coaach> list = FXCollections.observableArrayList();
        String requete = "select * from coach";

        try {
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Coaach(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getInt("tel"), rs.getString("adresse"), rs.getString("image"), rs.getDouble("rating"), rs.getDate("date")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoaachCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static ObservableList<Coaach> showTopCoachs() {
        ObservableList<Coaach> list = FXCollections.observableArrayList();
        String requete = "select * from coach order by rating desc";

        try {
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Coaach(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getInt("tel"), rs.getString("adresse"), rs.getString("image"), rs.getDouble("rating"), rs.getDate("date")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoaachCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void modCoach(Coaach c) {
        try {
            String requete = "update coach set nom = ?,prenom = ?, email = ?, tel= ?, adresse= ?,image= ? where id = ?";
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);

            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setString(3, c.getEmail());
            pst.setInt(4, c.getTel());
            pst.setString(5, c.getAdresse());
            pst.setString(6, c.getImage());
            pst.setInt(7, c.getId());
            pst.executeUpdate();
            System.out.println("Coach Modifié!");
        } catch (SQLException ex) {
            Logger.getLogger(CoaachCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void suppCoach(int id) {
        try {
            String requete = "delete from coach where id = ?";
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);

            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println("Coach Supprimé!");
        } catch (SQLException ex) {
            Logger.getLogger(CoaachCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<Coaach> rechCoach(String nom) {
        ObservableList<Coaach> list = FXCollections.observableArrayList();
        String requete = "select * from coach where nom like '%" + nom + "%'";

        try {
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);
            //.setString(1, nom);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Coaach(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getInt("tel"), rs.getString("adresse"), rs.getString("image"), rs.getDouble("rating"), rs.getDate("date")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoaachCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static Coaach getCoach(int id) {
        Coaach c = new Coaach();
        String requete = "select * from coach where id = ?";

        try {

            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c = new Coaach(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getInt("tel"), rs.getString("adresse"), rs.getString("image"), rs.getDouble("rating"), rs.getDate("date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoaachCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

}
