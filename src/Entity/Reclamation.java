/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author PCS
 */
public class Reclamation {
  private int id ;
    private String sujetReclamation;
    private String statu;
    private String descriptionReclamation ;
    private String dateReclamation;
    private int id_Client;

    public Reclamation(int id, String sujetReclamation, String statu, String descriptionReclamation, String dateReclamation, int id_Client) {
        this.id = id;
        this.sujetReclamation = sujetReclamation;
        this.statu = statu;
        this.descriptionReclamation = descriptionReclamation;
        this.dateReclamation = dateReclamation;
        this.id_Client = id_Client;
    }
    public Reclamation( String sujetReclamation, String statu, String descriptionReclamation, String dateReclamation) {
        this.sujetReclamation = sujetReclamation;
        this.statu = statu;
        this.descriptionReclamation = descriptionReclamation;
        this.dateReclamation = dateReclamation;
    }

    public Reclamation(String sujetReclamation, String descriptionReclamation,String dateReclamation,int id_Client) {
        this.sujetReclamation = sujetReclamation;
        this.descriptionReclamation = descriptionReclamation;
         this.dateReclamation = dateReclamation;
         this.id_Client = id_Client;
    }
     public Reclamation(int id, String sujetReclamation, String statu, String descriptionReclamation, String dateReclamation) {
        this.id = id;
        this.sujetReclamation = sujetReclamation;
        this.statu = statu;
        this.descriptionReclamation = descriptionReclamation;
        this.dateReclamation = dateReclamation;
    }


   

    public Reclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Reclamation(String string, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujetReclamation() {
        return sujetReclamation;
    }

    public void setSujetReclamation(String sujetReclamation) {
        this.sujetReclamation = sujetReclamation;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getDescriptionReclamation() {
        return descriptionReclamation;
    }

    public void setDescriptionReclamation(String descriptionReclamation) {
        this.descriptionReclamation = descriptionReclamation;
    }

    public String getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(String dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public int getId_Client() {
        return id_Client;
    }

    public void setId_Client(int id_Client) {
        this.id_Client = id_Client;
    }

    
    
}