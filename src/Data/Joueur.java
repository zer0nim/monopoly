package Data;

import java.util.ArrayList;

public class Joueur {
    private String nomJoueur;
    private int cash = 1500;
    private Carreau positionCourante;
    private ArrayList<Biens_achetables> propriétés = new ArrayList<>();

    public Joueur(String nomJoueur){
	this.setNomJoueur(nomJoueur);
    }
    public void payerLoyer(int l) {
    }

    public void recevoirLoyer(int l) {
    }

    
    
    //v--getters setters--v
    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public Carreau getPositionCourante() {
        return positionCourante;
    }

    public void setPositionCourante(Carreau c) {
        this.positionCourante = c;
    }

    public ArrayList<Biens_achetables> getPropriétés() {
        return propriétés;
    }

    public void setPropriétés(ArrayList<Biens_achetables> propriétés) {
        this.propriétés = propriétés;
    }
}