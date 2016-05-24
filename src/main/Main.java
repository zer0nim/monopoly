package main;

import Data.*;
import Ihm.*;
import static Ihm.Ihm.affBiens_achetables;
import Jeu.*;
import Utils.*;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Joueur> deathNote;

    public static void main(String[] args) {
	deathNote = new ArrayList<>();
	
	Controleur controleur = new Controleur();
	controleur.creerJoueurs();
	/*for(Carreau c : monop.getCarreaux()){
	    Ihm.affBiens(c);
	}*/
	
	while(controleur.getMonopoly().getJoueurs().size() > 1){
	    for (Joueur j : controleur.getMonopoly().getJoueurs()){
		controleur.jouerUnCoup(j);
		if(j.estMort()){
		    Ihm.Cimetiere(j);
		    deathNote.add(j);
		}
	    }
	    for (Joueur j : deathNote){
		controleur.getMonopoly().getJoueurs().remove(j);
	    }
	    deathNote.clear();    
	}
    }
}
