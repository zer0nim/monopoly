package Jeu;
import Ihm.*;
import Data.*;
import Utils.*;

public class Controleur {
    public Ihm ihm;
    public Monopoly monopoly;

    public void jouerUnCoup(Joueur j) {
	lancerDésAvancer(j);
	if(j.getPositionCourante().getClass().getSimpleName().equals("Biens_achetables")){ //le Joueur est sur un Biens_achetables
	    //Vérification bien déja possédé
		//j est le propriétaire
		//j n'est pas le propriétaire
	    //Proposition d'achat si assez d'argent
	}
	else{ //le Joueur est sur un AutreCarreau
	    
	    
	}
    }

    private Carreau lancerDésAvancer(Joueur j){
        j.setPositionCourante(getMonopoly().getCarreaux().get(PlateauUtilitaire.LancerDe()));
	return(j.getPositionCourante());
    }
    
    public Ihm getIhm() {
        return ihm;
    }

    public void setIhm(Ihm ihm) {
        this.ihm = ihm;
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public void setMonopoly(Monopoly monopoly) {
        this.monopoly = monopoly;
    }
}
