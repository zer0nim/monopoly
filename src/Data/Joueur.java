package Data;

import java.util.ArrayList;
import java.util.HashSet;
import Ihm.*;
import Jeu.ControleurGraphique;
import java.util.Arrays;
import java.util.Collections;

public class Joueur {
    private String nomJoueur; // nom du joueur j
    private int cash = 1; //solde de départ
    private Carreau positionCourante; //position du joueur j sur le plateau
    private ArrayList<Biens_achetables> propriétés = new ArrayList<>(); //liste des biens achetables possédés par le joueur j
    private HashSet<Groupe> groupes = new HashSet<>(); //liste des groupes où le joueur possède toutes les propriétés
    private HashSet<Object[]> listeConstructionsDispo = new HashSet<>();
    private HashSet<ProprieteAConstruire> proprietesAConstruire = new HashSet<>();
    private boolean maison;
    private boolean hotel;
    
    private int carteLibPrison = 0;
    private int enPrison = 0;
    private int compteDoubleDes = 0;
    private Enumeration.Pions pion;
    private ControleurGraphique controleur;

    public Joueur(String nomJoueur, Carreau c, ControleurGraphique controleur){
	this.setNomJoueur(nomJoueur);
	this.setPositionCourante(c);
        this.controleur = controleur;
    }
    public void payerArgent(int cash) {
	setCash(getCash() - cash); //débite le cash du joueur de 'cash'
    }

    public void recevoirArgent(int cash) {
	setCash(getCash() + cash); //augmente le cash du joueur de 'cash'
    }
    
    public boolean estMort(){//si cash < 0, return true
	return(getCash() < 0);
    }
    
    public void addPropriété(Biens_achetables ba){ //rajoute ba à l'ArrayList propriétés
	getPropriétés().add(ba);
    }
    
    public void vendrePropriétés(){
	for(Biens_achetables ba : propriétés){
	    ba.setPropriétaire(null);
	}
	propriétés.clear();
    }
    
    public void incrementCompteDoubleDes() {    compteDoubleDes++;  }
    
    public void resetCompteDoubleDes() {    compteDoubleDes = 0;   }
    
    public int getCompteDoubleDes() {   return compteDoubleDes; }
    
    //v--getters setters--v
    public String getNomJoueur() {
        return nomJoueur; //retourne le nom du joueur
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur; //définit le nom du joueur
    }

