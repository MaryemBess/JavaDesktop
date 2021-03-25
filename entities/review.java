/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus
 */
public class review {
    int id;
    int id_coach;
    double rating;
    String etat;
    String comment;

    public review(int id, int id_coach, double rating, String etat, String comment) {
        this.id = id;
        this.id_coach = id_coach;
        this.rating = rating;
        this.etat = etat;
        this.comment = comment;
    }
    
    public review(int id_coach, double rating, String etat, String comment) {
        this.id_coach = id_coach;
        this.rating = rating;
        this.etat = etat;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public int getId_coach() {
        return id_coach;
    }

    public double getRating() {
        return rating;
    }

    public String getEtat() {
        return etat;
    }

    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "review{" + "id=" + id + ", id_coach=" + id_coach + ", rating=" + rating + ", etat=" + etat + ", comment=" + comment + '}';
    }
    
    
}
