/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import CONNECTION.Myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Kenza
 */
public class CitationService {
    Connection cx = Myconnection.getInstance().getCnx();
    Statement st;
    ObservableList<citation> oc = FXCollections.observableArrayList();

    public void ajouterCitation(citation citation) {

        String rqt = "insert into citations (auteur,text,genre) values(?,?,?)";//précompilé

        try {
            PreparedStatement pst = cx.prepareStatement(rqt);
            //pst.setInt(1, book.getId());
            pst.setString(1, citation.getAuteur());
            pst.setString(3, citation.getGenre());
            pst.setString(2, citation.getText());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERREUR AJOUT");
            System.out.println(ex.getMessage());
        }

    }

    public void modifieCitation(citation citation) {
        try {
            String req = "update citations set id=?,auteur=?,text=?,genre=? where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
            pst.setInt(1, citation.getId());
            pst.setString(2, citation.getAuteur());
            pst.setString(4, citation.getGenre());
            pst.setString(3, citation.getText());
            pst.setInt(8, citation.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR UPDATE");
            System.out.println(ex.getMessage());
        }
    }

    public void modifiAuteur(int id, String auteur) {
        try {
            String req = "update citations set auteur=? where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
            pst.setString(1, auteur);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR UPDATE Auteur");
            System.out.println(ex.getMessage());
        }
    }

    public void modifiText(int id, String text) {
        try {
            String req = "update citations set text=? where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
            pst.setString(1, text);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR UPDATE TEXT");
            System.out.println(ex.getMessage());
        }
    }

    public void modifiGenre(int id, String genre) {
        try {
            String req = "update citations set genre=? where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
            pst.setString(1, genre);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR UPDATE GENRE");
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerCitation(int id) {
        try {
            String req = "delete from citations where Id =?";
            PreparedStatement ps = cx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERREUR SUPPRESSION");
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList afficherCitation() {

        String req = " select * from citations ";
        Statement st;
        try {
            st = cx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                citation e = new citation(1, "", "", "");
                e.setId(rs.getInt(1));
                e.setAuteur(rs.getString(2));
                e.setText(rs.getString(3));
                e.setGenre(rs.getString(4));
                oc.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("ERREUR AFFICHAGE");
            System.out.println(ex.getMessage());
        }
        return oc;
    }
     public static boolean textAlphabet(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isAlphabet = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[a-z A-Z]+")) {
            isAlphabet = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        System.out.println(inputTextField.getText().matches("[a-z A-Z]"));
        return isAlphabet;

    }

    public static boolean textFieldIsNull(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isNull = false;
        String validationString = null;
        //point out difference between null and isEmpty() *FIND OUT WHEN TO USE NULL
        if (inputTextField.getText().isEmpty()) {
            isNull = true;
            validationString = validationText;
        }
        String isEmpty = Boolean.toString(inputTextField.getText().isEmpty());
        String nil = Boolean.toString(inputTextField.getText() == null);
        inputLabel.setText(validationString);
        return isNull;
    }
    
}
