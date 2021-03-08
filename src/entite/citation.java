/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author Kenza
 */
public class citation {
     private int Id;
    private String auteur;
    private String text;
    private String genre;

    public citation(String auteur, String text, String genre) {
        this.auteur = auteur;
        this.text = text;
        this.genre = genre;
    }

    public citation(int Id, String auteur,String text) {
        this.Id = Id;
        this.auteur = auteur;
        this.text = text;
    }

    
    public citation(int Id, String auteur, String text, String genre) {
        this.Id = Id;
        this.auteur = auteur;
        this.text = text;
        this.genre = genre;
    }

    public int getId() {
        return Id;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getText() {
        return text;
    }

    public String getGenre() {
        return genre;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.Id;
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
        final citation other = (citation) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return auteur + ":" + text ;
    }
    
    
}
