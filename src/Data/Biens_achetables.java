package Data;

import Ihm.*;

public class Biens_achetables extends Carreau{
    private int prixAchat, prixPassage;
    private Joueur propriétaire;

    @Override
    public void action(Joueur j){
	if(getPropriétaire() != null){ //bien non possédé
	    if(getPropriétaire() != j){ //j n'est pas le propriétaire
		j.payerLoyer(getPrixPassage()); //j paye le loyer
	    }
	}
	else{
	    if(assezArgent(j)){//Proposition d'achat si assez d'argent
		Ihm.propositionAchat(j, this);
	    }
	}
    }
    
    public void acheterPropriété(Joueur j){
	setPropriétaire(j);
	j.payerLoyer(getPrixAchat());
    }
    
    public boolean assezArgent(Joueur j){
	return((getPrixAchat() - j.getCash()) <= 0);
    }
       


    
    
    
    
    //v--getters setters--v

    public int getPrixAchat() {
	return prixAchat;
    }

    public void setPrixAchat(int pa) {
	this.prixAchat = pa;
    }

    public int getPrixPassage() {
	return prixPassage;
    }

    public void setPrixPassage(int pp) {
	this.prixPassage = pp;
    }

    public Joueur getPropriétaire() {
	return propriétaire;
    }

    public void setPropriétaire(Joueur propriétaire) {
	this.propriétaire = propriétaire;
    }

}
