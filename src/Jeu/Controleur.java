package Jeu;
import Ihm.*;
import DataText.*;
import static Ihm.IhmText.affJoueur;
import java.util.ArrayList;
import java.util.Random;

public class Controleur {
    private MonopolyText monopoly;
    
    public Controleur(){
	this.monopoly = new MonopolyText();
    }

    public void jouerUnCoup(Joueur j) {
        IhmText.Afficher("");
	int resultD = lancerDésAvancer(j);
	int resultD2 = lancerDésAvancer(j);
        //int resultD = 2;
        //int resultD2 = 2;
	if (!j.estMort()){
	    resultD += resultD2;
            if(j.getPrison() != 0 && resultD == 2 * resultD2){
                j.setEnPrison(0);
                IhmText.Afficher("Vous venez de sortir de prison avec un double dé !");
                j.setPositionCourante(monopoly.getCarreaux().get(((j.getPositionCourante().getNumero() + resultD)-1)%40));
            }
	    affJoueur(j);
	    actionCarreau(j, resultD);
            construire(j);
            
            if(j.getPrison() == -1){
                j.setEnPrison(0);
                IhmText.Afficher("résultat lancé du dé: " + (resultD - resultD2));
                IhmText.Afficher("résultat lancé du dé: " + resultD2);
                j.setPositionCourante(monopoly.getCarreaux().get(((j.getPositionCourante().getNumero() + resultD)-1)%40));
                affJoueur(j);
                actionCarreau(j, resultD);
                construire(j);
            }
	}
	if (j.estMort()){ //pas de else il est peut etre mort en jouant
		j.vendrePropriétés();
	}
	if (resultD == 2 * resultD2){ //si double
	    IhmText.Afficher("Double au Dé !");
            j.incrementCompteDoubleDes();
            if (j.getCompteDoubleDes() == 3) {
                j.setPositionCourante(monopoly.getCarreaux().get(10));
                j.setEnPrison(3);
                IhmText.Afficher(j.getNomJoueur() + " est en prison. Il lui reste " + j.getPrison() + " tour(s) en prison.");
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

    public void actionCarreau(Joueur j, int resultD){
        if (j.getPositionCourante().getClass().getSimpleName().equals("Gare"))
		((DataText.Gare)j.getPositionCourante()).action(j, resultD);
	    else if (j.getPositionCourante().getClass().getSimpleName().equals("Compagnie"))
		((DataText.Compagnie)j.getPositionCourante()).action(j, resultD);
	    else if (j.getPositionCourante().getClass().getSimpleName().equals("ProprieteAConstruire"))
		((DataText.ProprieteAConstruire)j.getPositionCourante()).action(j, resultD);
	    else
		((DataText.AutreCarreau)j.getPositionCourante()).action(j, monopoly.getJoueurs(), monopoly.getCarreaux(), monopoly.getCartesChCo());
    }
    
    private int lancerDésAvancer(Joueur j){
	int ancPos = j.getPositionCourante().getNumero();
        int resultD = LancerDeN(6);
        IhmText.Afficher("résultat lancé du dé: " + resultD);
        if(j.getPrison() >= 0){
            j.setPositionCourante(monopoly.getCarreaux().get(((j.getPositionCourante().getNumero() + resultD)-1)%40));
        }
        if (j.getPositionCourante().getNumero() < ancPos) { //si ça nouvelle position est inférieur à la nouvelle
	    IhmText.Afficher(j.getNomJoueur() + " reçois son Salaire (case départ) sa position etait: " + j.getPositionCourante().getNumero());
            j.recevoirArgent(200); // on ajoute 200 de cash, car il est donc passé par le départ
        }
	return(resultD);
    }
    
    public int LancerDeN(int n){
	Random rand = new Random();
        return rand.nextInt(n)+1; //retourne un entier correspondant à un lancé de dé
    } 
    
    public void creerJoueurs(){
	int nbJoueur = IhmText.nbJoueur();
	for (int j = 0; j < nbJoueur ; j++) {
		monopoly.setJoueur(new Joueur(IhmText.nomJoueur(j+1), monopoly.getCarreaux().get(0)));
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

    public MonopolyText getMonopoly() {
        return monopoly;
    }

    public void setMonopoly(MonopolyText monopoly) {
        this.monopoly = monopoly;
    }
    
    public ArrayList<Integer> setCompteConstructions(){
        int compteMaison = 0;
        int compteHotel = 0;
        for(Joueur j : monopoly.getJoueurs()){
            for(Biens_achetables prop : j.getPropriétés()){
                if(prop.getClass().getSimpleName().contains("ProprieteAConstruire")){
                    for(Construction cons : ((ProprieteAConstruire)prop).getConstructions()){
                        if(cons.getType() == "maison"){
                            compteMaison+= 1;
                        }else{
                            compteHotel+= 1;
                        }
                    }
                }
            }
        }
        ArrayList<Integer> comptes = new ArrayList<>();
        comptes.add(compteMaison);
        comptes.add(compteHotel);
        return comptes;
    }
    
    public void construire(Joueur j){
        ArrayList<Integer> comptes = setCompteConstructions();
        boolean maisons = false;
        boolean hotels = false;
        if(comptes.get(0) < 32){
            maisons = true;
        }
        if(comptes.get(1) < 12){
            hotels = true;
        }
        j.achetterConstruction(maisons, hotels, true);
    }
}