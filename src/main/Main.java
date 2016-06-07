package main;

import Data.*;
import Ihm.*;
import Jeu.*;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Joueur> deathNote;

    public static void main(String[] args) {
	/*
	deathNote = new ArrayList<>(); //ArrayList qui permet de lister les joueurs à éliminer
	
	Controleur controleur = new Controleur();
	controleur.creerJoueurs();
	/*for(Carreau c : monop.getCarreaux()){
	    Ihm.affBiens(c);
	}*/
	/*
	while(controleur.getMonopoly().getJoueurs().size() > 1){ //tant qu'il y a au moins 2 joueurs
	    for (Joueur j : controleur.getMonopoly().getJoueurs()){ //On boucle sur les joueurs encore en jeu
		if (controleur.getMonopoly().getJoueurs().size() > 1){//tant qu'il y a au moins 2 joueurs
		    controleur.jouerUnCoup(j); //chaque joueur jous un coup
		    if(j.estMort()){ //si le joueur à 0 ou moins de cash
			Ihm.Cimetiere(j); //affiche que le joueur est éliminé
			deathNote.add(j); //ajoute le joueur à la liste d'élimination
		    }
		}
		
	    }
	    for (Joueur j : deathNote){ //pour chaque joueur de la liste d'élimination
		controleur.getMonopoly().getJoueurs().remove(j); //On élimine les joueur du monopoly
	    }
	    deathNote.clear(); //Puis on vide cette list pour le prochain passage
	}
	Ihm.Winner(controleur.getMonopoly().getJoueurs().get(0));
*/
	IhmFenetre ihmf = new IhmFenetre();
    }
}
