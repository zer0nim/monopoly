
package DataText;

import Data.*;
import Ihm.*;

public abstract class Biens_achetables extends Carreau{
    private int prixAchat, prixPassage;
    private Joueur propriétaire;

    public Biens_achetables(int prixAchat, int numero, String nomCarreau) {
	super(numero, nomCarreau);
	this.prixAchat = prixAchat;
    }
    
    public abstract int CalculLoyer(int resultde);
	
    public void action(Joueur j, int resultde){
	if(getPropriétaire() != null){ //bien possédé
	    if(getPropriétaire() != j){ //j n'est pas le propriétaire
		int loy = CalculLoyer(resultde);
                j.payerArgent(loy); //j paye le loyer
		getPropriétaire().recevoirArgent(loy);
		Ihm.Afficher(j.getNomJoueur() + " paye " + loy + "€ de loyer à " + getPropriétaire().getNomJoueur());
	    }
	}
	else{
	    if(assezArgent(j)){//Proposition d'achat si assez d'argent
		/*if (Ihm.propositionAchat(j, this)){
		    acheterPropriété(j);
		}*/
	    }
	    else{
		Ihm.Afficher("Pas assez d'argent pour acheter !");
	    }
	}
    }
    
    public void acheterPropriété(Joueur j){
	setPropriétaire(j); //Changer propriétaire de la case
	j.addPropriété(this); //Ajouté la case au propriétaire j
	j.payerArgent(getPrixAchat()); //Enlever le prix de la propriété au joueur j
    }
    
    public boolean assezArgent(Joueur j){
	return((getPrixAchat() - j.getCash()) <= 0); //retourne vrai si le joueur a assez d'argent pour acheter la propriété
    }
       

    
    
    
    
    //v--getters setters--v

    public int getPrixAchat() {
	return prixAchat;
    }

    public void setPrixAchat(int pa) {
	this.prixAchat = pa;
    }

    public Joueur getPropriétaire() {
	return propriétaire;
    }

    public void setPropriétaire(Joueur propriétaire) {
	this.propriétaire = propriétaire;
    }
    
}
