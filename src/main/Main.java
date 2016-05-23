package main;

import Data.*;
import Ihm.*;
import Jeu.*;
import Utils.*;

public class Main {
    
    public static void main(String[] args) {
	Controleur controleur = new Controleur();
	controleur.creerJoueurs();
	/*for(Carreau c : monop.getCarreaux()){
	    Ihm.affBiens(c);
	}*/
	
	while(controleur.getMonopoly().getJoueurs().size() > 1){
	    for (Joueur j : controleur.getMonopoly().getJoueurs()){
		controleur.jouerUnCoup(j);
	    }
	}
    }
}
