/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.Checkbox;
import javafx.scene.control.RadioButton;


/**
 *
 * @author ASUS
 */
public class Aliment {
    private int id_aliment;
    private String Libelle; 
    private String recette;
    private float calorie;
    private float gras;
    private float carbs;
    private int id_regime;
    //private Checkbox select ;
    public Checkbox select;
    boolean t = false;
    

    public Aliment() {
    }
    
    

    public Aliment(int id_aliment,String Libelle, String recette, float calorie, float gras, float carbs) {
        this.id_aliment = id_aliment;
        this.Libelle = Libelle;
        this.recette = recette;
        this.calorie = calorie;
        this.gras = gras;
        this.carbs = carbs;
        this.select = new Checkbox();
        
       
    }
    public Aliment(String Libelle, String recette, float calorie, float gras, float carbs) {
        
        this.Libelle = Libelle;
        this.recette = recette;
        this.calorie = calorie;
        this.gras = gras;
        this.carbs = carbs;
        
        
       
    }

    public int getId_aliment() {
        return id_aliment;
    }

    public String getLibelle() {
        return Libelle;
    }

    public String getRecette() {
        return recette;
    }

    public float getCalorie() {
        return calorie;
    }

    public float getGras() {
        return gras;
    }

    public float getCarbs() {
        return carbs;
    }

    public int getId_regime() {
        return id_regime;
    }

    public void setId_aliment(int id_aliment) {
        this.id_aliment = id_aliment;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public void setRecette(String recette) {
        this.recette = recette;
    }

    public void setCalorie(float calorie) {
        this.calorie = calorie;
    }

    public void setGras(float gras) {
        this.gras = gras;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public void setId_regime(int id_regime) {
        this.id_regime = id_regime;
    }

    public void setSelect(Checkbox select) {
        this.select = select;
        
    }

    public Checkbox getSelect() {
        
        return select;
    }
    public void setT(boolean t) {
        this.t = t;
        
    }

    public boolean getT() {
        return t;
    }
    
    
    @Override
    public String toString()
    {
        String result = "{";
        
        result += "'name':'"+this.Libelle;
        result += "','recipe Ingredients':"+this.Libelle;
        
        result += ",'fat':"+this.calorie;
        result += ",'protein':"+this.gras;
        result += ",'carbs':"+this.carbs;
        result += "}";
        
        return result.replace("'", "\"");
                
    }
    
    
}
