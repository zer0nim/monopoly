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
	    System.out.println("rejouer");
	    jouerUnCoup(j);
	}
    }

    private int lancerDésAvancer(Joueur j){
	int resultD = PlateauUtilitaire.LancerDe();
        j.setPositionCourante(monopoly.getCarreaux().get((j.getPositionCourante().getNumero() + resultD)%40));
	return(resultD);
    }
    
    public void creerJoueurs(){
	int i = 0;
	while (!Ihm.fini()){
	    if (getMonopoly().getJoueurs().size() <= 6){
		monopoly.setJoueur(new Joueur(Ihm.nomJoueur(), monopoly.getCarreaux().get(0)));
	    }
	    i++;
	}
	if (i < 2){
	    Ihm.Afficher("Pas assez de joueurs. \nRecommencez.");
	    monopoly.getJoueurs().clear();
	    creerJoueurs();
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
