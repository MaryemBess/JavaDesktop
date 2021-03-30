/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.activation.DataSource;

/**
 *
 * @author asus
 */
public class Music  {
    private int nombre;
    private String Titre;
    private String Genre;
    private String Artiste;
    private String MusicPath;
    private String image;

    public Music(int nombre, String Titre, String Genre, String Artiste, String MusicPath, String image) {
        this.nombre = nombre;
        this.Titre = Titre;
        this.Genre = Genre;
        this.Artiste = Artiste;
        this.MusicPath = MusicPath;
        this.image = image;
    }
    
    public int getNombre() {
        return nombre;
    }

    public String getTitre() {
        return Titre;
    }

    public String getGenre() {
        return Genre;
    }

    public String getArtiste() {
        return Artiste;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

  

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }
    
    public void setArtiste(String Artiste) {
        this.Artiste = Artiste;
    }

    public String getMusicPath() {
        return MusicPath;
    }

    public void setMusicPath(String MusicPath) {
        this.MusicPath = MusicPath;
    }

    @Override
    public String toString() {
        return "Music{" + "nombre=" + nombre + ", Titre=" + Titre + ", Genre=" + Genre + ", Artiste=" + Artiste + ", MusicPath=" + MusicPath + ", image=" + image + '}';
    }
    
     
  
    
    
}
    

    

