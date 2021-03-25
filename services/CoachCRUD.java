/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.coach;
import tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * @acthor ascs
 */
public class CoachCRUD {

    public void addCoach(coach c) {
        try {
            String requete = "INSERT INTO coach (nom,prenom,nb_likes,nb_dislikes,image,rating) "
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setInt(3, c.getNb_likes());
            pst.setInt(4, c.getNb_dislikes());
            pst.setString(5, c.getImage());
            pst.setDouble(6, c.getRating());

            pst.executeUpdate();
            System.out.println("Coach ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(CoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<coach> showcoach() {
        ObservableList<coach> list = FXCollections.observableArrayList();
        String requete = "select * from coach";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new coach(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),rs.getInt("nb_likes"),rs.getInt("nb_dislikes"), rs.getString("image"),rs.getDouble("rating")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        public static ObservableList<coach> showTopCoachs() {
        ObservableList<coach> list = FXCollections.observableArrayList();
        String requete = "select * from coach order by rating desc";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new coach(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),rs.getInt("nb_likes"),rs.getInt("nb_dislikes"), rs.getString("image"),rs.getDouble("rating")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void modCoach(coach c) {
        try {
            String requete = "update coach set nom = ?,prenom = ?, nb_likes = ?, nb_dislikes= ?, image= ? where id = ?";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setInt(3, c.getNb_likes());
            pst.setInt(4, c.getNb_dislikes());
            pst.setString(5, c.getImage());
            pst.setInt(6, c.getId());
            pst.executeUpdate();
            System.out.println("Coach Modifié!");
        } catch (SQLException ex) {
            Logger.getLogger(CoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void suppCoach(int id) {
        try {
            String requete = "delete from coach where id = ?";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println("Coach Supprimé!");
        } catch (SQLException ex) {
            Logger.getLogger(CoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<coach> rechCoach(String nom) {
        ObservableList<coach> list = FXCollections.observableArrayList();
        String requete = "select * from coach where nom like '%" + nom + "%'";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            //.setString(1, nom);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new coach(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),rs.getInt("nb_likes"),rs.getInt("nb_dislikes"), rs.getString("image"),rs.getDouble("rating")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