    public int getCash() {
        return cash; //retourne le cash du joueur
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public Carreau getPositionCourante() {
        return positionCourante; //retourne le carreau actuel du joueur
    }

    public void setPositionCourante(Carreau c) {
        this.positionCourante = c; //définit le position du joueur par le carreau 'c'
    }

    public ArrayList<Biens_achetables> getPropriétés() {
        return propriétés; //retourne une ArrayList de Biens_achetables, possédé par le joueur
    }
    
    public void setPropriétésAConstruire() {
        proprietesAConstruire.clear();
        for(Biens_achetables bien : propriétés){
            if (bien.getClass().getSimpleName().equals("ProprieteAConstruire")){
                proprietesAConstruire.add((ProprieteAConstruire)bien);
            }
        }
    }

    public void setPropriétés(ArrayList<Biens_achetables> propriétés) {
        this.propriétés = propriétés;
    }

    public int getCarteLibPrison() {
	return carteLibPrison;
    }

    public void setCarteLibPrison(int carteLibPrison) {
	this.carteLibPrison = carteLibPrison;
    }

    
    public int getPrison(){
                  return enPrison;
    }
    public void setEnPrison(int enPrison) {
	this.enPrison = enPrison;
    }
    
    public void removePropriete(ProprieteAConstruire prop){
        ArrayList<Biens_achetables> listePropriete = new ArrayList<>();
        for(Biens_achetables propriete : propriétés){
            if(propriete.getClass().getSimpleName() == "ProprieteAConstruire"){
                if(!propriete.equals(prop)){
                    
                }else{
                    listePropriete.add(propriete);
                }
            }else{
                listePropriete.add(propriete);
            }
        }
    }
    
    public void setGroupe(){
        groupes.clear();
        for(Biens_achetables propriete : getPropriétés()){
            if (propriete.getClass().getSimpleName().equals("ProprieteAConstruire")){
                if(((ProprieteAConstruire)propriete).possedeGroupe()){
                    groupes.add(((ProprieteAConstruire)propriete).getGroupe());
                }
            }
        }
    }
    
    public HashSet<ProprieteAConstruire> getProprietesGroupe(Groupe groupe){
        HashSet<ProprieteAConstruire> propGroupe = new HashSet<>();
        for(Carreau propriete : groupe.getCarreau()){
                propGroupe.add((ProprieteAConstruire)propriete);
        }
        return propGroupe;
    }
    
    public void setProprietePourConstruire(boolean maisons, boolean hotels){ //########### Vérifier si on peut construire dessus
        listeConstructionsDispo.clear();
        setGroupe();
        for(Groupe groupe : groupes){ // Cas : rien de construit, quelques maisons construites
            int nbConstructions = 0;
            ArrayList<Integer> nbC = new ArrayList<>();
            boolean change = false;
            for(ProprieteAConstruire prop : getProprietesGroupe(groupe)){
                    nbC.add(prop.getNbConstructions());
            }
            if(nbC.size() != 0){
                nbConstructions = Collections.max(nbC);
            }
            for(ProprieteAConstruire prop : getProprietesGroupe(groupe)){
                if(nbConstructions != prop.getNbConstructions()){
                    change = true;
                }
            }
            for(ProprieteAConstruire prop : getProprietesGroupe(groupe)){
                if((prop.getNbConstructions() < nbConstructions || nbConstructions == 0 || (!change && nbConstructions < 4)) && maisons){
                    listeConstructionsDispo.add(new Object[] {prop, "maison", nbConstructions, prop.getLoyers().get(nbConstructions), prop.getPrixMaison(), 0});
                }
                if((!change && nbConstructions == 4 && prop.getConstructions().size() != 1) && hotels){
                    listeConstructionsDispo.add(new Object[] {prop, "hotel", nbConstructions, prop.getLoyers().get(4), prop.getPrixHotel(), 0});
                }
            }
        }
    }
    
    public void achetterConstruction(boolean maisons, boolean hotels){
        setGroupe();
        setProprietePourConstruire(maisons, hotels);
        this.maison = maisons;
        this.hotel = hotels;
        if(!groupes.isEmpty() && !listeConstructionsDispo.isEmpty()){ // ##################################### Verifier s'il reste des cons à acheter
            controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{-1,0,1,-1});
        }
    }
        
    public void construire(Object[] constru){
        Object[] constructionAchettee = constru;
        if((Integer)(constructionAchettee[5]) != 1){
            ProprieteAConstruire prop = (ProprieteAConstruire) constructionAchettee[0];
            int prixAchat = (Integer)constructionAchettee[4];           
            if(getCash() >= prixAchat){
                setCash(getCash()-prixAchat);
                prop.addConstructions(new Construction(prop, this, (String) constructionAchettee[1]));
                controleur.setCom("Affichage",new Object[]{getNomJoueur()+ " : Vous venez d'ajouter une nouvelle construction à " + ((ProprieteAConstruire)constructionAchettee[0]).getNomCarreau() + ".", true});
                controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{-1,0,0,-1});
                controleur.getInterfacee().getFenetre().setInfosJoueurs(controleur);
            }else{
                controleur.setCom("Affichage",new Object[]{getNomJoueur()+ " : Vous n'avez pas assez d'argent pour cette construction.", true});
                controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{-1,0,1,-1});
                //###### Plateau action bouton construire
            }
        }
    }


    public Enumeration.Pions getPion() {
	return pion;
    }

    public void setPion(Enumeration.Pions pion) {
	this.pion = pion;
    }
    
    public HashSet<Object[]> getLiseConstructionsDispo(){
        return listeConstructionsDispo;
    }
}
