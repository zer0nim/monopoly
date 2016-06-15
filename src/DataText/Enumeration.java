package DataText;

import DataText.*;
import javax.swing.ComboBoxModel;


public class Enumeration {

    public static ComboBoxModel Pions;
    public enum Pions { //Différents pions que les joueurs pourront choisir
	Chapeau, Voiture, Bateau, DéÀCoudre, Chaussure, Brouette, Chien, FerÀRepasser, Cavalier;
    }
    
    public enum ActionChCo { //Différentes actions que les cartes chance et communautée peuvent avoir
	libPrison, reculerDeN, avancerJusqua, avancerJusquaScaseDep, modifyCash, payerCashHotels, allerPrison, anniversaire;
    }
}
