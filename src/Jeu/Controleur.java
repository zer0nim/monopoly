package Jeu;
import Ihm.*;
import Data.*;
import static Ihm.Ihm.affBiens;
import static Ihm.Ihm.affJoueur;
import Utils.*;

public class Controleur {
    private Monopoly monopoly;
    
    public Controleur(){
	this.monopoly = new Monopoly();
    }

    public void jouerUnCoup(Joueur j) {
	int resultD = lancerDésAvancer(j);
	int resultD2 = lancerDésAvancer(j);
	
	if (!j.estMort()){   
	    resultD += resultD2;

	    affJoueur(j);
	    j.getPositionCourante().action(j, resultD);
	}
	if (j.estMort()){ //pas de else il est peut etre mort en jouant
		j.vendrePropriétés();
	}
	if (resultD == 2 * resultD2){ //si double
	    Ihm.Afficher("Double au Dé !");
	    jouerUnCoup(j);
	}
    }

    private int lancerDésAvancer(Joueur j){
	int resultD = PlateauUtilitaire.LancerDe();
        j.setPositionCourante(monopoly.getCarreaux().get((j.getPositionCourante().getNumero() + resultD)%40));
	return(resultD);
    }
    
    public void creerJoueurs(){
	int nbJoueur = Ihm.nbJoueur();
	for (int j = 0; j < nbJoueur ; j++) {
		monopoly.setJoueur(new Joueur(Ihm.nomJoueur(j+1), monopoly.getCarreaux().get(0)));
	}
    }
    

    //v--getters setters--v

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public void setMonopoly(Monopoly monopoly) {
        this.monopoly = monopoly;
    }
}
