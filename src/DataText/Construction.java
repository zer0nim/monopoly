/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataText;

import Data.*;

/**
 *
 * @author dejouxr
 */
public class Construction {
    private ProprieteAConstruire terrain;
    private Joueur proprietaire;
    private String type;
    private int numero;
    
    
    public Construction(ProprieteAConstruire terrain, Joueur proprietaire, String type){
        this.terrain = terrain;
        this.proprietaire = proprietaire;
        this.type = type;
    }
    
    
    
    /**
     * @return the terrain
     */
    public ProprieteAConstruire getTerrain() {
        return terrain;
    }

    /**
     * @param terrain the terrain to set
     */
    public void setTerrain(ProprieteAConstruire terrain) {
        this.terrain = terrain;
    }

    /**
     * @return the proprietaire
     */
    public Joueur getProprietaire() {
        return proprietaire;
    }

    /**
     * @param proprietaire the proprietaire to set
     */
    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
