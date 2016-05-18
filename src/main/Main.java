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
    }
}
