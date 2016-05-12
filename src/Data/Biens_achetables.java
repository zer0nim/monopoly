package Data;

import Ihm.*;

public abstract class Biens_achetables extends Carreau{
    private int prixAchat, prixPassage;
    private Joueur propriétaire;

    public Biens_achetables(int prixAchat, int numero, String nomCarreau, Groupe groupe) {
	super(numero, nomCarreau, groupe);
	this.prixAchat = prixAchat;
    }	
	
    @Override
    public void action(Joueur j){
	if(getPropriétaire() != null){ //bien non possédé
	    if(getPropriétaire() != j){ //j n'est pas le propriétaire
		//j.payerLoyer(CalculLoyer()); //j paye le loyer
		System.out.print("rien");
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

    public Joueur getPropriétaire() {
	return propriétaire;
    }

    public void setPropriétaire(Joueur propriétaire) {
	this.propriétaire = propriétaire;
    }

}
