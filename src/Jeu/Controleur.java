package Jeu;

import Ihm.*;
import Data.*;
import static Ihm.Ihm.affJoueur;
import java.util.ArrayList;
import java.util.Random;

public class Controleur {

    private Monopoly monopoly;

    public Controleur() {
	this.monopoly = new Monopoly();
    }

    public void jouerUnCoup(Joueur j) {
	Ihm.Afficher("");
	int resultD = lancerDésAvancer(j);
	int resultD2 = lancerDésAvancer(j);
	//int resultD = 2;
	//int resultD2 = 2;
	if (!j.estMort()) {
	    resultD += resultD2;
	    if (j.getPrison() != 0 && resultD == 2 * resultD2) {
		j.setEnPrison(0);
		Ihm.Afficher("Vous venez de sortir de prison avec un double dé !");
		j.setPositionCourante(monopoly.getCarreaux().get(((j.getPositionCourante().getNumero() + resultD) - 1) % 40));
	    }
	    affJoueur(j);
	    actionCarreau(j, resultD);

	    if (j.getPrison() == -1) {
		j.setEnPrison(0);
		Ihm.Afficher("résultat lancé du dé: " + (resultD - resultD2));
		Ihm.Afficher("résultat lancé du dé: " + resultD2);
		j.setPositionCourante(monopoly.getCarreaux().get(((j.getPositionCourante().getNumero() + resultD) - 1) % 40));
		affJoueur(j);
		actionCarreau(j, resultD);
	    }
	}
	if (j.estMort()) { //pas de else il est peut etre mort en jouant
	    j.vendrePropriétés();
	}
	if (resultD == 2 * resultD2) { //si double
	    Ihm.Afficher("Double au Dé !");
	    j.incrementCompteDoubleDes();
	    if (j.getCompteDoubleDes() == 3) {
		j.setPositionCourante(monopoly.getCarreaux().get(10));
		j.setEnPrison(3);
		Ihm.Afficher(j.getNomJoueur() + " est en prison. Il lui reste " + j.getPrison() + " tour(s) en prison.");
		j.resetCompteDoubleDes();
	    } else {
		jouerUnCoup(j);
	    }
	} else {
	    j.resetCompteDoubleDes();
	}
	/*
	System.out.println("\nFin du tour, appuyer sur entrer pour continuer");

	Scanner input = new Scanner(System.in);
	String s = null;
	while ( true )
	{
	   s = input.nextLine();
	   if( !s.equals("\\n") ) 
	      break;
	}*/
    }

    private void actionCarreau(Joueur j, int resultD) {
	if (j.getPositionCourante().getClass().getSimpleName().equals("Gare")) {
	    ((Gare) j.getPositionCourante()).action(j, resultD);
	} else if (j.getPositionCourante().getClass().getSimpleName().equals("Compagnie")) {
	    ((Compagnie) j.getPositionCourante()).action(j, resultD);
	} else if (j.getPositionCourante().getClass().getSimpleName().equals("ProprieteAConstruire")) {
	    ((ProprieteAConstruire) j.getPositionCourante()).action(j, resultD);
	} else {
	    ((AutreCarreau) j.getPositionCourante()).action(j, monopoly.getJoueurs(), monopoly.getCarreaux(), monopoly.getCartesChCo());
	}
    }

    private int lancerDésAvancer(Joueur j) {
	int ancPos = j.getPositionCourante().getNumero();
	int resultD = LancerDeN(6);
	Ihm.Afficher("résultat lancé du dé: " + resultD);
	if (j.getPrison() == 0) {
	    j.setPositionCourante(monopoly.getCarreaux().get(((j.getPositionCourante().getNumero() + resultD) - 1) % 40));
	}
	if (j.getPositionCourante().getNumero() < ancPos) { //si ça nouvelle position est inférieur à la nouvelle
	    Ihm.Afficher(j.getNomJoueur() + " reçois son Salaire (case départ) sa position etait: " + j.getPositionCourante().getNumero());
	    j.recevoirArgent(200); // on ajoute 200 de cash, car il est donc passé par le départ
	}
	return (resultD);
    }

    public int LancerDeN(int n) {
	Random rand = new Random();
	return rand.nextInt(n) + 1; //retourne un entier correspondant à un lancé de dé
    }

    public void creerJoueurs() {
	int nbJoueur = Ihm.nbJoueur();
	for (int j = 0; j < nbJoueur; j++) {
	    monopoly.setJoueur(new Joueur(Ihm.nomJoueur(j + 1), monopoly.getCarreaux().get(0)));
	}
	quiCommence();
    }

    public void quiCommence() {
	Joueur j = null;
	int resDe, ancRes = 0;
	ArrayList<Joueur> njoueurs = new ArrayList<>();
	ArrayList<Joueur> njoueursMemeScore = new ArrayList<>();

	for (Joueur jCourant : monopoly.getJoueurs()) {
	    resDe = LancerDeN(6);
	    resDe += LancerDeN(6);
	    if (resDe >= ancRes) {
		if (resDe == ancRes) {
		    njoueurs.add(jCourant);
		} else {
		    njoueurs.clear();
		    njoueurs.add(jCourant);
		}
		ancRes = resDe;
	    }
	}
	while (njoueurs.size() > 1) {
	    ancRes = 0;
	    njoueursMemeScore = (ArrayList<Joueur>) njoueurs.clone();
	    njoueurs.clear();
	    for (Joueur jCourant : njoueursMemeScore) {
		resDe = LancerDeN(6);
		resDe += LancerDeN(6);
		if (resDe >= ancRes) {
		    if (resDe == ancRes) {
			njoueurs.add(jCourant);
		    } else {
			njoueurs.clear();
			njoueurs.add(jCourant);
		    }
		    ancRes = resDe;
		}
	    }
	}
	j = njoueurs.get(0);
	njoueurs.clear();
	int i = 0;
	for (Joueur jCourant : monopoly.getJoueurs()) {
	    njoueurs.add(monopoly.getJoueurs().get((monopoly.getJoueurs().indexOf(j) + i) % monopoly.getJoueurs().size()));
	    i++;
	}
	monopoly.setJoueurs(njoueurs);
    }

    //v--getters setters--v
    public Monopoly getMonopoly() {
	return monopoly;
    }

    public void setMonopoly(Monopoly monopoly) {
	this.monopoly = monopoly;
    }
}
