/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import CONNECTION.Myconnection;
import java.sql.ResultSet;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Kenza
 */
public class e_book {
    
    private int Id;
    private String auteur;
    private String titre;
    private String genre;
    private int evaluation;
    private int id_c;
    private int fav;
    private ComboBox citation;
    private String image;
     private String citat;

    public e_book() {
    }

    public e_book(String auteur, String titre, String genre, int id_c, String image) {
        this.auteur = auteur;
        this.titre = titre;
        this.genre = genre;
        this.id_c = id_c;
        this.image = image;
        
    }
public e_book(int Id, String auteur, String titre, String genre, int evaluation, int id_c, int fav, String image, String citat) {
        this.Id = Id;
        this.auteur = auteur;
        this.titre = titre;
        this.genre = genre;
        this.evaluation = evaluation;
        this.id_c = id_c;
        this.fav = fav;
        this.image = image;
        try {
            String req = " select e.id_c,c.id,c.auteur,c.text from e_books e INNER JOIN citations c on e.id_c =c.id";
            ResultSet rs = Myconnection.getInstance().getCnx().createStatement().executeQuery(req);
            
            while (rs.next()) {
                this.citat= rs.getString("auteur")+" : "+ rs.getString("text");
            }
             } catch (Exception ex) {
            System.out.println("ERREUR AFFICHAGE citation table");
            System.out.println(ex.getMessage());
        }
       
    }

  public void setCitat(String citat) {
        this.citat = citat;
    }
 public String getCitat() {
        return citat;
    }
    public e_book(String auteur, String titre, String genre, int evaluation, int id_c, int fav) {
        this.auteur = auteur;
        this.titre = titre;
        this.genre = genre;
        this.evaluation = evaluation;
        this.id_c = id_c;
        this.fav = fav;
//        this.citation = new ComboBox();
    }

    public e_book(int Id, String auteur, String titre, String genre, int id_c, ObservableList data) {
        this.Id = Id;
        this.auteur = auteur;
        this.titre = titre;
        this.genre = genre;
        this.id_c = id_c;
        this.citation = new ComboBox();
    }

    public e_book(int Id, String auteur, String titre, String genre, int evaluation, int id_c, int fav) {
        this.Id = Id;
        this.auteur = auteur;
        this.titre = titre;
//this.citation = new ComboBox();
        this.genre = genre;
        this.evaluation = evaluation;
        this.id_c = id_c;
        this.fav = fav;
    }

    public e_book(String auteur, String titre, String genre, int evaluation, int id_c, int fav, String image) {
        this.auteur = auteur;
        this.titre = titre;
        this.genre = genre;
        this.evaluation = evaluation;
        this.id_c = id_c;
        this.fav = fav;
        this.citation = citation;
        this.image = image;
    }

    public e_book(int Id, String auteur, String titre, String genre, int evaluation, int id_c, int fav, String image) {
        this.Id = Id;
        this.auteur = auteur;
        this.titre = titre;
        this.genre = genre;
        this.evaluation = evaluation;
        this.id_c = id_c;
        this.fav = fav;
        this.image = image;
    }

    public e_book(int Id, String auteur, String titre, String genre, int id_c, String image) {
        this.Id = Id;
        this.auteur = auteur;
        this.titre = titre;
        this.genre = genre;
        this.id_c = id_c;
        this.image = image;
    }

    public int getId() {
        return Id;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getTitre() {
        return titre;
    }

    public String getGenre() {
        return genre;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public int getId_c() {
        return id_c;
    }

    public int getFav() {
        return fav;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public void setCitation(ComboBox citation) {
        this.citation = citation;
    }

    public ComboBox getCitation() {
        return citation;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.Id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final e_book other = (e_book) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "e_book{" + "Id=" + Id + ", auteur=" + auteur + ", titre=" + titre + ", genre=" + genre + ", evaluation=" + evaluation + ", id_c=" + id_c + ", fav=" + fav + '}';
    }

    
}
