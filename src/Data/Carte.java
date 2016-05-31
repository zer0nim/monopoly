package Data;
import Data.Enumeration;
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
    
    public void Action(Joueur j, ArrayList<Joueur> joueurs, ArrayList<Carreau> carreaux){
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
    
    public void libPrison(Joueur j){
	j.setCarteLibPrison(j.getCarteLibPrison() + 1);
    }
    public void reculerDeN(Joueur j, int n, ArrayList<Carreau> carreaux){
	j.setPositionCourante(carreaux.get((j.getPositionCourante().getNumero() - n)%40));
    }
    public void avancerJusqua(Joueur j, int numCase, ArrayList<Carreau> carreaux){
	j.setPositionCourante(carreaux.get(numCase));
    }
    public void avancerJusquaScaseDep(Joueur j, int numCase, ArrayList<Carreau> carreaux){
	//trouver une façon pour qu'il n'ai pas les 200€ de la case départ
	avancerJusqua(j, numCase, carreaux);
    }
    public void modifyCash(Joueur j, int cash){
	j.payerArgent(cash);
    }
    public void payerCashHotels(Joueur j, int cash){
	//prix par maison = cash
	//prix par hotel = cash - 75
	//modifyCash(j, (cash*j.getMaisons.size + ((cash-75)*j.getHotels.size)) );
    }
    public void allerPrison(Joueur j, ArrayList<Carreau> carreaux){
	j.setEnPrison(true);
	avancerJusquaScaseDep(j, 10, carreaux);
    }
    public void anniversaire(Joueur j, int cash, ArrayList<Joueur> joueurs){
	for (Joueur jcourant : joueurs){
	    if (jcourant != j){
		j.payerArgent(cash);
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