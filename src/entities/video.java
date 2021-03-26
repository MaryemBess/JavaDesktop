/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author SAFA
 */
public class video {
    int id;
    String Titre;
    String Genre;
    String VideoPath;
    int nb_likes;
    int nb_dislikes;
    int mailSent;
    
    public video(int id ,String Titre, String Genre,String VideoPath,int nb_likes, int nb_dislikes, int mailSent) {
        this.id=id;
        this.Titre = Titre;
        this.Genre = Genre;
        this.VideoPath = VideoPath;
        this.nb_likes = nb_likes;
         this.nb_dislikes = nb_dislikes;
         this.mailSent = mailSent;
    }

    public int getNb_likes() {
        return nb_likes;
    }

    public void setNb_likes(int nb_likes) {
        this.nb_likes = nb_likes;
    }

    public int getNb_dislikes() {
        return nb_dislikes;
    }

    public void setNb_dislikes(int nb_dislikes) {
        this.nb_dislikes = nb_dislikes;
    }

    public int getId() {
        return id;
    }

    public String getVideoPath() {
        return VideoPath;
    }

   

    public String getTitre() {
        return Titre;
    }

    public String getGenre() {
        return Genre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public void setVideoPath(String VideoPath) {
        this.VideoPath = VideoPath;
    }

    public int getMailSent() {
        return mailSent;
    }

    public void setMailSent(int mailSent) {
        this.mailSent = mailSent;
    }

//    @Override
//    public String toString() {
//        return "Vedio{" + "Titre=" + Titre + ", Genre=" + Genre + ", nb_likes=" + nb_likes + ", nb_dislikes=" + nb_dislikes + '}';
//    }

    @Override
    public String toString() {
        return "video{" + "id=" + id + ", Titre=" + Titre + ", Genre=" + Genre + ", VideoPath=" + VideoPath + ", nb_likes=" + nb_likes + ", nb_dislikes=" + nb_dislikes + ", mailSent=" + mailSent + '}';
    }

    
    
   
    
    
}
