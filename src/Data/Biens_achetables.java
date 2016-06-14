
package Data;

import Ihm.*;
import Jeu.ControleurGraphique;

public abstract class Biens_achetables extends Carreau{
    private int prixAchat, prixPassage;
    private Joueur propriétaire;
    private ControleurGraphique controleur;

    public Biens_achetables(int prixAchat, int numero, String nomCarreau, ControleurGraphique controleur) {
	super(numero, nomCarreau);
	this.prixAchat = prixAchat;
        this.controleur = controleur;
    }
    
    public abstract int CalculLoyer(int resultde);
	
    public void action(Joueur j, int resultde){
	if(getPropriétaire() != null){ //bien possédé
            controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{-1,0,-1,1});
	    if(getPropriétaire() != j){ //j n'est pas le propriétaire
		int loy = CalculLoyer(resultde);
                j.payerArgent(loy); //j paye le loyer
		getPropriétaire().recevoirArgent(loy);
                controleur.setCom("Affichage",new Object[]{j.getNomJoueur()+ " paye " + loy + "€ de loyer à " + getPropriétaire().getNomJoueur()+".", true});
	    }
	}
	else{
	    if(assezArgent(j)){//Proposition d'achat si assez d'argent
                controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{-1,1,-1,1});
		controleur.setCom("Affichage", new Object[]{j.getNomJoueur()+ " : Vous pouvez achetter " + getNomCarreau() + " pour " + Integer.toString(getPrixAchat()) + "€ ?", true});
	    }
	    else{
		controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{-1,0,-1,1});
                controleur.setCom("Affichage", new Object[]{j.getNomJoueur()+ " : Vous n'avez pas assez d'argent pour acheter " + getNomCarreau() + ".", true});
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
