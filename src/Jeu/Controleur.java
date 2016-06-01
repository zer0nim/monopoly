package Jeu;
import Ihm.*;
import Data.*;
import static Ihm.Ihm.affBiens;
import static Ihm.Ihm.affJoueur;
import java.util.ArrayList;
import java.util.Random;

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
	    if (j.getPositionCourante().getClass().getSimpleName().equals("Gare"))
		((Gare)j.getPositionCourante()).action(j, resultD);
	    else if (j.getPositionCourante().getClass().getSimpleName().equals("Compagnie"))
		((Compagnie)j.getPositionCourante()).action(j, resultD);
	    else if (j.getPositionCourante().getClass().getSimpleName().equals("ProprieteAConstruire"))
		((ProprieteAConstruire)j.getPositionCourante()).action(j, resultD);
	    else
		((AutreCarreau)j.getPositionCourante()).action(j, monopoly.getJoueurs(), monopoly.getCarreaux(), monopoly.getCartesChCo());
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
	int ancPos = j.getPositionCourante().getNumero();
        int resultD = LancerDeN(6);
	Ihm.Afficher("résultat lancé du dé: " + resultD);
        j.setPositionCourante(monopoly.getCarreaux().get((j.getPositionCourante().getNumero() + resultD)%40));
        if (j.getPositionCourante().getNumero() < ancPos) { //si ça nouvelle position est inférieur à la nouvelle
	    Ihm.Afficher(j.getNomJoueur() + " reçois son Salaire (case départ) sa position etait: " + j.getPositionCourante().getNumero());
            j.recevoirArgent(200); // on ajoute 200 de cash, car il est donc passé par le départ
        }
	return(resultD);
    }
    
    public int LancerDeN(int n){
	Random rand = new Random();
        return rand.nextInt(n)+1; //retourne un entier correspondant à un lancé de dé
    } 
    
    public void creerJoueurs(){
	int nbJoueur = Ihm.nbJoueur();
	for (int j = 0; j < nbJoueur ; j++) {
		monopoly.setJoueur(new Joueur(Ihm.nomJoueur(j+1), monopoly.getCarreaux().get(0)));
	}
	quiCommence();
    }
    
    public void quiCommence(){
	Joueur j = null;
	int resDe, ancRes = 0;
	ArrayList<Joueur> njoueurs = new ArrayList<>();
	ArrayList<Joueur> njoueursMemeScore = new ArrayList<>();

	for (Joueur jCourant : monopoly.getJoueurs()){
	    resDe = LancerDeN(6);
	    resDe += LancerDeN(6);
	    if (resDe >= ancRes){
		if (resDe == ancRes){
		    njoueurs.add(jCourant);
		}
		else{
		    njoueurs.clear();
		    njoueurs.add(jCourant);
		}
		ancRes = resDe;
	    }
	}
	while (njoueurs.size() > 1){
	    ancRes = 0;
	    njoueursMemeScore = (ArrayList<Joueur>)njoueurs.clone();
	    njoueurs.clear();
	    for (Joueur jCourant : njoueursMemeScore){
		resDe = LancerDeN(6);
		resDe += LancerDeN(6);
		if (resDe >= ancRes){
		    if (resDe == ancRes){
			njoueurs.add(jCourant);
		    }
		    else{
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
	for (Joueur jCourant : monopoly.getJoueurs()){
		njoueurs.add(monopoly.getJoueurs().get(     (monopoly.getJoueurs().indexOf(j) + i) % monopoly.getJoueurs().size()       ));
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
