package Data;

import Ihm.*;
import java.util.ArrayList;

public class Carte {

    private String description;
    private Enumeration.ActionChCo typeAction;
    private int infoAction;

    public Carte(String description, Enumeration.ActionChCo typeAct, int infoAction) {
	setDescription(description);
	setTypeAction(typeAct);
	setInfoAction(infoAction);
    }

    public void Action(Joueur j, ArrayList<Joueur> joueurs, ArrayList<Carreau> carreaux) {
	Ihm.Afficher("Carte pioché: " + getDescription());
	switch (getTypeAction().name()) {
	    case "libPrison":
		libPrison(j);
		break;
	    case "reculerDeN":
		reculerDeN(j, getInfoAction(), carreaux);
		break;
	    case "avancerJusqua":
		avancerJusqua(j, getInfoAction(), carreaux);
		break;
	    case "avancerJusquaScaseDep":
		avancerJusquaScaseDep(j, getInfoAction(), carreaux);
		break;
	    case "modifyCash":
		modifyCash(j, getInfoAction());
		break;
	    case "payerCashHotels":
		payerCashHotels(j, getInfoAction());
		break;
	    case "allerPrison":
		allerPrison(j, carreaux);
		break;
	    case "anniversaire":
		anniversaire(j, getInfoAction(), joueurs);
		break;
	    default:
		break;
	}
    }

    public void libPrison(Joueur j) {
	j.setCarteLibPrison(j.getCarteLibPrison() + 1);
    }

    public void reculerDeN(Joueur j, int n, ArrayList<Carreau> carreaux) {
	j.setPositionCourante(carreaux.get(((j.getPositionCourante().getNumero() - n) - 1) % 40));
    }

    public void avancerJusqua(Joueur j, int numCase, ArrayList<Carreau> carreaux) { //Faire l'action de la carte ?
	int ancPos = j.getPositionCourante().getNumero();
	j.setPositionCourante(carreaux.get(numCase));

	if (j.getPositionCourante().getNumero() < ancPos) { //si ça nouvelle position est inférieur à la nouvelle
	    Ihm.Afficher(j.getNomJoueur() + " reçois son Salaire (case départ)");
	    j.recevoirArgent(200); // on ajoute 200 de cash, car il est donc passé par le départ
	}

    }

    public void avancerJusquaScaseDep(Joueur j, int numCase, ArrayList<Carreau> carreaux) {
	j.setPositionCourante(carreaux.get(numCase));
    }

    public void modifyCash(Joueur j, int cash) {
	j.recevoirArgent(cash);
    }

    public void payerCashHotels(Joueur j, int cash) {
	//prix par maison = cash
	//prix par hotel = cash - 75
	//modifyCash(j, (cash*j.getMaisons.size + ((cash-75)*j.getHotels.size)) );
    }

    public void allerPrison(Joueur j, ArrayList<Carreau> carreaux) {
	j.setEnPrison(3);
	Ihm.Afficher(j.getNomJoueur() + " est en prison. Il lui reste " + j.getPrison() + " tour(s) en prison.");
	avancerJusquaScaseDep(j, 10, carreaux);
    }

    public void anniversaire(Joueur j, int cash, ArrayList<Joueur> joueurs) {
	for (Joueur jcourant : joueurs) {
	    if (jcourant != j) {
		jcourant.payerArgent(cash);
		j.recevoirArgent(cash);
	    }
	}
	//cash * nb joueurs et retirer l'argent des joueurs
    }

    //v--getters setters--v
    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Enumeration.ActionChCo getTypeAction() {
	return typeAction;
    }

    public void setTypeAction(Enumeration.ActionChCo typeAction) {
	this.typeAction = typeAction;
    }

    /**
     * @return the infoAction
     */
    public int getInfoAction() {
	return infoAction;
    }

    /**
     * @param infoAction the infoAction to set
     */
    public void setInfoAction(int infoAction) {
	this.infoAction = infoAction;
    }

}
