package Data;

import java.util.ArrayList;

public class Joueur {
    private String nomJoueur;
    private int cash = 1500;
    private ArrayList<Gare> gares = new ArrayList<>();
    private Carreau positionCourante;
    private ArrayList<ProprieteAConstruire> proprietés = new ArrayList<>();
    private ArrayList<Biens_achetables> propriétés = new ArrayList<>();

    public void payerLoyer(int L) {
    }

    public void recevoirLoyer(int L) {
    }

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

    public ArrayList<Gare> getGares() {
        return gares;
    }

    public void setGares(ArrayList<Gare> gares) {
        this.gares = gares;
    }

    public Carreau getPositionCourante() {
        return positionCourante;
    }

    public void setPositionCourante(Carreau positionCourante) {
        this.positionCourante = positionCourante;
    }

    public ArrayList<ProprieteAConstruire> getProprietés() {
        return proprietés;
    }

    public void setProprietés(ArrayList<ProprieteAConstruire> proprietés) {
        this.proprietés = proprietés;
    }

    public ArrayList<Biens_achetables> getPropriétés() {
        return propriétés;
    }

    public void setPropriétés(ArrayList<Biens_achetables> propriétés) {
        this.propriétés = propriétés;
    }
}