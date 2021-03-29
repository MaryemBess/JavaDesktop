/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author htc
 */
import java.sql.Date;
import java.sql.Date;
import services.PlanningcreerCons;


/**
 *
 * @author htc
 */
public class Planningcreer {
    private int id ;
    private int like ;
    private int dislike;
    private int id_crea;
    private Date date_cr;
    private String descri;
    private String nom_p;

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String Nom_p) {
        this.nom_p = Nom_p;
    }

    
    public Planningcreer() {
    }

    public Planningcreer(int id, int like, int dislike, int id_crea, String descri) {
        this.id = id;
        this.like = like;
        this.dislike = dislike;
        this.id_crea = id_crea;
        this.descri = descri;
    }

    public Planningcreer(int id,String descri, int like, int dislike, int id_crea, Date date_cr) {
        this.id = id;
        this.like = like;
        this.dislike = dislike;
        this.id_crea = id_crea;
        this.date_cr = date_cr;
        this.descri = descri;
        PlanningcreerCons x=new PlanningcreerCons();
    
        this.nom_p = x.getnomcreateur(this.id_crea);
        System.out.println(this.id_crea);
        System.out.println(this.nom_p);
    }

    public Date getDate_cr() {
        return date_cr;
    }

    public String getDescription() {
        return descri;
    }

    public int getDisliker() {
        return dislike;
    }

    public int getId() {
        return id;
    }

    public int getId_c() {
        return id_crea;
    }

    public int getLiker() {
        return like;
    }

    public void setDate_cr(Date date_cr) {
        this.date_cr = date_cr;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_crea(int id_crea) {
        this.id_crea = id_crea;
    }

    public void setLike(int like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "Planningcreer{" + "id=" + id + ", like=" + like + ", dislike=" + dislike + ", id_crea=" + id_crea + ", date_cr=" + date_cr + ", descri=" + descri + '}';
    }
     
     
    
    
    
}

