/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.video;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import CONNECTION.Myconnection;

/**
 *
 * @author SAFA
 */
public class videoCRUD {

    public void addVideo(video c) {
        try {
            String requete = "INSERT INTO video (titre,genre,Nb_likes,Nb_dislikes,Videopath) "
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);

            pst.setString(1, c.getTitre());
            pst.setString(2, c.getGenre());
            pst.setInt(3, c.getNb_likes());
            pst.setInt(4, c.getNb_dislikes());
            pst.setString(5,c.getVideoPath());


            pst.executeUpdate();
            System.out.println("Video ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(videoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//
    public static ObservableList<video> showvideo() {
        ObservableList<video> listv = FXCollections.observableArrayList();
        String requete = "select * from video";

        try {
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                listv.add(new video(rs.getInt("id_v"), rs.getString("titre"), rs.getString("genre"),rs.getString("Videopath"),rs.getInt("nb_likes"),rs.getInt("nb_dislikes"),rs.getInt("mailSent")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(videoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listv;
    }

    public static void modMailSentChange1(int id) {
        try {
            String requete = "update video set mailSent= 1 where id_v = ?";
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);

            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("coach notifié!");
        } catch (SQLException ex) {
            Logger.getLogger(videoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void modMailSentChange0(int id) {
        try {
            String requete = "update video set mailSent= 0 where id_v = ?";
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(videoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modVideo(video v) {
        try {
            String requete = "update video set titre = ?,genre = ?, nb_likes = ?, nb_dislikes= ?,Videopath= ? where id_v = ?";
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);

            pst.setString(1, v.getTitre());
            pst.setString(2, v.getGenre());
            pst.setInt(3, v.getNb_likes());
            pst.setInt(4, v.getNb_dislikes());
             pst.setString(5,v.getVideoPath());
            pst.setInt(6, v.getId());
               
            
            pst.executeUpdate();
            System.out.println("Video Modifié!");
        } catch (SQLException ex) {
            Logger.getLogger(MusicCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void suppVideo(int id) {
        try {
            String requete = "delete from video where id_v = ?";
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);

            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Video Supprimée!");
        } catch (SQLException ex) {
            Logger.getLogger(MusicCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<video> rechVideo(String titre) {
        ObservableList<video> listv = FXCollections.observableArrayList();
        String requete = "select * from video where titre like '%" + titre + "%'";

        try {
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                listv.add(new video(rs.getInt("id_v"), rs.getString("titre"), rs.getString("genre"),rs.getString("VideoPath"), rs.getInt("nb_likes"), rs.getInt("nb_dislikes"), rs.getInt("mailSent")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(videoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listv;
    }
}
