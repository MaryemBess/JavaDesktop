/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Music;
import entities.favoris;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author SAFA
 */
public class FavorisCRUD {

    public void addFavoris(favoris f) {
        try {
            String requete = "INSERT INTO favoris (id_user,id_music) "
                    + "VALUES (?,?)";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setInt(1, f.getId_user());
            pst.setInt(2, f.getId_music());

            pst.executeUpdate();
            System.out.println("Music ajouté au favoris!");
        } catch (SQLException ex) {
            Logger.getLogger(FavorisCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void suppFavoris(int id) {
        try {
            String requete = "delete from favoris where id = ?";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);

            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Music Supprimée du favoris!");
        } catch (SQLException ex) {
            Logger.getLogger(FavorisCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        public static ObservableList<favoris> filterFavoris(int id) {
        ObservableList<favoris> list = FXCollections.observableArrayList();
        String requete = "select * from favoris where id_coach = ?";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new favoris(rs.getInt("nombre"), rs.getInt("titre"), rs.getInt("genre")));
            }

            System.out.println("favorite list filtered by coach id!");
        } catch (SQLException ex) {
            Logger.getLogger(FavorisCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static ObservableList<Music> showFavoris(ObservableList<favoris> fav) {
        ObservableList<Music> list = FXCollections.observableArrayList();
        String requete = "select * from music where nombre in (";
        for (favoris f : fav){
            requete +=f.getId_music()+",";
        }
        requete.substring(-1);
        requete+=")";

        try {
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Music(rs.getInt("id"), rs.getString("i"), rs.getString("genre"),rs.getString("zzz"),rs.getString("ss"), rs.getString("image")));
            }

            System.out.println("favorite list shown!");
        } catch (SQLException ex) {
            Logger.getLogger(MusicCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
