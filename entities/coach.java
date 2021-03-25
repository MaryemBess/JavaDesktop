/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javax.activation.DataSource;

/**
 *
 * @author asus
 */
public class coach {

   

    int id;
    String nom;
    String prenom;
    int nb_likes;
    int nb_dislikes;
    String image;
    double rating;

    public coach(int id, String nom, String prenom, int nb_likes, int nb_dislikes, String image, double rating) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.nb_likes = nb_likes;
        this.nb_dislikes = nb_dislikes;
        this.image = image;
        this.rating = rating;
    }
    
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNb_likes() {
        return nb_likes;
    }

    public int getNb_dislikes() {
        return nb_dislikes;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNb_likes(int nb_likes) {
        this.nb_likes = nb_likes;
    }

    public void setNb_dislikes(int nb_dislikes) {
        this.nb_dislikes = nb_dislikes;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "coach{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", nb_likes=" + nb_likes + ", nb_dislikes=" + nb_dislikes + ", image=" + image + ", rating=" + rating + '}';
    }

    
    }
