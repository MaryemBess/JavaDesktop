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
public class favoris {
    int id,id_user,id_music;

    public favoris(int id, int id_user, int id_music) {
        this.id = id;
        this.id_user = id_user;
        this.id_music = id_music;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_music() {
        return id_music;
    }

    public void setId_music(int id_music) {
        this.id_music = id_music;
    }
    
    
}
