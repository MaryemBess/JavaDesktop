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
import java.sql.Timestamp;

/**
 *
 * @author htc
 */
public class Tache {
    private int id;
    private int id_p;
    private int id_m;
    private int id_v;
    private int id_c;
    private int id_b;
    private int like;
    private int dislike;
    private int id_t;
    private String type;
    private String nom;
    private String etat_tache;
    private int etat_tache_b;

    public String getEtat() {
        return etat_tache;
    }

    public void setEtat(String etat_tache) {
        this.etat_tache = etat_tache;
    }
    
private int idnonnull;
private Timestamp date;

    public void setDate(Timestamp date) {
        this.date = date;
    }
 
    public Tache() {
    }
public Tache(int id,int id_p,int id_t, String type, String nom,Timestamp date) {
        this.id = id;   
        this.type = type;
        this.nom = nom;
        this.date = date;
        this.id_p=id_p;
        this.id=id;
        this.id_t=id_t;
    }
    public Tache(int id ,int id_t, String type, String nom,Timestamp date,int etat_tache) {
        this.id = id;   
        this.id_t=id_t;
        this.type = type;
        this.nom = nom;
        this.date = date;
        if (etat_tache==0){
            this.etat_tache="Non terminé";
        }else{
            this.etat_tache="terminé";
        }
    }
    public Tache(String nom, String type,Timestamp date) {
        this.id = id;   
        this.type = type;
        this.nom = nom;
        this.date = date;
    }

    public int getId_p() {
        return id_p;
    }

    public String getNom() {
        return nom;
    }
    

    public Tache(int id, int id_m, int id_v, int id_c, int id_b, int like, int dislike, String type, String nom,int idnonnull) {
        this.id = id;
        this.id_m = id_m;
        this.id_v = id_v;
        this.id_c = id_c;
        this.id_b = id_b;
        this.like = like;
        this.dislike = dislike;
        this.type = type;
        this.nom = nom;
        if ("music".equals(type)){
            this.idnonnull=this.id_m;
        }
        if ("video".equals(type)){
            this.idnonnull=this.id_v;
        }
        if ("citation".equals(type)){
            this.idnonnull=this.id_c;
        }
        if ("e_book".equals(type)){
            this.idnonnull=this.id_b;
        }
    }

    public Timestamp getDate() {
        return date;
    }

    public int getIdnonnull() {
        return idnonnull;
    }

    public void setIdnonnull(int idnonnull) {
        this.idnonnull = idnonnull;
    }

    
    public int getDislike() {
        return dislike;
    }

    public int getId_t() {
        return id_t;
    }

    public int getId_b() {
        return id_b;
    }

    public int getId_c() {
        return id_c;
    }

    public int getId_m() {
        return id_m;
    }

    public int getId_v() {
        return id_v;
    }

    public int getLike() {
        return like;
    }

    public int getId() {
        return id;
    }

    public String getNom_tache() {
        return nom;
    }

    public String getType_tache() {
        return type;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_b(int id_b) {
        this.id_b = id_b;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public void setId_m(int id_m) {
        this.id_m = id_m;
    }

    public void setId_v(int id_v) {
        this.id_v = id_v;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Tache{" + "id=" + id + ", id_m=" + id_m + ", id_v=" + id_v + ", id_c=" + id_c + ", id_b=" + id_b + ", like=" + like + ", dislike=" + dislike + ", type=" + type + ", nom=" + nom + '}';
    }
    
    
    
}

