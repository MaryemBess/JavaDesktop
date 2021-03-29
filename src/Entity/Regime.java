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
public class Regime {
    private int id_regime;
    private String description;
    private int duration;
    private int id_user;

    public Regime() {
    }

    public Regime(String description, int duration) {
        
        this.description = description;
        this.duration = duration;
        
    }

    public Regime(int id, String description, int duration) {
        this.id_regime=id;
        this.description = description;
        this.duration = duration;
    }

    public int getId_regime() {
        return id_regime;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_regime(int id_regime) {
        this.id_regime = id_regime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    
}
