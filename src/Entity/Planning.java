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
import services.PlanningCrud;

/**
 *
 * @author htc
 */
public class Planning {
    private int id_p;
    private int num_planning;
    private int id_user;
    private Date ajout;
    private String nom_p;
    private int nb_taches;

    public Planning() {
    }

    public int getNb_taches() {
        return nb_taches;
    }

    public Planning(int id_p,int id_user,Date ajout, String nom_p, int nb_taches) {
      
        this.id_p = id_p;
       this.id_user = id_user;
        this.ajout = ajout;
        this.nom_p = nom_p;
        PlanningCrud pc=new PlanningCrud();
        
        this.nb_taches = pc.getnbr_tache(this.id_p);
    }
    public Planning(int id_p,int id_user,String nom_p) {
      
        this.id_p = id_p;
       this.id_user = id_user;
        this.nom_p = nom_p;
       
    }

    public Date getDate_ajout() {
        return ajout;
    }

    public int getId_p() {
        return id_p;
    }

    public int getId_u() {
        return id_user;
    }

    public int getNb_tache() {
        return nb_taches;
    }

    public String getNom_planning() {
        return nom_p;
    }

    public int getNum_planning() {
        return num_planning;
    }

    public void setAjout(Date ajout) {
        this.ajout = ajout;
    }

    public void setId(int id) {
        this.id_p = id;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNb_taches(int nb_taches) {
        this.nb_taches = nb_taches;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public void setNum_planning(int num_planning) {
        this.num_planning = num_planning;
    }

    @Override
    public String toString() {
        return "Planning{" + "id=" + id_p + ", num_planning=" + num_planning + ", id_user=" + id_user + ", ajout=" + ajout + ", nom_p=" + nom_p + ", nb_taches=" + nb_taches + '}';
    }
    
    
    
}
