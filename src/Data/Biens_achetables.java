
package Data;

import Ihm.*;

public abstract class Biens_achetables extends Carreau{
    private int prixAchat, prixPassage;
    private Joueur propriétaire;

    public Biens_achetables(int prixAchat, int numero, String nomCarreau) {
	super(numero, nomCarreau);
	this.prixAchat = prixAchat;
    }
    
    public abstract int CalculLoyer(int resultde);
	
    @Override
    public void action(Joueur j, int resultde){
	if(getPropriétaire() != null){ //bien possédé
	    if(getPropriétaire() != j){ //j n'est pas le propriétaire
                j.payerLoyer(CalculLoyer(resultde)); //j paye le loyer
		getPropriétaire().addCash(resultde);
	    }
	}
	else{
	    if(assezArgent(j)){//Proposition d'achat si assez d'argent
		if (Ihm.propositionAchat(j, this)){
		    acheterPropriété(j);
		}
	    }
	    else{
		Ihm.Afficher("Pas assez d'argent pour acheter !");
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

    public Joueur getPropriétaire() {
	return propriétaire;
    }

    public void setPropriétaire(Joueur propriétaire) {
	this.propriétaire = propriétaire;
    }

}
