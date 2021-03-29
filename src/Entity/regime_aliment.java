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
public class regime_aliment {
    private int id;
    private int id_regime;
    private int id_aliment;

    public regime_aliment() {
    }

    public regime_aliment(int id, int id_regime, int id_aliment) {
        this.id = id;
        this.id_regime = id_regime;
        this.id_aliment = id_aliment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_regime() {
        return id_regime;
    }

    public void setId_regime(int id_regime) {
        this.id_regime = id_regime;
    }

    public int getId_aliment() {
        return id_aliment;
    }

    public void setId_aliment(int id_aliment) {
        this.id_aliment = id_aliment;
    }
    
    
    
}
