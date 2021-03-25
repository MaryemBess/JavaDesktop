/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.coach;
import entities.review;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author asus
 */
public class reviewCRUD {

    public void updateCoachRating(int id) {
        ObservableList<review> listRating = showReviews(id);
        double rating = listRating.stream().mapToDouble(e -> e.getRating()).average().getAsDouble();
        System.out.println(rating);

        String requete = "update coach set rating = ? where id = ?";
        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setDouble(1, rating);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(reviewCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("rating updated");

    }

    public static ObservableList<review> showReviews(int id) {
        ObservableList<review> list = FXCollections.observableArrayList();
        String requete = "select * from review where id_coach = ?";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new review(rs.getInt("id"), rs.getInt("id_coach"), rs.getDouble("rating"), rs.getString("etat"), rs.getString("comment")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(reviewCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void addReview(review r) {
        try {
            String requete = "INSERT INTO review (id,id_coach,rating,etat,comment) "
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setInt(1, r.getId());
            pst.setInt(2, r.getId_coach());
            pst.setDouble(3, r.getRating());
            pst.setString(4, r.getEtat());
            pst.setString(5, r.getComment());

            pst.executeUpdate();
            System.out.println("Review ajouté ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(CoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<review> showReview() {
        ObservableList<review> list = FXCollections.observableArrayList();
        String requete = "select * from review";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new review(rs.getInt("id"), rs.getInt("id_coach"), rs.getDouble("rating"), rs.getString("etat"), rs.getString("comment")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoachCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
