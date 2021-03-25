/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.e_book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javax.swing.JOptionPane;
import utils.myconnexion;

/**
 *
 * @author Kenza
 */
public class e_bookCRUD {

    Connection cx = myconnexion.getInstance().getCnx();
    Statement st;
    ObservableList<e_book> oc = FXCollections.observableArrayList();

    public void ajouterBook(e_book book) {

        String rqt = "insert into e_books (auteur,genre,favoris,titre,evaluation,id_c,image) values(?,?,?,?,?,?,?)";//précompilé

        try {
            PreparedStatement pst = cx.prepareStatement(rqt);
            //pst.setInt(1, book.getId());
            pst.setString(1, book.getAuteur());
            pst.setString(2, book.getGenre());
            pst.setInt(3, book.getFav());
            pst.setString(4, book.getTitre());
            pst.setInt(5, book.getEvaluation());
            pst.setInt(6, book.getId_c());
            pst.setString(7, book.getImage());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERREUR AJOUT");
            System.out.println(ex.getMessage());
        }

    }

    public void modifieBook(e_book book) {
        try {
            String req = "update e_books set id=?,auteur=?,genre=?,favoris=?,titre=?,evaluation=?,id_c=? where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
            pst.setInt(1, book.getId());
            pst.setString(2, book.getAuteur());
            pst.setString(3, book.getGenre());
            pst.setInt(4, book.getFav());
            pst.setString(5, book.getTitre());
            pst.setInt(6, book.getEvaluation());
            pst.setInt(7, book.getId_c());
            pst.setInt(8, book.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR UPDATE");
            System.out.println(ex.getMessage());
        }
    }

    public void modifiAuteur(int id, String auteur) {
        try {
            String req = "update e_books set auteur=? where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
            pst.setString(1, auteur);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR UPDATE Auteur");
            System.out.println(ex.getMessage());
        }
    }

    public void modifiTitre(int id, String titre) {
        try {
            String req = "update e_books set titre=? where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
            pst.setString(1, titre);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR UPDATE TITRE");
            System.out.println(ex.getMessage());
        }
    }

    public void modifiGenre(int id, String genre) {
        try {
            String req = "update e_books set genre=? where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
            pst.setString(1, genre);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR UPDATE GENRE");
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerBook(int id_book) {
        try {
            String req = "delete from e_books where Id =?";
            PreparedStatement ps = cx.prepareStatement(req);
            ps.setInt(1, id_book);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERREUR SUPPRESSION");
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList afficherBook() {

        String req = " select * from e_books ";
        Statement st;
        try {
            st = cx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                e_book e = new e_book(1, "", "", "", 1, 1, 1, "");
                e.setId(rs.getInt(1));
                e.setAuteur(rs.getString(2));
                e.setGenre(rs.getString(3));
                e.setFav(rs.getInt(4));
                e.setTitre(rs.getString(5));
                e.setEvaluation(rs.getInt(6));
                e.setId_c(rs.getInt(7));
                e.setImage(rs.getString(8));
                oc.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("ERREUR AFFICHAGE");
            System.out.println(ex.getMessage());
        }
        return oc;
    }

    public ObservableList<e_book> gete_bookListBack() {
        ObservableList<e_book> list = FXCollections.observableArrayList();
        String req = "SELECT * from e_books";
        try {
            st = cx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                e_book r = new e_book(rs.getInt("id"), rs.getString("auteur"), rs.getString("titre"), rs.getString("genre"), rs.getInt("id_c"), rs.getString("image"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("Probléme list PDF");
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public void nombreVue(e_book book) {
        try {
          
            String req = "update e_books set favoris = ( favoris + " + 1 + ") where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
//            int x=book.getFav()+1;
//            pst.setInt(1, x);
            pst.setInt(1, book.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR incrementer nombre de vue");
            System.out.println(ex.getMessage());
        }
//        JOptionPane.showMessageDialog(null, "increment DONE!");
    }
    public ObservableList<PieChart.Data> getDataStat(){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
         String req = "SELECT evaluation, favoris from e_books ";
        try {
            st = cx.createStatement();
            ResultSet rs = st.executeQuery(req);
           while(rs.next()){
               data.add(new PieChart.Data (rs.getString("evaluation"),rs.getInt("favoris")));
           }
        } catch (SQLException ex) {
           System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
        return data;
    }
}
