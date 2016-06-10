package DataText;

import Data.*;
import java.util.ArrayList;
import Ihm.*;

public class ProprieteAConstruire extends Biens_achetables{
	private int prixPassage;
        private Groupe groupe;
        private ArrayList<Construction> constructions;
        private ArrayList<Integer> loyers;
        private int prixMaison;
        private int prixHotel;
    
    
    public ProprieteAConstruire(int prixAchat, int prixPassage, int numero, String nomCarreau, Groupe groupe, ArrayList<Integer> loyers, int prixMaison, int prixHotel) {
	super(prixAchat, numero, nomCarreau);
	setGroupe(groupe);
	setPrixPassage(prixPassage);
        constructions = new ArrayList<>();
        this.loyers = loyers;
        this.prixMaison = prixMaison;
        this.prixHotel = prixHotel;
    }

    @Override
    public int CalculLoyer(int resultde){
        int loyer = prixPassage;
        int nbCons = 0;
        if(possedeGroupe()){
           if(getConstructions().size() != 0){
                if(getConstructions().get(0).getType() == "hotel"){
                    loyer = getLoyers().get(4);
                }else{
                    loyer = getLoyers().get(getConstructions().size()-1);
                }
           }else{
                loyer = prixPassage * 2;
           }
        }
	return(loyer);
    }
    

    public boolean possedeGroupe(){
        int groupeSize = getGroupe().getCarreau().size();
        int i = 0;
	for (Biens_achetables bien : getPropriétaire().getPropriétés()){
	    if (bien.getClass().getSimpleName().equals("ProprieteAConstruire")){
		if(((ProprieteAConstruire)bien).getGroupe() == this.getGroupe()){
                    i++;
                }
	    }
	}
        return (i == groupeSize);
    }
    //v--getters setters--v

    public int getPrixPassage() {
	return prixPassage;
    }

    public void setPrixPassage(int prixPassage) {
	this.prixPassage = prixPassage;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
    public ArrayList<Construction> getConstructions(){
        return constructions;
    }
    
    public ArrayList<Construction> getMaisons(){
        ArrayList<Construction> constructionsMaison = new ArrayList<>();
        for(Construction cons : constructions){
            if(cons.getType() == "maison"){
                constructionsMaison.add(cons);
            }
        }
        return constructionsMaison;
    }
    
    public ArrayList<Construction> getHotel(){
        ArrayList<Construction> constructionsHotel = new ArrayList<>();
        for(Construction cons : constructions){
            if(cons.getType() == "hotel"){
                constructionsHotel.add(cons);
            }
        }
        return constructionsHotel;
    }
    
    public int getNbConstructions(){
        int nbConstruction ;
        if(getHotel().size() != 0){
            nbConstruction = 4;
        }else{
            nbConstruction = getConstructions().size();
        }
        return nbConstruction;
    }
    
    public void addConstructions(Construction cons){
        if(cons.getType() == "hotel"){
            setConstructions(new ArrayList<Construction>());
        }
        this.constructions.add(cons);
        
    }
    
    public void removeConstructions(Construction cons){
        this.constructions.remove(cons);
    }

    /**
     * @return the loyers
     */
    public ArrayList<Integer> getLoyers() {
        return loyers;
    }

    /**
     * @return the prixMaison
     */
    public int getPrixMaison() {
        return prixMaison;
    }

    /**
     * @return the prixHotel
     */
    public int getPrixHotel() {
        return prixHotel;
    }

    /**
     * @param constructions the constructions to set
     */
    public void setConstructions(ArrayList<Construction> constructions) {
        this.constructions = constructions;
    }
}