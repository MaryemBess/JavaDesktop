/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Music;
import entities.favoris;
import tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @acthor ascs
 */
public class MusicCRUD {

    public void addMusic(Music m) {
        try {
            String requete = "INSERT INTO musique (titre,genre,Artiste,MusicPath) "
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setString(1, m.getTitre());
            pst.setString(2, m.getGenre());
            pst.setString(3, m.getArtiste());
            pst.setString(4, m.getMusicPath());

            pst.executeUpdate();
            System.out.println("Music ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(MusicCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<Music> showmusic() {
        ObservableList<Music> list = FXCollections.observableArrayList();
        String requete = "select * from musique";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Music(rs.getInt("nombre"), rs.getString("titre"), rs.getString("genre"), rs.getString("Artiste"),rs.getString("MusicPath"), rs.getString("image")));
            }

            System.out.println("Music Ajoutée!");
        } catch (SQLException ex) {
            Logger.getLogger(MusicCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void modMusic(Music C) {
        try {
            String requete = "update musique set titre = ?,genre = ?,Artiste = ?,Musicpath = ? where nombre = ?";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setString(1, C.getTitre());
            pst.setString(2, C.getGenre());
            pst.setString(3, C.getArtiste());
            pst.setString(4, C.getMusicPath());
            pst.setInt(5, C.getNombre());

            pst.executeUpdate();
            System.out.println("Music Modifiée!");
        } catch (SQLException ex) {
            Logger.getLogger(MusicCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void suppMusic(int id) {
        try {
            String requete = "delete from musique where nombre = ?";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Music Supprimée!");
        } catch (SQLException ex) {
            Logger.getLogger(MusicCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<Music> rechMusics(String titre) {
        ObservableList<Music> list = FXCollections.observableArrayList();
        String requete = "select * from musique where titre like '%" + titre + "%'";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                list.add(new Music(Integer.parseInt(rs.getString("nombre")), rs.getString("titre"), rs.getString("genre"), rs.getString("Artiste"),rs.getString("MusicPath"), rs.getString("image")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(MusicCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
