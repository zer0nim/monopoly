package Jeu;
import Ihm.*;
import Data.*;
import Utils.*;

public class Controleur {
    private Monopoly monopoly;
    
    public Controleur(){
	this.monopoly = new Monopoly();
    }

    public void jouerUnCoup(Joueur j) {
        j.getPositionCourante().action(j, lancerDésAvancer(j).getNumero());
    }

    private Carreau lancerDésAvancer(Joueur j){
        j.setPositionCourante(getMonopoly().getCarreaux().get(PlateauUtilitaire.LancerDe()));
	return(j.getPositionCourante());
    }
    
    public void creerJoueurs(){
	int i = 0;
	while (!Ihm.fini()){
	    if (getMonopoly().getJoueurs().size() <= 6){
		monopoly.setJoueur(new Joueur(Ihm.nomJoueur()));
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
