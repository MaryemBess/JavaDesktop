/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author ASUS
 */
public class Health {
    private int id_health;
    private float totale_calories;
    private float totale_carbs;
    private float totale_gras;
    private float moy_tension;
    private float poids;
    private float hauteur;
    private int id_user;

    public Health() {
    }

    public Health(float totale_calories, float totale_carbs, float totale_gras, float moy_tension, float poids, float hauteur, int id_user) {
        
        this.totale_calories = totale_calories;
        this.totale_carbs = totale_carbs;
        this.totale_gras = totale_gras;
        this.moy_tension = moy_tension;
        this.poids = poids;
        this.hauteur = hauteur;
        this.id_user = id_user;
    }

    public int getId_health() {
        return id_health;
    }

    public void setId_health(int id_health) {
        this.id_health = id_health;
    }

    public float getTotale_calories() {
        return totale_calories;
    }

    public void setTotale_calories(float totale_calories) {
        this.totale_calories = totale_calories;
    }

    public float getTotale_carbs() {
        return totale_carbs;
    }

    public void setTotale_carbs(float totale_carbs) {
        this.totale_carbs = totale_carbs;
    }

    public float getTotale_gras() {
        return totale_gras;
    }

    public void setTotale_gras(float totale_gras) {
        this.totale_gras = totale_gras;
    }

    public float getMoy_tension() {
        return moy_tension;
    }

    public void setMoy_tension(float moy_tension) {
        this.moy_tension = moy_tension;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public float getHauteur() {
        return hauteur;
    }

    public void setHauteur(float hauteur) {
        this.hauteur = hauteur;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    
    
    
}
